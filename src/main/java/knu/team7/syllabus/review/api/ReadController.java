package knu.team7.syllabus.review.api;

import knu.team7.syllabus.review.api.dto.response.ApiResponse;
import knu.team7.syllabus.review.api.dto.response.ReadResponse;
import knu.team7.syllabus.review.api.dto.response.ReviewResponse;
import knu.team7.syllabus.review.application.usecase.ReadUseCase;
import knu.team7.syllabus.review.domain.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.OptionalDouble;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReadController {
    private final ReadUseCase readUseCase;

    @GetMapping("/review")
    public ResponseEntity<ApiResponse<?>> getReview(@RequestParam("crseNo")String crseNo,
                                                    @RequestParam("profNm")String profNm) {
        try {
            List<Review> reviewList = readUseCase.readReviews(crseNo, profNm);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(ReadResponse.builder()
                            .crseNo(crseNo)
                            .profNm(profNm)
                            .avgRating(averageRating(reviewList))
                            .reviews(reviewResponseMapper(reviewList))
                            .build())
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }

    }

    @GetMapping("/review/user")
    public ResponseEntity<ApiResponse<?>> getReview(@AuthenticationPrincipal Jwt jwt) {
        try {
            // jwt에서 userId 추출
            String userId = jwt.getSubject();
            List<Review> reviewList = readUseCase.readReviewByUserId(userId);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(reviewResponseMapper(reviewList))
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }
    }

    private float averageRating(List<Review> reviewList) {
        OptionalDouble avgOpRating = reviewList.stream().mapToDouble(Review::getRating).average();
        if (avgOpRating.isPresent()) {
            return (float) avgOpRating.getAsDouble();
        }
        return 0;
    }

    private List<ReviewResponse> reviewResponseMapper(List<Review> reviewList) {
        return reviewList.stream().map(
                review -> ReviewResponse.builder()
                        .crseNo(review.getCrseNo())
                        .profNm(review.getProfNm())
                        .year(review.getYear())
                        .season(review.getSeason())
                        .rating(review.getRating())
                        .contents(review.getContents())
                        .createdAt(review.getCreatedAt())
                        .build()
        ).toList();
    }

}

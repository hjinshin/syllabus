package knu.team7.syllabus.review.application.usecase;

import knu.team7.syllabus.review.application.port.in.WriteCommand;

public interface WriteUseCase {
    void writeReview(WriteCommand command);
}

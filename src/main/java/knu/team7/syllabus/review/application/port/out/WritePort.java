package knu.team7.syllabus.review.application.port.out;

import knu.team7.syllabus.review.application.port.in.WriteCommand;

public interface WritePort {
    void writeReview(WriteCommand command);
}

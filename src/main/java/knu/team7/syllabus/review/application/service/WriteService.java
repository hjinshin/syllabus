package knu.team7.syllabus.review.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.review.application.port.in.WriteCommand;
import knu.team7.syllabus.review.application.port.out.WritePort;
import knu.team7.syllabus.review.application.usecase.WriteUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class WriteService implements WriteUseCase {
    private final WritePort writePort;
    @Override
    public void writeReview(WriteCommand command) {
        writePort.writeReview(command);
    }
}

package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.usecase.*;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class AsyncService implements AsyncUseCase {
    private final FetchScheduleUseCase fetchScheduleUseCase;
    private final CreateScheduleUseCase createScheduleUseCase;
    private final CreateEvaluationUseCase createEvaluationUseCase;
    private final CreateSyllabusUseCase createSyllabusUseCase;

    @Async
    public void fetchAndCreateSchedule(List<Course> courseList, String option) throws Exception {
        List<ScheduleCommand> commandList = fetchScheduleUseCase.fetchSchedule(courseList);
        System.out.println(option + " scheduleList: " + commandList.size());
        createScheduleUseCase.createSchedule(commandList);
        System.out.println(option + " schedule");
    }

    @Async
    public void createEvaluation(List<List<SyllabusCommand>> list, String option) {
        List<EvaluationCommand> evaluationCommandList = new ArrayList<>();
        for (List<SyllabusCommand> commandList : list) {
            if (commandList == null || commandList.size() == 0) {
                continue;
            }
            SyllabusCommand command = commandList.get(0);
            evaluationCommandList.add(EvaluationCommand.builder()
                    .crseNo(command.crseNo())
                    .year(Integer.parseInt(command.year()))
                    .season(command.season())
                    .attendance(Float.parseFloat(command.attendance()))
                    .midExam(Float.parseFloat(command.midExam()))
                    .finalExam(Float.parseFloat(command.finalExam()))
                    .assignment(Float.parseFloat(command.assignment()))
                    .presentation(Float.parseFloat(command.presentation()))
                    .debate(Float.parseFloat(command.debate()))
                    .safetyEdu(Float.parseFloat(command.safetyEdu()))
                    .etc(Float.parseFloat(command.etc()))
                    .total(Float.parseFloat(command.total()))
                    .build()
            );
        }
        createEvaluationUseCase.createEvaluation(evaluationCommandList);
        System.out.println(option + " evaluation");
    }

    @Async
    public void createSyllabus(List<List<SyllabusCommand>> list, String option) {
        createSyllabusUseCase.createSyllabus(list);
        System.out.println(option + " syllabus");

    }
}

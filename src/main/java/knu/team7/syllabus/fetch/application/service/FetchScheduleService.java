package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchSchedulePort;
import knu.team7.syllabus.fetch.application.usecase.FetchScheduleUseCase;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FetchScheduleService implements FetchScheduleUseCase {
    private final FetchSchedulePort fetchSchedulePort;
    @Override
    public List<ScheduleCommand> fetchSchedule(List<Course> courseList) throws Exception {

        List<ScheduleCommand> list = new ArrayList<>();
        for (Course course : courseList) {
            list.addAll(fetchScheduleByDoPlan(course, "Kor"));
            list.addAll(fetchScheduleByDoPlan(course, "Eng"));
        }
        return list;
    }

    @Override
    public List<ScheduleCommand> fetchScheduleByDoPlan(Course course, String doPlan) throws Exception {
        return fetchSchedulePort.fetchSchedule(course, doPlan);
    }
}

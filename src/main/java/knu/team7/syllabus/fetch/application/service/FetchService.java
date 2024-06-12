package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.usecase.*;
import knu.team7.syllabus.fetch.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FetchService implements FetchUseCase {

    private final FetchCategoryUseCase fetchCategoryUseCase;
    private final FetchLectureUseCase fetchLectureUseCase;
    private final FetchSyllabusUseCase fetchSyllabusUseCase;
    private final CreateSubjectUseCase createSubjectUseCase;
    private final CreateSectionUseCase createSectionUseCase;
    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final CreateLectureUseCase createLectureUseCase;
    private final AsyncUseCase asyncUseCase;
    private final LoadCourseUseCase loadCourseUseCase;

    @Async
    @Override
    public void fetchGE(int year, String season) throws Exception {
        String option = "[GE]";
        List<Category> list = fetchCategoryUseCase.fetchGECategory();
        System.out.println(option + " GEList: " + list.size());

        List<LectureCommand> commandList = fetchLectureUseCase.fetchGELecture(list, year, season);
        System.out.println(option + " lectureCommandList: " + commandList.size());

        fetchData(list, commandList, option, year, season);
    }

    @Async
    @Override
    public void fetchOther(int year, String season) throws Exception {
        String option = "[Other]";
        List<Category> list = fetchCategoryUseCase.fetchOtherCategory();
        System.out.println(option + " OtherList: " + list.size());
        List<LectureCommand> commandList = fetchLectureUseCase.fetchOtherLecture(list, year, season);
        System.out.println(option + " lectureCommandList: " + commandList.size());

        fetchData(list, commandList, option, year, season);
    }

    private void fetchData(List<Category> list, List<LectureCommand> lectureCommandList, String option, int year, String season) throws Exception {

        List<Section> savedSections = createSectionUseCase.createSection(list);
        System.out.println(option + " saved Sections: " + savedSections.size());

        List<Subject> savedSubjects = createSubjectUseCase.createSubject(lectureCommandList);
        System.out.println(option + " saved Subjects: " + savedSubjects.size());

        List<Course> savedCourses = createCourseUseCase.createCourse(lectureCommandList);
        System.out.println(option + " saved Courses: " + savedCourses.size());

        List<Course> courseList = loadCourseUseCase.loadCourseByYearAndSeason(year, season);
        asyncUseCase.fetchAndCreateSchedule(courseList, option);

        List<List<SyllabusCommand>> syllabusList = fetchSyllabusUseCase.fetchSyllabus(lectureCommandList);
        System.out.println(option + " fetch syllabusList: " + syllabusList.size());

        asyncUseCase.createEvaluation(syllabusList, option);

        List<Department> savedDeparts = createDepartmentUseCase.createDepartment(lectureCommandList);
        System.out.println(option + " saved Departs: " + savedDeparts.size());

        asyncUseCase.createSyllabus(syllabusList, option);


        createLectureUseCase.createLecture(lectureCommandList);
        System.out.println(option + " lecture");

        System.out.println(option + "Fetch Complete");
    }

}

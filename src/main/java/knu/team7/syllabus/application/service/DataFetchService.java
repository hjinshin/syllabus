package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.*;
import knu.team7.syllabus.application.port.out.command.OutLectureCommand;
import knu.team7.syllabus.application.port.out.command.OutScheduleCommand;
import knu.team7.syllabus.application.usecase.*;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.domain.model.*;
import knu.team7.syllabus.infrastructure.adapter.dto.in.SyllabusParamsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.*;

@UseCase
@RequiredArgsConstructor
public class DataFetchService implements DataFetchUseCase {

    private final ListUseCase listUseCase;
    private final LectureUseCase lectureUseCase;
    private final SyllabusUseCase syllabusUseCase;
    private final ScheduleUseCase scheduleUseCase;
    private final CreateSubjectSectionUseCase createListUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final CreateSubjectCodeUseCase createSubjectCodeUseCase;
    private final CreateSyllabusUseCase createSyllabusUseCase;
    private final CreateProfessorUseCase createProfessorUseCase;
    private final CreateEvaluationUseCase createEvaluationUseCase;
    private final CreateLectureUseCase createLectureUseCase;
    private final CreateScheduleUseCase createScheduleUseCase;

    @Override
    @Async
    public void fetchGEData(int year, String season) throws Exception {
        String option = "[GE]";

        // ge 목록 불러오기
        List<ListCommand> list = listUseCase.getGEList();
        System.out.println(option + " GEList");

        // ge 목록 저장
        List<SubjectSection> subjectSectionList = createListUseCase.createSubjectSection(list);
        System.out.println(option + " subjectSectionList");

        // ge lecture 불러오기
        List<OutLectureCommand> outLectureCommandList = lectureUseCase.getGELectureList(list, String.valueOf(year), season);
        System.out.println(option + " outLectureCommandList");

        // GE 저장
        create(list, outLectureCommandList, option);

    }

    @Override
    @Async
    public void fetchOtherData(int year, String season) throws Exception {
        String option = "[Other]";

        // Other 목록 불러오기
        List<ListCommand> list = listUseCase.getOtherList();
        System.out.println(option + " otherList");

        // 목록 저장
        List<SubjectSection> subjectSectionList = createListUseCase.createSubjectSection(list);
        System.out.println(option + " subjectSectionList");

        // Other lecture 불러오기
        List<OutLectureCommand> outLectureCommandList = lectureUseCase.getOtherLectureList(list, String.valueOf(year), season);
        System.out.println(option + " outLectureCommandList");

        // Other 저장
        create(list, outLectureCommandList, option);
    }

    private void create(List<ListCommand> list, List<OutLectureCommand> outLectureCommandList, String option) throws Exception {

        // course 저장 및 가져오기
        List<Course> courseList = createAndGetCourse(outLectureCommandList);
        System.out.println(option + " courseList");

        // 목록 저장
        List<SubjectSection> subjectSectionList = createListUseCase.createSubjectSection(list);
        System.out.println(option + " subjectSectionList");


        // department 저장 및 가져오기
        List<Department> departmentList = createAndGetDepartment(outLectureCommandList);
        System.out.println(option + " departmentList");


        // subjectcode 저장 및 가져오기
        List<SubjectCode> sbjctCodeList = createAndGetSubjectCode(outLectureCommandList);
        System.out.println(option + " sbjctCodeList");

        // syllabus 저장 및 가져오기
        List<Syllabus> syllabusList = createAndGetSyllabus(outLectureCommandList, courseList);
        System.out.println(option + " syllabusList");

        // professor 저장 및 가져오기
        List<Professor> professorList = createAndGetProfessor(syllabusList);
        System.out.println(option + " professorList");

        // evaluation 저장 및 가져오기
        List<Evaluation> evaluationList = createAndGetEvaluation(syllabusList, courseList);
        System.out.println(option + " evaluationList");

        // lecture 저장 및 가져오기
        List<Lecture> lectureList = createAndGetLecture(outLectureCommandList, syllabusList, courseList, professorList, departmentList, sbjctCodeList, subjectSectionList, evaluationList);
        System.out.println(option + " lectureList");


        // schedule command
        createSchedule(courseList);
        System.out.println(option + " Schedule");



        // lectureTime command
        // lectureTime 저장

    }

    private List<Department> createAndGetDepartment(List<OutLectureCommand> outLectureCommandList) {
        // department command
        List<DepartmentCommand> departmentCommandList = createDepartmentCommandList(outLectureCommandList);
        // department 저장


        return createDepartmentUseCase.createDepartment(departmentCommandList);
    }

    private List<SubjectCode> createAndGetSubjectCode(List<OutLectureCommand> outLectureCommandList) {
        // subjectcode command
        List<SubjectCodeCommand> subjectCodeCommandList = createSubjectCodeCommandList(outLectureCommandList);
        // subjectCode 저장

        return createSubjectCodeUseCase.createSubjectCode(subjectCodeCommandList);
    }

    private List<Course> createAndGetCourse(List<OutLectureCommand> outLectureCommandList) {
        // course command
        List<CourseCommand> courseCommandList = createCourseCommandList(outLectureCommandList);
        // course 목록 저장
        return createCourseUseCase.createCourse(courseCommandList);
    }

    private List<Syllabus> createAndGetSyllabus(List<OutLectureCommand> outLectureCommandList, List<Course> courseList) throws Exception {
        List<SyllabusParamsCommand> syllabusParamsCommandList = createSyllabusParamsCommandList(outLectureCommandList);
        // syllabus 불러오기
        List<Syllabus> syllabusList = syllabusUseCase.getSyllabusList(syllabusParamsCommandList);
        // syllabus command
        List<SyllabusCommand> syllabusCommandList = createSyllabusCommandList(syllabusList, courseList);
        // syllabus 저장
        createSyllabusUseCase.createSyllabus(syllabusCommandList);
        return syllabusList;
    }

    private List<Professor> createAndGetProfessor(List<Syllabus> syllabusList) {
        List<ProfessorCommand> professorCommandList = createProfessorCommandList(syllabusList);
        // professor 목록 저장
        return createProfessorUseCase.createProfessor(professorCommandList);
    }

    private List<Evaluation> createAndGetEvaluation(List<Syllabus> syllabusList, List<Course> courseList) {
        // evaluation command
        List<EvaluationCommand> evaluationCommandList = createEvaluationCommandList(syllabusList, courseList);
        // evaluation 저장
        return createEvaluationUseCase.createEvaluation(evaluationCommandList);
    }

    private List<Lecture> createAndGetLecture(List<OutLectureCommand> outLectureCommandList, List<Syllabus> syllabusList,
                                              List<Course> courseList, List<Professor> professorList,
                                              List<Department> departmentList, List<SubjectCode> subjectCodeList,
                                              List<SubjectSection> subjectSectionList, List<Evaluation> evaluationList) {
        List<LectureCommand> lectureCommandList = createLectureCommandList(outLectureCommandList, syllabusList, courseList, professorList, departmentList, subjectCodeList, subjectSectionList, evaluationList);
        // lecture 목록 저장
        return createLectureUseCase.createLecture(lectureCommandList);
    }
    private void createSchedule(List<Course> courseList) throws Exception {
        // schedule command
        List<ScheduleCommand> scheduleCommandList = createScheduleCommandList(courseList);
        // schedule 저장
        createScheduleUseCase.createSchedule(scheduleCommandList);
    }
    private List<SubjectCodeCommand> createSubjectCodeCommandList(List<OutLectureCommand> outLectureCommandList) {
        return outLectureCommandList.stream()
                .map(outLectureCommand -> SubjectCodeCommand.builder()
                        .sbjetCd(outLectureCommand.sbjetCd())
                        .sbjetNm(outLectureCommand.sbjetNm())
                        .build()
                ).toList();
    }
    private List<CourseCommand> createCourseCommandList(List<OutLectureCommand> outLectureCommandList) {
        return outLectureCommandList.stream()
                .map(outLectureCommand -> CourseCommand.builder()
                        .crseNo(outLectureCommand.crseNo())
                        .year(outLectureCommand.estblYear())
                        .season(outLectureCommand.estblSmstrSctnm())
                        .build()
                ).toList();
    }
    private List<DepartmentCommand> createDepartmentCommandList(List<OutLectureCommand> outLectureCommandList) {
        return outLectureCommandList.stream()
                .map(outLectureCommand -> DepartmentCommand.builder()
                        .college(outLectureCommand.estblUnivNm())
                        .depart(outLectureCommand.estblDprtnNm())
                        .build()
                ).toList();
    }
    private List<SyllabusParamsCommand> createSyllabusParamsCommandList(List<OutLectureCommand> outLectureCommandList) {
        return outLectureCommandList.stream()
                .map(outLectureCommand -> SyllabusParamsCommand.builder()
                        .crseNo(outLectureCommand.crseNo())
                        .year(outLectureCommand.estblYear())
                        .season(outLectureCommand.estblSmstrSctcd())
                        .build()
                ).toList();
    }

    private List<SyllabusCommand> createSyllabusCommandList(List<Syllabus> syllabusList, List<Course> courseList) {
        List<SyllabusCommand> list = new ArrayList<>();
        for (int i = 0; i < syllabusList.size(); i++) {
            Course course = courseList.get(i);
            Syllabus syllabus = syllabusList.get(i);
            list.add(SyllabusCommand.builder()
                    .crseGoal(syllabus.getCrseGoal())
                    .eduGoal(syllabus.getEduGoal())
                    .summary(syllabus.getSummary())
                    .textbook(syllabus.getTextbook())
                    .evalMethd(syllabus.getEvalMethd())
                    .intviTimeLoc(syllabus.getIntviTimeLoc())
                    .refer(syllabus.getRefer())
                    .course(course)
                    .build()
            );
        }
        return list;
    }

    private List<ProfessorCommand> createProfessorCommandList(List<Syllabus> syllabusList) {
        return syllabusList.stream().map(
                syllabus -> ProfessorCommand.builder()
                        .profNm(syllabus.getProfNm())
                        .profTel(syllabus.getProfTel())
                        .profEmail(syllabus.getProfEmail())
                        .build()
                ).toList();
    }
    private List<EvaluationCommand> createEvaluationCommandList(List<Syllabus> syllabusList, List<Course> courseList) {
        List<EvaluationCommand> list = new ArrayList<>();
        for (int i = 0; i < syllabusList.size(); i++) {
            Course course = courseList.get(i);
            Syllabus syllabus = syllabusList.get(i);
            list.add(EvaluationCommand.builder()
                    .attendance(syllabus.getAttendance())
                    .midExam(syllabus.getMidExam())
                    .finalExam(syllabus.getFinalExam())
                    .assignment(syllabus.getAssignment())
                    .presentation(syllabus.getPresentation())
                    .debate(syllabus.getDebate())
                    .safetyEdu(syllabus.getSafetyEdu())
                    .etc(syllabus.getEtc())
                    .total(syllabus.getTotal())
                    .course(course)
                    .build()
            );
        }
        return list;
    }
    private List<LectureCommand> createLectureCommandList(List<OutLectureCommand> outLectureCommandList, List<Syllabus> syllabusList,
                                                          List<Course> courseList, List<Professor> professorList,
                                                          List<Department> departmentList, List<SubjectCode> subjectCodeList,
                                                          List<SubjectSection> subjectSectionList, List<Evaluation> evaluationList) {
        List<LectureCommand> list = new ArrayList<>();
        for (int i = 0; i < outLectureCommandList.size(); i++) {
            OutLectureCommand outLectureCommand = outLectureCommandList.get(i);
            Syllabus syllabus = syllabusList.get(i);
            Course course = courseList.get(i);
            Professor professor = professorList.get(i);
            Department department = departmentList.get(i);
            SubjectCode subjectCode = subjectCodeList.get(i);
            SubjectSection subjectSection = subjectSectionList.stream()
                    .filter(item -> Objects.equals(item.getCodeId(), outLectureCommand.codeId()))
                    .findFirst()
                    .orElse(null);
            Evaluation evaluation = evaluationList.get(i);

            list.add(LectureCommand.builder()
                    .credit(Integer.parseInt(outLectureCommand.crdit()))
                    .lecCr(Integer.parseInt(outLectureCommand.thryTime()))
                    .pracCr(Integer.parseInt(outLectureCommand.thryTime()))
                    .grade(outLectureCommand.estblGrade())
                    .building(outLectureCommand.lctrmInfo())
                    .room(outLectureCommand.rmnmCd())
                    .capacity(Integer.parseInt(outLectureCommand.attlcPrscpCnt()))
                    .lang(outLectureCommand.doPlan())
                    .isRemote(Objects.equals(outLectureCommand.expniSllbsYn(), "Y"))
                    .note(outLectureCommand.rmrk())
                    .preSbjet(syllabus.getPreSbjet())
                    .postSbjet(syllabus.getPostSbjet())
                    .realLecTime(outLectureCommand.lssnsRealTimeInfo())
                    .course(course)
                    .professor(professor)
                    .department(department)
                    .subjectCode(subjectCode)
                    .subjectSection(subjectSection)
                    .evaluation(evaluation)
                    .build());
        }
        return list;
    }

    private List<ScheduleCommand> createScheduleCommandList(List<Course> courseList) throws Exception {
        List<ScheduleCommand> list = new ArrayList<>();
        for (Course course : courseList) {

            List<OutScheduleCommand> outList = scheduleUseCase.getSchedule(course.getYear(), course.getSeason(), course.getCrseNo());
            for (OutScheduleCommand outScheduleCommand : outList) {
                list.add(ScheduleCommand.builder()
                        .lssnsGoalCntns(outScheduleCommand.lssnsGoalCntns())
                        .lssnsMethd(outScheduleCommand.lssnsMethd())
                        .rsrchCntns(outScheduleCommand.rsrchCntns())
                        .weekSn(outScheduleCommand.weekSn())
                        .weekNote(outScheduleCommand.weekNote())
                        .course(course)
                        .build());
            }
        }
        return list;
    }

}

package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.out.command.OutLectureCommand;
import knu.team7.syllabus.fetch.application.port.out.command.OutScheduleCommand;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.*;
import knu.team7.syllabus.fetch.application.usecase.*;
import knu.team7.syllabus.fetch.domain.model.*;
import knu.team7.syllabus.fetch.application.service.dto.in.SyllabusParamsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.Async;

import java.util.*;

@EnableRetry
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
        System.out.println(option + " GEList: " + list.size());

        // ge lecture 불러오기
        List<OutLectureCommand> outLectureCommandList = lectureUseCase.getGELectureList(list, String.valueOf(year), season);
        System.out.println(option + " outLectureCommandList: " + outLectureCommandList.size());

        // GE 저장
        create(list, outLectureCommandList, option);

    }

    @Override
    @Async
    public void fetchOtherData(int year, String season) throws Exception {
        String option = "[Other]";

        // Other 목록 불러오기
        List<ListCommand> list = listUseCase.getOtherList();
        System.out.println(option + " otherList: " + list.size());


        // Other lecture 불러오기
        List<OutLectureCommand> outLectureCommandList = lectureUseCase.getOtherLectureList(list, String.valueOf(year), season);
        System.out.println(option + " outLectureCommandList: " + outLectureCommandList.size());

        // Other 저장
        create(list, outLectureCommandList, option);
    }

    private void create(List<ListCommand> list, List<OutLectureCommand> outLectureCommandList, String option) throws Exception {

        // subjectcode 저장 및 가져오기
        List<SubjectCode> sbjctCodeList = createAndGetSubjectCode(outLectureCommandList);
        System.out.println(option + " sbjctCodeList: " + sbjctCodeList.size());

        // course 저장 및 가져오기
        List<Course> courseList = createAndGetCourse(outLectureCommandList, sbjctCodeList);
        System.out.println(option + " courseList: " + courseList.size());

        // 목록 저장
        List<SubjectSection> subjectSectionList = createListUseCase.createSubjectSection(list);
        System.out.println(option + " subjectSectionList: " + subjectSectionList.size());


        // department 저장 및 가져오기
        List<Department> departmentList = createAndGetDepartment(outLectureCommandList);
        System.out.println(option + " departmentList: " + departmentList.size());


        // syllabus 저장 및 가져오기
        List<Syllabus> syllabusList = createAndGetSyllabus(outLectureCommandList, courseList);
        System.out.println(option + " syllabusList: " + syllabusList.size());

        // professor 저장 및 가져오기
        List<Professor> professorList = createAndGetProfessor(syllabusList);
        System.out.println(option + " professorList: " + professorList.size());

        // evaluation 저장 및 가져오기
        List<Evaluation> evaluationList = createAndGetEvaluation(syllabusList);
        System.out.println(option + " evaluationList: " + evaluationList.size());

        // lecture 저장 및 가져오기
        List<Lecture> lectureList = createAndGetLecture(outLectureCommandList, syllabusList, professorList, departmentList, subjectSectionList, evaluationList);
        System.out.println(option + " lectureList: " + lectureList.size());

        // schedule 저장
        createSchedule(courseList);
        System.out.println(option + " Schedule");

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

    private List<Course> createAndGetCourse(List<OutLectureCommand> outLectureCommandList, List<SubjectCode> sbjctCodeList) {
        // course command
        List<CourseCommand> courseCommandList = createCourseCommandList(outLectureCommandList, sbjctCodeList);
        // course 목록 저장
        return createCourseUseCase.createCourse(courseCommandList);
    }

    private List<Syllabus> createAndGetSyllabus(List<OutLectureCommand> outLectureCommandList, List<Course> courseList) throws Exception {
        List<SyllabusParamsCommand> syllabusParamsCommandList = createSyllabusParamsCommandList(outLectureCommandList);
        // syllabus 불러오기
        List<Syllabus> syllabusList = syllabusUseCase.getSyllabusList(syllabusParamsCommandList, courseList);
        // syllabus command
        List<SyllabusCommand> syllabusCommandList = createSyllabusCommandList(syllabusList);
        // syllabus 저장
        createSyllabusUseCase.createSyllabus(syllabusCommandList);
        return syllabusList;
    }

    private List<Professor> createAndGetProfessor(List<Syllabus> syllabusList) {
        List<ProfessorCommand> professorCommandList = createProfessorCommandList(syllabusList);
        // professor 목록 저장
        return createProfessorUseCase.createProfessor(professorCommandList);
    }

    private List<Evaluation> createAndGetEvaluation(List<Syllabus> syllabusList) {
        // evaluation command
        List<EvaluationCommand> evaluationCommandList = createEvaluationCommandList(syllabusList);
        // evaluation 저장
        return createEvaluationUseCase.createEvaluation(evaluationCommandList);
    }

    private List<Lecture> createAndGetLecture(List<OutLectureCommand> outLectureCommandList, List<Syllabus> syllabusList,
                                              List<Professor> professorList, List<Department> departmentList,
                                              List<SubjectSection> subjectSectionList, List<Evaluation> evaluationList) {
        List<LectureCommand> lectureCommandList = createLectureCommandList(outLectureCommandList, syllabusList, professorList, departmentList, subjectSectionList, evaluationList);
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
    private List<CourseCommand> createCourseCommandList(List<OutLectureCommand> outLectureCommandList, List<SubjectCode> sbjctCodeList) {
        List<CourseCommand> list = new ArrayList<>();
        for (int i = 0; i < outLectureCommandList.size(); i++) {
            OutLectureCommand command = outLectureCommandList.get(i);
            SubjectCode code = sbjctCodeList.get(i);
            list.add(CourseCommand.builder()
                    .crseNo(command.crseNo())
                    .year(command.estblYear())
                    .season(command.estblSmstrSctnm())
                    .subjectCode(code)
                    .build());
        }
        return list;
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

    private List<SyllabusCommand> createSyllabusCommandList(List<Syllabus> syllabusList) {
        return syllabusList.stream().map(
                syllabus -> SyllabusCommand.builder()
                        .crseGoal(syllabus.getCrseGoal())
                        .eduGoal(syllabus.getEduGoal())
                        .summary(syllabus.getSummary())
                        .textbook(syllabus.getTextbook())
                        .evalMethd(syllabus.getEvalMethd())
                        .intviTimeLoc(syllabus.getIntviTimeLoc())
                        .refer(syllabus.getRefer())
                        .doPlan(syllabus.getDoPlan())
                        .course(syllabus.getCourse())
                        .build()
        ).toList();
    }

    private List<ProfessorCommand> createProfessorCommandList(List<Syllabus> syllabusList) {
        return syllabusList.stream()
                .filter(syllabus -> Objects.equals(syllabus.getDoPlan(), "Kor"))
                .map(
                syllabus -> ProfessorCommand.builder()
                        .profNm(syllabus.getProfNm())
                        .profTel(syllabus.getProfTel())
                        .profEmail(syllabus.getProfEmail())
                        .build()
                ).toList();
    }
    private List<EvaluationCommand> createEvaluationCommandList(List<Syllabus> syllabusList) {
        return syllabusList.stream()
                .filter(syllabus -> Objects.equals(syllabus.getDoPlan(), "Kor"))
                .map(
                syllabus -> EvaluationCommand.builder()
                        .attendance(Float.parseFloat(syllabus.getAttendance()))
                        .midExam(Float.parseFloat(syllabus.getMidExam()))
                        .finalExam(Float.parseFloat(syllabus.getFinalExam()))
                        .assignment(Float.parseFloat(syllabus.getAssignment()))
                        .presentation(Float.parseFloat(syllabus.getPresentation()))
                        .debate(Float.parseFloat(syllabus.getDebate()))
                        .safetyEdu(Float.parseFloat(syllabus.getSafetyEdu()))
                        .etc(Float.parseFloat(syllabus.getEtc()))
                        .total(Float.parseFloat(syllabus.getTotal()))
                        .course(syllabus.getCourse())
                        .build()
        ).toList();
    }
    private List<LectureCommand> createLectureCommandList(List<OutLectureCommand> outLectureCommandList, List<Syllabus> syllabusList,
                                                          List<Professor> professorList, List<Department> departmentList,
                                                          List<SubjectSection> subjectSectionList, List<Evaluation> evaluationList) {
        List<LectureCommand> list = new ArrayList<>();
        for (int i = 0; i < outLectureCommandList.size(); i++) {
            OutLectureCommand outLectureCommand = outLectureCommandList.get(i);
            Syllabus syllabus = syllabusList.get(i * 2);
            Professor professor = professorList.get(i);
            Department department = departmentList.get(i);
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
                    .lang(syllabus.getLang())
                    .isRemote(Objects.equals(outLectureCommand.expniSllbsYn(), "Y"))
                    .note(outLectureCommand.rmrk())
                    .preSbjet(syllabus.getPreSbjet())
                    .postSbjet(syllabus.getPostSbjet())
                    .realLecTime(outLectureCommand.lssnsRealTimeInfo())
                    .lecTime(outLectureCommand.lssnsTimeInfo())
                    .course(syllabus.getCourse())
                    .professor(professor)
                    .department(department)
                    .subjectSection(subjectSection)
                    .evaluation(evaluation)
                    .humanities(outLectureCommand.humanities())
                    .sdg(outLectureCommand.sdg())
                    .flipped(outLectureCommand.flipped())
                    .nU(outLectureCommand.nU())
                    .dgKp(outLectureCommand.dgKp())
                    .su(outLectureCommand.su())
                    .build());
        }
        return list;
    }

    private List<ScheduleCommand> createScheduleCommandList(List<Course> courseList) throws Exception {
        List<ScheduleCommand> list = new ArrayList<>();
        for (Course course : courseList) {
            list.addAll(addScheduleCommandList(course, "Kor"));
            list.addAll(addScheduleCommandList(course, "Eng"));
        }
        return list;
    }

    private List<ScheduleCommand> addScheduleCommandList(Course course, String doPlan) throws Exception {
        List<ScheduleCommand> list = new ArrayList<>();
        List<OutScheduleCommand> outList = scheduleUseCase.getSchedule(course.getYear(), course.getSeason(), course.getCrseNo(), doPlan);
        for (OutScheduleCommand outScheduleCommand : outList) {
            list.add(ScheduleCommand.builder()
                    .lssnsGoalCntns(outScheduleCommand.lssnsGoalCntns())
                    .lssnsMethd(outScheduleCommand.lssnsMethd())
                    .rsrchCntns(outScheduleCommand.rsrchCntns())
                    .weekSn(outScheduleCommand.weekSn())
                    .weekNote(outScheduleCommand.weekNote())
                    .course(course)
                    .doPlan(doPlan)
                    .build());
        }
        return list;
    }
}

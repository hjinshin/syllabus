package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.LectureCommand;
import knu.team7.syllabus.application.port.out.CreateLecturePort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.domain.model.*;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.*;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.LectureRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements CreateLecturePort {
    private final LectureRepository lectureRepository;
    @Override
    public List<Lecture> createLecture(List<LectureCommand> list) {
        List<LectureJpaEntity> saveJpaEntities = list.stream().map(
                        command -> LectureJpaEntity.builder()
                                .credit(command.credit())
                                .lecCr(command.lecCr())
                                .pracCr(command.pracCr())
                                .grade(command.grade())
                                .building(command.building())
                                .room(command.room())
                                .capacity(command.capacity())
                                .lang(command.lang())
                                .isRemote(command.isRemote())
                                .note(command.note())
                                .preSbjet(command.preSbjet())
                                .postSbjet(command.postSbjet())
                                .realLecTime(command.realLecTime())
                                .courseJpaEntity(courseJpaEntityBuilder(command.course()))
                                .professorJpaEntity(professorJpaEntityBuilder(command.professor()))
                                .departmentJpaEntity(departmentJpaEntityBuilder(command.department()))
                                .subjectSectionJpaEntity(subjectSectionJpaEntityBuilder(command.subjectSection()))
                                .evaluation(evaluationJpaEntityBuilder(command.evaluation()))
                                .build())
                .filter(entity -> !lectureRepository.existsByCourseJpaEntity(entity.getCourseJpaEntity()))
                .toList();
        lectureRepository.saveAll(saveJpaEntities);
        return list.stream().map(
                item -> Lecture.builder()
                        .id(lectureRepository.findByCourseJpaEntity(courseJpaEntityBuilder(item.course()))
                                .getId())
                        .credit(item.credit())
                        .lecCr(item.lecCr())
                        .pracCr(item.pracCr())
                        .grade(item.grade())
                        .building(item.building())
                        .room(item.room())
                        .capacity(item.capacity())
                        .lang(item.lang())
                        .isRemote(item.isRemote())
                        .note(item.note())
                        .preSbjet(item.preSbjet())
                        .postSbjet(item.postSbjet())
                        .realLecTime(item.realLecTime())
                        .course(item.course())
                        .professor(item.professor())
                        .department(item.department())
                        .subjectSection(item.subjectSection())
                        .evaluation(item.evaluation())
                        .build()
        ).toList();
    }

    private CourseJpaEntity courseJpaEntityBuilder(Course course) {
        return CourseJpaEntity.builder()
                .id(course.getId())
                .crseNo(course.getCrseNo())
                .year(Integer.parseInt(course.getYear()))
                .season(course.getSeason())
                .subjectCodeJpaEntity(SubjectCodeJpaEntity.builder()
                        .sbjetCd(course.getSubjectCode().getSbjetCd())
                        .sbjetNm(course.getSubjectCode().getSbjetNm())
                        .build())
                .build();

    }
    private ProfessorJpaEntity professorJpaEntityBuilder(Professor professor) {
        return ProfessorJpaEntity.builder()
                .id(professor.getId())
                .profNm(professor.getProfNm())
                .profTel(professor.getProfTel())
                .profEmail(professor.getProfEmail())
                .build();
    }
    private DepartmentJpaEntity departmentJpaEntityBuilder(Department department) {
        return DepartmentJpaEntity.builder()
                .id(department.getId())
                .college(department.getCollege())
                .depart(department.getDepart())
                .build();
    }

    private SubjectSectionJpaEntity subjectSectionJpaEntityBuilder(SubjectSection subjectSection) {
        return SubjectSectionJpaEntity.builder()
                .codeId(subjectSection.getCodeId())
                .sbjetSctnm(subjectSection.getSbjetSctnm())
                .lSct(subjectSection.getLSct())
                .mSct(subjectSection.getMSct())
                .sSct(subjectSection.getSSct())
                .build();
    }
    private EvaluationJpaEntity evaluationJpaEntityBuilder(Evaluation evaluation) {
        return EvaluationJpaEntity.builder()
                .id(evaluation.getId())
                .attendance(evaluation.getAttendance())
                .midExam(evaluation.getMidExam())
                .finalExam(evaluation.getFinalExam())
                .assignment(evaluation.getAssignment())
                .presentation(evaluation.getPresentation())
                .debate(evaluation.getDebate())
                .safetyEdu(evaluation.getSafetyEdu())
                .etc(evaluation.getEtc())
                .total(evaluation.getTotal())
                .courseJpaEntity(courseJpaEntityBuilder(evaluation.getCourse()))
                .build();
    }
}

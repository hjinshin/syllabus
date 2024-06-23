import { IGeoData } from "./IGeoData";

export interface ICourse {
  courseName: string; // "수학", "과학", ...
  courseNameEn: string; // "Mathematics", "Science", ...
  subnjectCode: string; // "MATH", "SCI", ...
  time: AssignedTime;
  building: IGeoData;
}

// 교양, 군사학, 교직, 연구산학처, 대학, 대학원, 전문대학원, 원격수업, 원격강의, 플립드러닝 강좌, 거점국립대 원격강좌, 대구경북권원격강좌, SU평가강좌, 실험실습실기강좌, KNU미래설계, 인문교양, SDG교양
// represents in english
export enum ECourseType {
  General = "General",
  Military = "Military",
  Teaching = "Teaching",
  Research = "Research",
  University = "University",
  GraduateSchool = "GraduateSchool",
  ProfessionalGraduateSchool = "ProfessionalGraduateSchool",
  RemoteClass = "RemoteClass",
  RemoteLecture = "RemoteLecture",
  FlippedLearning = "FlippedLearning",
  NationalUniversityRemoteLecture = "NationalUniversityRemoteLecture",
  DaeguGyeongbukRegionRemoteLecture = "DaeguGyeongbukRegionRemoteLecture",
  SUAssessmentCourse = "SUAssessmentCourse",
  ExperimentalPracticeCourse = "ExperimentalPracticeCourse",
  KNUFutureDesign = "KNUFutureDesign",
  HumanitiesGeneral = "HumanitiesGeneral",
  SDGGeneral = "SDGGeneral",
}

// 첨성인기초, 첨성인핵심, 첨성인일반, 첨성인소양
export enum GeneralCourseType {
  Basic = "Basic",
  Core = "Core",
  General = "General",
  Elective = "Elective",
}

// 독서와토론, 사고교육, 글쓰기, 외국어, 수리, 기초과학, 실용영어, 소프트웨어
export enum HumanitiesGeneralCourseType {
  ReadingAndDiscussion = "ReadingAndDiscussion",
  ThinkingEducation = "ThinkingEducation",
  Writing = "Writing",
  ForeignLanguage = "ForeignLanguage",
  Mathematics = "Mathematics",
  BasicScience = "BasicScience",
  PracticalEnglish = "PracticalEnglish",
  Software = "Software",
}

//

export interface AssignedTime {
  day: Day[];
  times: ITime[];
}

export interface ITime {
  actualTime: ActualTime;
  name:
    | "0A"
    | "0B"
    | "1A"
    | "1B"
    | "2A"
    | "2B"
    | "3A"
    | "3B"
    | "4A"
    | "4B"
    | "5A"
    | "5B"
    | "6A"
    | "6B"
    | "7A"
    | "7B"
    | "8A"
    | "8B"
    | "9A"
    | "9B";
}

export type ActualTime = [number, number]; // [epoch, epoch]

export enum Day {
  Monday,
  Tuesday,
  Wednesday,
  Thursday,
  Friday,
  Saturday,
  Sunday,
}

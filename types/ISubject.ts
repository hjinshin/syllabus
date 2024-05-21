export interface ISubject {
  subjectName: string; // "수학", "과학", ...
  subjectNameEn: string; // "Mathematics", "Science", ...
  subnjectCode: string; // "MATH", "SCI", ...
  time: AssignedTime;
}

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

export type ActualTime =
  | "8:00 - 9:00"
  | "9:00 - 10:00"
  | "10:00 - 11:00"
  | "11:00 - 12:00"
  | "13:00 - 14:00"
  | "14:00 - 15:00";

export type Day =
  | "Monday"
  | "Tuesday"
  | "Wednesday"
  | "Thursday"
  | "Friday"
  | "Saturday"
  | "Sunday";

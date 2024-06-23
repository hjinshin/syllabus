import { Day } from "./api/ILectureResponse";
import { ILecture } from "./api/ILectureResponse";

export interface ITimeTableUnit {
  lecture: ILecture;
  timeUnits: ITimeUnit[];
}

export interface ITimeUnit {
  day: DoW;
  time: Time;
}

export enum Time {
  "09:00",
  "09:30",
  "10:00",
  "10:30",
  "11:00",
  "11:30",
  "12:00",
  "12:30",
  "13:00",
  "13:30",
  "14:00",
  "14:30",
  "15:00",
  "15:30",
  "16:00",
  "16:30",
  "17:00",
  "17:30",
  "18:00",
  "18:30",
  "19:00",
  "19:30",
  "20:00",
  "20:30",
  "21:00",
}

export enum TimeCode {
  "0A",
  "0B",
  "1A",
  "1B",
  "2A",
  "2B",
  "3A",
  "3B",
}

export enum DoW {
  MON,
  TUE,
  WED,
  THU,
  FRI,
  SAT,
  SUN,
}

export const hasDuplicatedSchedule = (
  timeTable: ITimeTableUnit[],
  times: ITimeUnit[],
): boolean => {
  console.log("hasDuplicatedSchedule");
  console.log(timeTable);
  console.log(times);
  return timeTable.some((unit) => {
    return unit.timeUnits.some((unitTime) => {
      return times.some((time) => {
        return unitTime.day === time.day && unitTime.time === time.time;
      });
    });
  });
};

export const dayTypeToDoW = (day: Day): DoW => {
  console.log("dayTypeToDoW");
  console.log(day);
  switch (day) {
    case Day.MON:
      return DoW.MON;
    case Day.TUE:
      return DoW.TUE;
    case Day.WED:
      return DoW.WED;
    case Day.THU:
      return DoW.THU;
    case Day.FRI:
      return DoW.FRI;
    case Day.SAT:
      return DoW.SAT;
    case Day.SUN:
      return DoW.SUN;
    default:
      throw new Error("Invalid day");
  }
};

export const krDayToEngDay = (day: string): DoW => {
  console.log("krDayToEngDay");
  console.log(day);
  switch (day.trim()) {
    case "월":
      return DoW.MON;
    case "화":
      return DoW.TUE;
    case "수":
      return DoW.WED;
    case "목":
      return DoW.THU;
    case "금":
      return DoW.FRI;
    case "토":
      return DoW.SAT;
    case "일":
      return DoW.SUN;
    default:
      throw new Error("Invalid day");
  }
};
/**
 *
 * @param timeString 월 10:30 ~ 12:00,금 09:00 ~ 10:30,.. or 월 10:30 ~ 12:00,화 14:00 ~ 15:00,금 09:00 ~ 10:30
 * @returns
 */
export const timeStringToTimeUnit = (timeString: string): ITimeUnit[] => {
  const timeUnits: ITimeUnit[] = [];
  const timeStrings = timeString.split(",");
  console.log("timeStrings");
  console.log(timeStrings);
  timeStrings.forEach((timeStr) => {
    const token = timeStr.split(" ");
    const dayStr = token[0];
    const timeRangeStr = token[1] + " ~ " + token[3];
    console.log("dayStr");
    console.log(dayStr);
    console.log("timeRangeStr");
    console.log(timeRangeStr);

    const day = krDayToEngDay(dayStr);
    console.log("day");
    console.log(day);
    const [startTimeStr, endTimeStr] = timeRangeStr.split(" ~ ");
    console.log("startTimeStr");
    console.log(startTimeStr);
    console.log("endTimeStr");
    console.log(endTimeStr);

    const startTime = Time[startTimeStr.trim() as keyof typeof Time]
    const endTime = Time[endTimeStr.trim() as keyof typeof Time];
    for (let i = startTime; i < endTime; i += 2) {
      timeUnits.push({ day, time: i });
      timeUnits.push({ day, time: i + 1 });
    }
  });
  console.log("timeUnits");
  console.log(timeUnits);
  return timeUnits;
};

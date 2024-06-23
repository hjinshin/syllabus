import { IGeoData, IT1, IT5 } from "@/types/IGeoData";
import { Day, ICourse } from "@/types/ICourse";

export const getTimeTables: () => ICourse[] = () => [
  {
    courseName: "수학",
    courseNameEn: "Mathematics",
    subnjectCode: "MATH",
    time: {
      day: [Day.Monday, Day.Wednesday],
      times: [
        { name: "0A", actualTime: [8, 9] },
        { name: "0B", actualTime: [9, 10] },
      ],
    },
    building: IT1,
  },
  {
    courseName: "과학",
    courseNameEn: "Science",
    subnjectCode: "SCI",
    time: {
      day: [Day.Monday, Day.Wednesday],
      times: [
        { name: "0A", actualTime: [8, 9] },
        { name: "0B", actualTime: [9, 10] },
      ],
    },
    building: IT5,
  },
  {
    courseName: "영어",
    courseNameEn: "English",
    subnjectCode: "ENG",
    time: {
      day: [Day.Monday, Day.Tuesday],
      times: [{ name: "0A", actualTime: [13, 14] }],
    },
    building: IT5,
  },
];

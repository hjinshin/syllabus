import { IGeoData, IT1, IT5 } from "@/types/IGeoData";
import { ICourse } from "@/types/ICourse";

export const getTimeTables: () => ICourse[] = () => [
  {
    courseName: "수학",
    courseNameEn: "Mathematics",
    subnjectCode: "MATH",
    time: {
      day: ["Monday", "Wednesday", "Friday"],
      times: [
        { name: "0A", actualTime: "8:00 - 9:00" },
        { name: "0B", actualTime: "9:00 - 10:00" },
      ],
    },
    building: IT1,
  },
  {
    courseName: "과학",
    courseNameEn: "Science",
    subnjectCode: "SCI",
    time: {
      day: ["Tuesday", "Thursday"],
      times: [
        { name: "0A", actualTime: "8:00 - 9:00" },
        { name: "0B", actualTime: "9:00 - 10:00" },
      ],
    },
    building: IT5,
  },
  {
    courseName: "영어",
    courseNameEn: "English",
    subnjectCode: "ENG",
    time: {
      day: ["Monday", "Wednesday", "Friday"],
      times: [
        { name: "0A", actualTime: "8:00 - 9:00" },
        { name: "0B", actualTime: "9:00 - 10:00" },
      ],
    },
    building: IT5,
  },
];

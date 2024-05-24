import { IGeoData, IT1, IT5 } from "@/types/IGeoData";
import { ISubject } from "@/types/ISubject";

export const getTimeTables: () => ISubject[] = () => [
  {
    subjectName: "수학",
    subjectNameEn: "Mathematics",
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
    subjectName: "과학",
    subjectNameEn: "Science",
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
    subjectName: "영어",
    subjectNameEn: "English",
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

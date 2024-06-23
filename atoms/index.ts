
import { ITimeTableUnit } from "@/types/ITime";
import { ILecture } from "@/types/api/ILectureResponse";
import { atom } from "jotai";
import { atomWithImmer } from "jotai-immer";

export const showCommandAtom = atom<boolean>(false);
// export const selectedLectureAtom = atom<ICourse | null>(null);

// export const selectedCoursesAtom = atom<ICourse[]>([]);

export const timeTableAtom = atomWithImmer<ITimeTableUnit[]>([]);
export const selectedCourseAtom = atom<ILecture | null>(null);
export const openDrawerAtom = atom<boolean>(false);

import { ICourse } from "@/types/ICourse";
import { atom } from "jotai";

export const showCommandAtom = atom<boolean>(false);
export const selectedLectureAtom = atom<ICourse | null>(null);

export const selectedCoursesAtom = atom<ICourse[]>([]);

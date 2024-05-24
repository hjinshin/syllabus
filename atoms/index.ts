import { ISubject } from "@/types/ISubject";
import { atom } from "jotai";

export const showCommandAtom = atom<boolean>(false);
export const selectedSubjectAtom = atom<ISubject | null>(null);

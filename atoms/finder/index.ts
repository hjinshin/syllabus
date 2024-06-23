import { IFilter, filters } from "@/types/IFilter";
import { ILecture } from "@/types/api/ILectureResponse";
import { SearchQuery } from "@/types/etcTypes";
import { atom } from "jotai";
import { atomWithImmer } from "jotai-immer";

// export const selectedCourseAtom = atomWithImmer<ICourse | null>(null);
export const viewAllFiltersAtom = atom<boolean>(false);
export const selectedFiltersAtom = atomWithImmer<IFilter[]>(filters);
export const lectureListAtom = atom<ILecture[] | null>(null);
export const searchQueryInputAtom = atom<string>("");
export const searchQueryAtom = atom<SearchQuery | null>(null);


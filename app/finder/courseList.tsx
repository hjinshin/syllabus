import { lectureListAtom } from "@/atoms/finder";
import { ILecture } from "@/types/api/ILectureResponse";
import { useAtom } from "jotai";

// const Lecture = ({ lecture }: { lecture: ILecture }) => {
//   return <div className="flex flex-col w-full">
//     <div className="" >
//       <p className="text-lg font-bold">{lecture.sbjctNm}</p>
//       <p className="text-sm font-medium">{lecture.}</p>
//     </div>
//   </div>;
  
  

const LectureList = () => {
  const [lectureList, setLectureList] = useAtom(lectureListAtom);

  return <div className="flex w-full flex-col"></div>;
};

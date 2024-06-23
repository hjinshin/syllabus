import { selectedLectureAtom, selectedCoursesAtom } from "@/atoms";
import TimeTable from "@/components/time-table";
import MiniTimeTable from "@/components/time-table/mini-table";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";
import { useAtom } from "jotai";
import { CalendarSearch, Undo2 } from "lucide-react";

const Title = () => {
  const [selectedCourse, setSelectedCourse] = useAtom(selectedLectureAtom);
  const [selectedCourses, setSelectedCourses] = useAtom(selectedCoursesAtom);

  return (
    <CardHeader className="p-4">
      <CardTitle className="div flex flex-row items-center justify-between">
        <p className="text-base text-neutral-700">
          {selectedCourse?.courseName || "지금까지 담은 과목들"}
        </p>
        {selectedCourse ? (
          <TooltipProvider delayDuration={0}>
            <Tooltip>
              <TooltipTrigger>
                <Undo2
                  className="h-6 w-6 cursor-pointer rounded-md stroke-neutral-600 p-1 transition hover:bg-neutral-100"
                  onClick={() => setSelectedCourse(null)}
                />
              </TooltipTrigger>
              <TooltipContent className="text-xs text-neutral-500">
                과목 선택 취소
              </TooltipContent>
            </Tooltip>
          </TooltipProvider>
        ) : null}
      </CardTitle>
    </CardHeader>
  );
};

const Content = () => {
  return (
    <CardContent className="p-3">
      <MiniTimeTable />
    </CardContent>
  );
};

const OccupiedTime = () => {
  return (
    // <TooltipProvider delayDuration={0}>
    //   <Tooltip>
    //     <TooltipTrigger>
          <Card className="w-96 select-none">
            <Title />
            <Content />
          </Card>
    //     </TooltipTrigger>
    //     <TooltipContent className="flex flex-row items-center space-x-1.5">
    //       <CalendarSearch className="h-4 w-4 stroke-neutral-500" />
    //       <p className="text-xs text-neutral-500">클릭해서 자세히 보기</p>
    //     </TooltipContent>
    //   </Tooltip>
    // </TooltipProvider>
  );
};

export default OccupiedTime;

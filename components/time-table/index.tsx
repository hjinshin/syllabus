"use client";

import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "../ui/tooltip";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import { useAtom, useAtomValue } from "jotai";
import { DoW, Time } from "@/types/ITime";
import { openDrawerAtom, selectedCourseAtom, timeTableAtom } from "@/atoms";
import { Table } from "../ui/table";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "../ui/dialog";
import { Button } from "../ui/button";
import { SquareMousePointer } from "lucide-react";
const Dummy = () => <div className="h-8 w-12"></div>;

const colorsForCourse = ["#fee2e2", "#d1fae5", "#ecfccb", "#f1f5f9"];

// const Day = ({ day }: { day: string }) => (
//   <div className="flex w-12 flex-row text-sm text-neutral-500">{day}</div>
// );

// const Days = () => (
//   <div className="flex w-full flex-row">
//     <Day day="월" />
//     <Day day="화" />
//     <Day day="수" />
//     <Day day="목" />
//     <Day day="금" />
//     <Day day="토" />
//     <Day day="일" />
//   </div>
// );

// const Time = ({ time }: { time: number }) => {
//   return (
//     <div className="flex w-full -translate-y-2 flex-row text-sm text-neutral-500">
//       {time}
//     </div>
//   );
// };
// const Times = () => {
//   return (
//     <div className="flex flex-col">
//       {Array.from({ length: 10 }, (_, i) => (
//         <Time time={i + 9} key={i} />
//       ))}
//     </div>
//   );
// };

// const ActualData = ({ day }: { day: Day }) => {
//   const [selectedCouse, setSelectedCourse] = useAtom(selectedLectureAtom);
//   const courses = getTimeTables();

//   const intToDay = (int: number) => {
//     switch (int) {
//       case 0:
//         return "월요일";
//       case 1:
//         return "화요일";
//       case 2:
//         return "수요일";
//       case 3:
//         return "목요일";
//       case 4:
//         return "금요일";
//       case 5:
//         return "토요일";
//       case 6:
//         return "일요일";
//       default:
//         return "";
//     }
//   };

//   return (
//     <div className="flex flex-col">
//       {[...Array(7)].map((_, i) => {
//         // const included = courses.find(
//         //   (course) =>
//         //     course.time.day[i] === i as Day &&
//         //     course.time.times[0].actualTime[0] === day,
//         // );
//         const included = courses[0];

//         return (
//           <div className="flex w-full flex-row" key={i}>
//             {included ? (
//               <TooltipProvider delayDuration={0}>
//                 <Tooltip>
//                   <TooltipTrigger>
//                     <div
//                       className={`flex w-full flex-row rounded-md border border-neutral-200 bg-neutral-100 p-1`}
//                     >
//                       <div className="flex w-full flex-row text-sm text-neutral-500">
//                         {included.courseName}
//                       </div>
//                     </div>
//                   </TooltipTrigger>
//                   <TooltipContent className="text-xs text-neutral-500">
//                     {included.building.buildingName}
//                   </TooltipContent>
//                 </Tooltip>
//               </TooltipProvider>
//             ) : (
//               <div className="flex w-full flex-row"></div>
//             )}
//           </div>
//         );
//       })}
//     </div>
//   );
// };

const Cell = ({ hour, day }: { hour: Time; day: DoW }) => {
  const [timeTable, setTimeTable] = useAtom(timeTableAtom);
  const [openDrawer, setOpenDrawer] = useAtom(openDrawerAtom);
  const [selectedCourse, setSelectedCourse] = useAtom(selectedCourseAtom);

  const thisCellShouldBeFilled = (() => {
    const thisCourse = timeTable.find((lecture, index) => {
      if (
        lecture.timeUnits.find((timeUnit) => {
          if (timeUnit.day === day && timeUnit.time === hour) {
            console.log(`timeUnit.day === day && timeUnit.time === hour`);
            console.log(timeUnit.day === day && timeUnit.time === hour);
            console.log(
              "timeunit.day",
              timeUnit.day,
              "day",
              day,
              "timeunit.time",
              timeUnit.time,
              "hour",
              hour,
            );
          }
          return timeUnit.day === day && timeUnit.time === hour;
        })
      ) {
        return true;
      } else {
        return false;
      }
    });
    if (!!thisCourse) return thisCourse;
    else {
      return false;
    }
  })();

  if (thisCellShouldBeFilled) {
    console.log(`thisCellShouldBeFilled`);
    console.log(thisCellShouldBeFilled);
  }

  /**
   *
   * check if this cell is center of the schedule of the course
   */
  const thisCellIsCenter = (() => {
    if (thisCellShouldBeFilled) {
      const thisCourse = thisCellShouldBeFilled;
      const timeUnits = thisCourse.timeUnits;
      const center = timeUnits.length / 2;
      const isCenter =
        timeUnits[center].day === day && timeUnits[center].time === hour;
      return isCenter;
    }
    return false;
  })();

  if (thisCellIsCenter) {
    console.log(`thisCellIsCenter`);
    console.log(thisCellIsCenter);
  }

  return (
    // <Dialog>
    //   <DialogTrigger>
        <div
          className={`mx-1 h-full w-full text-center transition duration-300 ease-out ${thisCellShouldBeFilled ? `cursor-pointer bg-red-200 hover:shadow-sm px-1` : ``}`}
        >
          {thisCellShouldBeFilled ? (
            <div
              className="flex h-full w-full flex-col items-center justify-center"
              // style={{
              //   background:
              //     colorsForCourse[
              //       thisCellShouldBeFilled.findIndex((course) => course === thisCourse) % 3
              //     ],
              // }}
            >
              <p className="text-sm font-medium text-neutral-700">
                {thisCellIsCenter && thisCellShouldBeFilled
                  ? thisCellShouldBeFilled.lecture.sbjctNm
                  : ""}
              </p>
              <p className="text-xs text-neutral-500">
                {thisCellIsCenter && thisCellShouldBeFilled
                  ? thisCellShouldBeFilled.lecture.building
                  : ""}
              </p>
            </div>
          ) : null}
        </div>
    //   </DialogTrigger>
    //   <DialogContent>
    //     <DialogHeader>
    //       <DialogTitle>
    //         {thisCellShouldBeFilled && thisCellShouldBeFilled.lecture.sbjctNm}
    //       </DialogTitle>
    //     </DialogHeader>
    //     <div className="flex w-full flex-col">
    //       {thisCellShouldBeFilled ? (
    //         <div className="flex flex-col gap-y-1">
    //           <p className="text-sm">
    //             {thisCellShouldBeFilled.lecture.building}
    //           </p>
    //           <p className="text-sm">
    //             {thisCellShouldBeFilled.lecture.realLecTime}
    //           </p>

    //           <div className="flex w-full flex-row justify-end">
    //             <Button
    //               className="space-x-1.5"
    //               onClick={() => {
    //                 if (thisCellShouldBeFilled)
    //                   setSelectedCourse(thisCellShouldBeFilled.lecture);
    //                 setOpenDrawer(true);
    //               }}
    //             >
    //               <SquareMousePointer className="h-4 w-4" />
    //               <p>자세히 보기</p>
    //             </Button>
    //           </div>
    //         </div>
    //       ) : null}
    //     </div>
    //   </DialogContent>
    // </Dialog>
    // <div
    //   className={`m-1 h-full w-full rounded-sm text-center transition duration-300 ease-out ${thisCellShouldBeFilled ? `cursor-pointer hover:border hover:shadow-sm active:scale-[0.95]` : ``}`}
    // >

    // </div>
  );
};

const TimeTable = () => {
  const [timeTable, setTimeTable] = useAtom(timeTableAtom);
  // const timeLength = Math.max(
  //   ...timeTable.map((lecture) =>
  //     Math.max(...lecture.timeUnits.map((timeUnit) => timeUnit.time)),
  //   ),
  // );
  return (
    <Table className="h-full w-full max-w-xl table-auto select-none rounded-lg border-none bg-neutral-50/50">
      <thead>
        <tr className="border-0 border-none">
          {["", "월", "화", "수", "목", "금"].map((day, index) => (
            <th
              key={index}
              className="h-12 select-none border-none font-medium"
            >
              {day}
            </th>
          ))}
        </tr>
      </thead>
      <tbody className="border-0  border-none p-0">
        {Array.from({ length: 20 }, (_, hour) => (
          <tr key={hour} className="m-0  border-0 border-none p-0">
            <td className="m-0  border-0 border-none p-0">
              <div className="flex h-6  w-24 flex-col items-center justify-center rounded-md text-center transition">
                {
                  /* 9:00 ~ 21:00 */
                  hour % 2 === 0 && hour / 2 + 9 < 10
                    ? `0${hour / 2 + 9}시`
                    : hour % 2 === 0 && `${hour / 2 + 9}시`
                }
              </div>
            </td>
            {[...Array(5)].map((_, day) => (
              <td
                className="relative m-0 w-1/5  border-0  border-none p-0"
                key={day}
              >
                <Cell day={day} hour={hour} key={day} />
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TimeTable;

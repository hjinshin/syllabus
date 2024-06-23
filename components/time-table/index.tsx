import { ITime } from "@/types/ICourse";
import { Day } from "@/types/ICourse";
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
import { selectedLectureAtom } from "@/atoms";
import { useAtom } from "jotai";
import { getTimeTables } from "@/lib/dummy-data-provider";
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

const Time = ({ time }: { time: number }) => {
  return (
    <div className="flex w-full -translate-y-2 flex-row text-sm text-neutral-500">
      {time}
    </div>
  );
};
const Times = () => {
  return (
    <div className="flex flex-col">
      {Array.from({ length: 10 }, (_, i) => (
        <Time time={i + 9} key={i} />
      ))}
    </div>
  );
};

const ActualData = ({ day }: { day: Day }) => {
  const [selectedCouse, setSelectedCourse] = useAtom(selectedLectureAtom);
  const courses = getTimeTables();

  const intToDay = (int: number) => {
    switch (int) {
      case 0:
        return "월요일";
      case 1:
        return "화요일";
      case 2:
        return "수요일";
      case 3:
        return "목요일";
      case 4:
        return "금요일";
      case 5:
        return "토요일";
      case 6:
        return "일요일";
      default:
        return "";
    }
  };

  return (
    <div className="flex flex-col">
      {[...Array(7)].map((_, i) => {
        // const included = courses.find(
        //   (course) =>
        //     course.time.day[i] === i as Day &&
        //     course.time.times[0].actualTime[0] === day,
        // );
        const included = courses[0];

        return (
          <div className="flex w-full flex-row" key={i}>
            {included ? (
              <TooltipProvider delayDuration={0}>
                <Tooltip>
                  <TooltipTrigger>
                    <div
                      className={`flex w-full flex-row rounded-md border border-neutral-200 bg-neutral-100 p-1`}
                    >
                      <div className="flex w-full flex-row text-sm text-neutral-500">
                        {included.courseName}
                      </div>
                    </div>
                  </TooltipTrigger>
                  <TooltipContent className="text-xs text-neutral-500">
                    {included.building.buildingName}
                  </TooltipContent>
                </Tooltip>
              </TooltipProvider>
            ) : (
              <div className="flex w-full flex-row"></div>
            )}
          </div>
        );
      })}
    </div>
  );
};

const Cell = ({ hour, day }: { hour: number; day: number }) => {
  const data = getTimeTables();
  const actualHour = hour + 9;
  const actualDay = (() => {
    switch (day) {
      case 0:
        return Day.Monday;
      case 1:
        return Day.Tuesday;
      case 2:
        return Day.Wednesday;
      case 3:
        return Day.Thursday;
      case 4:
        return Day.Friday;
      default:
        return Day.Monday;
    }
  })();

  const thisCourse = data.find((course) => {
    const includedTimes: ITime[] = course.time.times;

    return includedTimes.some((time) => {
      return (
        time.actualTime[0] === actualHour || time.actualTime[1] === actualHour
      );
    });
  });

  const thisCellShouldBeFilled = data.some((course) => {
    type includedTime = {
      day: Day;
      times: ITime[];
    };
    const includedTimes: includedTime[] = course.time.day.reduce(
      (acc, day, index) => {
        return [
          ...acc,
          {
            day,
            times: course.time.times,
          },
        ] as includedTime[];
      },
      [] as includedTime[],
    );

    return includedTimes.some((time) => {
      return (
        time.day === actualDay &&
        time.times.some((actualTime) => {
          return (
            actualTime.actualTime[0] === actualHour ||
            actualTime.actualTime[1] === actualHour
          );
        })
      );
    });
  });

  return (
    <div
      className={`m-1 h-full w-full rounded-sm text-center transition duration-300 ease-out ${thisCellShouldBeFilled ? `cursor-pointer hover:border hover:shadow-sm active:scale-[0.95]` : ``}`}
    >
      {thisCellShouldBeFilled ? (
        <div
          className="flex h-full w-full flex-col items-center justify-center rounded-sm"
          style={{
            background:
              colorsForCourse[
                data.findIndex((course) => course === thisCourse) % 3
              ],
          }}
        >
          <p className="text-sm font-medium text-neutral-700">
            {thisCourse?.courseName}
          </p>
          <p className="text-xs text-neutral-500">
            {thisCourse?.building.buildingName}
          </p>
        </div>
      ) : null}
    </div>
  );
};

const TimeTable = () => {
  return (
    <table className="h-full max-w-xl w-full table-auto border-collapse select-none">
      <thead>
        <tr className="">
          {["", "월", "화", "수", "목", "금"].map((day, index) => (
            <th key={index} className="h-12 select-none font-medium">
              {day}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {Array.from({ length: 10 }, (_, hour) => (
          <tr key={hour}>
            <td>
              <div className="flex h-16 w-24 w-full flex-col items-center justify-center rounded-md text-center transition">
                {hour + 9}
              </div>
            </td>
            {[...Array(5)].map((_, day) => (
              <td className="relative w-1/5" key={day}>
                <Cell day={day} hour={hour} key={day} />
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TimeTable;

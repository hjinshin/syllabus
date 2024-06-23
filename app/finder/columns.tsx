"use client";

import { getTimeTables } from "@/lib/dummy-data-provider";
import { ColumnDef } from "@tanstack/react-table";
import { MoreHorizontal } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Checkbox } from "@/components/ui/checkbox";
import { DataTableColumnHeader } from "@/components/ui/column-header";
import { ILecture } from "@/types/api/ILectureResponse";

// export const colums: ColumnDef<ICourse>[] = timeTable.map(course=>({
//   accessorKey: course.courseName,
//   header:
// }))

export const columns: ColumnDef<ILecture>[] = [
  {
    id: "select",
    header: ({ table }) => (
      <Checkbox
        checked={
          table.getIsAllPageRowsSelected() ||
          (table.getIsSomePageRowsSelected() && "indeterminate")
        }
        onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
        aria-label="Select all"
      />
    ),
    cell: ({ row }) => (
      <Checkbox
        checked={row.getIsSelected()}
        onCheckedChange={(value) => row.toggleSelected(!!value)}
        aria-label="Select row"
      />
    ),
  },
  {
    accessorKey: "sbjctNm",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="과목 이름" />
    ),
  },
  // {
  //   accessorKey: "courseNameEn",
  //   header: ({ column }) => (
  //     <DataTableColumnHeader column={column} title="과목 이름(영어)" />
  //   ),
  // },
  {
    accessorKey: "cresNo",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="과목 코드" />
    ),
    cell: ({ row }) => {
      return <span>{row.original.cresNo}</span>;
    }
  },
  {
    accessorKey: "time",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="시간" />
    ),
    cell: ({ row }) => {
      const time = row.original.realLecTime;

      // const dayToString = (day: number) => {
      //   switch (day) {
      //     case 0:
      //       return "월";
      //     case 1:
      //       return "화";
      //     case 2:
      //       return "수";
      //     case 3:
      //       return "목";
      //     case 4:
      //       return "금";
      //     case 5:
      //       return "토";
      //     case 6:
      //       return "일";
      //     default:
      //       return "";
      //   }
      // };

      return (
        <div className="flex flex-row items-center space-x-1">
          {time}
          {/* {time.day.map((day, index) => {
            return (
              <span key={index} className="text-nowrap">
                {dayToString(day)} {time.times[0].actualTime[index]}시
                {index < time.day.length - 1 ? " ~" : ""}
              </span>
            );
          })} */}
        </div>
      );
    },
  },
  {
    id: "actions",
    cell: ({ row }) => {
      const original = row.original;

      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuItem
              onClick={() =>
                navigator.clipboard.writeText(original.cresNo)
              }
            >
              과목코드 복사
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            {/* <DropdownMenuItem>View customer</DropdownMenuItem>
            <DropdownMenuItem>View payment details</DropdownMenuItem> */}
          </DropdownMenuContent>
        </DropdownMenu>
      );
    },
  },
];

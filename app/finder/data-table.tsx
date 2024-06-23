"use client";

import {
  ColumnDef,
  ColumnFiltersState,
  SortingState,
  VisibilityState,
  flexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getPaginationRowModel,
  getSortedRowModel,
  useReactTable,
} from "@tanstack/react-table";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import { DataTableViewOptions } from "@/components/ui/column-toggle";
import { DataTablePagination } from "@/components/ui/data-table-pagination";
import { motion } from "framer-motion";

import { Input } from "@/components/ui/input";
import { useState } from "react";
import { useAtom } from "jotai";
import { showMapPopupAtom } from "@/atoms/map-popup-atoms";
import { selectedCourseAtom } from "@/atoms";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { ILecture } from "@/types/api/ILectureResponse";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import axios from "axios";
import { AlarmClockPlus, SquareMousePointer } from "lucide-react";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";
import { toast } from "sonner";
import { timeTableAtom } from "@/atoms";
import {
  ITimeTableUnit,
  hasDuplicatedSchedule,
  timeStringToTimeUnit,
} from "@/types/ITime";

interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[];
  data: TData[];
}
export default function DataTable<TData, TValue>({
  columns,
  data,
}: DataTableProps<TData, TValue>) {
  const [showMapPopup, setShowMapPopup] = useAtom(showMapPopupAtom);
  const [sorting, setSorting] = useState<SortingState>([]);
  const [columnFilters, setColumnFilters] = useState<ColumnFiltersState>([]);
  const [columnVisibility, setColumnVisibility] = useState<VisibilityState>({});
  const [rowSelection, setRowSelection] = useState({});
  const [selectedCourse, setSelectedCourse] = useAtom(selectedCourseAtom);
  const [timeTable, setTimeTable] = useAtom(timeTableAtom);
  const table = useReactTable({
    data,
    columns,
    onSortingChange: setSorting,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    getSortedRowModel: getSortedRowModel(),
    onColumnFiltersChange: setColumnFilters,
    getFilteredRowModel: getFilteredRowModel(),
    onRowSelectionChange: setRowSelection,
    state: {
      sorting,
      columnFilters,
      rowSelection,
    },
  });

  return (
    <div className="flex w-full flex-col space-y-4">
      <div className="flex items-center py-0.5">
        <Input
          placeholder="검색..."
          value={(table.getColumn("email")?.getFilterValue() as string) ?? ""}
          onChange={(event) =>
            table.getColumn("email")?.setFilterValue(event.target.value)
          }
          className="max-w-sm"
        />
      </div>
      <div className="rounded-md border">
        <Table>
          <TableHeader>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => {
                  return (
                    <TableHead key={header.id}>
                      {header.isPlaceholder
                        ? null
                        : flexRender(
                            header.column.columnDef.header,
                            header.getContext(),
                          )}
                    </TableHead>
                  );
                })}
              </TableRow>
            ))}
          </TableHeader>
          <TableBody>
            {table.getRowModel().rows?.length ? (
              table.getRowModel().rows.map((row) => (
                <TableRow
                  data-testid="search-result-row"
                  className="cursor-pointer transition-colors duration-200 ease-in-out hover:bg-neutral-100"
                  onClick={async () => {
                    // const response =  await axios.get(`/api/v1/lecture/select?year=2024&season=1학기&crseNo=${(row.original as ILecture).crseNo}`);
                    // console.log(response.data)
                    setSelectedCourse(row.original as ILecture);
                    // setShowMapPopup(true);
                  }}
                  key={row.id}
                  data-state={row.getIsSelected() && "selected"}
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell key={cell.id}>
                      {flexRender(
                        cell.column.columnDef.cell,
                        cell.getContext(),
                      )}
                    </TableCell>
                  ))}
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell
                  colSpan={columns.length}
                  className="h-24 text-center"
                >
                  결과가 없습니다
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
      <DataTablePagination table={table} />
      <Dialog
        open={!!selectedCourse}
        onOpenChange={(e) => {
          if (!e) {
            setSelectedCourse(null);
          }
        }}
      >
        <DialogContent className="flex w-full flex-col space-y-4">
          <DialogHeader>
            <DialogTitle className="space-x-1.5">
              <p className="inline">{selectedCourse?.sbjctNm}</p>
              <TooltipProvider delayDuration={0}>
                <Tooltip>
                  <TooltipTrigger
                    onClick={() => {
                      if (selectedCourse?.crseNo) {
                        navigator.clipboard.writeText(selectedCourse?.crseNo);
                        toast.info("복사되었습니다.", {
                          position: "top-center",
                          duration: 700,
                        });
                      }
                    }}
                  >
                    <span className="text-sm text-neutral-500">
                      {selectedCourse?.crseNo}
                    </span>
                  </TooltipTrigger>
                  {/* <TooltipContent className="text-xs text-neutral-500">
                    클릭해서 복사
                  </TooltipContent> */}
                </Tooltip>
              </TooltipProvider>
            </DialogTitle>
            <DialogDescription>{selectedCourse?.professor}</DialogDescription>
          </DialogHeader>
          <div className="grid grid-cols-2 gap-4">
            <div className="flex flex-col items-start space-y-1">
              <p className="text-sm font-medium text-neutral-700">교수</p>
              <p className="text-xs text-neutral-500 hover:text-neutral-600">
                {selectedCourse?.professor}
              </p>
            </div>
            <div className="flex flex-col items-start space-y-1">
              <p className="text-sm font-medium text-neutral-700">학점</p>
              <p className="text-xs text-neutral-500 hover:text-neutral-600">
                {selectedCourse?.credit}
              </p>
            </div>
            <div className="flex flex-col items-start space-y-1">
              <p className="text-sm font-medium text-neutral-700">강의동</p>
              <p className="text-xs text-neutral-500 hover:text-neutral-600">
                {selectedCourse?.building} {selectedCourse?.room}
              </p>
            </div>
            <div className="flex flex-col items-start space-y-1">
              <p className="text-sm font-medium text-neutral-700">시간</p>
              <p className="text-xs text-neutral-500 hover:text-neutral-600">
                {selectedCourse?.realLecTime}
              </p>
            </div>
            <div className="flex flex-col items-start space-y-1">
              <p className="text-sm font-medium text-neutral-700">학년</p>
              <p className="text-xs text-neutral-500 hover:text-neutral-600">
                {selectedCourse?.grade}
              </p>
            </div>
          </div>
          <div className="flex w-full flex-row justify-end space-x-2 py-2">
            <Link
              href={`https://sy.knu.ac.kr/sugang?sbjtCd=${selectedCourse?.crseNo}`}
            >
              <Button
                variant="secondary"
                className="flex flex-row items-center space-x-1.5"
              >
                <SquareMousePointer className="h-4 w-4" />
                <p>자세히 보기</p>
              </Button>
            </Link>
            <Button
              disabled={
                !!timeTable.find(
                  (course) => course.lecture.crseNo === selectedCourse?.crseNo,
                )
              }
              className="flex flex-row items-center space-x-1.5"
              onClick={() => {
                if (selectedCourse) {
                  const calculatedTimeUnit = timeStringToTimeUnit(
                    selectedCourse.realLecTime,
                  );

                  const isConflict = hasDuplicatedSchedule(
                    timeTable,
                    calculatedTimeUnit,
                  );

                  if (isConflict) {
                    toast.error("시간표가 겹칩니다.", {
                      position: "top-center",
                      duration: 700,
                    });
                    return;
                  }

                  const timeTableUnit: ITimeTableUnit = {
                    lecture: selectedCourse,
                    timeUnits: calculatedTimeUnit,
                  };

                  setTimeTable((draft) => {
                    draft.push(timeTableUnit);
                  });
                  toast.success("시간표에 추가되었습니다.", {
                    position: "top-center",
                    duration: 700,
                  });
                  console.log(timeTable.concat(timeTableUnit));
                }
              }}
            >
              <AlarmClockPlus className="h-4 w-4" />
              <p>
                {!!timeTable.find(
                  (course) => course.lecture.crseNo === selectedCourse?.crseNo,
                )
                  ? `시간표에 추가됨`
                  : `시간표에 추가`}
              </p>
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
}

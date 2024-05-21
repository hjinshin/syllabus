"use client";

import { getTimeTables } from "@/lib/dummy-data-provider";
import { ISubject } from "@/types/ISubject";
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

const timeTable = getTimeTables();

// export const colums: ColumnDef<ISubject>[] = timeTable.map(subject=>({
//   accessorKey: subject.subjectName,
//   header:
// }))

export const columns: ColumnDef<ISubject>[] = [
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
    accessorKey: "subjectName",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="과목 이름" />
    ),
  },
  {
    accessorKey: "subjectNameEn",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="과목 이름(영어)" />
    ),
  },
  {
    accessorKey: "subnjectCode",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="과목 코드" />
    ),
  },
  {
    accessorKey: "time",
    header: ({ column }) => (
      <DataTableColumnHeader column={column} title="시간" />
    ),
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
                navigator.clipboard.writeText(original.subnjectCode)
              }
            >
              과목코드 복사
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem>View customer</DropdownMenuItem>
            <DropdownMenuItem>View payment details</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      );
    },
  },
];

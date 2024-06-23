"use client";

import {
  Book,
  Calculator,
  Calendar,
  CircleSlash,
  Clock,
  CreditCard,
  School,
  Settings,
  Smile,
  User,
} from "lucide-react";

import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
  CommandSeparator,
  CommandShortcut,
} from "@/components/ui/command";
import { useAtom } from "jotai";
import { showCommandAtom } from "@/atoms";
import { useEffect } from "react";

const Empty = () => (
  <div className="flex h-full w-full flex-row items-center justify-center space-x-2 py-20">
    <CircleSlash className="h-12 w-12 stroke-neutral-500" />
    <p className="text-neutral-500">다른 검색어를 입력해볼까요?</p>
  </div>
);

export function CommandDemo() {
  const [showCommand, setShowCommand] = useAtom(showCommandAtom);

  useEffect(() => {
    document.addEventListener("keydown", (event) => {
      if (event.key === "k" && event.metaKey) {
        setShowCommand(true);
      }
    });

    return () => {
      document.removeEventListener("keydown", (event) => {
        if (event.key === "k" && event.metaKey) {
          setShowCommand(true);
        }
      });
    };
  }, []);

  return (
    <>
      {showCommand === true ? (
        <div
          className="fixed inset-0 flex h-full w-full items-center justify-center bg-neutral-900/20"
          onClick={(e) => {
            setShowCommand(false);
          }}
        >
          <Command
            className="m-8 h-full max-h-96 w-full max-w-3xl rounded-lg border shadow-md"
            onClick={(e) => {
              e.stopPropagation();
            }}
          >
            <CommandInput placeholder="무엇을 검색할까요?" />
            <CommandList>
              <CommandEmpty asChild>
                <Empty />
              </CommandEmpty>
              <CommandGroup heading="최근 찾은 기능">
                <CommandItem className="flex flex-row items-center space-x-1.5">
                  <div className="flex flex-row items-end space-x-1 rounded-sm bg-neutral-100 px-1.5 py-1">
                    <Book className=" h-4 w-4" />
                    <span className="text-xs font-medium text-neutral-500">
                      ITEC0416-003
                    </span>
                  </div>
                  <span>고급웹프로그래밍</span>
                </CommandItem>
                <CommandItem>
                  <Clock className="mr-2 h-4 w-4" />
                  <span>최근 시간표</span>
                </CommandItem>
              </CommandGroup>
              <CommandGroup heading="자주 찾는 기능">
                <CommandItem>
                  <Clock className="mr-2 h-4 w-4" />
                  <span>최근 시간표</span>
                </CommandItem>
                <CommandItem>
                  <School className="mr-2 h-4 w-4" />
                  <span>강의 검색</span>
                </CommandItem>
                <CommandItem>
                  <Smile className="mr-2 h-4 w-4" />
                  <span>강의평</span>
                </CommandItem>
              </CommandGroup>
              <CommandSeparator />
              <CommandGroup heading="빠른 설정">
                <CommandItem>
                  <User className="mr-2 h-4 w-4" />
                  <span>프로필</span>
                  <CommandShortcut>⌘P</CommandShortcut>
                </CommandItem>
                {/* <CommandItem>
                  <CreditCard className="mr-2 h-4 w-4" />
                  <span>Billing</span>
                  <CommandShortcut>⌘B</CommandShortcut>
                </CommandItem> */}
                <CommandItem>
                  <Settings className="mr-2 h-4 w-4" />
                  <span>모든 설정</span>
                  <CommandShortcut>⌘S</CommandShortcut>
                </CommandItem>
              </CommandGroup>
            </CommandList>
          </Command>
        </div>
      ) : null}
    </>
  );
}

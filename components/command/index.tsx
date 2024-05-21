"use client";

import {
  Calculator,
  Calendar,
  CreditCard,
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
            <CommandInput placeholder="Type a command or search..." />
            <CommandList>
              <CommandEmpty>No results found.</CommandEmpty>
              <CommandGroup heading="Suggestions">
                <CommandItem>
                  <Calendar className="mr-2 h-4 w-4" />
                  <span>Calendar</span>
                </CommandItem>
                <CommandItem>
                  <Smile className="mr-2 h-4 w-4" />
                  <span>Search Emoji</span>
                </CommandItem>
                <CommandItem>
                  <Calculator className="mr-2 h-4 w-4" />
                  <span>Calculator</span>
                </CommandItem>
              </CommandGroup>
              <CommandSeparator />
              <CommandGroup heading="Settings">
                <CommandItem>
                  <User className="mr-2 h-4 w-4" />
                  <span>Profile</span>
                  <CommandShortcut>⌘P</CommandShortcut>
                </CommandItem>
                <CommandItem>
                  <CreditCard className="mr-2 h-4 w-4" />
                  <span>Billing</span>
                  <CommandShortcut>⌘B</CommandShortcut>
                </CommandItem>
                <CommandItem>
                  <Settings className="mr-2 h-4 w-4" />
                  <span>Settings</span>
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

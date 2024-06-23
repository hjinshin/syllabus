"use client";

import Link from "next/link";
import { useSession, signIn, signOut } from "next-auth/react";

import { IMenuItem } from "@/types/IMenuItem";
import { CommandIcon, Plus, Squirrel } from "lucide-react";
import { useAtom } from "jotai";
import { showCommandAtom } from "@/atoms";
import Credentials from "./credentials";

const Title = () => {
  return (
    <div className="flex flex-row space-x-2.5 italic">
      <Squirrel className="h-5 w-5 cursor-pointer stroke-neutral-700 hover:stroke-neutral-500" />
    </div>
  );
};

const menuItems: IMenuItem[] = [
  {
    title: "홈",
    href: "/",
  },
  {
    title: "강의 찾기",
    href: "/finder",
  },
];

const MenuItem = ({ item }: { item: IMenuItem }) => {
  return (
    <Link
      className="cursor-pointer text-sm text-neutral-500 hover:text-neutral-700"
      href={item.href}
    >
      {item.title}
    </Link>
  );
};

const InvokeCommand = () => {
  const [, setShowCommand] = useAtom(showCommandAtom);
  return (
    <div
      className="flex cursor-pointer select-none flex-row items-center space-x-1.5 rounded-md p-1.5 hover:bg-neutral-200"
      onClick={() => {
        setShowCommand(true);
      }}
    >
      <p className="text-xs text-neutral-500">명령창</p>
      <div className="flex flex-row items-center space-x-0.5">
        <CommandIcon className="rounded-md bg-neutral-200 stroke-neutral-600 p-1" />
        {/* <Plus className="h-2.5 w-2.5 stroke-neutral-600" /> */}
        <div className="flex h-6 w-6 flex-row items-center justify-center rounded-md bg-neutral-200 p-1 text-xs text-neutral-600">
          K
        </div>
      </div>
    </div>
  );
};

const MenuItems = () => {
  return (
    <div className="flex flex-row items-center space-x-4">
      <InvokeCommand />
      {menuItems.map((item, key) => (
        <MenuItem item={item} key={key} />
      ))}
    </div>
  );
};

const SearchBar = () => {
  return (
    <div className="flex flex-row items-center space-x-2">
      <input
        className="h-7 w-48 rounded-md border border-neutral-300 px-2 text-sm"
        type="text"
        placeholder="검색"
      />
      {/* <button className="flex h-7 w-7 items-center justify-center rounded-md bg-neutral-200 hover:bg-neutral-200">
        <Plus className="h-4 w-4 stroke-neutral-600" />
      </button> */}
    </div>
  );
};

const Header = () => {
  return (
    <nav className="sticky top-0 flex h-12 w-full flex-row items-center justify-between bg-white/70 px-4 backdrop-blur-xl">
      <div className="flex flex-row items-center space-x-4">
        <Title />
        <SearchBar />
      </div>
      <MenuItems />
      {/* <Credentials /> */}
    </nav>
  );
};

export default Header;

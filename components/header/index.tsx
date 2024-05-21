"use client";

import Link from "next/link";

import { IMenuItem } from "@/types/IMenuItem";
import { CommandIcon } from "lucide-react";
import { useAtom } from "jotai";
import { showCommandAtom } from "@/atoms";

const Title = () => {
  return <div className="flex flex-row space-x-2.5 italic">logo</div>;
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
  { title: "계정", href: "/credentials" },
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
  const [showCommand, setShowCommand] = useAtom(showCommandAtom);
  return (
    <div className="flex cursor-pointer select-none flex-row items-center space-x-1 rounded-md p-1.5 hover:bg-neutral-200">
      <CommandIcon
        className="h-4 w-4 stroke-neutral-600"
        onClick={() => {
          setShowCommand(true);
        }}
      />
      <p className="text-xs text-neutral-500">명령 열기</p>
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

const Header = () => {
  return (
    <nav className="sticky top-0 flex h-12 w-full flex-row items-center justify-between border-b bg-white/70 px-4 backdrop-blur-xl">
      <Title />
      <MenuItems />
    </nav>
  );
};

export default Header;

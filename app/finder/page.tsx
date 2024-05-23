"use client";

import { getTimeTables } from "@/lib/dummy-data-provider";
import { DataTable } from "./data-table";
import { columns } from "./columns";
import MapWrapper from "./map-wrapper";
import { showMapPopupAtom } from "@/atoms/map-popup-atoms";
import { useAtom } from "jotai";
import { motion } from "framer-motion";
import { ICardData } from "@/types/ICardData";

interface Props extends ICardData {
  isSelected: boolean;
  history: {
    push: (route: string) => void;
  };
}

const Title = () => (
  <h1 className="text-xl font-semibold text-neutral-700">강의 찾기</h1>
);

const Finder = () => {
  const [showMapPopup, setShowMapPopup] = useAtom(showMapPopupAtom);
  const timeTables = getTimeTables();
  console.log(timeTables);
  return (
    <main className="container h-full w-full max-w-5xl flex-col space-y-4 px-8 py-5">
      <Title />
      <div
        className={`flex w-full h-full flex-row items-start ${showMapPopup === true ? `` : ``}`}
      >
        <DataTable columns={columns} data={timeTables} />
        <MapWrapper />
      </div>
    </main>
  );
};

export default Finder;

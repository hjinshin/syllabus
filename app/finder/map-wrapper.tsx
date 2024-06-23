"use client";

import { mapHeightAtom, mapWidthAtom } from "@/atoms/mapAtoms";
import { useAtom } from "jotai";
import Map from "react-map-gl";
import { twj } from "tw-to-css";
import { motion, useAnimate } from "framer-motion";
import { useEffect, useRef } from "react";
import { Map as MapType } from "mapbox-gl";
import { showMapPopupAtom } from "@/atoms/map-popup-atoms";
import { selectedLectureAtom } from "@/atoms";

// TODO use memoization
const MapWrapper = () => {
  const [mapWidth, setMapWidth] = useAtom(mapWidthAtom);
  const [mapHeight, setMapHeight] = useAtom(mapHeightAtom);
  const [selectedCourse, setSelectedCourse] = useAtom(selectedLectureAtom);
  const [showMapPopup, setShowMapPopup] = useAtom(showMapPopupAtom);
  const [mapWrapperScope, mapWrapperAnimate] = useAnimate<HTMLDivElement>();
  const [mapScope, mapAnimate] = useAnimate<HTMLDivElement>();
  // const mapRef = useRef<MapType>(null);

  const clickHandler = () => {
    setShowMapPopup(!showMapPopup);
  };
  return (
    <motion.div
      className={`relative flex flex-row items-center justify-center ${showMapPopup === true ? `fixed h-screen w-screen` : `relative`}`}
      layout
    >
      <div className="flex h-full w-full flex-col items-center">
        <motion.div
          onClick={clickHandler}
          // ref={mapScope}
          className={`relative h-72 w-72 overflow-hidden rounded-lg bg-white shadow-2xl backdrop-blur-2xl ${showMapPopup === true ? `` : ``}`}
        >
          <div className="absolute z-50 flex w-full select-none flex-row items-center space-x-2">
            <p className="m-2 rounded-lg bg-white/60 px-2 py-1.5 text-base font-semibold text-neutral-700 backdrop-blur-2xl">
              {selectedCourse ? `강의 위치` : `전체보기`}
            </p>
          </div>
          <Map
            // ref={mapRef}
            mapboxAccessToken={`pk.eyJ1IjoicnVieTIwMjQiLCJhIjoiY2x3aXBmb2dwMGNhbDJscDRmdXVnOXVsYyJ9.j2U3vvUAjY3XeH5pr7xU3Q`}
            initialViewState={{
              latitude: 35.88929,
              longitude: 128.6105,
              zoom: 14,
            }}
            interactive={false}
            zoom={selectedCourse ? 16 : 14}
            latitude={
              selectedCourse?.building.buildingLocation.latitude || 35.88929
            }
            longitude={
              selectedCourse?.building.buildingLocation.longitude || 128.6105
            }
            style={{ width: `${360}px`, height: `${360}px` }}
            mapStyle={`mapbox://styles/mapbox/streets-v11`}
            // mapStyle="mapbox://styles/mapbox/light-v11"
          />
          {/* </Card> */}
        </motion.div>
      </div>
    </motion.div>
  );
};

export default MapWrapper;

"use client";

import { mapHeightAtom, mapWidthAtom } from "@/atoms/mapAtoms";
import { useAtom } from "jotai";
import Map from "react-map-gl";
import { twj } from "tw-to-css";
import { motion, useAnimate } from "framer-motion";
import { useEffect, useRef } from "react";
import { Map as MapType } from "mapbox-gl";
import { showMapPopupAtom } from "@/atoms/map-popup-atoms";

const MapWrapper = () => {
  const [mapWidth, setMapWidth] = useAtom(mapWidthAtom);
  const [mapHeight, setMapHeight] = useAtom(mapHeightAtom);
  const [showMapPopup, setShowMapPopup] = useAtom(showMapPopupAtom);
  const [mapWrapperScope, mapWrapperAnimate] = useAnimate<HTMLDivElement>();
  const [mapScope, mapAnimate] = useAnimate<HTMLDivElement>();
  // const mapRef = useRef<MapType>(null);

  //   // useEffect(()=>{
  //   //   //map init....
  //   //   const resizer = new ResizeObserver(_.debounce(() => mapRef.current!.resize(), 100));
  //   //   resizer.observe();

  //   //     return () => {
  //   //     resizer.disconnect();
  //   //     map.remove();
  //   //   };
  // },[])

  useEffect(() => {
    mapWrapperAnimate(mapWrapperScope.current, {
      position: showMapPopup ? "fixed" : "relative",
      top: showMapPopup ? 0 : undefined,
      left: showMapPopup ? 0 : undefined,
      right: showMapPopup ? 0 : undefined,
      bottom: showMapPopup ? 0 : undefined,
      width: showMapPopup ? "100%" : undefined,
      height: showMapPopup ? "100%" : undefined,
      zIndex: showMapPopup ? 50 : undefined,
      backdropFilter: showMapPopup ? "blur(10px)" : undefined,
    });
    mapAnimate(mapScope.current, {
      width: showMapPopup ? "80%" : "480px",
      height: showMapPopup ? "55%" : "480px",
    });
  }, [showMapPopup]);

  const clickHandler = () => {
    setShowMapPopup(!showMapPopup);
  };
  return (
    <div
      ref={mapWrapperScope}
      className="flex flex-row items-center justify-center"
      // animate={{
      //   width: mapWidth,
      //   height: mapHeight,
      // }}
      // className={`cursor-pointer overflow-hidden rounded-lg drop-shadow-2xl transition-all duration-200 ease-in hover:scale-[1.012] ${
      //   showMapPopup === true
      //     ? `absolute left-0 right-0 top-0 z-50 h-full w-full bg-black/50`
      //     : ``
      // }`}
      // className={`flex flex-row items-center justify-center ${showMapPopup ? "fixed bottom-0 left-0 right-0 top-0 z-50 backdrop-blur-2xl" : ""}`}

      // whileHover={{
      //   scale: 1.02,
      //   // transition: { duration: 0.5 },
      // }}
      // animate={{
      //   transition: { duration: 0.5 },
      // }}
    >
      <div
        ref={mapScope}
        className={`h-96 w-96 rounded-lg bg-white shadow-2xl ${showMapPopup === true ? `bg-white/60` : ``}`}
        // initial={{
        //   position: "relative",
        //   // width: "100%",
        //   // height: "100%",
        // }}
        // animate={
        //   showMapPopup
        //     ? {
        //         // position: "fixed",
        //         // top: 0,
        //         // left: 0,
        //         // right: 0,
        //         // bottom: 0,
        //         width: "80%",
        //         height: "55%",
        //         zIndex: 60,
        //         backgroundColor: "white",
        //         backdropFilter: "blur(10px)",
        //       }
        //     : {}
        // }
        onClick={clickHandler}
      >
        {/* <input
        type="range"
        min="0"
        max="1000"
        value={mapWidth}
        onChange={(e) => {
          setMapWidth(parseInt(e.target.value));
          console.log(`mapWidth: ${mapWidth}  `);
        }} */}
        {/* /> */}
        {/* <Card className="absolute "> */}
        {/* <Map
        // ref={mapRef}
        mapboxAccessToken={`pk.eyJ1IjoicnVieTIwMjQiLCJhIjoiY2x3aXBmb2dwMGNhbDJscDRmdXVnOXVsYyJ9.j2U3vvUAjY3XeH5pr7xU3Q`}
        initialViewState={{
          latitude: 35.88929,
          longitude: 128.6105,
          zoom: 14,
        }}
        style={{ width: `${480}px`, height: "480px" }}
        mapStyle="mapbox://styles/mapbox/light-v11"
        // onDrag={(e) => {}}
        // onDragStart={(e) => {
        //   console.log("drag start");
        // }}
      /> */}
        {/* </Card> */}
      </div>
    </div>
  );
};

export default MapWrapper;

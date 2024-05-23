import * as React from "react";
import { motion } from "framer-motion";
import { closeSpring } from "./animations";

export const Image = ({
  id,
  isSelected,
  pointOfInterest = 0,
  backgroundColor,
}: {
  id: string;
  isSelected: boolean;
  pointOfInterest?: number;
  backgroundColor: string;
}) => {
  return (
    <motion.div
      className="card-image-container"
      layout
      style={{ backgroundColor, originX: 0, originY: 0 }}
    >
      <motion.img
        className="card-image"
        src={`images/${id}.jpg`}
        alt=""
        initial={false}
        animate={
          isSelected ? { x: -20, y: -20 } : { x: -pointOfInterest, y: 0 }
        }
        transition={closeSpring}
      />
    </motion.div>
  );
};

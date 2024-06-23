"use client";

import SwaggerUI from "swagger-ui-react";
import "swagger-ui-react/swagger-ui.css";

const Docs = () => (
  <SwaggerUI url="../../api/index.yml" />
);

export default Docs;

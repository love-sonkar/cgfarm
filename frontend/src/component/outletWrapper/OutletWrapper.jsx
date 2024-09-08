import React from "react";
import { Outlet } from "react-router-dom";

const OutletWrapper = () => {
  return (
    <div>
      {/* {header} */}
      <Outlet />
      {/* {footer} */}
    </div>
  );
};

export default OutletWrapper;

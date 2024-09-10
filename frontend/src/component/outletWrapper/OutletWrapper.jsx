import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { FullScreenSpinner } from "../Spinner/Spinner";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";

const OutletWrapper = () => {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    document.querySelector("html").classList.remove("light", "dark");
    document.querySelector("html").classList.add("light");
    setLoading(false);
  }, []);

  return (
    <div className="min-h-[95vh] flex flex-col ">
      <Header />
      <div className="flex-1">
        {loading ? <FullScreenSpinner /> : <Outlet />}
      </div>
      <Footer />
    </div>
  );
};

export default OutletWrapper;

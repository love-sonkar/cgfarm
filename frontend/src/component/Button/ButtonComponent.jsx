import React from "react";
import Spinner from "../Spinner/Spinner";

const ButtonComponent = ({
  children,
  onClick = () => {},
  type,
  disabled,
  className = "",
  ...props
}) => {
  return (
    <button
      {...props}
      disabled={disabled}
      type={type}
      onClick={onClick}
      className={`text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center mr-3 md:mr-0 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 cursor-pointer grid place-content-center disabled:bg-blue-300 disabled:cursor-not-allowed ${className}`}
    >
      {disabled ? <Spinner /> : children}
    </button>
  );
};

export default ButtonComponent;

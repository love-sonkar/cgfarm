import React, { useId, useState } from "react";
import { IoEyeOutline, IoEyeOffOutline } from "react-icons/io5";

const InputBox = ({ type, placeholder, register, title, className = "" }) => {
  const [typeCheck, setTypecheck] = useState(false);
  const check = type === "password";
  const id = useId();
  const handleEye = () => {
    setTypecheck(!typeCheck);
  };
  const styleEye = {
    position: "absolute",
    right: "9px",
    top: "14px",
    cursor: "pointer",
  };
  return (
    <div className="relative">
      <label
        htmlFor={id}
        className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
      >
        {title}
      </label>
      <input
        {...register}
        id={id}
        type={check ? (typeCheck ? "text" : "password") : type}
        className={`bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${className}`}
        placeholder={placeholder}
      />
      {check &&
        (typeCheck ? (
          <IoEyeOutline
            style={styleEye}
            className="text-blue-500 dark:text-white"
            onClick={handleEye}
          />
        ) : (
          <IoEyeOffOutline
            style={styleEye}
            className="text-blue-500 dark:text-white"
            onClick={handleEye}
          />
        ))}
    </div>
  );
};

export default InputBox;

export const TextArea = ({ register, placeholder, className = "", title }) => {
  const id = useId();
  return (
    <>
      <label
        htmlFor={id}
        className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
      >
        {title}
      </label>
      <textarea
        {...register}
        id={id}
        rows="8"
        placeholder={placeholder}
        className={`bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500  resize-none${className}`}
      />
    </>
  );
};

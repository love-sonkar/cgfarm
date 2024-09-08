import { StrictMode, Suspense } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import OutletWrapper from "./component/outletWrapper";
import "./index.css";

const route = createBrowserRouter([
  {
    path: "/",
    element: <OutletWrapper />,
    children: [
      {
        path: "/",
        element: <App />,
      },
    ],
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Suspense fallback={"hi"}>
      <RouterProvider router={route} />
    </Suspense>
  </StrictMode>
);

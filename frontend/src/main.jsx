import { StrictMode, Suspense } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import OutletWrapper from "./component/outletWrapper";
import "./index.css";
import ErrorPage from "./component/ErrorPage/ErrorPage.jsx";
import { FullScreenSpinner } from "./component/Spinner/Spinner.jsx";
import AdminPage from "./component/AdminPage/AdminPage.jsx";

const route = createBrowserRouter([
  {
    path: "/",
    element: <OutletWrapper />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/",
        element: <App />,
      },
      {
        path: "/admin",
        element: <AdminPage />,
      },
    ],
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Suspense fallback={<FullScreenSpinner />}>
      <RouterProvider router={route} />
    </Suspense>
  </StrictMode>
);

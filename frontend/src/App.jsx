import { useEffect, useState } from "react";
import AuthService from "./urlHelper/AuthService";

function App() {
  const [count, setCount] = useState(0);
  const authService = new AuthService();

  useEffect(() => {
    renderData();
  }, []);

  const renderData = async () => {
    try {
      let res = await authService.getHii();
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };

  return <>hi</>;
}

export default App;

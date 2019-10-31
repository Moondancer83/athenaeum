import * as React from "react";

import { FileProvider } from "./contexts/FileContext";
import Header from "./Header/Header";
import Main from "./Main/Main";

export default function App(): React.ReactElement {
  return (
    <FileProvider>
      <Header />
      <Main />
    </FileProvider>
  );
}

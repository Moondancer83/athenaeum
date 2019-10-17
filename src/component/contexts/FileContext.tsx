import * as React from "react";

import FileData from "../../api/FileData";
import initFilesData from "../../api/initFilesData";
import CustomProviderProps from "./CustomProviderProps";

let FileContext: React.Context<FileData[]>;
const { Provider } = (FileContext = React.createContext<FileData[]>([]));

function FileProvider(props: CustomProviderProps) {
  const [files, setFiles] = React.useState<FileData[]>([]);

  React.useEffect(() => {
    if (files.length < 15) {
      setTimeout(() => {
        setFiles(files.concat(initFilesData));
      }, 1000);
    }
  });

  return <Provider value={files}>{props.children}</Provider>;
}

export default FileContext;
export { FileProvider };

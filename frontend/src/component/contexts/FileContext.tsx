import * as React from "react";

import FileData from "../../api/FileData";
import CustomProviderProps from "./CustomProviderProps";
import fileResources from "../../api/fileResources";
import DocumentListConverter from "./DocumentListConverter";

let FileContext: React.Context<FileData[]>;
const { Provider } = (FileContext = React.createContext<FileData[]>([]));

function FileProvider(props: CustomProviderProps): React.ReactElement {
  const [files, setFiles] = React.useState<FileData[]>([]);

  const fetch = () => {
    fileResources.list(e => {
      const files: FileData[] = e._embedded.documents.map(DocumentListConverter);
      setFiles(files);
    });
  };

  React.useEffect(() => {
    if (files.length) {
      setTimeout(fetch, 5000);
    } else {
      fetch();
    }
  });

  return <Provider value={files}>{props.children}</Provider>;
}

export default FileContext;
export { FileProvider };

import * as React from "react";

import MUIDataTable from "mui-datatables";

import FileContext from "../contexts/FileContext";
import { columns, options, transformData } from "./MuiTable.config";
import FileUploader from "../FileUploader";
import FileData from "../../api/FileData";
import fileResources from "../../api/fileResources";

export default function FileList(): React.ReactElement {
  const files = React.useContext(FileContext);

  function uploadHandler(event: any) {
    const rawFile = event.target.files[0];
    console.log("file", rawFile);
    const file: FileData = {
      name: rawFile.name,
      type: rawFile.type,
      size: rawFile.size,
      modifiedAt: new Date(),
      owner: "user",
      data: rawFile
    };

    fileResources.upload(file);
  }

  return (
    <>
      <MUIDataTable title={"Files"} data={transformData(files)} columns={columns} options={options} />
      <FileUploader onChange={uploadHandler} />
    </>
  );
}

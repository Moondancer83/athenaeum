import * as React from "react";

import MUIDataTable from "mui-datatables";

import FileContext from "../contexts/FileContext";
import { columns, options, transformData } from "./MuiTable.config";

export default function FileList(): React.ReactElement {
  const files = React.useContext(FileContext);

  return <MUIDataTable title={"Files"} data={transformData(files)} columns={columns} options={options} />;
}

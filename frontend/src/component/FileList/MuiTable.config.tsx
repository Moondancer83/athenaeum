import * as React from "react";

import DeleteIcon from "@material-ui/icons/Delete";
import GetAppIcon from "@material-ui/icons/GetApp";
import IconButton from "@material-ui/core/IconButton";

import FileData from "../../api/FileData";

const KILO = 1024;
const MEGA: number = KILO * KILO;

export const columns = [
  "ID",
  "NAME",
  "OWNER",
  "MODIFICATION DATE",
  {
    name: "SIZE",
    options: {
      customBodyRender: (value: number) =>
        value >= MEGA
          ? (value / MEGA).toLocaleString("en-US", { maximumFractionDigits: 2 }) + " MB"
          : value >= KILO
          ? (value / KILO).toLocaleString("en-US", { maximumFractionDigits: 2 }) + " KB"
          : value + " B"
    }
  },
  {
    name: "ACTIONS",
    options: {
      customBodyRender: (): React.ReactElement => (
        <>
          <IconButton>
            <GetAppIcon />
          </IconButton>
          <IconButton>
            <DeleteIcon />
          </IconButton>
        </>
      )
    }
  }
];

export const options = {
  pages: 8,
  filterType: "multiselect" as "multiselect",
  selectableRows: "none" as "none",
  fixedHeader: true,
  scroll: "scrollMaxHeight"
};

export const transformData = (files: FileData[]): any[] => {
  return files.map(file => [file.id, file.name, file.owner, file.modifiedAt.toLocaleDateString(), file.size]);
};

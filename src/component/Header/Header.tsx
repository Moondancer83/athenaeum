import * as React from "react";

import AppBar from "@material-ui/core/AppBar";
import IconButton from "@material-ui/core/IconButton";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";

import AccountCircle from "@material-ui/icons/AccountCircle";
import AppsIcon from "@material-ui/icons/Apps";
import HelpOutlineIcon from "@material-ui/icons/HelpOutline";
import MenuBookIcon from "@material-ui/icons/MenuBook";

export default function Header() {
  return (
    <AppBar>
      <Toolbar variant={"dense"}>
        <IconButton color={"inherit"}>
          <MenuBookIcon />
        </IconButton>
        <Typography variant={"h6"} style={{ flexGrow: 1 }}>
          Athenaeum
        </Typography>
        <IconButton color={"inherit"}>
          <HelpOutlineIcon />
        </IconButton>
        <IconButton color={"inherit"}>
          <AccountCircle />
        </IconButton>
        <IconButton color={"inherit"}>
          <AppsIcon />
        </IconButton>
      </Toolbar>
    </AppBar>
  );
}

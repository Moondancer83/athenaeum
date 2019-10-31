import * as React from "react";

import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";

import FileList from "../FileList/FileList";

export default function Main(): React.ReactElement {
  return (
    <main
      style={{
        position: "fixed",
        width: "100%",
        height: "calc(100% - 48px - 32px)",
        marginTop: "48px",
        paddingTop: 16,
        paddingBottom: 16,
        overflowY: "auto"
      }}
    >
      <Container maxWidth={"lg"} style={undefined}>
        <Paper>
          <FileList />
        </Paper>
      </Container>
    </main>
  );
}

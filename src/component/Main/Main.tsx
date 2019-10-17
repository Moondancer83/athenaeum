import * as React from "react";

import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";

import FileList from "../FileList/FileList";

export default function Main() {
    return (
        <Container maxWidth={"lg"} style={{marginTop: 48, paddingTop: 16}}>
            <Paper>
                <FileList/>
            </Paper>
        </Container>
    );
}
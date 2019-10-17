import * as React from "react";

import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";

import DeleteIcon from '@material-ui/icons/Delete';
import GetAppIcon from '@material-ui/icons/GetApp';
import IconButton from "@material-ui/core/IconButton";

import FileContext from "../contexts/FileContext";

export default function FileList() {
    const files = React.useContext(FileContext);

    return (
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>Name</TableCell>
                    <TableCell align={"right"}>Owner</TableCell>
                    <TableCell align={"right"}>Last modified</TableCell>
                    <TableCell align={"right"}>File size</TableCell>
                    <TableCell align={"right"}/>
                </TableRow>
            </TableHead>
            <TableBody>
                {files.map(file => (
                    <TableRow>
                        <TableCell>{file.name}</TableCell>
                        <TableCell align={"right"}>{file.owner}</TableCell>
                        <TableCell align={"right"}>{file.modifiedAt.toLocaleDateString()}</TableCell>
                        <TableCell align={"right"}>{file.size} bytes</TableCell>
                        <TableCell align={"right"}>
                            <IconButton><GetAppIcon/></IconButton>
                            <IconButton><DeleteIcon/></IconButton>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    );
}
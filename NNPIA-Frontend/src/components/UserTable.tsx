import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Switch} from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import UserProps from "../domain/UserProps.ts";
import {useRef} from "react";

interface UserTableProps {
    users : UserProps[],
    tableTitle : string
}

const UserTable = ({users, tableTitle = "Uživatelé"} : UserTableProps) => {
    const changeActivityHandler = (event : React.MouseEvent) => { event.preventDefault(); setStatus(!status); }

    return <>
        <p>{tableTitle}</p>
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>E-mail</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {users.map((user) => {
                        const initialActive = useRef(user.status.active);
                        return <TableRow key={user.guid}>
                            <TableCell>{user.guid}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>{user.status.active ?
                                (<CheckCircleIcon color={"primary"} />) :
                                (<CancelIcon color={"secondary"} />)}</TableCell>
                            <TableCell>
                                <Switch
                                    size={"small"}
                                    color={"primary"}
                                    defaultChecked={initialActive.current}
                                    onClick={() => user.status.setActive(!user.status.active)}></Switch>
                            </TableCell>
                        </TableRow>
                    })}
                </TableBody>
            </Table>
        </TableContainer>
    </>
}

export default UserTable;
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Switch} from "@mui/material";
import UserProps from "../domain/UserProps.ts";

interface UserTableProps {
    users : UserProps[],
    tableTitle : string
}

const UserTable = ({users, tableTitle = "Uživatelé"} : UserTableProps) => {
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
                    {users.map((user) => (
                        <TableRow key={user.guid}>
                            <TableCell>{user.guid}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>{user.active ? "active" : "passive"}</TableCell>
                            <TableCell>
                                <Switch size={"small"} color={"primary"} defaultChecked={user.active}></Switch>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    </>

}

export default UserTable;
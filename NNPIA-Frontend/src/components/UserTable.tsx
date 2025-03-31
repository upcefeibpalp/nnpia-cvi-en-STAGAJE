import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Switch,
    CircularProgress, Button
} from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import UserProps from "../domain/UserProps.ts";
import {useEffect, useState} from "react";
import axios, {AxiosError} from "axios";

interface UserTableProps {
    tableTitle : string
}

const UserTable = ({tableTitle = "Uživatelé"} : UserTableProps) => {
    const [users, setUsers] = useState(new Array<UserProps>())
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState("")

    const toggleActiveStatus = (userId : number) => {
        setUsers((prevUsers: UserProps[]) => prevUsers.map((user) => {
            if (user.id === userId)
                return {...user, active: !user.active};
            return user;
        }));
    }
    const fetchUsers = async () => {
        setLoading(true);
        setError("");

        try {
            const body = await axios.get("http://localhost:9000/api/v1/users");
            setUsers(body.data);
        } catch (error: unknown) {
            if (typeof error === "string") {
                setError(error)
            } else if (error instanceof AxiosError) {
                setError(error.response?.data.message)
            }else if (error instanceof Error) {
                setError(error.message)
            }
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        fetchUsers();
    }, [])

    return <>
        <p>{tableTitle}</p>
        {loading && <CircularProgress />}
        {error && <p style={{color: "red"}}> Chyba: {error}</p>}
        <Button variant={"contained"} onClick={() => fetchUsers()} disabled={loading}>
            {loading ? "Načítání..." : "Aktualizovat"}
        </Button>
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
                        return <TableRow key={user.id}>
                            <TableCell>{user.id}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>{user.active ?
                                (<CheckCircleIcon color={"primary"} />) :
                                (<CancelIcon color={"secondary"} />)}</TableCell>
                            <TableCell>
                                <Switch
                                    size={"small"}
                                    color={"primary"}
                                    checked={user.active}
                                    onChange={() => toggleActiveStatus(user.id)}></Switch>
                            </TableCell>
                        </TableRow>
                    })}
                </TableBody>
            </Table>
        </TableContainer>
    </>
}

export default UserTable;
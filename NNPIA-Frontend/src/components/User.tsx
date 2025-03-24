import "./User.css"
import {useState} from "react";
import {Button} from "@mui/material";
import UserProps from "../domain/UserProps.ts";


const User = ({guid, email, active} : UserProps) => {
    const [status, setStatus] = useState(active)
    const btnClickHandler = (event : React.MouseEvent) => { event.preventDefault(); setStatus(!status); }

    return <>
        <p className={"name"}>User {guid}</p>
        <ul>
            <li>ID: {guid}</li>
            <li>E-mail: {email}</li>
            <li className={"status"}>Active: {status ? "active" : "passive"}</li>
        </ul>
        <Button variant={"contained"} color={status ? "secondary" : "primary"} onClick={btnClickHandler}>{status ? "Zablokovat" : "Aktivovat"}</Button>
    </>
};

export default User;
import "./User.css"
import {Button} from "@mui/material";
import UserProps from "../domain/UserProps.ts";

const User = ({guid, email, status: {active, setActive}} : UserProps) => {
    const btnClickHandler = (event : React.MouseEvent) => { event.preventDefault(); setActive(!active); }

    return <>
        <p className={"name"}>User {guid}</p>
        <ul>
            <li>ID: {guid}</li>
            <li>E-mail: {email}</li>
            <li className={"status"}>Active: {active ? "active" : "passive"}</li>
        </ul>
        <Button variant={"contained"} color={active ? "secondary" : "primary"} onClick={btnClickHandler}>{active ? "Zablokovat" : "Aktivovat"}</Button>
    </>
}

export default User;
import "./App.css";
import UserTable from "./components/UserTable.tsx";
import UserProps from "./domain/UserProps.ts";
import {useState} from "react";


function App() {
    const userStatuses = [useState(true), useState(false), useState(true)]
    const userProps : UserProps[] = [
        {guid: 0, email:"user1@upce.cz", status:{active:userStatuses[0][0], setActive:userStatuses[0][1]}},
        {guid: 1, email:"user2@upce.cz", status:{active:userStatuses[1][0], setActive:userStatuses[1][1]}},
        {guid: 2, email:"user3@upce.cz", status:{active:userStatuses[2][0], setActive:userStatuses[2][1]}},
    ];

  return (
    <>
        <UserTable tableTitle={"Uživatelé"} users={userProps}>
        </UserTable>
    </>
  )
}

export default App

import "./App.css"
import UserTable from "./components/UserTable.tsx"
import UserProps from "./domain/UserProps.ts";


function App() {
    const userProps : UserProps[] = [
        {guid: 0, email:"user1@upce.cz", active:true},
        {guid: 1, email:"user2@upce.cz", active:false},
        {guid: 2, email:"user3@upce.cz", active:true},
    ];
  return (
    <>
        <UserTable tableTitle={"Uživatelé"} users={userProps}>
        </UserTable>
    </>
  )
}

export default App

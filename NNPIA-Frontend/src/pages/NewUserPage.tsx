import UserPropsFull from "../domain/UserPropsFull.ts";
import UserForm from "../components/UserForm.tsx";

const NewUserPage = () => {
    const handleCreateUser = async (newUser : UserPropsFull) => {
        try {
            console.log(newUser);
        } catch (error) {
            console.error("Error creating new user: ", error)
        }
    }

    return <UserForm formTitle={"Přidat nového uživatele"} onSubmit={handleCreateUser} />
}

export default NewUserPage;
interface UserProps {
    guid : number,
    email : string,
    status : {
        active : boolean,
        setActive : any
    }
}

export default UserProps;
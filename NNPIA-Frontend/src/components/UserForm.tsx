import {useForm, Controller} from "react-hook-form";
import { TextField, Button, Switch, FormControlLabel, Box } from "@mui/material";
import UserPropsFull from "../domain/UserPropsFull.ts";

interface UserFormProps {
    formTitle : string
    onSubmit : (data : UserPropsFull) => void
}

const UserForm = ({ formTitle, onSubmit } : UserFormProps) => {
    const {handleSubmit, control, reset, formState: {errors}} = useForm({defaultValues: {email: "", password: "", active: true}});

    const submitHandler = (data: any) => {
        onSubmit({id: 0, ...data});
        reset();    // clear the form
    }

    return <Box component={"form"} onSubmit={handleSubmit(submitHandler)} sx={{display: "flex", flexDirection: "column", gap: 2}}>
        <p>{formTitle}</p>
        <Controller name={"email"} control={control} rules={{required: "Zadejte e-mail!"}} render={({field}) => (
            <TextField label={"E-mail"} type={"email"} variant={"outlined"} error={!!errors.email} helperText={errors.email?.message} {...field} />)}></Controller>
        <Controller name={"password"} control={control} rules={{required: "Zadejte heslo!"}} render={({field}) => (
            <TextField label={"Heslo"} type={"password"} variant={"outlined"} error={!!errors.password} helperText={errors.password?.message} {...field} />)}></Controller>
        <Controller name={"active"} control={control} render={({field}) => (
            <FormControlLabel label={"Status - aktivní"} control={<Switch checked={field.value} onChange={e => field.onChange(e.target.checked)} />} />)}></Controller>
        <Button type={"submit"} variant={"contained"} color={"primary"}>Vytvořit nového uživatele</Button>
    </Box>
}

export default UserForm;
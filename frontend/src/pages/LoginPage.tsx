import {ChangeEvent, FormEvent, useState} from "react";
import {Button, TextField} from "@mui/material";
import "../css/LoginPage.css"
import {useNavigate} from "react-router-dom";

type LoginPageProps = {
    login: (username: string, password: string) => Promise<string>
}

export default function LoginPage(props:LoginPageProps){

    const [username, setUserName] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    const navigate = useNavigate()

    function onUsernameChange(event: ChangeEvent<HTMLInputElement>){
        setUserName(event.target.value)
    }

    function onPasswordChange(event: ChangeEvent<HTMLInputElement>){
        setPassword(event.target.value)
    }


 function onSubmit(event: FormEvent<HTMLFormElement>) {
     event.preventDefault()
        props.login(username, password)
            .then(() => {
                navigate("/books/")
            })

 }

    return (
        <div className={"LoginPage"}>
            <form onSubmit={onSubmit} className={"LoginForm"}>
                <TextField placeholder={ "Username"} value={username} onChange={onUsernameChange} className={"Textfield"}/><br/>
                <TextField type={"password"} placeholder={"Password"} value={password} onChange={onPasswordChange} className={"Textfield"}/><br/>
                <Button type={"submit"} className={"LoginButton"}>Login</Button>
            </form>
        </div>
    )
}
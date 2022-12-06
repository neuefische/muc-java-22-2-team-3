import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {Button, TextField} from "@mui/material";
import "../css/LoginPage.css"

export default function LoginPage(){

    const [username, setUserName] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    function onUsernameChange(event: ChangeEvent<HTMLInputElement>){
        setUserName(event.target.value)
    }

    function onPasswordChange(event: ChangeEvent<HTMLInputElement>){
        setPassword(event.target.value)
    }


 function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
     axios.post("/books/users/login", undefined, {
         auth: {
             username,
             password
         }
     })
         .then(response => response.data)
         .then(data => {console.log(data)})
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
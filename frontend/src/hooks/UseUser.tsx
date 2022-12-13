import {useEffect, useState} from "react";
import axios from "axios";
import { LoginData } from "../model/LoginData";



export default function useUser(){

     const [userName, setUserName] = useState<string>()

    useEffect(()=> {
        axios.get("/users/me")
            .then(response => response.data)
            .then(setUserName)
    }, [])


 function login(username: string, password: string){

     return axios.post("/users/login", undefined, {
         auth: {
             username,
             password
         }
     })
         .then(response => response.data)
         .then(data => {
             setUserName(data)
             return data
         })
 }

 function logout(){
         return axios.post("/users/logout")
             .then(response => response.data)
             .then(data => {
                 setUserName(data)
                 return data
             })
 }
function addUser(newUser: LoginData){
         axios.post("/users/signup/", newUser)
/*             .then(savedUser=>{
                 setUserList((prevState)=>{
                     return [...prevState, savedUser.data]
                 })
             })*/
             .catch(console.error)
}


    return {userName, login, logout, addUser}
}
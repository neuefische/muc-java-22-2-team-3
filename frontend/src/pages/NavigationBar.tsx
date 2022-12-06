import {NavLink, useNavigate} from "react-router-dom";
import "../components/NavigationBar.css";
import {Button} from "@mui/material";

type NavigationBarProps ={
    logout: () => Promise<string>
}

export default function NavigationBar(props: NavigationBarProps) {

    const navigation = useNavigate()

    function logoutButton(){
        props.logout().then(() =>
        navigation("/books/home"))
    }

    return (
        <nav className={"nav-bar"}>
            <NavLink to={"/books/"} className={"site-title"}>BOOK-PARADISE</NavLink>
            <ul >
                <li >
                    <NavLink to={"/books/home"}>Home</NavLink>
                </li>
                <li>
                    <NavLink to={"/books"}>Books</NavLink>
                </li>
                <li >
                    <NavLink to={"/users/me/favoritebooks"}>Favoriten</NavLink>
                </li>
                <li >
                    <NavLink to={"/users/login"}>Login</NavLink>
                </li><li >
                    <Button onClick={logoutButton} >Logout</Button>
                </li>

            </ul>
        </nav>
    )
}
import {NavLink} from "react-router-dom";
import "../components/NavigationBar.css";

export default function NavigationBar() {

    return (
        <nav className={"nav-bar"}>
            <ul >

                <li >
            <NavLink to={"books/home"} >Home</NavLink>
                </li>
                <li >
            <NavLink to={"books/bookList"} >Books</NavLink>
            </li>
            </ul>
        </nav>
    )
}
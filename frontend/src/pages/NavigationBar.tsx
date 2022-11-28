import {NavLink} from "react-router-dom";
import "../components/NavigationBar.css";

export default function NavigationBar() {

    return (
        <nav className={"nav-bar"}>
            <a href={"/books/"} className={"site-title"}>BOOK-PARADISE</a>
            <ul >
                <li >
                    <a href={"/books/home"} >Home</a>
                </li>
                <li >
                    <a href={"/books"} >Books</a>
                </li>
            </ul>
        </nav>
    )
}
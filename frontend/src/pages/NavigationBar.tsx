import {Link, NavLink} from "react-router-dom";
import "../components/NavigationBar.css";

export default function NavigationBar() {

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
                    <NavLink to={"/books/favoriten"}>Favoriten</NavLink>
                </li>
            </ul>
        </nav>
    )
}
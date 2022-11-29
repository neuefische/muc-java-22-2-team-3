import {Link, NavLink} from "react-router-dom";
import "../components/NavigationBar.css";

export default function NavigationBar() {

    return (
        <nav className={"nav-bar"}>
            <Link to={"/books/"} className={"site-title"}>BOOK-PARADISE</Link>
            <ul >
                <li >
                    <Link to={"/books/home"} >Home</Link>
                </li>
                <li >
                    <Link to={"/books"} >Books</Link>
                </li>
            </ul>
        </nav>
    )
}
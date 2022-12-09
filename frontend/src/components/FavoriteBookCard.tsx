import {BookData} from "../model/BookData";
import React, {useEffect, useState} from "react";
import "../css/BookCard.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";


type FavoriteBookCardProps={
    book:BookData,
    deleteBook(id: string): void
    // updateStatus(id: string): void
}

export default function FavoriteBookCard(props: FavoriteBookCardProps){
    const [bookStatus, setBookStatus] = useState("")

    useEffect(() => {
        getStatus()
    },[])

    const navigate = useNavigate()

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/" + props.book.id!)
    }

/*    function updateBookStatus(){
        props.updateStatus(id!)
    }*/


    function getStatus(){
        axios.get("/users/me/favoritebooks/" + props.book.id!)
            .then(response => response.data)
            .then(setBookStatus)
    }

    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <p>{bookStatus}</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
            {/*onClick = {updateBookStatus}*/}
            <button className={"Favorites"}>Status update</button>
        </div>

    )
}
import {BookData} from "../model/BookData";
import React from "react";
import "../css/BookCard.css"
import {useNavigate} from "react-router-dom";

type FavoriteBookCardProps={
    book:BookData,
    deleteBook(id: string): void
    updateStatus(id: string): void
}

export default function FavoriteBookCard(props: FavoriteBookCardProps){
    const navigate = useNavigate()

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/" + props.book.id)
    }

    function updateBookStatus(){
        props.updateStatus(props.book.id!)
    }


    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
            <button onClick={updateBookStatus} className={"Favorites"}>Status update</button>
        </div>

    )
}
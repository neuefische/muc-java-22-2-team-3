import {BookData} from "../model/BookData";
import React from "react";
import "../css/BookCard.css"
import {useNavigate} from "react-router-dom";
type BookCardProps={
    book:BookData,
    deleteBook(id: string): void
}
export default function BookCard(props:BookCardProps){
    const navigate = useNavigate()

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/search/" + props.book.id)
    }

    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
        </div>

    )
}
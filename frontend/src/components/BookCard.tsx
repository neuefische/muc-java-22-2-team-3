import {BookData} from "../model/BookData";
import React from "react";
import "../css/BookCard.css"
type BookCardProps={
    book:BookData,
    deleteBook(id: string): void
    getBookByIDFunction: (id: string ) => void

}
export default function BookCard(props:BookCardProps){

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookIDOnClick(){
        props.getBookByIDFunction(props.book.id!)
    }

    return(
        <div className={"BookClass"}>
            <p>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <button onClick={deleteBook}>Delete</button>
            <button onClick={getBookIDOnClick}>Details</button>
        </div>

    )
}
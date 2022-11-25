import {BookData} from "../model/BookData";
import React from "react";
type BookCardProps={
    book:BookData
    getBookByIDFunction: (id: string ) => void
    deleteBook(id: string): void

}
export default function BookCard(props:BookCardProps){

    function getBookIDOnClick(){
        props.getBookByIDFunction(props.book.id!)
    }
    function deleteBook(){
        props.deleteBook(props.book.id!)
    }

    return(
        <div>
            {props.book.id}
            {props.book.author}
            {props.book.isbn}
            {props.book.title}
            <button onClick={getBookIDOnClick}>Details</button>
            <button onClick={deleteBook}>Delete</button>
        </div>

    )
}
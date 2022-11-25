import {BookData} from "../model/BookData";
import React from "react";
type BookCardProps={
    book:BookData,
    deleteBook(id: string): void

}
export default function BookCard(props:BookCardProps){

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }

    return(
        <div>
            {props.book.id}
            {props.book.author}
            {props.book.isbn}
            {props.book.title}
            <button onClick={deleteBook}>Delete</button>
        </div>

    )
}
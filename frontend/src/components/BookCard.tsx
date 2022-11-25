import {BookData} from "../model/BookData";
import React from "react";
type BookCardProps={
    book:BookData

}
export default function BookCard(props:BookCardProps){


    return(
        <div>
            {props.book.id}
            {props.book.author}
            {props.book.isbn}
            {props.book.title}
        </div>

    )
}
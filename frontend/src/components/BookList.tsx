import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[]
}
export default function BookList(props:BookListProps){
    const getBookList=
         props.bookList.map((book)=>{
            return <BookCard book={book}></BookCard>})

    return(
        <div>
            {getBookList}

        </div>
    )
}
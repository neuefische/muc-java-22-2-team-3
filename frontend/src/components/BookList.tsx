import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[],
    deleteBook(id: string): void

}
export default function BookList(props:BookListProps){
    const getBookList=
         props.bookList.map((book)=>{
                 return <BookCard book={book} deleteBook={props.deleteBook} key={book.id} />;}
         )
    return(
        <div>
            {getBookList}
        </div>
    )
}
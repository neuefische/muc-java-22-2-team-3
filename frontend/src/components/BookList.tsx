import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[],
    deleteBook(id: string): void
    addBookToFavorites(bookId: string): void

}
export default function BookList(props:BookListProps){
    const getBookList=
         props.bookList.map((book)=>{
                 return <BookCard book={book} deleteBook={props.deleteBook} addBookToFavorites={props.addBookToFavorites} key={book.id} />;}
         )
    return(
        <div>
            {getBookList}
        </div>
    )
}
import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[],
    deleteBook(id: string): void
}
export default function BookList(props:BookListProps){
    const getBookList = () => {
        return props.bookList.map((book) => {
                if (book.id) {
                    return <BookCard book={book} deleteBook={props.deleteBook} key={book.id}></BookCard>;
                }
                else return null
            }
        )
    }

    return(
        <div>
            {getBookList()}
        </div>
    )
}
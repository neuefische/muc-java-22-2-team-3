import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[]
    getBookByIDInBookList: (id: string) => void
    bookList:BookData[],
    deleteBook(id: string): void
}
export default function BookList(props:BookListProps){
    const getBookList=
         props.bookList.map((book)=>{
             if(book.id){
                 return <BookCard book={book} getBookByIDFunction={props.getBookByIDInBookList} key={book.id} ></BookCard > ;}
             }
         )
            return <BookCard book={book} deleteBook={props.deleteBook}></BookCard>})

    return(
        <div>
            {getBookList}
        </div>
    )
}
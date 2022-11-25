import React from "react";
import {BookData} from "../model/BookData";
import BookCard from "./BookCard";
type BookListProps={
    bookList:BookData[],
    deleteBook(id: string): void
    getBookByIDInBookList: (id: string) => void

}
export default function BookList(props:BookListProps){
    const getBookList=
         props.bookList.map((book)=>{
        
             if(book.id){
                 return <BookCard book={book} getBookByIDFunction={props.getBookByIDInBookList} deleteBook={props.deleteBook} key={book.id} ></BookCard > ;}
             }
         )
           

    return(
        <div>
            {getBookList}
        </div>
    )
}
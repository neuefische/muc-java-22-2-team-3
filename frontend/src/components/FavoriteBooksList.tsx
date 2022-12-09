import React from "react";
import {BookData} from "../model/BookData";
import FavoriteBookCard from "./FavoriteBookCard";
type FavoriteBookListProps={
    bookList:BookData[],
    deleteBook(id: string): void
    // updateStatus(bookId: string): void

}
export default function FavoriteBookList(props: FavoriteBookListProps){
    const getBookList=
        props.bookList.map((book)=>{
            return <FavoriteBookCard book={book}
                                     deleteBook={props.deleteBook}
                                     key={book.id}
                                     // updateStatus={props.updateStatus}
                                    />
        }
        )
    return(
        <div>
            {getBookList}
        </div>
    )
}
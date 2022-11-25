import BookList from "./BookList";
import {useEffect, useState} from "react";
import {BookData} from "../model/BookData";
import axios from "axios";

export default function BookApp() {
const [bookList,setBookList]=useState<BookData[]>([])

    useEffect(() => {
        getAllBooks()
    },[])

 function getAllBooks() {
    axios.get("books").then(response=>response.data).then(data=>setBookList(data))

 }

    const filteredBook = (id: string): BookData[] => {
        return bookList.filter(book => {
            return book.id === id
        })
    }

 function getBookByID(id: string){
    if(id){
        setBookList(filteredBook(id))
    }
 }

    return (
        <>
            <BookList bookList={bookList} getBookByIDInBookList={getBookByID}/>
        </>

    )
}
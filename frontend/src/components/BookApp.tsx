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
    return (
        <BookList bookList={bookList}/>
    )
}
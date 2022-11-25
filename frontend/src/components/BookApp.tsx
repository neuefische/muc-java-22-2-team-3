import BookList from "./BookList";
import {useEffect, useState} from "react";
import {BookData} from "../model/BookData";
import axios from "axios";
import AddBook from "./AddBook";

export default function BookApp() {
const [bookList,setBookList]=useState<BookData[]>([])

    useEffect(() => {
        getAllBooks()
    },[])

 function getAllBooks() {
    axios.get("books").then(response=>response.data).then(data=>setBookList(data))

 }
 function addBook(newBook: BookData){
    axios.post("books", newBook).then(savedBook =>{
        setBookList((prevState)=>{
            return [...prevState, savedBook.data]
        })
    })
        .catch(console.error)
 }
 
 function deleteBook(deletedId: string){
    axios.delete("books" + deletedId)
        .then(()=>{
            const newList = bookList.filter((book: BookData)=>
            book.id!==deletedId)
            setBookList(newList)
        })
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
 

      


        <section>
            <h1>Bibliothek</h1>
            <BookList bookList={bookList} deleteBook={deleteBook} getBookByIDInBookList={getBookByID}/>
            
            <AddBook addBook={addBook} />

        </section>

      

    )
}
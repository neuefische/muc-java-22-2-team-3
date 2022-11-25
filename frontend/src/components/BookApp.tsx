import BookList from "./BookList";
import {useEffect, useState} from "react";
import {BookData} from "../model/BookData";
import axios from "axios";
import AddBook from "./AddBook";
import SearchForTitle from "./SearchForTitle";
import SearchForISBN from "./SearchForISBN";

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

 function getBookByKeyword(keyword: string){
    setBookList(filteredBookByTitle(keyword))
 }

 const filteredBookByTitle = (word: string) => {
    return bookList.filter(book => {
        return book.title.toLowerCase().includes(word.toLowerCase())
    })
 }

    function getBookByISBN(isbn: string){
        setBookList(filteredBookByISBN(isbn))
    }

    const filteredBookByISBN = (isbn: string) => {
        return bookList.filter(book => {
            return book.isbn.toLowerCase().includes(isbn.toLowerCase())
        })
    }

    return (

        <section>
            <h1>Bibliothek</h1>
            <BookList bookList={bookList} deleteBook={deleteBook} getBookByIDInBookList={getBookByID}/>
            <SearchForISBN inputFieldValue={getBookByISBN}/>
            <SearchForTitle inputFieldValue={getBookByKeyword}/>

            <AddBook addBook={addBook} />

        </section>

    )
}
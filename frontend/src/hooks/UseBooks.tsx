import {useEffect, useState} from "react";
import {BookData} from "../model/BookData";
import axios from "axios";

type UseBooksReturn = {
    bookList: BookData[],
    addBook: (book: BookData) => void,
    deleteBook: (id: string) => void,
    getBookByID: (id: string) => void,
    getBookByISBN: (isbn: string) => void,
    getBookByAuthor: (name: string) => void,
    getBookByKeyword: (keyword: string) => void
}

export default function useBooks(): UseBooksReturn{
    const [bookList,setBookList]=useState<BookData[]>([])

    useEffect(() => {
        getAllBooks()
    },[])

    function getAllBooks() {
        axios.get("/books").then(response=>response.data).then(data=>setBookList(data))
    }

    function addBook(newBook: BookData){
        axios.post("/books", newBook).then(savedBook =>{
            setBookList((prevState)=>{
                return [...prevState, savedBook.data]
            })
        })
            .catch(console.error)
    }

    function deleteBook(deletedId: string){
        axios.delete("/books/" + deletedId)
            .then(()=>{
                const newList = bookList.filter((book: BookData)=>
                    book.id!==deletedId)
                setBookList(newList)
            })
            .catch(console.error)
    }

    function getBookByID(id: string){
        axios.get("/books/" + id)
            .then(response => response.data)
            .then(data => setBookList(data))
/*            .then(() => {
                const filteredBook = bookList.filter(book => {
                        return book.id === id
                    })
                if(id){
                    setBookList(filteredBook)
                }
            })*/
            .catch(console.error)

    }

    function getBookByKeyword(keyword: string){
        axios.get("/books/by-keyword/?keyword=" + keyword)
            .then(response => response.data)
            .then(data => setBookList(data))
/*            .then(() => {
                const filteredBookByTitle = bookList.filter(book => {
                        return book.title.toLowerCase().includes(keyword.toLowerCase())
                    })
                setBookList(filteredBookByTitle)
            })*/
            .catch(console.error)

    }

    function getBookByISBN(isbn: string){
                const filteredBookByISBN = bookList.filter(book => {
                        return book.isbn.toLowerCase().includes(isbn.toLowerCase())
                    })
                setBookList(filteredBookByISBN)

/*        axios.get("/books/by-isbn/?isbn=" + isbn)
            .then(response => response.data)
            .then(data => setBookList(data))
            .catch(console.error)*/

    }

    function getBookByAuthor(name: string){
        axios.get("/books/by-author/?name=" + name)
            .then(response => response.data)
            .then(data => setBookList(data))
/*            .then(() => {
                const filteredBookByAuthor =
                    bookList.filter(book => {
                        return book.author.toLowerCase().includes(name.toLowerCase())
                    })
                setBookList(filteredBookByAuthor)
            })*/
            .catch(console.error)

    }

    return {bookList, addBook, deleteBook, getBookByID, getBookByISBN, getBookByAuthor, getBookByKeyword}
}
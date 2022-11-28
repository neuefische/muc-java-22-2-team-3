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

export default function UseBooks(): UseBooksReturn{
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

    function getBookByAuthor(name: string){
        setBookList(filteredBookByAuthor(name))
    }

    const filteredBookByAuthor = (name: string) => {
        return bookList.filter(book => {
            return book.author.toLowerCase().includes(name.toLowerCase())
        })
    }

    return {bookList, addBook, deleteBook, getBookByID, getBookByISBN, getBookByAuthor, getBookByKeyword}
}
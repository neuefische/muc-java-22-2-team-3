import {useEffect, useState} from "react";
import {BookData} from "../model/BookData";
import axios from "axios";

type UseFavoriteBooksReturn = {
    bookList: BookData[],
    deleteBook: (id: string) => void,
    getBookByISBN: (isbn: string) => void,
    getBookByAuthor: (name: string) => void,
    getBookByKeyword: (keyword: string) => void,
    // updateBookStatus: (bookId: string) => void
    // getBookStatus: (bookId: string) => string
}

export default function useFavoriteBooks(): UseFavoriteBooksReturn{
    const [bookList,setBookList]=useState<BookData[]>([])


    useEffect(() => {
        getAllBooks()
    },[])

    function getAllBooks() {
        axios.get("/users/me/favoritebooks/").then(response=>response.data).then(data=> {
            console.log(data)
            setBookList(data)
        })
    }

    function deleteBook(deletedId: string){
        axios.delete("/users/me/favoritebooks/" + deletedId)
            .then(()=>{
                const newList = bookList.filter((book: BookData)=>
                    book.id!==deletedId)
                setBookList(newList)
            })
            .catch(console.error)
    }


    function getBookByKeyword(keyword: string){
        axios.get("/users/me/favoritebooks/search/?title=" + keyword)
            .then(response => response.data)
            .then(data => {
                setBookList(data)
            })
            .catch(console.error)

    }

    function getBookByISBN(isbn: string){
        axios.get("/users/me/favoritebooks/search/?isbn=" + isbn)
            .then(response => response.data)
            .then(data => setBookList(data))
            .catch(console.error)

    }

    function getBookByAuthor(name: string){
        axios.get("/users/me/favoritebooks/search/?author=" + name)
            .then(response => response.data)
            .then(data => setBookList(data))
            .catch(console.error)

    }

    function updateBookStatus(bookId: string){
        axios.put("/me/favoritebooks/update/" + bookId)
            .then(response => response.data)
            .then(data => setBookList(data))
            .catch(console.error)

    }

    function getBookStatus(bookId: string){

        axios.get("/users/me/favoritebooks/"+ bookId)
            .then(response => response.data)
            .then(data => {
                return data
            })
            .catch(console.error)
    }

    return {
        bookList,
        deleteBook,
        getBookByISBN,
        getBookByAuthor,
        getBookByKeyword,
        // updateBookStatus,
        // getBookStatus
    }
}
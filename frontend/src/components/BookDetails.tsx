import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {BookData} from "../model/BookData";
import axios from "axios";

export default function BookDetails(){

    const id = useParams().id

    const [book, setBook] = useState<BookData>()

    useEffect(() => {
        if(id){
            getBookDetailsByID(id)
        }
    },[])

    function getBookDetailsByID(id: string){
        axios.get("books/{id}" + id)
            .then(response => response.data)
            .then(data => {
                setBook(data)
            })
    }

    return(
        <>{book?
            <section>
            <h1>{book.title}</h1>
        Author: {book.author}<br/>
        ISBN: {book.isbn}
            </section>
            : <p>Loading...</p>
        }
        </>
    )
}
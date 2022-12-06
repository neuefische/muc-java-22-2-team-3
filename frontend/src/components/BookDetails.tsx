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
        //eslint-disable-next-line
    },[])

    function getBookDetailsByID(id: string){
        axios.get("/books/search/" + id)
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
                ISBN: {book.isbn}<br/>
                Description: {book.description}
            </section>
            : <p>Loading...</p>
        }
        </>
    )
}
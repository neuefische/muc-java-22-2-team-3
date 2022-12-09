import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {BookData} from "../model/BookData";
import axios from "axios";
import { Button } from "@mui/material";



export default function BookDetails(){



    const params = useParams()

    const id: string | undefined = params.id

    const [book, setBook] = useState<BookData>()


    useEffect(() => {
        if(id){
            getBookDetailsByID()
        }
        //eslint-disable-next-line
    },[])

    function getBookDetailsByID(){

        axios.get("/books/" + id)
            .then(response => {
                console.log(response.data)
                setBook(response.data)
            })
    }

return(
        <div>{book?
            <section>
                <h1>{book.title}</h1>
                Author: {book.author}<br/>
                ISBN: {book.isbn}<br/>
                Description: {book.description}<br /><br />


            </section>
            : <p>Loading...</p>
        }
        </div>
    )
}
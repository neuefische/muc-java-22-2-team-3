import React, {useEffect, useState} from "react";
import {useParams, useNavigate} from "react-router-dom";
import {BookData} from "../model/BookData";
import axios from "axios";

export default function BookDetails() {

    const params = useParams()

    const id: string | undefined = params.id

    const [book, setBook] = useState<BookData>()


    const navigate = useNavigate();


    useEffect(() => {
        if (id) {
            getBookDetailsByID()
        }
        //eslint-disable-next-line
    }, [])

    function getBookDetailsByID() {

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
            <button onClick={() => navigate(-1)}>Go back</button>
        </div>
    )
}
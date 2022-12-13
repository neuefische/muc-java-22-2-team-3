import {BookData} from "../model/BookData";
import React, {useEffect, useState} from "react";
import "../css/BookCard.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";


type FavoriteBookCardProps={
    book:BookData,
    deleteBook(id: string): void
    updateStatus(id: string): Promise<string>
}

export default function FavoriteBookCard(props: FavoriteBookCardProps){
    const [bookStatus, setBookStatus] = useState("To read")

    useEffect(() => {
        getStatus()
        //eslint-disable-next-line
    },[])

    const navigate = useNavigate()

    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/" + props.book.id!)
    }

    function updateBookStatus(){
            props.updateStatus(props.book.id!)
                .then(data => {
                    setBookStatus(data)
                })
    }

    function getStatus(){
        axios.get("/users/me/favoritebooks/status/" + props.book.id!)
            .then(response => response.data)
            .then(data => setBookStatus(data))

    }

    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <p>{bookStatus}</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
            <button onClick = {updateBookStatus} className={"Favorites"} >Status update</button>
        </div>

    )
}
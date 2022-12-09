import {BookData} from "../model/BookData";
import React from "react";
import "../css/BookCard.css"
import {useNavigate} from "react-router-dom";
import { Button } from "@mui/material";
import {useState} from "react";
type BookCardProps={
    book:BookData,
    deleteBook(id: string): void
    onSubmitAddToFavorites(id:string): void
}
export default function BookCard(props:BookCardProps){
    const navigate = useNavigate()


    function deleteBook(){
        props.deleteBook(props.book.id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/" + props.book.id)
    }
    function onSubmitAddToFavorites(){
        return props.onSubmitAddToFavorites(props.book.id!)

    }

    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
            <Button onClick={onSubmitAddToFavorites}>To favorites</Button>
        </div>

    )
}
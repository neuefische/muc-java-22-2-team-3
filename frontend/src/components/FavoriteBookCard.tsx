import {BookData} from "../model/BookData";
import React from "react";
import "../css/BookCard.css"
import {useNavigate, useParams} from "react-router-dom";


type FavoriteBookCardProps={
    book:BookData,
    // bookStatus(id: string): string
    deleteBook(id: string): void
    // updateStatus(id: string): void
}

export default function FavoriteBookCard(props: FavoriteBookCardProps){

    const params = useParams()

    const id: string | undefined = params.id

    const navigate = useNavigate()

    function deleteBook(){
        props.deleteBook(id!)
    }
    function getBookByIDOnClick(){
        navigate("/books/" + id)
    }

/*    function updateBookStatus(){
        props.updateStatus(id!)
    }*/



    return(
        <div className={"BookClass"}>
            <p className={"Title"}>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.isbn}</p>
            <p>Status</p>
            <button onClick={getBookByIDOnClick} className={"Details"} >Details</button>
            <button onClick={deleteBook} className={"Delete"}>Delete</button>
            {/*onClick = {updateBookStatus}*/}
            <button className={"Favorites"}>Status update</button>
        </div>

    )
}
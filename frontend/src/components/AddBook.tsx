import {BookData} from '../model/BookData';
import React, {ChangeEvent, useState} from 'react';

type AddBookProps = {
    addBook(newBook: BookData): void
}

export default function AddBook(props: AddBookProps){
    
    const [newBookTitle, setNewBookTitle] = useState<string>("")
    const [newBookAuthor, setNewBookAuthor] = useState<string>("")
    const [newBookIsbn, setNewBookIsbn] = useState<string>("")
    
    const newBookTitleInput = (event:ChangeEvent<HTMLInputElement>)=>{
        setNewBookTitle(event.target.value)
    }
    const newBookAuthorInput = (event:ChangeEvent<HTMLInputElement>)=>{
        setNewBookAuthor(event.target.value)
    }
    const newBookIsbnInput = (event:ChangeEvent<HTMLInputElement>)=>{
        setNewBookIsbn(event.target.value)
    }
    function addBook(){
        const newBook: BookData = {
            title: newBookTitle,
            author: newBookAuthor,
            isbn: newBookIsbn
        }
        props.addBook(newBook)
    }
    
    return(
        <section>
            <h1>New Book:</h1>
            Title: <input onChange={newBookTitleInput}/>
            Author: <input onChange={newBookAuthorInput}/>
            ISBN: <input onChange={newBookIsbnInput}/>
            <button onClick={addBook}>Add</button>
        </section>
            
    )
    
}

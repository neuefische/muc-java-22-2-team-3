import {BookData} from '../model/BookData';
import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';

type AddBookProps = {
    addBook(newBook: BookData): void
}

export default function AddBook(props: AddBookProps){
    const emptyInput: BookData = {
        "author": "",
        "title": "",
        "isbn": ""
    }
    
    const [inputValue, setInputValue] = useState(emptyInput)

    useEffect(()=>{

    }, [inputValue])

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault()
        props.addBook(inputValue)
        setInputValue(emptyInput)
    }

    function handleOnClick(event: ChangeEvent<HTMLInputElement>){
        const fieldName = event.target.name
        const fieldValue = event.target.value

        setInputValue(prevState => ({
            ...prevState, [fieldName]: fieldValue
        }))
    }
    
    return(
        <form onSubmit={handleSubmit}>
            <h1>New Book:</h1>
            Title: <input type={"text"} onChange={handleOnClick} name={"title"} value={inputValue.title}/>
            Author: <input type={"text"} onChange={handleOnClick} name={"author"} value={inputValue.author}/>
            ISBN: <input type={"text"} onChange={handleOnClick} name={"isbn"} value={inputValue.isbn}/>
            <button>Add</button>
        </form>
            
    )
    
}

import React, {ChangeEvent, FormEvent, useState} from "react";

type SearchForAuthorProps = {
    inputFieldValue: (keyword: string) => void
}

export default function SearchForAuthor(props: SearchForAuthorProps){

    const [inputValue, setInputValue] = useState("")

    function handleSubmit(event: FormEvent){
        event.preventDefault()
        props.inputFieldValue(inputValue)
        setInputValue("")
    }

    function handleOnChange(event: ChangeEvent<HTMLInputElement>){
        setInputValue(event.target.value)
    }

    return(
        <form onSubmit={handleSubmit}>
            Author name:
            <input onChange={handleOnChange} value={inputValue}/>
            <button>search</button>
        </form>
    )
}
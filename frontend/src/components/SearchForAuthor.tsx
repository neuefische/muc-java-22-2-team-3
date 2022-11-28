import React, {ChangeEvent, useState} from "react";

type SearchForAuthorProps = {
    inputFieldValue: (keyword: string) => void
}

export default function SearchForAuthor(props: SearchForAuthorProps){

    const [inputValue, setInputValue] = useState("")

    function getInputFieldValue(event: ChangeEvent<HTMLInputElement>){
        setInputValue(event.target.value)
    }

    function setInputFieldValue(){
        props.inputFieldValue(inputValue)
    }

    return(
        <>
            Author name:
            <input onChange={getInputFieldValue} value={""}/>
            <button onClick={setInputFieldValue}>search</button>
        </>
    )
}
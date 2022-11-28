import React, {ChangeEvent, useState} from "react";

type SearchForISBNProps = {
    inputFieldValue: (isbn: string) => void
}

export default function SearchForISBN(props: SearchForISBNProps){

    const [inputValue, setInputValue] = useState("")

    function getInputFieldValue(event: ChangeEvent<HTMLInputElement>){
        setInputValue(event.target.value)
    }

    function setInputFieldValue(){
        props.inputFieldValue(inputValue)
    }

    return(
        <>
            ISBN:
            <input onChange={getInputFieldValue} value={inputValue}/>
            <button onClick={setInputFieldValue}>search</button>
        </>
    )
}
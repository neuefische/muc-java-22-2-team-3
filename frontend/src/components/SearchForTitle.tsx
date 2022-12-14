import React, {ChangeEvent, useState} from "react";

type SearchForTitleProps = {
    inputFieldValue: (keyword: string) => void
}

export default function SearchForTitle(props: SearchForTitleProps){

    const [inputValue, setInputValue] = useState("")

    function getInputFieldValue(event: ChangeEvent<HTMLInputElement>){
        setInputValue(event.target.value)
    }

    function setInputFieldValue(){
        props.inputFieldValue(inputValue)
        setInputValue("")
    }

    return(
        <>
            Stichwort:
            <input onChange={getInputFieldValue} value={inputValue}/>
            <button onClick={setInputFieldValue}>search</button>
        </>
    )
}
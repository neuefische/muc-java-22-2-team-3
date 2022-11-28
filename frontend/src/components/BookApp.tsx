import BookList from "./BookList";
import AddBook from "./AddBook";
import SearchForTitle from "./SearchForTitle";
import SearchForISBN from "./SearchForISBN";
import SearchForAuthor from "./SearchForAuthor";
import UseBooks from "../hooks/UseBooks";

export default function BookApp() {

    const {bookList, addBook, deleteBook, getBookByID, getBookByISBN, getBookByAuthor, getBookByKeyword} = UseBooks();

    return (

        <section>
            <h1>Bibliothek</h1>
            <BookList bookList={bookList} deleteBook={deleteBook} getBookByIDInBookList={getBookByID}/><br/>

            <SearchForTitle inputFieldValue={getBookByKeyword}/><br/>
            <SearchForAuthor inputFieldValue={getBookByAuthor}/><br/>
            <SearchForISBN inputFieldValue={getBookByISBN}/><br/>
            <AddBook addBook={addBook} />
        </section>

    )
}
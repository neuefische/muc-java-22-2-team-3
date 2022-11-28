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
            <table>
                <thead>
                <tr>
                    <th>Titel</th>
                    <th>Author</th>
                    <th>ISBN</th>
                </tr>
                </thead>
            </table>
            <BookList bookList={bookList} deleteBook={deleteBook} getBookByIDInBookList={getBookByID}/><br/>
            <div className={"InputFields"}>
            <SearchForTitle inputFieldValue={getBookByKeyword}/><br/>
            <SearchForAuthor inputFieldValue={getBookByAuthor}/><br/>
            <SearchForISBN inputFieldValue={getBookByISBN}/><br/>
            <AddBook addBook={addBook} />
            </div>
        </section>

    )
}
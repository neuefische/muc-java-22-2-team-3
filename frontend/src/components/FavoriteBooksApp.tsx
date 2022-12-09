import useFavoriteBooks from "../hooks/UseFavoriteBooks";
import SearchForTitle from "./SearchForTitle";
import SearchForAuthor from "./SearchForAuthor";
import SearchForISBN from "./SearchForISBN";
import FavoriteBooksList from "./FavoriteBooksList";


export default function FavoriteBooksApp() {
    //, updateBookStatus, getBookStatus
    const {bookList, deleteBook, getBookByISBN, getBookByAuthor, getBookByKeyword} = useFavoriteBooks();

    return (

        <section>
            <h1>Favoriten</h1>
            <table>
                <thead>
                <tr>
                    <th>Titel</th>
                    <th>Author</th>
                    <th>ISBN</th>
                </tr>
                </thead>
            </table>
            <FavoriteBooksList bookList={bookList}
                               deleteBook={deleteBook}
                               // updateStatus={updateBookStatus}
                               // getBookStatus={getBookStatus}
            /><br/>
            <div className={"InputFields"}>
                <SearchForTitle inputFieldValue={getBookByKeyword}/><br/>
                <SearchForAuthor inputFieldValue={getBookByAuthor}/><br/>
                <SearchForISBN inputFieldValue={getBookByISBN}/><br/>
            </div>
        </section>
    )
}
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import BookApp from "./components/BookApp";
import Home from './pages/Home';
import NavigationBar from './pages/NavigationBar';
import BookDetails from "./components/BookDetails";
import LoginPage from "./pages/LoginPage";
import useUser from "./hooks/UseUser";
import SignUp from './components/SignUp';
import FavoriteBooksApp from "./components/FavoriteBooksApp";



function App() {

    const {userName, login, logout, addUser} = useUser()

  return (
      <BrowserRouter>
        <NavigationBar logout={logout} />
          <h2>Hallo {userName}!</h2>
        <Routes>
            <Route path={"/books/home"} element={ <Home />}></Route>
            <Route path={"/books"} element={<BookApp />}></Route>
            <Route path={"/books/:id"} element={<BookDetails/>} />
            <Route path={"/users/login"} element={<LoginPage login={login}/>} />
            <Route path={"/users/signup"} element={<SignUp addUser={addUser}  />} />
            <Route path={"/users/me/favorites"} element={<FavoriteBooksApp/>}/>
        </Routes>
</BrowserRouter>


  );
}

export default App;

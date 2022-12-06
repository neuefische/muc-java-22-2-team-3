import React, {useState} from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import BookApp from "./components/BookApp";
import Home from './pages/Home';
import NavigationBar from './pages/NavigationBar';
import BookDetails from "./components/BookDetails";
import LoginPage from "./pages/LoginPage";

function App() {

    const[username, setUsername] = useState<string>()

  return (
      <BrowserRouter>
        <NavigationBar />
        <Routes>
            <Route path={"/books/home"} element={ <Home />}></Route>
            <Route path={"/books"} element={<BookApp />}></Route>
            <Route path={"/books/:id"} element={<BookDetails/>} />
            <Route path={"/books/users/login"} element={<LoginPage/>} />

        </Routes>
</BrowserRouter>


  );
}

export default App;

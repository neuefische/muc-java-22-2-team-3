import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import BookApp from "./components/BookApp";
import NavigationBar from './pages/NavigationBar';

function App() {
  return (

      <BrowserRouter>
        <NavigationBar />
        <Routes>
          <Route path={"/books/home"} ></Route>
          <Route path={"/books/bookList"} element={<BookApp />}></Route>
        </Routes>
</BrowserRouter>


  );
}

export default App;

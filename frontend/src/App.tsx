import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import BookApp from "./components/BookApp";
import Home from './pages/Home';
import NavigationBar from './pages/NavigationBar';

function App() {
  return (

      <BrowserRouter>
        <NavigationBar />
        <Routes>
            <Route path={"/books/home"} element={ <Home />}></Route>
          <Route path={"/books"} element={<BookApp />}></Route>
        </Routes>
</BrowserRouter>


  );
}

export default App;

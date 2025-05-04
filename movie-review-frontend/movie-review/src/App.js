import logo from './logo.svg';
import './App.css';
import Layout from './component/layout';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './component/home/page';
import { useState, useEffect } from 'react';
import axiosConfig from './api/axiosConfig';
import api from './api/axiosConfig';

function App() {

  const [movies, setMovies] = useState();

  const getMovies = async () => {

    try {
      const response = await api.get("/movie/all");
      console.log(response.data);
      setMovies(response.data);
    } catch (err) {
      console.log(err);
    }

  }


  useEffect(() => {
    getMovies();
  }, []);


  return (
    <main>
      <Routes>
        <Route path="/*" element={<Layout />}>
          <Route path="" element={<Home />}></Route>
        </Route>
      </Routes>
    </main>
  );
}

export default App;

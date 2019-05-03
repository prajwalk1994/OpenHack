import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import Menu from './components/Menu';
import './App.css';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <div>
          <Menu />
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;

import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import ProductArchive from './components/ProductArchive';

const App = () => {
  const [facts, setFacts] = useState([]);

  useEffect(() => {
    const fetchFacts = async () => {
      try {
        const response = await fetch('https://meowfacts.herokuapp.com/?count=2');
        const data = await response.json();
        setFacts(data.data.slice(0, 2));
      } catch (error) {
        console.error('Error buscando datos sobre gatos:', error);
      }
      
    };

    fetchFacts();
  }, []);

  const [uselessFact, setUselessFact] = useState('');

  useEffect(() => {
    const fetchUselessFact = async () => {
      try {
        const response = await fetch('https://uselessfacts.jsph.pl/api/v2/facts/today');
        const data = await response.json();
        setUselessFact(data.text);
      } catch (error) {
        console.error('Error buscando useless fact:', error);
      }
    };

    fetchUselessFact();
  }, []);

  return (
    <div>
      <Router>
        <h1>Product Management App</h1>
        <Routes>
          <Route path="/" element={<ProductArchive />} />
        </Routes>
      </Router>

      <h2>Sabías que...</h2>
      <ul>
        {facts.map((fact, index) => (
          <li key={index}>{fact}</li>
        ))}
      </ul>
      <h2>Useless Fact of the Day</h2>
        <p>{uselessFact}</p>
    </div>
  );
};

export default App;








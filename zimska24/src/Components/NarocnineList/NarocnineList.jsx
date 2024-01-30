import React, { useState, useEffect } from 'react';
import NarocninaTab from '../NarocninaTab/NarocninaTab';
import '../../App.css'

function NarocnineList() {
  const [narocnine, setNarocnine] = useState([]);
  const [selectedNarocnina, setSelectedNarocnina] = useState(null);

  const handleSelectNarocnina = (narocnina, what) => {
    let updatedNarocnina;

    if (what == "reset") {
      updatedNarocnina = { ...narocnina, trenutnaPoraba: 0 };
    } else {
      updatedNarocnina = { ...narocnina, trenutnaPoraba: narocnina.trenutnaPoraba + 1 };
    }
      
    setSelectedNarocnina(updatedNarocnina);
    
    setNarocnine(narocnine.map(n => n.id === narocnina.id ? updatedNarocnina : n));

    fetch(`http://127.0.0.1:8080/narocnine/${narocnina.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedNarocnina),
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
    })
    .catch(error => {
      console.error('Error updating narocnina:', error);
    });
  };

  useEffect(() => {
    fetch('http://127.0.0.1:8080/narocnine')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => setNarocnine(data))
      .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
      });
  }, []);

  return (
    <div>
      <h1>Mozne naročnine</h1>
      <div className="narocnine-list">
        {narocnine.map((narocnina, index) => (
          index % 3 === 0 &&
          <div key={narocnina.id} className="narocnine-row">
            {narocnine.slice(index, index + 3).map(item => (
              <NarocninaTab key={item.id} narocnina={item} onSelect={handleSelectNarocnina} />
            ))}
          </div>
        ))}
      </div>
      {selectedNarocnina && <div>Selected: {selectedNarocnina.naziv}</div>}
    </div>
  );
}

export default NarocnineList;
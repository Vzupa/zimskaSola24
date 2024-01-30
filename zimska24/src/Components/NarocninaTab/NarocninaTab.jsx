import '../../App.css'
import HorizontalBar from '../HorizontalBar/HorizontalBar'

function NarocninaTab({ narocnina, onSelect }) {

  return (
    <div className="narocnina-tab">
      <p>Naziv: {narocnina.naziv}</p>
      <p>GigaPodatki: {narocnina.gigaPodatki}</p>
      <p>Trenutna Poraba: {narocnina.trenutnaPoraba}</p>
      <p>Cena: {narocnina.cena}</p>
      <HorizontalBar narocnina={narocnina} />
      <button onClick={() => onSelect(narocnina, "reset")}>Select Narocnina</button>
      <button onClick={() => onSelect(narocnina, "increment")}>Increment Usage</button>
    </div>
  );
}

export default NarocninaTab;
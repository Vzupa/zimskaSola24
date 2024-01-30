import '../../App.css'

function HorizontalBar({ narocnina }) {
  let usagePercentage = Math.min((narocnina.trenutnaPoraba / narocnina.gigaPodatki) * 100, 100);
  usagePercentage = Math.max(usagePercentage, 0); // Ensure it's not less than 0

  // Determine bar color based on usage
  let barColor;
  if (usagePercentage <= 75) {
    barColor = 'green';
  } else if (usagePercentage <= 90) {
    barColor = 'yellow';
  } else {
    barColor = 'red';
  }

  return (
    <div className="usage-bar" style={{ width: '100%', backgroundColor: '#ddd', position: 'relative' }}>
      <div style={{ width: `${usagePercentage}%`, backgroundColor: barColor, height: '20px' }}>
        {/* Text inside the bar */}
        <span className="usage-text">{`${usagePercentage.toFixed(1)}% Used`}</span>
      </div>
    </div>
  )
}

export default HorizontalBar;
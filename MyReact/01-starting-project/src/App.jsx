
import Header from './components/Header/Header.jsx';
import { EXAMPLES } from './data.js'
import CoreConcepts from './components/CoreConcepts.jsx';
import Examples from './components/Examples.jsx';

function App() {
  return (
    <>
      <Header />      
      <main>
        <CoreConcepts></CoreConcepts>
        <Examples></Examples>        
      </main>
    </>
  );
}

export default App;

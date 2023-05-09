// import { useState } from 'react';
// // import { Tabs, Tab } from 'react-bootstrap';

// //import CadastroFornecedor from './CadastroFornecedor';

// function App() {
//   const [key, setKey] = useState('Fornecedores');

//   return (
//     <div>

//       <ListaFornecedores />

//       <ListaEmpresas />



//     </div>
//   );
// }

// export default App;

import React, { Fragment } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './pages/Navbar';
import CadastroEmpresa from './pages/CadastroEmpresa';
import CadastroFornecedor from './pages/CadastroFornecedor';
import ListaFornecedores from './pages/ListaFornecedores';
import ListaEmpresas from './pages/ListaEmpresas';
import './global.css'
import CadastroEmpresaFornecedor from './pages/CadastroEmpresaFornecedor';

function App() {
  return (
    <Router>
      < Navbar />

      <Fragment>

        <Routes>

          <Route path="/empresa" element={<ListaEmpresas />}> </Route>
          <Route path="/fornecedor" element={<ListaFornecedores />}></Route>
          <Route path="/cadastro-empresa" element={<CadastroEmpresa />}></Route>
          <Route path="/cadastro-fornecedor" element={<CadastroFornecedor />}></Route>
          <Route path="/cadastro-fornecedorempresa" element={<CadastroEmpresaFornecedor />}></Route>
        </Routes>
      </Fragment>
    </Router>
  );
}

export default App;


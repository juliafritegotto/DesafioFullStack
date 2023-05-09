import { useState, useEffect } from 'react';
import axios from 'axios';
import './Lista.css';
import { Link } from 'react-router-dom';

function ListaFornecedores() {
    const [Fornecedores, setFornecedores] = useState([]);
    const [nomeFiltro, setNomeFiltro] = useState('');
    const [documentoFiltro, setDocumentoFiltro] = useState('');



    useEffect(() => {
        axios.get(
            'http://localhost:8080/fornecedor',
        ).then(response => {
            setFornecedores(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }, []);

    const handleNomeChange = (event) => {
        setNomeFiltro(event.target.value);
    }

    const handleDocumentoChange = (event) => {
        setDocumentoFiltro(event.target.value);
    }

    const filteredFornecedores = Fornecedores.filter(fornecedor => {
        return (
            fornecedor.nome.toLowerCase().includes(nomeFiltro.toLowerCase()) &&
            fornecedor.documento.toLowerCase().includes(documentoFiltro.toLowerCase())
        )
    });

    return (
        <div>
            <h1>Fornecedores</h1>
            <div className="filtro">
                <div>
                    <label htmlFor="nomeFiltro">Nome: </label>
                    <input type="text" id="nomeFiltro" value={nomeFiltro} onChange={handleNomeChange} />
                </div>
                <div>
                    <label htmlFor="documentoFiltro">Documento: </label>
                    <input type="text" id="documentoFiltro" value={documentoFiltro} onChange={handleDocumentoChange} />
                </div>
            </div>
            <ul>
                {filteredFornecedores.map(fornecedor => {
                    return (
                        <li key={fornecedor.id}>
                            <p><strong>Nome:</strong> {fornecedor.nome}</p>
                            <p><strong>Documento:</strong> {fornecedor.documento}</p>
                            <p><strong>Email:</strong> {fornecedor.email}</p>
                            <p><strong>CEP:</strong> {fornecedor.cep}</p>
                        </li>
                    )
                })}
            </ul >
            <Link to="/cadastro-fornecedor" className="custom-link">
                <button className="btn">Adicionar Fornecedor</button>
            </Link>
        </div >
    );
}


export default ListaFornecedores;
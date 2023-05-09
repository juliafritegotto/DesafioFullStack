import { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './Lista.css';

function ListaEmpresas() {
    const [empresas, setEmpresas] = useState([]);
    const [nomeFiltro, setNomeFiltro] = useState('');
    const [cnpjFiltro, setCnpjFiltro] = useState('');

    useEffect(() => {
        axios.get(
            'http://localhost:8080/api/empresas',
        ).then(response => {
            setEmpresas(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }, []);

    const handleNomeChange = (event) => {
        setNomeFiltro(event.target.value);
    }

    const handleCnpjChange = (event) => {
        setCnpjFiltro(event.target.value);
    }

    const filteredEmpresas = empresas.filter(empresa => {
        return (
            empresa.nomeFantasia.toLowerCase().includes(nomeFiltro.toLowerCase()) &&
            empresa.cnpj.toLowerCase().includes(cnpjFiltro.toLowerCase())
        )
    });

    return (
        <div>
            <h1>Empresas</h1>
            <div className="filtro">
                <div>
                    <label htmlFor="nomeFiltro">Nome Fantasia: </label>
                    <input type="text" id="nomeFiltro" value={nomeFiltro} onChange={handleNomeChange} />
                </div>
                <div>
                    <label htmlFor="cnpjFiltro">CNPJ: </label>
                    <input type="text" id="cnpjFiltro" value={cnpjFiltro} onChange={handleCnpjChange} />
                </div>
            </div>
            <ul>
                {filteredEmpresas.map(empresa => {
                    return (
                        <li key={empresa.id}>
                            <p><strong>Nome:</strong> {empresa.nomeFantasia}</p>
                            <p><strong>CNPJ:</strong> {empresa.cnpj}</p>
                            <p><strong>CEP:</strong> {empresa.cep}</p>


                        </li>
                    )
                })}
            </ul>
            <Link to="/cadastro-empresa" className="custom-link">
                <button className="btn">Adicionar Empresa</button>
            </Link>
        </div>
    );
}

export default ListaEmpresas;

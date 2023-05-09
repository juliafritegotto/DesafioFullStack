import React, { useState } from 'react';
import axios from 'axios';

function CadastroEmpresa() {
    const [nomeFantasia, setNomeFantasia] = useState('');
    const [cnpj, setCnpj] = useState('');
    const [cep, setCep] = useState('');
    const [Fornecedores, setFornecedores] = useState([]);
    const [nomeFornecedor, setNomeFornecedor] = useState('');

    const handlenomeFantasiaChange = (event) => {
        setNomeFantasia(event.target.value);
    };

    const handleCEP = (event) => {
        setCep(event.target.value);
    };

    const handleCNPJ = (event) => {
        setCnpj(event.target.value);
    };

    const handleNomeFornecedorChange = (event) => {
        setNomeFornecedor(event.target.value);
    };

    const adicionarFornecedor = () => {
        if (nomeFornecedor.trim() !== '') {
            setFornecedores([...Fornecedores, nomeFornecedor]);
            setNomeFornecedor('');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        // let data = JSON.stringify({
        //     username: this.state.username,
        //     password: password
        // });

        // const response = axios.post(url, data, { headers: { "Content-Type": "application/json" } })

        try {
            const response = await axios.post('http://localhost:8080/empresa', {
                nomeFantasia,
                cnpj,
                cep
            });

            console.log('Dados enviados para o backend:', response.data);

            // Limpar o formulário após o envio
            setNomeFantasia('');
            setCnpj('');
            setCep('');
            setFornecedores([]);
            setNomeFornecedor('');
        } catch (error) {
            console.error('Erro ao enviar os dados:', error);
        }
    };

    return (
        <div className='containerCadastro'>

            <h1>Cadastro de Empresa</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="nomeFantasia">Nome da Empresa:</label>
                    <input
                        type="text"
                        id="nomeFantasia"
                        className="fieldCadastro"
                        value={nomeFantasia}
                        onChange={handlenomeFantasiaChange}
                    /><br />

                    <label htmlFor="cep">CEP:</label>
                    <input
                        type="text"
                        id="cep"
                        className="fieldCadastro"
                        value={cep}
                        onChange={handleCEP}
                    /><br />

                    <label htmlFor="cnpj">CNPJ:</label>
                    <input
                        type="text"
                        id="cnpj"
                        className="fieldCadastro"
                        value={cnpj}
                        onChange={handleCNPJ}
                    /><br />


                </div>
                <div>
                    <label htmlFor="nomeFornecedor">Pesquisar Fornecedor:</label>
                    <input
                        type="text"
                        id="nomeFornecedor"
                        className="fieldCadastro"
                        value={nomeFornecedor}
                        onChange={handleNomeFornecedorChange}
                    />
                    <button type="button" onClick={adicionarFornecedor}>
                        Adicionar
                    </button>
                </div>
                <ul>
                    {Fornecedores.map((Fornecedor, index) => (
                        <li key={index}>{Fornecedor}</li>
                    ))}
                </ul>
                <button type="submit" className="btnCadastro">Cadastrar Empresa</button>
            </form>
        </div>


    );
}

export default CadastroEmpresa;

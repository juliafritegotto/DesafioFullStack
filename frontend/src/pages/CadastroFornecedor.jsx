import { useState } from 'react';
import axios from 'axios';
import './Form.css'

function CadastroEmpresa() {
    const [tipoDocumento, setTipoDocumento] = useState('CPF');
    const [dataNascimento, setDataNascimento] = useState('');
    const [documento, setNumeroDocumento] = useState('');
    const [rg, setRg] = useState('');
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [cep, setCep] = useState('');
    const [empresa, setEmpresa] = useState('');

    const handleTipoDocumentoChange = (event) => {
        setTipoDocumento(event.target.value);
    };

    const handleNumeroDocumentoChange = (event) => {
        setNumeroDocumento(event.target.value);
    };

    const handleDataNascimentoChange = (event) => {
        setDataNascimento(event.target.value);
    };

    const handleRgChange = (event) => {
        setRg(event.target.value);
    };

    const handleNomeChange = (event) => {
        setNome(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handleEmpresaChange = (event) => {
        setEmpresa(event.target.value);
    };

    const handleCepChange = (event) => {
        setCep(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/fornecedores', {
                documento,
                nome,
                email,
                cep,
                dataNascimento,
                rg

            });

            console.log('Dados enviados para o backend:', response.data);

            setNumeroDocumento('');
            setNome('');
            setEmail('');
            setCep('');
            setDataNascimento('');
            setRg('');


        } catch (error) {
            console.error('Erro ao enviar os dados:', error);
        }
    };


    return (
        <div className='containerCadastro'>
            <h1>Cadastro de Fornecedor</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="tipoDocumento">Tipo de Documento:</label>
                    <select id="tipoDocumento" className="selectCadastro" value={tipoDocumento} onChange={handleTipoDocumentoChange}>
                        <option value="CPF">CPF</option>
                        <option value="CNPJ">CNPJ</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="documento">{tipoDocumento === 'CPF' ? 'CPF' : 'CNPJ'}:</label>
                    <input type="text" id="documento" className="fieldCadastro" value={documento} onChange={handleNumeroDocumentoChange} />
                </div>

                {tipoDocumento === 'CPF' && (
                    <div>
                        <div>
                            <label htmlFor="rg">RG:</label>
                            <input type="text" id="rg" className="fieldCadastro" value={rg} onChange={handleRgChange} />
                        </div>

                        <div>
                            <label htmlFor="dataNascimento">Data de Nascimento:</label>
                            <input type="date" id="dataNascimento" className="fieldCadastro" value={dataNascimento} onChange={handleDataNascimentoChange} />
                        </div>
                    </div>


                )}



                <div>
                    <label htmlFor="nome">Nome:</label>
                    <input type="text" id="nome" className="fieldCadastro" value={nome} onChange={handleNomeChange} />
                </div>

                <div>
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" className="fieldCadastro" value={email} onChange={handleEmailChange} />
                </div>
                <div>
                    <label htmlFor="cep">CEP:</label>
                    <input type="text" id="cep" className="fieldCadastro" value={cep} onChange={handleCepChange} />
                </div>
                {/* 
                <div>
                    <label htmlFor="empresa">Empresa:</label>
                    <input type="text" id="empresa" className="fieldCadastro" value={empresa} onChange={handleEmpresaChange} />
                </div> */}

                <button type="submit" className="btnCadastro">Cadastrar</button>
            </form>
        </div>
    );
}

export default CadastroEmpresa;

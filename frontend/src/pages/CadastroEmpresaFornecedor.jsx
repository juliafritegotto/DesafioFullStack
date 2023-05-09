import { useState } from 'react';
import axios from 'axios';
import './Form.css'

function CadastroEmpresaFornecedor() {
    const [tipoDocumento, setTipoDocumento] = useState('CPF');
    const [dataNascimento, setDataNascimento] = useState('');
    const [rg, setRg] = useState('');
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [empresa, setEmpresa] = useState('');

    const handleTipoDocumentoChange = (event) => {
        setTipoDocumento(event.target.value);
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

    const handleSubmit = (event) => {
        event.preventDefault();

        const data = {
            tipoDocumento,
            dataNascimento,
            rg,
            nome,
            email,
            empresa,
        };

        axios.post('http://localhost:8080/fornecedor', data)
            .then(response => {
                console.log(response.data);
                // Limpar os campos após o cadastro
                setTipoDocumento('CPF');
                setDataNascimento('');
                setRg('');
                setNome('');
                setEmail('');
                setEmpresa('');
            })
            .catch(error => {
                console.error(error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="tipoDocumento">Tipo de Documento:</label>
                <select id="tipoDocumento" value={tipoDocumento} onChange={handleTipoDocumentoChange}>
                    <option value="CPF">CPF</option>
                    <option value="CNPJ">CNPJ</option>
                </select>
            </div>

            <div>
                <label htmlFor="numeroDocumento">{tipoDocumento === 'CPF' ? 'CPF' : 'CNPJ'}:</label>
                <input type="text" id="numeroDocumento" value={numeroDocumento} onChange={handleNumeroDocumentoChange} />
            </div>

            {tipoDocumento === 'CPF' && (
                <div>
                    <label htmlFor="dataNascimento">Data de Nascimento:</label>
                    <input type="date" id="dataNascimento" value={dataNascimento} onChange={handleDataNascimentoChange} />
                </div>
            )}

            <div>
                <label htmlFor="rg">RG:</label>
                <input type="text" id="rg" value={rg} onChange={handleRgChange} />
            </div>

            <div>
                <label htmlFor="nome">Nome:</label>
                <input type="text" id="nome" value={nome} onChange={handleNomeChange} />
            </div>

            <div>
                <label htmlFor="email">Email:</label>
                <input type="email" id="email" value={email} onChange={handleEmailChange} />
            </div>

            <div>
                <label htmlFor="empresa">Empresa:</label>
                <input type="text" id="empresa" value={empresa} onChange={handleEmpresaChange} />
            </div>

            <button type="submit">Cadastrar</button>
        </form>
    );
}

export default CadastroEmpresaFornecedor;

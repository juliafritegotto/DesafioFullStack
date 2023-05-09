import React, { useState } from 'react';
import axios from 'axios';
import './Form.css'

function CadastroFornecedor() {
    const [documento, setDocumento] = useState('');
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [cep, setCep] = useState('');
    const [nomeEmpresa, setNomeEmpresa] = useState('');
    // const [isValid, setIsValid] = useState(false);


    const handleDocumentoChange = (event) => {
        setDocumento(event.target.value);
    };

    const handleNomeChange = (event) => {
        setNome(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handleCepChange = (event) => {
        setCep(event.target.value);
    };

    function isValidForm() {
        return nome !== '' && documento !== '' && email !== '' && cep !== '';
    }

    // const handleNomeEmpresaChange = (event) => {
    //     setNomeEmpresa(event.target.value);
    // };

    // const adicionarEmpresa = () => {
    //     if (        setNnomeEmpresa(event.target.value).trim() !== '') {
    //     setEmpresas(event.target.value);
    //     ([...empresas, noem]);
    //         setNomeFornecedor('');
    //     }
    // };

    // function handleFieldChange(field, value) {
    //     switch (field) {
    //         case 'nome':
    //             setNome(value);
    //             break;
    //         case 'endereco':
    //             setEndereco(value);
    //             break;
    //         case 'telefone':
    //             setTelefone(value);
    //             break;
    //         case 'email':
    //             setEmail(value);
    //             break;
    //         default:
    //             break;
    //     }

    //     if (nome !== '' && endereco !== '' && telefone !== '' && email !== '') {
    //         setIsValid(true);
    //     } else {
    //         setIsValid(false);
    //     }
    // }

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/fornecedor', {
                documento,
                nome,
                email,
                cep
                // empresas: fornecedores, // Envia a lista de empresas junto com os dados do fornecedor
            });

            console.log('Dados enviados para o backend:', response.data);

            // Limpar o formulário após o envio
            setDocumento('');
            setNome('');
            setEmail('');
            setCep('');
            // setEmpresas([]);

        } catch (error) {
            console.error('Erro ao enviar os dados:', error);
        }
    };

    return (
        <div className='containerCadastro'>
            <h1>Cadastro de Fornecedor</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="nome">Nome:</label>
                    <input type="text" id="nome" className="fieldCadastro" value={nome} onChange={handleNomeChange} />
                </div>
                <div>
                    <label htmlFor="documento">Documento:</label>
                    <input type="text" id="documento" className="fieldCadastro" value={documento} onChange={handleDocumentoChange} />
                </div>

                <div>
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" className="fieldCadastro" value={email} onChange={handleEmailChange} />
                </div>
                <div>
                    <label htmlFor="cep">CEP:</label>
                    <input type="text" id="cep" className="fieldCadastro" value={cep} onChange={handleCepChange} />
                </div>
                {/* <div>
                    <label htmlFor="nome">Empresas atendidas:</label>
                    <input
                        type="text"
                        id="nome"
                        className="fieldCadastro"
                        value={nome}
                        onChange={handleNomeChange}
                    />
                    <button type="button" onClick={adicionarFornecedor}>
                        Adicionar
                    </button> 
                </div> */}
                {/* <ul>
                    {fornecedores.map((empresa, index) => (
                        <li key={index}>{empresa}</li>
                    ))}
                </ul> */}
                <button type="submit" className="btnCadastro" disabled={!isValidForm()}>
                    Cadastrar Fornecedor
                </button>

            </form>
        </div>


    );

}

export default CadastroFornecedor;






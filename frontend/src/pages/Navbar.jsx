import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
    return (
        <nav className="navbar">
            <ul className="nav-list">
                <li className="nav-item">
                    <Link to="/" className="nav-link">Home</Link>
                </li>
                <li className="nav-item">
                    <Link to="/empresas" className="nav-link">Empresas</Link>
                </li>
                <li className="nav-item">
                    <Link to="/fornecedores" className="nav-link">Fornecedores</Link>
                </li>
                <li className="nav-item">
                    <Link to="/cadastro-empresa" className="nav-link">Cadastrar Empresa</Link>
                </li>
                <li className="nav-item">
                    <Link to="/cadastro-fornecedor" className="nav-link">Cadastrar Fornecedor</Link>
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;

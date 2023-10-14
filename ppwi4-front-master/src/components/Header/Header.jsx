import React from "react";
import { useState } from "react";
import * as Faicons from 'react-icons/fa'
import { PROFILE_IMAGE } from "../../config/config";

const Header = ({isToggle}) => {

    const [toggle, setToggle] = useState(false);

    const handleToggleClick = () => {
        console.log("Header " + toggle)
        setToggle(!toggle);
        isToggle(toggle);
    }

    return (
        <header className="app-header">
            <nav class="navbar">
            <a href="#" class="logo"> &lt;Cadastro Escola&gt;</a>
            <div className="dt">
                <div className="nav-menu">
                    <i><Faicons.FaBars onClick={handleToggleClick}/></i>
                </div>
                <div className="nav-menu">
                    <img src={PROFILE_IMAGE.USER} alt="foto do usuário" />
                    <span>Usuário</span>
                    <div className="app-logout">
                        <i><Faicons.FaSignOutAlt/></i>
                    </div>
                </div>
            </div>
            </nav>
        </header>
    )
}

export default Header;
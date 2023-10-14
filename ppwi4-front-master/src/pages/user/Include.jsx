import React, { useState } from 'react'
import * as FaIcons from 'react-icons/fa';
import * as MdIcons from 'react-icons/md';
import * as AiIcons from 'react-icons/ai';
import ShowMessage from '../../components/Messages/ShowMessage'
import { DEFAULT_BUTTON_SIZE, DEFAULT_SHOW_MESSAGE_ICON_SIZE, PROFILE_IMAGE, SERVER_GET_IMAGE } from '../../config/config';

const Include = () => {
    const [photo, setPhoto] = useState(null);

    const handleInputFile = () => {

    }

    return (
        <>
            <ShowMessage iconTitle={<FaIcons.FaUserEdit size={DEFAULT_SHOW_MESSAGE_ICON_SIZE}/>} 
                title='Cadastro de usuários'
                description='Incluir novos usuários ao sistema' 
                iconReturn={<MdIcons.MdList size={DEFAULT_SHOW_MESSAGE_ICON_SIZE}/>} 
                path='Usuário' 
                url='/user/list' 
                titleUrl='Listagem de usuários'
            />
            <div className='app-window'>
                <img src={photo === null ? 
                    PROFILE_IMAGE.AVATAR : 
                    `${SERVER_GET_IMAGE}${photo}`} alt="Foto do usuário" />
                <div className='fileInput'>
                    <input type='file' onChange='handleInputFile'/>
                    <button id='uploadFile' className='btn btn-success btn-lg uploadFile' title='Enviar foto do usuário'>
                        <i>
                            <FaIcons.FaUpload size={DEFAULT_BUTTON_SIZE}/>
                        </i>
                    </button>
                </div>
                <form>
                    <label  htmlFor="username" className='control-label'>userName:</label>
                    <input type="text" id='username' name='username' className='form-control'/>

                    <label  htmlFor="email" className='control-label'>email:</label>
                    <input type="email" id='email' name='email' className='form-control'/>

                    <label  htmlFor="password" className='control-label'>senha:</label>
                    <input type="password" id='password' name='password' className='form-control'/>

                    <label  htmlFor="rePassword" className='control-label'>confirmar:</label>
                    <input type="password" id='rePassword' name='rePassword' className='form-control'/>


                    <div>
                        <button type='button' className='btn btn-success'>Salvar</button>
                        <button type='button' className='btn btn-danger'>Cancelar</button>
                    </div>
                </form>
            </div>
        </>
    )
}

export default Include
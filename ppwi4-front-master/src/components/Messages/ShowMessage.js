import React from "react";
import { Link } from "react-router-dom"

const ShowMessage = ({iconTitle, title, description, iconReturn, path, url, titleUrl}) => {
    return (
        <>
            <div className="app-title-system">
                <div>
                    <h3>
                        <i>{iconTitle}</i>
                        {title}
                    </h3>
                    <p>{description}</p>
                </div>
                <ul className="breadcrumb">
                    <li className="breadcrumb-item">
                        <i>{iconReturn}</i>
                    </li>
                    {
                        path ? <li className="breadcrumb-item">{path}</li> : null
                    }
                    <li className="breadcrumb-item">
                        <Link to={url}>{titleUrl}</Link>
                    </li>
                </ul>
            </div>
        </>
    )
}

export default ShowMessage;
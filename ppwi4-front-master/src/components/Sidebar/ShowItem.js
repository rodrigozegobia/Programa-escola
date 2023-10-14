import { Link } from "react-router-dom";

const ShowItem = ({path, icon, page, index}) => {
    return (
        <>
            <li className="list" key={index}>
                <Link to={path}>
                    <i>{icon}</i>
                    <span className="link">{page}</span>
                </Link>
            </li>
        </>
    )
}

export default ShowItem;
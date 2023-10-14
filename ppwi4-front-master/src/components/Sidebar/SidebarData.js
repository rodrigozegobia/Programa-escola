import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';

const SidebarData = [
    {
        page: 'Página principal',
        icon: <AiIcons.AiFillDashboard/>,
        path: '/dashboard'
    },
    {
        page: 'Usuário',
        icon: <FaIcons.FaUser/>,
        path: '/user/list'
    },
    {
        page: 'Direitos de acesso',
        icon: <FaIcons.FaTablet/>,
        path: '/role/list'
    },
];

export default SidebarData;
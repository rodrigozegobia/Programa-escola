import http from "../config/http";

const userList = async () => {
    return (
        http({
            method: 'GET',
            url: '/user/list'
        }).then((response) => {
            return response?.data;
        })
    )
};

const findById = async (id) => {
    return (
        http({
            method: 'GET',
            url: `/user/get/${id}`
        }).then((response) => {
            return response?.data;
        }).catch((error) => {
            return error.response;
        })
    )
};

export {
    userList,
    findById
}
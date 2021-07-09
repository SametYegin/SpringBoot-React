import axios from "axios";

export const signup = (body) => {
    return axios.post('/users' , body);
};

export const login = creds => {
    return axios.post('/auth', {} , { auth : creds});
};

export const getUsers = () => {
    return axios.get('/listall');
};

export const updateUsers = email => {
    return axios.put(`/update/${email}`);
};

export const deleteUsers = (body) => {
    return axios.delete(`/delete/${body}`);
};

export const getUser = email => {
    return axios.get(`/getUser/${email}`);
};
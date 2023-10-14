import axios from 'axios';
import { SERVER } from './config';

const http = axios.create({
    baseURL: SERVER
});

export default http;
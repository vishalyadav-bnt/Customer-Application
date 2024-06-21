import http from 'k6/http';
import { sleep, check } from 'k6';
const BASE_URL = 'http://localhost:8080';
export const options = {
    vus: 10,
    duration: '10s',
};
const headers = {
    'Content-Type': 'application/json',
};
export default function () {
    let customerPayload = JSON.stringify({
        "id": 8,
        "name": "kishan kumar",
        "sal": 21000
    });
    let postResponse = http.post(`${BASE_URL}/customer`, customerPayload, { headers: headers });
    check(postResponse, {
        'POST status is 200 or 201': (res) => res.status === 200 || res.status === 201,
    });
    let getResponse = http.get(`${BASE_URL}/customer`, { headers: headers });
    check(getResponse, {
        'GET status is 200': (res) => res.status === 200,
    });
}
import { check } from 'k6';
import http from 'k6/http';
import { CLASSIC_API, METRICS, TAGS } from '../config.js';

export const options = {
    scenarios: {
        constant_load: {
            executor: 'constant-vus',
            vus: 100,
            duration: '30s',
            tags: { scenario: 'constant_load' }
        },
        ramp_up: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '10s', target: 50 },
                { duration: '20s', target: 100 },
                { duration: '10s', target: 0 }
            ],
            tags: { scenario: 'ramp_up' }
        }
    },
    thresholds: {
        http_req_duration: [METRICS.http_req_duration.threshold],
        http_req_failed: [METRICS.http_req_failed.threshold],
        iteration_duration: [METRICS.iteration_duration.threshold]
    },
    tags: TAGS
};

export default function () {
    const payload = JSON.stringify({
        number: Math.floor(Math.random() * 10) + 1
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const response = check(http.post(`${CLASSIC_API}/square`, payload, params), {
        'is status 200': (r) => r.status === 200,
        'has results': (r) => r.json().results !== undefined,
        'has total': (r) => r.json().total !== undefined
    });
} 
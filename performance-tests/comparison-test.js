import { check, group } from 'k6';
import http from 'k6/http';
import { REACTIVE_API, CLASSIC_API, METRICS, TAGS } from './config.js';

export const options = {
    scenarios: {
        high_load: {
            executor: 'constant-arrival-rate',
            rate: 200,
            duration: '30s',
            preAllocatedVUs: 50,
            maxVUs: 100,
            tags: { scenario: 'high_load' }
        }
    },
    thresholds: {
        'http_req_duration{api:reactive}': [METRICS.http_req_duration.threshold],
        'http_req_duration{api:classic}': [METRICS.http_req_duration.threshold],
        'http_req_failed{api:reactive}': [METRICS.http_req_failed.threshold],
        'http_req_failed{api:classic}': [METRICS.http_req_failed.threshold]
    },
    tags: TAGS
};

export default function () {
    const payload = JSON.stringify({
        number: Math.floor(Math.random() * 1000) + 1
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
        tags: { api: 'reactive' }
    };

    group('Reactive API Test', function () {
        const reactiveResponse = http.post(`${REACTIVE_API}/square`, payload, params);
        check(reactiveResponse, {
            'reactive status is 200': (r) => r.status === 200,
            'reactive has valid response': (r) => {
                const body = r.json();
                return body.results !== undefined && body.total !== undefined;
            }
        });
    });

    params.tags.api = 'classic';
    group('Classic API Test', function () {
        const classicResponse = http.post(`${CLASSIC_API}/square`, payload, params);
        check(classicResponse, {
            'classic status is 200': (r) => r.status === 200,
            'classic has valid response': (r) => {
                const body = r.json();
                return body.results !== undefined && body.total !== undefined;
            }
        });
    });
} 
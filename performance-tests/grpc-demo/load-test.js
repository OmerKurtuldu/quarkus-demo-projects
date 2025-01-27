import grpc from 'k6/net/grpc';
import { check, sleep } from 'k6';
import { METRICS, TAGS } from '../config.js';

const client = new grpc.Client();
client.load(['../grpc-demo'], 'square.proto');

export const options = {
    scenarios: {
        constant_load: {
            executor: 'constant-vus',
            vus: 100,
            duration: '30s',
            tags: { scenario: 'grpc_constant_load' }
        },
        ramp_up: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '10s', target: 50 },
                { duration: '20s', target: 100 },
                { duration: '10s', target: 0 }
            ],
            tags: { scenario: 'grpc_ramp_up' }
        }
    },
    thresholds: {
        grpc_req_duration: [METRICS.http_req_duration.threshold],
        'grpc_req_failed': ['rate<0.01'],
        iteration_duration: [METRICS.iteration_duration.threshold]
    },
    tags: { ...TAGS, service: 'grpc-square' }
};

export default function () {
    client.connect('localhost:9090', {
        plaintext: true
    });

    const payload = {
        number: Math.floor(Math.random() * 1000) + 1
    };

    const response = client.invoke('square.SquareService/Calculate', payload);

    check(response, {
        'status is OK': (r) => r && r.status === grpc.StatusOK,
        'has valid response': (r) => r && r.message && r.message.results && r.message.total !== undefined
    });

    client.close();
    sleep(0.1); // 100ms bekleme
} 
import grpc from 'k6/net/grpc';
import http from 'k6/http';
import { check, group, sleep } from 'k6';
import { REACTIVE_API, METRICS, TAGS } from './config.js';

const client = new grpc.Client();
client.load(['./grpc-demo'], 'square.proto');

export const options = {
    scenarios: {
        high_load: {
            executor: 'constant-arrival-rate',
            rate: 200,
            duration: '30s',
            preAllocatedVUs: 50,
            maxVUs: 100,
            tags: { scenario: 'high_load_comparison' }
        }
    },
    thresholds: {
        'http_req_duration{api:rest}': [METRICS.http_req_duration.threshold],
        'grpc_req_duration{api:grpc}': [METRICS.http_req_duration.threshold],
        'http_req_failed{api:rest}': [METRICS.http_req_failed.threshold],
        'grpc_req_failed{api:grpc}': ['rate<0.01']
    },
    tags: { ...TAGS, test: 'grpc-vs-rest' }
};

export default function () {
    const number = Math.floor(Math.random() * 1000) + 1;

    group('gRPC Test', function () {
        client.connect('localhost:9090', {
            plaintext: true
        });

        const grpcResponse = client.invoke('square.SquareService/Calculate', {
            number: number
        });

        check(grpcResponse, {
            'grpc status is OK': (r) => r && r.status === grpc.StatusOK,
            'grpc has valid response': (r) => r && r.message && r.message.results && r.message.total !== undefined
        });

        client.close();
    });

    group('REST Test', function () {
        const payload = JSON.stringify({
            number: number
        });

        const params = {
            headers: {
                'Content-Type': 'application/json',
            },
            tags: { api: 'rest' }
        };

        const restResponse = http.post(`${REACTIVE_API}/square`, payload, params);
        
        check(restResponse, {
            'rest status is 200': (r) => r.status === 200,
            'rest has valid response': (r) => {
                const body = r.json();
                return body.results !== undefined && body.total !== undefined;
            }
        });
    });

    sleep(0.1); // 100ms bekleme
} 
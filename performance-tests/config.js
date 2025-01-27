export const REACTIVE_API = 'http://localhost:8080';
export const CLASSIC_API = 'http://localhost:8081';

export const METRICS = {
    http_req_duration: {
        threshold: 'p(95) < 500', // 95% of requests should complete within 500ms
        message: 'HTTP request duration is too high'
    },
    http_req_failed: {
        threshold: 'rate < 0.01', // Less than 1% error rate
        message: 'HTTP request failure rate is too high'
    },
    iteration_duration: {
        threshold: 'p(90) < 1000', // 90% of iterations should complete within 1s
        message: 'Iteration duration is too high'
    }
};

export const TAGS = {
    testType: 'performance',
    service: 'square-api'
}; 
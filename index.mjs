import http from 'http';


export async function handler(event) {
    const options = {
        hostname: 'example.com',
        port: 8080,
        path: '/public/api/v1/fetch',
        method: 'GET'
    };

    return new Promise((resolve, reject) => {
        const req = http.request(options, (res) => {
            let data = '';

            res.on('data', (chunk) => {
                data += chunk;
            });

            res.on('end', () => {
                resolve({
                    statusCode: 200,
                    body: data
                });
            });
        });

        req.on('error', (error) => {
            reject({
                statusCode: 500,
                body: JSON.stringify({ message: error.message })
            });
        });

        req.end();
    }).catch((error) => {
        return {
            statusCode: 500,
            body: JSON.stringify({ message: error.message, error })
        };
    });
}
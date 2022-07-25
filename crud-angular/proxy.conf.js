const PROXY_CONFIG = [
  {
    context: ['/api/v1'],
    target: 'http://localhost:8080/',
    secure: false,
    logLevel: 'debug',
    pathRewrite: { '^/api/v1': '' }
    //changeOrigin: true,




  }
]

module.exports = PROXY_CONFIG

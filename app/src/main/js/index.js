// content of index.js
/*const http = require('http')
var recieve = require('./recieve')
const port = 3000

const requestHandler = (request, response) => {
  console.log(request.url)
  recieve.getKey('keys2.txt')
  response.end('Hello Node.js Server!')
}

const server = http.createServer(requestHandler)

server.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})*/
const express = require('express')
var recieve = require('./recieve')
var create_account = require('./create_account')
const app = express()
const port = 3000

app.get('/', (request, response) => {
  response.send('Hello from Nzinghaa!')
})

app.get('/create_account', (request, response) => {	
  response.send(create_account.create_account())
})

app.get('/recieve', (request, response) => {
  response.json({key: recieve.getKey('keys2.txt')})
})

app.get('/send0-1', (request, response) => {
  response.send('Hello from send0-1!')
})

app.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})
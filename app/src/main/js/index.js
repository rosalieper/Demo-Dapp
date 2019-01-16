
const express = require('express')
const bodyParser = require('body-parser');
var recieve = require('./recieve')
var create_account = require('./create_account')
var send = require('./send0-1')
var app = express()
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

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.post('/send', (request, response) => {
	var post=request.body
	if(post.amount){
		console.log(post.amount)
  		response.send(send.transaction(post.amount, post.address))	
	}
	else{
		response.json({message: "Sorry Error 500: Parameters missing"})
	}
	
})

app.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})
var StellarSdk = require('stellar-sdk');
//var pair = StellarSdk.Keypair.random();
var recieve = require('./recieve');
var server = new StellarSdk.Server('http://core.nzinghaa.com:11625', {allowHttp: true});

var publicKey = recieve.getKey('keys2.txt');
var request = require('request');
request.get({
  url: 'http://core.nzinghaa.com:11625',
  qs: { addr: publicKey },
  json: true
}, function(error, response, body) {
	try{
		// the JS SDK uses promises for most actions, such as retrieving an account
		server.loadAccount(publicKey).then(function(account) {
		  console.log('Balances for account: ' + publicKey);
		  account.balances.forEach(function(balance) {
		    console.log('Type:', balance.asset_type, ', Balance:', balance.balance);
		  });
		});
		    //console.log('SUCCESS! You have a new account :)\n');

	}
	catch(err){
		console.log("ERROR!!!!");
		console.log(err);
	}
  if (error || response.statusCode !== 200) {
    console.error('ERROR!', error || body);
  }
  else {
    console.log('SUCCESS! You have a new account :)\n', body);
  }
});
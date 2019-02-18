//create a completely new and unique pair of keys
// see more about KeyPair objects: https://stellar.github.io/js-stellar-sdk/Keypair.html

var StellarSdk = require('stellar-sdk');
var pair = StellarSdk.Keypair.random();
//var server = new StellarSdk.Server('https://horizon-testnet.stellar.org');
var server = new StellarSdk.Server('http://172.31.41.130:11626', {allowHttp: true});
const fs = require('fs');

module.exports = {

create_account: function (){
	let secretKey = pair.secret();
	// SAV76USXIJOBMEQXPANUOQM6F5LIOTLPDIDVRJBFFE2MDJXG24TAPUU7
	let publicKey = pair.publicKey();
	// GCFXHS4GXL6BVUCXBWXGTITROWLVYXQKQLF4YH5O5JT3YZXCYPAFBJZB

	//saving the keys to a file
	fs.writeFile("keys.txt", secretKey+"\npublicKey: "+publicKey, function(err) {
	    if(err) {
	        return console.log(err);
	    }

	    console.log("The keys were saved!");
	}); 


	//The SDK does not have tools for creating test accounts, so you'll have to
	// make your own HTTP request.
	var request = require('request');
	request.get({
	  url: 'https://friendbot.stellar.org',
	  qs: { addr: pair.publicKey() },
	  json: true
	}, function(error, response, body) {
		try{
			// the JS SDK uses promises for most actions, such as retrieving an account
			server.loadAccount(pair.publicKey()).then(function(account) {
			  console.log('Balances for account: ' + pair.publicKey());
			  account.balances.forEach(function(balance) {
			    console.log('Type:', balance.asset_type, ', Balance:', balance.balance);
			  });
			});
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
	return "Yoopii! New Account";
}
}
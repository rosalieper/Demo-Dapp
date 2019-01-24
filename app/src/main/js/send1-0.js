//sending from one to zero
//destination id is sent from the android ui
//the sender id is from the text file saved on the server

var StellarSdk = require('stellar-sdk');
var fs = require('fs');
var path = require('path');

module.exports = {
  transaction: function (amounts, adress){
    let res;
    StellarSdk.Network.useTestNetwork();
    var server = new StellarSdk.Server('http://172.31.41.130:11626', {allowHttp: true});
    //var server = new StellarSdk.Server('172.31.41.130:11626');

    var sourceKeys = StellarSdk.Keypair
      .fromSecret(getPrivateKey('keys2.txt'));
    //var destinationId = 'GCXEGAU4GMLTYWMJPOIHCHMIIHWSK72ULZP3PD37TCLEWQDR7BIV3VEK';
    var amount = amounts;
   var destinationId = adress;
    // Transaction will hold a built transaction we can resubmit if the result is unknown.
    var transaction;

    // First, check to make sure that the destination account exists.
    // You could skip this, but if the account does not exist, you will be charged
    // the transaction fee when the transaction fails.
    return new Promise(function(resolve, reject) {
     // Do async job
     server.loadAccount('GCXEGAU4GMLTYWMJPOIHCHMIIHWSK72ULZP3PD37TCLEWQDR7BIV3VEK')
      // If the account is not found, surface a nicer error message for logging.
      .catch(StellarSdk.NotFoundError, function (error) {

        throw new Error('The destination account does not exist!');
        reject(error);
      })
      // If there was no error, load up-to-date information on your account.
      .then(function() {
        return server.loadAccount(sourceKeys.publicKey());
      })
      .then(function(sourceAccount) {
        // Start building the transaction.
        transaction = new StellarSdk.TransactionBuilder(sourceAccount)
        //console.log(amount)
        //console.log(adress)
          .addOperation(StellarSdk.Operation.payment({
            destination: destinationId,
            // Because Stellar allows transaction in many currencies, you must
            // specify the asset type. The special "native" asset represents Lumens.
            asset: StellarSdk.Asset.native(),
            //amount: "2"
            amount: amount
          }))
          // A memo allows you to add your own metadata to a transaction. It's
          // optional and does not affect how Stellar treats the transaction.
          .addMemo(StellarSdk.Memo.text('Test Transaction'))
          .build();
        // Sign the transaction to prove you are actually the person sending it.
        transaction.sign(sourceKeys);
        // And finally, send it off to Stellar!
        return server.submitTransaction(transaction);
      })
      .then(function(result) {
        res = 1;
        var balance_amt;


        server.loadAccount(getPublicKey("keys2.txt")).then(function(account) {
          console.log('Balances for account: ' + getPublicKey("keys2.txt"));
          account.balances.forEach(function(balance) {
            balance_amt = balance.balance;
            console.log('Type:', balance.asset_type, ', Balance:', balance_amt);
            message = "Your transaction was succesfull. Your remaining balance is: "+balance_amt;
        	resolve(JSON.stringify(message));
        	console.log('Success! Results'); 
          });
        });
        //You send "+ amount+" XLM to "+adress;

      })
      .catch(function(error) {
        res = 0;
        console.error('Something went wrong!', error);
        // If the result is unknown (no response body, timeout etc.) we simply resubmit
        // already built transaction:
        // server.submitTransaction(transaction);
        return "Something went wrong, Sorry! PLease check the address again.";
      })
       
    })
    /*if()
    {
        console.log("QQQQQQQQQQQQQQQQQQQQQQQQ");
        return message;
    }else if (res == 0)
    {
        return "Something went wrong, Sorry! PLease check the address again.";
    }*/
  }
}

  function getPrivateKey(filename){

    var buffer= fs.readFileSync(filename);
    //convert buffer to string
    contents= buffer.toString();
    var res = contents.split("\n");
    return res[0];
}

//this function returns only the public key with no preceeding message
function getPublicKey(filename){
      var buffer= fs.readFileSync(filename);
      //convert buffer to string
      contents= buffer.toString();
      var res = contents.split("\n");
      var key = res[1].split(" ");
      return key[1];
  }

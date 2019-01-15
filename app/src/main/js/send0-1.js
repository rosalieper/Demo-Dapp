//sending from one to zero
//destination id is sent from the android ui
//the sender id is from the text file saved on the server

var StellarSdk = require('stellar-sdk');
var fs = require('fs');
var path = require('path');

module.exports = {
  transaction: function (amount, adress){
    let res;
    StellarSdk.Network.useTestNetwork();
    var server = new StellarSdk.Server('https://horizon-testnet.stellar.org');
    var sourceKeys = StellarSdk.Keypair
      .fromSecret(getPrivateKey('keys2.txt'));
    //var destinationId = 'GCXEGAU4GMLTYWMJPOIHCHMIIHWSK72ULZP3PD37TCLEWQDR7BIV3VEK';
    var destinationId = adress;
    // Transaction will hold a built transaction we can resubmit if the result is unknown.
    var transaction;

    // First, check to make sure that the destination account exists.
    // You could skip this, but if the account does not exist, you will be charged
    // the transaction fee when the transaction fails.
    server.loadAccount(destinationId)
      // If the account is not found, surface a nicer error message for logging.
      .catch(StellarSdk.NotFoundError, function (error) {
        throw new Error('The destination account does not exist!');
      })
      // If there was no error, load up-to-date information on your account.
      .then(function() {
        return server.loadAccount(sourceKeys.publicKey());
      })
      .then(function(sourceAccount) {
        // Start building the transaction.
        transaction = new StellarSdk.TransactionBuilder(sourceAccount)
          .addOperation(StellarSdk.Operation.payment({
            destination: destinationId,
            // Because Stellar allows transaction in many currencies, you must
            // specify the asset type. The special "native" asset represents Lumens.
            asset: StellarSdk.Asset.native(),
            //amount: "10"
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
        console.log('Success! Results:', result);
      })
      .catch(function(error) {
        res = 0;
        console.error('Something went wrong!', error);
        // If the result is unknown (no response body, timeout etc.) we simply resubmit
        // already built transaction:
        // server.submitTransaction(transaction);
      });

      if(res == 1){
        message = "Your transaction was succesfull."; //You send "+ amount+" XLM to "+adress;
        return message;
      }
      else if (res == 0){
        return "Something went wrong, Sorry! PLease check the address again.";
      }
  }
}

  function getPrivateKey(filename){

    var buffer= fs.readFileSync(filename);
    //convert buffer to string
    contents= buffer.toString();
    var res = contents.split("\n");
    console.log(res[0]);
    return res[0];
}

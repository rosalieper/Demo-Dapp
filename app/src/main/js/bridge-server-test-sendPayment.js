var request = require('request');
var StellarSdk = require('stellar-sdk');

var operation = new Operations();

operation.allowTrust('GCF2J7RUK7FDHJP7HLXEFQ543FLPTSM3G5MKZQO3OD372RSUI2WNIWF2', 'XLM', true, 'GBYTPI2ZJVW57UCSH3AYWQ757N7R4RDJMGLSPDKQOVM4A36V5LOEB7OH');
request.post({
  url: 'http://54.214.183.113:8005/payment',
  form: {
    id: '_id127',
    amount: '1',
    asset_code: 'XLM',
    asset_issuer: 'GBYTPI2ZJVW57UCSH3AYWQ757N7R4RDJMGLSPDKQOVM4A36V5LOEB7OH',
    //asset_issuer: 'GCF2J7RUK7FDHJP7HLXEFQ543FLPTSM3G5MKZQO3OD372RSUI2WNIWF2',
    destination: 'GCF2J7RUK7FDHJP7HLXEFQ543FLPTSM3G5MKZQO3OD372RSUI2WNIWF2',
    source: 'SAV75E2NK7Q5JZZLBBBNUPCIAKABN64HNHMDLD62SZWM6EBJ4R7CUNTZ'
  }
}, function(error, response, body) {
  if (error || response.statusCode !== 200) {
    console.error('ERROR!', error || body);
  }
  else {
    console.log('SUCCESS!', body);
  }
});

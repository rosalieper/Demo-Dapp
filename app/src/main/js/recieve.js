var fs = require('fs');
var path = require('path');

module.exports = {
	getKey: function GetPublicKey(filename){
	    var buffer= fs.readFileSync(filename);
	    //convert buffer to string
	    contents= buffer.toString();
	    var res = contents.split("\n");
	    console.log(res[1]);
	    return res[1];
	}
}


//GetPublicKey('keys2.txt');
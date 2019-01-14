var fs = require('fs');
var path = require('path');

function GetPublicKey(filename){
	//contents contains the content of filename in a buffer formate
    var buffer= fs.readFileSync(filename);
    contents= buffer.toString();
    var res = contents.split("\n");
    console.log(res[1]);
    return res[1];
}

GetPublicKey('keys2.txt');
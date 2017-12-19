'use strict';

var express = require('express');
var xssec = require('@sap/xssec');
var xsenv = require('@sap/xsenv');
var Https = require('https');
var HttpsProxyAgent = require('https-proxy-agent');
var stringify = require('json-stringify');

var app = express();
var PORT = process.env.PORT || 3000;

app.get('/ssbFetch*', function(req, res) {
	var jwtToken = req.headers.authorization;
	// fetch JWT token
	xssec.createSecurityContext(jwtToken.replace("Bearer", "").trim(), xsenv.getServices({
		uaa: 'i076222-xsuaa'
	}).uaa, function(error, securityContext) {
		if (error) {
			res.send(error.toString());
			return;
		}

		// fetch oAuth client token
		securityContext.requestTokenForClient({
			clientid: "sb-clone85b71710f4f14016828aa2a5af7df09a!b259|connectivity!b137",
			clientsecret: "4HKXfCobdHUXZG30tyGkBxqHMmw=",
			url: "https://i076222trial.authentication.sap.hana.ondemand.com"
		}, null, function(oAuthClientTokenError, oAuthClientToken) {
			if (oAuthClientTokenError) {
				res.send(oAuthClientTokenError.toString());
				return;
			}
			var agent = new HttpsProxyAgent({
				host: '10.0.85.1',
				port: 20003
			});
			var ssbReq = Https.request({
				hostname: 'ssb',
				port: 443,
				method: 'GET',
				headers: {
					"SAP-Connectivity-Authentication": jwtToken,
					"Proxy-Authorization": "Bearer " + oAuthClientToken,
					"SAP-Connectivity-SCC-Location_ID": "canary_cf"
				},
				path: '/sap/opu/odata/SSB/SMART_BUSINESS_DESIGNTIME_SRV/INDICATORS?$top=1',
				agent: agent
			}, function(ssbres) {
				ssbres.on('data', function(data) {
					res.send(data.toString());
				});
			});
			ssbReq.on('error', function(err) {
				res.send('ssbrequest error ' + err.toString());
			});
			ssbReq.end();
		});
	});
})

var server = app.listen(PORT, function() {
	var host = server.address().address;
	var port = server.address().port;
	console.log("Example app listening at http://%s:%s", host, port)
})

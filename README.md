# Titanium Android Module for SMS Verification

## Description

Get mobile number & Receive SMS Message

## Accessing the Module

To access this module from JavaScript, you would do the following:

    var ASV = require('ti.andsmsverification');

The ti.andsmsverification variable is a reference to the Module object.

## Method
	1. hasPhonePermission();

	2. hasSMSPermission();

	3. requestPhonePermissions(e);
		- Request both permissins at once	

	4. getMobileNumber();

	5. settingsOpen();
		- Application's setting page open in Android's Setting(System).

## Event
	1. onSMSReceive

## Usage
It's work on everywhere.

    var ASV = require('ti.andsmsverification');
    // check permission
    if (!ASV.hasPhonePermission() || !ASV.hasSMSPermission()) {
      // request permission
      ASV.requestPhonePermissions(function (e) {
        if (e.success) {
          // success
          Ti.API.info('requestPhonePermissions : success');
          registSmsReceiver();
          getMobileNumber();
        } else {
          // nothing
        }
      });
    } else {
      Ti.API.info('requestPhonePermissions : already have');
      registSmsReceiver();
      getMobileNumber();
    }

    // android get mobile number
    function getMobileNumber() {
      var ASV = require('ti.andsmsverification');
      var mobileNumber = ASV.getMobileNumber();
      Ti.API.debug("getMobileNumber :", mobileNumber);
      ...
    }

    // get sms receiver
    function registSmsReceiver() {
      var ASV = require('ti.andsmsverification');
      ASV.addEventListener("onSMSReceive", function(e) {
        Ti.API.debug("onSMSReceive :", e);
        ...
      });
    }

  
## Reference

http://i5on9i.blogspot.kr/2015/10/blog-post_70.html
http://itmir.tistory.com/424

## Author

Dongwoo Gim, BOXOUT THINKERS

## License

MIT License

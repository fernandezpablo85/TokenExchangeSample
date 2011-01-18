#LinkedIn OAuth exchange sample code (Java)

###Motivation

This is a fully working sample to show you how to exchange 
an OAuth 2.0 token (the ones that the javascript API uses) for 
a common OAuth 1.0a token

###How-To

The whole application runs over SSL, this is because the cookie that LinkedIn's API
sends is a secure one (read more about [seucre cookies](http://en.wikipedia.org/wiki/HTTP_cookie#Secure_Cookie)).

For this we first need to create a self-signed ssl certificate, with keytool:

    $ keytool -genkey -alias jetty6 -keyalg RSA -keystore target/jetty-ssl.keystore -storepass jetty6 -keypass jetty6 -dname "CN=your name or domain"

once this is done just head to the root of the project and run

    mvn jetty:run
    
you'll find the sample application on [localhost](https://localhost:8433/exchange)


###Workflow explained

The app is a landing JSP that makes an XHR request to another (secured) endpoint in the same domain.

This request carries the cookie with all the necessary data to make the exchange, this cookie is named 'linkedin_oauth_APIKEY' where "APIKEY" is your 
actual Api key (obtained from developer.linkedin.com)

The application then verifies that the cookie is valid, making a simple signature check (see [this method](https://github.com/fernandezpablo85/TokenExchangeSample/blob/master/src/main/java/com/linkedin/oauth/ExchangeService.java#L67))
This step is **really** important and we encourage you to do the same, besides it's really easy :)

Then if everything looks good, we perform the exchange step and retrieve the OAuth1.0a token
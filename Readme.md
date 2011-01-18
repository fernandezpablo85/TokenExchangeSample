#h1 LinkedIn OAuth exchange sample code (Java)

#h3 Motivation

This is a fully working sample to show you how to exchange 
an OAuth 2.0 token (the ones that the javascript API uses) for 
a common OAuth 1.0a token

#h3 How-To

The whole application runs over SSL, this is because the cookie that LinkedIn's API
sends is a secure one (read more about [seucre cookies](http://en.wikipedia.org/wiki/HTTP_cookie#Secure_Cookie)).

For this we first need to create a self-signed ssl certificate, with keytool:

    $ keytool -genkey -alias jetty6 -keyalg RSA -keystore target/jetty-ssl.keystore -storepass jetty6 -keypass jetty6 -dname "CN=your name or domain"

once this is done just head to the root of the project and run

    mvn jetty:run
    
you'll find the sample application on [localhost](https://localhost:8433/exchange)
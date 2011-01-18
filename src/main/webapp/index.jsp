<%@ page import="com.linkedin.oauth.ExchangeService" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
  <script src='http://platform.linkedin.com/in.js'>
    api_key: <%= ExchangeService.KEY %>
    onLoad: window.frameworkLoaded
    credentials_cookie: true
  </script>
  <script>
    function frameworkLoaded () {
       IN.Event.on(IN, "auth", window.onAuth);
    }
    
    function onAuth () {
      $('#contents').append('<p><a id="obtain" href="#">read and exchange the token</a> from the secured session cookie</p>');
      $('#obtain').click(function(){
        $.get("./process_cookie.jsp", function(response) {
          var response = JSON.parse(response);
          $('#contents').append('<h3>The original OAuth 2.0 token is:</h3>');
          $('#contents').append('<code>' + response.access_token + '</code>');
          $('#contents').append('<h3>The response from the exchange is:</h3>');
          $('#contents').append('<code>' + response.oauth_one_token + '</code>');
        });
      });
    }
  </script>
  <title>Connect Token Exchange</title>
</head>
<body>
  <h2>Connect Token Exchange Application</h2>
  <script type="IN/Login"></script>
  <div id='contents'></div>
</body>
</html>

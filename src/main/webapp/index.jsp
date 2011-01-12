<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
  <script src='http://platform.linkedin.com/in.js'>
    api_key: TRLHWeqqa-r0j2vBk6zavp-tWTbjQtfAjn2vc4edcdKxb_QmVcbmLOTB93atIhSV
    onLoad: window.frameworkLoaded
  </script>
  <script>
    function frameworkLoaded () {
       IN.Event.on(IN, "auth", window.onAuth);
    }
    
    function onAuth () {
      $('#contents').append('<p>Got the OAuth 2.0 token: <strong>' + IN.ENV.auth.oauth_token + '</strong></p>');
      $('#contents').append('<p><a href="./process_cookie.jsp?token=' + IN.ENV.auth.oauth_token + '"> exchange it </a> for a 1.0 Token</p>');
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

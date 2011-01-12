<%@ page import="com.linkedin.oauth.helper.Utils" %>
<html>
  <head>
  <title>Exchanged</title>
  </head>
  <body>
    <h2>OAuth 2.0 token</h2>
    <code><%= request.getParameter("token") %></code>
    <h3>exchanged for</h3>
    <h2>OAuth 1.0 token response</h2>
    <code><%= Utils.exchangeToken(request.getParameter("token")) %></code> 
  </body>
<html>
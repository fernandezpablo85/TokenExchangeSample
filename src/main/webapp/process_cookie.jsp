<%@ page import="com.linkedin.oauth.helper.Utils" %>
<%@ page import="javax.servlet.http.Cookie" %>
<% String exchanged = ""; %>
<% 
for(Cookie c : request.getCookies()) 
{
  if(c.getName().equals("linkedin_oauth_" + Utils.KEY)) 
  {
    String value = c.getValue();
    exchanged = Utils.exchangeToken(value);
  }
}
%>

<%= exchanged %>
<%@ page import="com.linkedin.oauth.ExchangeService" %>
<%@ page import="javax.servlet.http.Cookie" %>
<% String exchanged = ""; %>
<% ExchangeService service = ExchangeService.instance; %>
<% 
for(Cookie c : request.getCookies()) 
{
  if(c.getName().equals("linkedin_oauth_" + ExchangeService.KEY)) 
  {
    String value = c.getValue();
    exchanged = service.exchangeToken(value);
  }
}
%>

<%= exchanged %>

package com.linkedin.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

public class ExchangeService
{
  public static final String OAUTH2_ACCESS_TOKEN = "xoauth_oauth2_access_token";
  public static final String KEY = "TRLHWeqqa-r0j2vBk6zavp-tWTbjQtfAjn2vc4edcdKxb_QmVcbmLOTB93atIhSV";
  public static final String SECRET = "MPXgUasPngzU71aThOozHKXGRi0s9f7AtmssFdculA9A0FJ8x1dquTAc_1pRc_qT";
  public static final String ACCESS_TOKEN_ENDPOINT = "https://api.linkedin.com/uas/oauth/accessToken";
  public static final ExchangeService instance = new ExchangeService();
  
  private Gson parser;
  
  public ExchangeService()
  {
    this.parser = new Gson();
  }
  
  public String exchangeToken(String _cookie)
  {
    OAuthCookie cookie = parseCookie(_cookie);
    
    // We need this in order to accept all SSL certs
    Utils.trustSelfSignedCertificates();
    OAuthService service = new ServiceBuilder()
                                  .apiKey(KEY)
                                  .apiSecret(SECRET)
                                  .provider(LinkedInApi.class)
                                  .build();
    
    // Exchange 2.0 token for 1.0a (long lived)
    OAuthRequest request = new OAuthRequest(Verb.POST, ACCESS_TOKEN_ENDPOINT);
    
    // Add the 2.0 token as a parameter
    request.addBodyParameter(OAUTH2_ACCESS_TOKEN, cookie.access_token);
    
    // Use an empty 1.0a access_token
    Token token = new Token("","");
    
    // Sign and then send the request
    service.signRequest(token, request);
    Response response = request.send();
    cookie.oauth_one_token = response.getBody();
    return parser.toJson(cookie);
  }
  
  private OAuthCookie parseCookie(String cookie)
  { 
    return parser.fromJson(cookie, OAuthCookie.class);
  }
}

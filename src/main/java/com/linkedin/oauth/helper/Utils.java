package com.linkedin.oauth.helper;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Utils
{
  public static final String KEY = "TRLHWeqqa-r0j2vBk6zavp-tWTbjQtfAjn2vc4edcdKxb_QmVcbmLOTB93atIhSV";
  private static final String ACCESS_TOKEN_ENDPOINT = "https://api.linkedin.com/uas/oauth/accessToken";
  
  public static String exchangeToken(String token2)
  {
    // We need this in order to accept all SSL certs
    fixHttps();
    OAuthService service = new ServiceBuilder()
                                  .apiKey(Utils.KEY)
                                  .apiSecret("MPXgUasPngzU71aThOozHKXGRi0s9f7AtmssFdculA9A0FJ8x1dquTAc_1pRc_qT")
                                  .provider(LinkedInApi.class)
                                  .build();
    
    // Exchange 2.0 token for 1.0a (long lived)
    OAuthRequest request = new OAuthRequest(Verb.POST, ACCESS_TOKEN_ENDPOINT);
    
    // Add the 2.0 token as a parameter
    request.addBodyParameter("xoauth_oauth2_access_token", token2);
    
    // Use an empty 1.0a access_token
    Token token = new Token("","");
    
    // Sign and then send the request
    service.signRequest(token, request);
    Response response = request.send();
    return response.getBody();
  }
  
  // Plumbing code to accept all certificates
  private static void fixHttps()
  {
    TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }
    };
    SSLContext sc;
    try
    {
      sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
        public boolean verify(String string,SSLSession ssls) {
        return true;
        }
        });
    } catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    } catch (KeyManagementException e)
    {
      e.printStackTrace();
    }
  }
}

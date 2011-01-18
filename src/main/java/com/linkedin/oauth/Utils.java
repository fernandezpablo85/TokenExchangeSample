package com.linkedin.oauth;

import javax.net.ssl.*;

public class Utils
{
  public static void trustSelfSignedCertificates()
  {
    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
    {
      public java.security.cert.X509Certificate[] getAcceptedIssuers()
      {
        return null;
      }

      public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }

      public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
    }};
    
    SSLContext sc;
    try
    {
      sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
      {
        public boolean verify(String string, SSLSession ssls)
        {
          return true;
        }
      });
    } 
    catch (Exception e)
    {
      e.printStackTrace();
    } 
  }
}

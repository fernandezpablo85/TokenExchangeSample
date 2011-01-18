package com.linkedin.oauth;

import java.util.*;

public class OAuthCookie
{
  public OAuthCookie(){}
  
  String signature_method;
  String signature_order;
  String access_token;
  String signature;
  String member_id;
  String oauth_one_token;
  
  public Map<String, String> toMap()
  {
    Map<String, String> result = new HashMap<String, String>();
    result.put("signature_method", signature_method);
    result.put("signature_order", signature_order);
    result.put("access_token", access_token);
    result.put("signature", signature);
    result.put("member_id", member_id);
    return result;
  }

}

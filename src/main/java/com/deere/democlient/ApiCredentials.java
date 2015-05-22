package com.deere.democlient;

import com.deere.rest.OAuthClient;
import com.deere.rest.OAuthToken;

public abstract class ApiCredentials {
    
    public static final OAuthClient CLIENT = new OAuthClient("Replace with App id from developer.deere.com", "replace with secret for the App from developer.deere.com");
    
    public static final OAuthToken TOKEN = new OAuthToken("Replace with actual token generated after running GetOauthToken.java", "Replace with actual token secret after running GetOauthToken.java");
}

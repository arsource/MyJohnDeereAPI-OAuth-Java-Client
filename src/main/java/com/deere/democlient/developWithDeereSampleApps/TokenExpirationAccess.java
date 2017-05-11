package com.deere.democlient.developWithDeereSampleApps;

import com.deere.api.axiom.generated.v3.Token;
import com.deere.democlient.brokers.CurrentTokenBroker;

public class TokenExpirationAccess {
    public static void main(String[] args) {
        CurrentTokenBroker currentTokenBroker = new CurrentTokenBroker();
        Token token = currentTokenBroker.getCurrentTokenDetails();
        System.out.println("Expiration Date: " + token.getExpirationDate());
    }
}

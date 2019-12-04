package com.example.etherwid;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSettings {
    private String userPoolId = "us-east-2_HoMu90HDP";
    private String clientId = "";
    private String clientSecret = "";
    private Regions cognitoRegion = Regions.US_EAST_2;

    private Context context;

    public CognitoSettings(Context context){
        this.context = context;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Regions getCognitoRegion() {
        return cognitoRegion;
    }

    public void setCognitoRegion(Regions cognitoRegion) {
        this.cognitoRegion = cognitoRegion;
    }

    public CognitoUserPool getUserPool() {
        return new CognitoUserPool(context, userPoolId, clientId, clientSecret, cognitoRegion);
    }




}

package com.logtari.flowpaywallet.common;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException (String message){
        super(message);
    }
}

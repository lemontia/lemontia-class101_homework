package net.class101.etc;

public class SoldOutException extends Exception {
    public SoldOutException(String msg){
        super(msg);
    }
}

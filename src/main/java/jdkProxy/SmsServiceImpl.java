package jdkProxy;

public class SmsServiceImpl implements SmsService{
    public String send(String msg){
        System.out.println("send message: " + msg);
        return msg;
    }
}

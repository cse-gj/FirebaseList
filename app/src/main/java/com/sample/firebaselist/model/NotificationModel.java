package com.sample.firebaselist.model;

//알림 메시지
public class NotificationModel {
    public String to;
    public Data data = new Data();
    public Notification notification = new Notification(); //초기화

    //Notification은 import를 하면 안된다.

    public static class Notification {
        public String title;
        public String text;
    }

    public static class Data {
        public String title;
        public String text;
    }
}

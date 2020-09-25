package com.yeyi.experience.design.behavioral;

import java.util.Date;

/**
 * 中介者模式
 */
public class MediatorPattern {

    //中介
    static class ChatRoom {
        public static void showMessage(User user, String message){
            System.out.println(new Date().toString()
                    + " [" + user.getName() +"] : " + message);
        }
    }

    static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String name){
            this.name  = name;
        }

        public void sendMessage(String message){
            ChatRoom.showMessage(this,message);
        }
    }

    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}

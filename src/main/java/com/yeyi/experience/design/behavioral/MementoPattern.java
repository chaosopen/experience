package com.yeyi.experience.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 *
 * 应用实例： 1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctri + z。 4、IE 中的后退。 4、数据库的事务管理。
 */
public class MementoPattern {

    static class Memento {
        private String state;

        public Memento(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    static class Originator {
        private String state;

        public void setState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }

        public Memento saveStateToMemento(){
            return new Memento(state);
        }

        public void getStateFromMemento(Memento Memento){
            state = Memento.getState();
        }
    }

    static class CareTaker {
        private List<Memento> mementoList = new ArrayList<>();

        public void add(Memento state){
            mementoList.add(state);
        }

        public Memento get(int index){
            return mementoList.get(index);
        }
    }

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}

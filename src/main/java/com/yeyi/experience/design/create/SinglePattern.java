package com.yeyi.experience.design.create;

/**
 * 单例模式
 * @author mahaoran
 */
public class SinglePattern {

    /**
     * volatile防止重排序（指创建对象的时候）
     * 创建对象需要三个步骤
     * 1.给 singlePattern 分配内存
     * 2.创建 singlePattern 对象实例
     * 3.将实例引用到分配内存空间
     * 指令重排序不能保证2和3顺序，导致被引用（不为null），但是实例没有值就会出现问题
     */
    private volatile static SinglePattern singlePattern;

    private SinglePattern(){}

    public static SinglePattern getInstance() {
        if (singlePattern == null) {
            /**
             * synchonized可以保证可见性
             * 里面也要做非空判断
             */
            synchronized (SinglePattern.class) {
                //双重校验，防止多次创建
                if (singlePattern == null) {
                    singlePattern = new SinglePattern();
                }
            }
        }
        return singlePattern;
    }
}

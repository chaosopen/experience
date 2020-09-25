package com.yeyi.experience.design.struct;

import java.util.HashMap;

/**
 * 享元模式
 * 主要用于减少创建对象的数量，尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象
 *
 * 应用实例： 1、JAVA 中的 String，如果有则返回，如果没有则创建一个字符串保存在字符串缓存池里面。 2、数据库的数据池。
 */
public class FlyweightPattern {
    interface Shape {
        void draw();
    }

    //圆形
    static class Circle implements Shape {
        private String color;
        private int x;
        private int y;
        private int radius;

        public Circle(String color){
            this.color = color;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Circle: Draw() [Color : " + color
                    +", x : " + x +", y :" + y +", radius :" + radius);
        }
    }

    static class ShapeFactory {
        private static final HashMap<String, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = (Circle)circleMap.get(color);

            if(circle == null) {
                circle = new Circle(color);
                circleMap.put(color, circle);
                System.out.println("Creating circle of color : " + color);
            }
            return circle;
        }
    }

    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
            Circle circle =
                    (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}

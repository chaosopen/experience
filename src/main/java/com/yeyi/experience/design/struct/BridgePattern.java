package com.yeyi.experience.design.struct;

/**
 * 桥接模式
 * 把抽象化与实现化解耦
 * 按照面向对象抽象化拆分
 *
 * 应用实例： 1、猪八戒从天蓬元帅转世投胎到猪，转世投胎的机制将尘世划分为两个等级，即：灵魂和肉体，前者相当于抽象化，后者相当于实现化。生灵通过功能的委派，调用肉体对象的功能，使得生灵可以动态地选择。
 * 2、墙上的开关，可以看到的开关是抽象的，不用管里面具体怎么实现的。
 */
public class BridgePattern {

    interface DrawAPI {
        void drawCircle(int radius, int x, int y);
    }

    static class RedCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: red, radius: "
                    + radius +", x: " +x+", "+ y +"]");
        }
    }

    static class GreenCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: "
                    + radius +", x: " +x+", "+ y +"]");
        }
    }

    static abstract class Shape {
        protected DrawAPI drawAPI;
        protected Shape(DrawAPI drawAPI){
            this.drawAPI = drawAPI;
        }
        public abstract void draw();
    }

    static class Circle extends Shape {
        private int x, y, radius;

        public Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public void draw() {
            drawAPI.drawCircle(radius,x,y);
        }
    }

    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

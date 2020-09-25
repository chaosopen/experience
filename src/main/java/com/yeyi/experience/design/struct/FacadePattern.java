package com.yeyi.experience.design.struct;

/**
 * 外观模式
 * 一个方法，里面包含了很多操作，client只需要关注调用这个方法，把一群操作，封装成一个方法。
 *
 * 应用实例： 1、去医院看病，可能要去挂号、门诊、划价、取药，让患者或患者家属觉得很复杂，如果有提供接待人员，只让接待人员来处理，就很方便。
 * 2、JAVA 的三层开发模式。
 */
public class FacadePattern {

    interface Shape {
        void draw();
    }

    //长方形
    static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Rectangle::draw()");
        }
    }

    //正方形
    static class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Square::draw()");
        }
    }

    //圆形
    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Circle::draw()");
        }
    }

    static class ShapeMaker {
        private Shape circle;
        private Shape rectangle;
        private Shape square;

        public ShapeMaker() {
            circle = new Circle();
            rectangle = new Rectangle();
            square = new Square();
        }

        public void drawCircle(){
            circle.draw();
        }
        public void drawRectangle(){
            rectangle.draw();
        }
        public void drawSquare(){
            square.draw();
        }
    }

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}

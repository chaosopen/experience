package com.yeyi.experience.design.struct;

/**
 * 装饰器模式
 * 为让自己的能力增强，使得增强后的自己能够使用更多的方法，拓展在自己基础之上的功能的，叫装饰器模式
 *
 * 应用实例： 1、孙悟空有 72 变，当他变成"庙宇"后，他的根本还是一只猴子，但是他又有了庙宇的功能。
 * 2、不论一幅画有没有画框都可以挂在墙上，但是通常都是有画框的，并且实际上是画框被挂在墙上。
 * 在挂在墙上之前，画可以被蒙上玻璃，装到框子里；这时画、玻璃和画框形成了一个物体。
 */
public class DecoratorPattern {

    //形状接口
    interface Shape {
        void draw();
    }

    //长方形
    static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    //圆形
    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    //形状装饰者抽象
    static abstract class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        public ShapeDecorator(Shape decoratedShape){
            this.decoratedShape = decoratedShape;
        }

        public void draw(){
            decoratedShape.draw();
        }
    }

    static class RedShapeDecorator extends ShapeDecorator {

        public RedShapeDecorator(Shape decoratedShape) {
            super(decoratedShape);
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape){
            System.out.println("Border Color: Red");
        }
    }

    public static void main(String[] args) {

        Shape circle = new Circle();
//        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
//        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}

package com.yeyi.experience.design.create;

import java.util.Hashtable;

/**
 * 原型模式
 * 用于创建重复的对象，缓存对象，需要创建重复对象的时候克隆一个
 */
public class PrototypePattern {

    static abstract class Shape implements Cloneable {

        private String id;
        protected String type;

        abstract void draw();

        public String getType(){
            return type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    static class Rectangle extends Shape {

        public Rectangle(){
            type = "Rectangle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    static class Square extends Shape {

        public Square(){
            type = "Square";
        }

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    static class Circle extends Shape {

        public Circle(){
            type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    static class ShapeCache {

        private static Hashtable<String, Shape> shapeMap
                = new Hashtable<String, Shape>();

        public static Shape getShape(String shapeId) {
            Shape cachedShape = shapeMap.get(shapeId);
            return (Shape) cachedShape.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        // shapeMap.put(shapeKey, shape);
        // 例如，我们要添加三种形状
        public static void loadCache() {
            Circle circle = new Circle();
            circle.setId("1");
            shapeMap.put(circle.getId(),circle);

            Square square = new Square();
            square.setId("2");
            shapeMap.put(square.getId(),square);

            Rectangle rectangle = new Rectangle();
            rectangle.setId("3");
            shapeMap.put(rectangle.getId(),rectangle);
        }
    }

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}

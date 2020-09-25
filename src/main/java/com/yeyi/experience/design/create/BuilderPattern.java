package com.yeyi.experience.design.create;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * 建造者模式是将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 */
public class BuilderPattern {
    public interface Item {
        String name();
        //包装
        Packing packing();
        float price();
    }
    public interface Packing {
        String pack();
    }

    //包装纸
    static class Wrapper implements Packing {

        @Override
        public String pack() {
            return "Wrapper";
        }
    }

    //瓶子
    static class Bottle implements Packing {

        @Override
        public String pack() {
            return "Bottle";
        }
    }

    //汉堡
    static abstract class Burger implements Item {

        @Override
        public Packing packing() {
            return new Wrapper();
        }

        @Override
        public abstract float price();
    }

    //冷饮
    static abstract class ColdDrink implements Item {

        @Override
        public Packing packing() {
            return new Bottle();
        }

        @Override
        public abstract float price();
    }

    //饼
    static class VegBurger extends Burger {

        @Override
        public float price() {
            return 25.0f;
        }

        @Override
        public String name() {
            return "Veg Burger";
        }
    }

    //汉堡
    static class ChickenBurger extends Burger {

        @Override
        public float price() {
            return 50.5f;
        }

        @Override
        public String name() {
            return "Chicken Burger";
        }
    }

    //可口可乐
    static class Coke extends ColdDrink {

        @Override
        public float price() {
            return 30.0f;
        }

        @Override
        public String name() {
            return "Coke";
        }
    }

    //百事可乐
    static class Pepsi extends ColdDrink {

        @Override
        public float price() {
            return 35.0f;
        }

        @Override
        public String name() {
            return "Pepsi";
        }
    }

    static class Meal {
        private List<Item> items = new ArrayList<Item>();

        public void addItem(Item item){
            items.add(item);
        }

        public float getCost(){
            float cost = 0.0f;
            for (Item item : items) {
                cost += item.price();
            }
            return cost;
        }

        public void showItems(){
            for (Item item : items) {
                System.out.print("Item : "+item.name());
                System.out.print(", Packing : "+item.packing().pack());
                System.out.println(", Price : "+item.price());
            }
        }
    }

    static class MealBuilder {

        public Meal prepareVegMeal (){
            Meal meal = new Meal();
            meal.addItem(new VegBurger());
            meal.addItem(new Coke());
            return meal;
        }

        public Meal prepareNonVegMeal (){
            Meal meal = new Meal();
            meal.addItem(new ChickenBurger());
            meal.addItem(new Pepsi());
            return meal;
        }
    }

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}

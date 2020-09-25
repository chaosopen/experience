package com.yeyi.experience.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 *
 * 应用实例：struts 1 中的 action 核心控制器 ActionServlet 只有一个，相当于 Invoker，
 * 而模型层的类会随着不同的应用有不同的模型类，相当于具体的 Command。
 */
public class CommandPattern {

    //命令接口
    interface Order {
        void execute();
    }

    static class Stock {

        private String name = "ABC";
        private int quantity = 10;

        public void buy(){
            System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] bought");
        }
        public void sell(){
            System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] sold");
        }
    }

    static class BuyStock implements Order {
        private Stock abcStock;

        public BuyStock(Stock abcStock){
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.buy();
        }
    }

    static class SellStock implements Order {
        private Stock abcStock;

        public SellStock(Stock abcStock){
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.sell();
        }
    }

    //调用类
    static class Broker {
        private List<Order> orderList = new ArrayList<Order>();

        public void takeOrder(Order order){
            orderList.add(order);
        }

        public void placeOrders(){
            for (Order order : orderList) {
                order.execute();
            }
            orderList.clear();
        }
    }

    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}

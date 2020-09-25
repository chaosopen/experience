package com.yeyi.experience.design.behavioral;

/**
 * 策略模式
 * 定义的固定的接口并有一系列不同类型的具体实现，能够在程序中动态使用这些具体的实现。
 *
 * 策略模式常与代理模式、工厂模式一起使用，以实现策略之间的灵活切换
 *
 * 优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
 * 缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。
 *
 * 注意事项：策略模式如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 */
public class StrategyPattern {

    static abstract class Strategy {

        private String value;

        protected Strategy(String value){
            this.value  = value;
        }

        abstract int doOperation(int num1, int num2);


        protected String getValue() {
            return this.value;
        }
    }

    /**
     * 加操作
     */
    static class OperationAdd extends Strategy{

        public OperationAdd() {
            super("+");
        }

        @Override
        public int doOperation(int num1, int num2) {
            super.value = "+";
            return num1 + num2;
        }
    }

    /**
     * 减操作
     */
    static class OperationSubtract extends Strategy{

        public OperationSubtract() {
            super("-");
        }
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    /**
     * 乘操作
     */
    static class OperationMultiply extends Strategy{

        public OperationMultiply() {
            super("*");
        }
        @Override
        public int doOperation(int num1, int num2) {
            super.value = "*";
            return num1 * num2;
        }

    }

    /**
     * 上下文 控制 客户端
     * 1.context是否是必须的？
     * 这个类其实是做上下文操作的，当上下文特别简单的时候，就显示不到他的作用了，
     * 例如对策略执行完的数据处理等等，都是需要写到context的。
     * 因为需要变更还可能只是处理处理数据的方式，而不是策略本身，
     * 加上这个context符合单一原则的，当上下文处理方式变了，只要改动context的代码就行，
     * 不需要对client处理。最好是留这个类。
     * 针对不同的行为做一些相对应的处理
     */
    static class Context {
        private Strategy strategy;

        public Context(Strategy strategy){
            this.strategy = strategy;
        }

        public int executeStrategy(int num1, int num2){
            int value = strategy.doOperation(num1, num2);
            System.out.println("本次执行操作为：" + strategy.getValue());
            return value;
        }
    }

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}

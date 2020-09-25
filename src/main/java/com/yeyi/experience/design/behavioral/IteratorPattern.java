package com.yeyi.experience.design.behavioral;

/**
 * 迭代器模式
 *
 * 关键代码：定义接口：hasNext, next。
 *
 * 应用实例：JAVA 中的 iterator。
 *
 * 使用场景： 1、访问一个聚合对象的内容而无须暴露它的内部表示。
 * 2、需要为聚合对象提供多种遍历方式。 3、为遍历不同的聚合结构提供一个统一的接口。
 */
public class IteratorPattern {

    interface Iterator {
        boolean hasNext();
        Object next();
    }

    //容器
    interface Container {
        Iterator getIterator();
    }

    static class NameRepository implements Container {
        public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator {

            int index;

            @Override
            public boolean hasNext() {
                return index < names.length;
            }

            @Override
            public Object next() {
                if(this.hasNext()){
                    return names[index++];
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}

package com.yeyi.experience.design.struct;

/**
 * 代理模式
 * 让别人帮助你做你并不关心的事情，叫代理模式
 */
public class AgentPattern {
    public interface Image {
        void display();
    }
    static class RealImage implements Image {

        private String fileName;

        public RealImage(String fileName){
            this.fileName = fileName;
//            loadFromDisk(fileName);
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }

        private void loadFromDisk(String fileName){
            System.out.println("Loading " + fileName);
        }
    }
    static class ProxyImage implements Image{

        private RealImage realImage;
        private String fileName;

        public ProxyImage(String fileName){
            this.fileName = fileName;
            realImage = new RealImage(this.fileName);
        }

        @Override
        public void display() {
            realImage.display();
        }
    }
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}

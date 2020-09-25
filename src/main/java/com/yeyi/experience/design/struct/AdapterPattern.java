package com.yeyi.experience.design.struct;

/**
 * 适配器模式
 * 作为两个不兼容的接口之间的桥梁
 * 举个真实的例子，读卡器是作为内存卡和笔记本之间的适配器。您将内存卡插入读卡器，
 * 再将读卡器插入笔记本，这样就可以通过笔记本来读取内存卡
 * 代码示例，音频播放器设备只能播放 mp3 文件，通过使用一个更高级的音频播放器来播放 vlc 和 mp4 文件
 * 自身执行不了的方法，丢给适配器来完成执行
 *
 * 应用实例： 1、美国电器 110V，中国 220V，就要有一个适配器将 110V 转化为 220V。
 * 2、JAVA JDK 1.1 提供了 Enumeration 接口，而在 1.2 中提供了 Iterator 接口，想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式。
 * 3、在 LINUX 上运行 WINDOWS 程序。 4、JAVA 中的 jdbc。
 */
public class AdapterPattern {

    interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    //先进的播放器
    interface AdvancedMediaPlayer {
        void playVlc(String fileName);
        void playMp4(String fileName);
    }

    static class VlcPlayer implements AdvancedMediaPlayer{
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: "+ fileName);
        }

        @Override
        public void playMp4(String fileName) {
            //什么也不做
        }
    }

    static class Mp4Player implements AdvancedMediaPlayer{

        @Override
        public void playVlc(String fileName) {
            //什么也不做
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: "+ fileName);
        }
    }

    static class MediaAdapter implements MediaPlayer {

        AdvancedMediaPlayer advancedMusicPlayer;

        public MediaAdapter(String audioType){
            if(audioType.equalsIgnoreCase("vlc") ){
                advancedMusicPlayer = new VlcPlayer();
            } else if (audioType.equalsIgnoreCase("mp4")){
                advancedMusicPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if(audioType.equalsIgnoreCase("vlc")){
                advancedMusicPlayer.playVlc(fileName);
            }else if(audioType.equalsIgnoreCase("mp4")){
                advancedMusicPlayer.playMp4(fileName);
            }
        }
    }

    static class AudioPlayer implements MediaPlayer {
        MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {

            //播放 mp3 音乐文件的内置支持
            if(audioType.equalsIgnoreCase("mp3")){
                System.out.println("Playing mp3 file. Name: "+ fileName);
            }
            //mediaAdapter 提供了播放其他文件格式的支持
            else if(audioType.equalsIgnoreCase("vlc")
                    || audioType.equalsIgnoreCase("mp4")){
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            }
            else{
                System.out.println("Invalid media. "+
                        audioType + " format not supported");
            }
        }
    }
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}


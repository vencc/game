package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by huanghuanhuan on 9/30/16.
 */
public class AudioPlayer implements Runnable {
  private String fileName;
  public AudioPlayer(String fileName){
    this.fileName=fileName;
  }
  public void run(){
    File soundFile=new File(fileName);
    if (!soundFile.exists()) {
      System.err.println("Wave file not found:" + fileName);
      return;
    }

    AudioInputStream audioInputStream = null; // 创建音频输入流对象
    try {
      audioInputStream = AudioSystem.getAudioInputStream(soundFile); // 创建音频对象
    } catch (UnsupportedAudioFileException e1) {
      e1.printStackTrace();
      return;
    } catch (IOException e1) {
      e1.printStackTrace();
      return;
    }

    AudioFormat format = audioInputStream.getFormat(); // 音频格式
    SourceDataLine auline = null; // 源数据线
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

    try {
      auline = (SourceDataLine) AudioSystem.getLine(info);
      auline.open(format);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
      return;
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    auline.start();
    int nBytesRead = 0;
    byte[] abData = new byte[100000];

    try {
      while (nBytesRead != -1) {
        nBytesRead = audioInputStream.read(abData, 0, abData.length);
        if (nBytesRead >= 0)
          auline.write(abData, 0, nBytesRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    } finally {
      auline.drain();
      auline.close();
    }
  }
}

package entity;

import java.io.Serializable;

import entity.User;

/**
 * 功能: 个人信息实体类
 */
public class User implements Serializable {
  public User(String name, String fileName, int winNum,int loseNum,int tiedNum) {
    this.name = name;
    this.loseNum=loseNum;
    this.tiedNum=tiedNum;
    this.winNum = winNum;
    this.fileName = fileName;
  }

  public User(String name) {
    this.name = name;
    winNum = 0;
    loseNum = 0;
    tiedNum = 0;
  }

  private String fileName;  // 头像路径

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  private String name;  // 姓名
  private int winNum;  // 战绩
  private int loseNum;
  private int tiedNum;

  public int getLoseNum() {
    return loseNum;
  }

  public int getTiedNum() {
    return tiedNum;
  }

  public void setLoseNum(int loseNum) {
    this.loseNum = loseNum;
  }

  public void setTiedNum(int tiedNum) {
    this.tiedNum = tiedNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWinNum() {
    return winNum;
  }

  public void setWinNum(int winNum) {
    this.winNum = winNum;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj==null) return false;
    User user=(User)obj;
    if(getName().equals(user.getName())) return true;
    return false;
  }
}

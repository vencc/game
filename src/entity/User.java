package entity;

import java.io.Serializable;

/**
 * 功能: 个人信息实体类
 */
public class User implements Serializable {
  public User(String name){
    this.name=name;
    winNum=0;
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
  
}

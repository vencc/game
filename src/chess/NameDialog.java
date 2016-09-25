package chess;

import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能: 姓名输入框
 * 作者: 黄欢欢   时间: 2016-09-21
 */
public class NameDialog extends JDialog {
  private Home home;
  private User user;
  private NameDialog nameDialog=this;
  private JTextField nameTextField=new JTextField();
  private JLabel nameTip=new JLabel("请输入姓名");
  private JButton ok=new JButton("确定");
  private JButton cancel=new JButton("取消");

  public NameDialog(Home home, User user){
    this.user=user;
    this.setModal(true);
    this.home=home;
    this.setResizable(false);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setBounds((int)(home.getWidth()*0.4),(int)(home.getHeight()*0.4),260,150);
    this.setLayout(null);

    nameTip.setBounds(30,20,100,15);
    nameTextField.setBounds(30,40,200,30);
    ok.setBounds(30,80,90,30);
    cancel.setBounds(140,80,90,30);

    addAction();

    this.add(nameTip);
    this.add(ok);
    this.add(nameTextField);
    this.add(cancel);
    this.setVisible(true);
  }

  /**
   * 功能: 给成员属性添加监听事件
   * 作者: 黄欢欢   时间: 2016-09-21
   */
  private void addAction(){
    ok.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        home.toRoomList();
        nameDialog.dispose();
      }
    });
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nameDialog.dispose();
      }
    });
  }
}

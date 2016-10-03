package chess;


import entity.UpdatePicture;
import entity.User;

import javax.swing.*;

import msg.ClientLoginMsg;
import net.MyClient;
import net.MyServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能: 姓名输入框
 * 作者: 黄欢欢   时间: 2016-09-21
 */
public class NameDialog extends JDialog {
  private Home home;
  private NameDialog nameDialog = this;
  private JTextField nameTextField = new JTextField();
  private JLabel nameTip = new JLabel("请输入姓名");
  private JButton ok = new JButton("确定");
  private JButton cancel = new JButton("取消");

  public NameDialog(Home home) {
    MyClient.getMyClient().setNamedialog(this);
    this.setModal(true);
    this.home = home;
    this.setResizable(false);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setBounds((int) (home.getWidth() * 0.4), (int) (home.getHeight() * 0.4), 260, 150);
    this.setLayout(null);

    nameTip.setBounds(30, 20, 100, 15);
    nameTextField.setBounds(30, 40, 200, 30);
    ok.setBounds(30, 80, 90, 30);
    cancel.setBounds(140, 80, 90, 30);

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
  private void addAction() {
    ok.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!MyClient.getMyClient().isConnected()) {
          if(!MyClient.getMyClient().connect()) return;
        }else{

        }
          System.out.println("网络成功连接");
          String name=nameTextField.getText();
        if(name.trim()!="") {
          User user = MyServer.getMyServer().findUser(name);
          if (user == null) {
            String[] options = {"创建", "重新输入"};
            int res = JOptionPane.showOptionDialog(null, "确定创建新用户吗", "当前输入的名字是新用户",
                JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
                null, options, options[0]);
            if (res == 0) {
              nameDialog.dispose();
              user = new User(name);
              new UpdatePicture(user,1);
            }
          } else {
            nameDialog.dispose();
            ClientLoginMsg msg = new ClientLoginMsg(user.getName());
            MyClient.getMyClient().sendMsg(msg);
          }

        }
      }
    });
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nameDialog.dispose();
      }
    });
  }

  public void loginSuc(User user) {
    home.toRoomList(user);
    nameDialog.dispose();
  }
  public void showMessage(){
    JOptionPane.showMessageDialog(null,"该帐号已登录","",JOptionPane.WARNING_MESSAGE);
  }

  public void loginFail() {
  }
}

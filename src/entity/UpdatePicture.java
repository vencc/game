package entity;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chess.Home;
import chess.RoomList;
import dao.IUserDao;
import dao.IUserDaoImp;
import msg.ClientLoginMsg;
import net.MyClient;
import net.MyServer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdatePicture extends JFrame {
  public UpdatePicture u = this;
  private MyServer idi = MyServer.getMyServer();
  private User user;
  private JLabel btnNewButton;
  private JLabel btnNewButton_1;
  private JLabel btnNewButton_2;
  private JLabel btnNewButton_3;
  private JLabel btnNewButton_4;
  private JLabel btnNewButton_5;
  private JLabel btnNewButton_6;
  private JLabel btnNewButton_7;
  private JLabel btnNewButton_8;
  private JLabel btnNewButton_9;
  JLabel btnNewButton_10;
  JLabel btnNewButton_11;
  JLabel btnNewButton_12;
  JLabel btnNewButton_13;
  JLabel btnNewButton_14;
  JLabel btnNewButton_15;
  JLabel btnNewButton_16;
  JLabel btnNewButton_17;
  JLabel btnNewButton_18;

  public UpdatePicture(User user,int flag) {
    this.user = user;
    idi.insertUser(user);
    this.setBounds(100, 100, 400, 400);
    this.setResizable(false);
    setVisible(true);
    getContentPane().setLayout(new GridLayout(5, 10));
    btnNewButton = new JLabel(new ImageIcon("resource/photo/1.jpg"));
    getContentPane().add(btnNewButton);
    btnNewButton_1 = new JLabel(new ImageIcon("resource/photo/2.jpg"));
    getContentPane().add(btnNewButton_1);
    btnNewButton_2 = new JLabel(new ImageIcon("resource/photo/3.jpg"));
    getContentPane().add(btnNewButton_2);
    btnNewButton_3 = new JLabel(new ImageIcon("resource/photo/4.jpg"));
    getContentPane().add(btnNewButton_3);
    btnNewButton_4 = new JLabel(new ImageIcon("resource/photo/5.jpg"));
    getContentPane().add(btnNewButton_4);
    btnNewButton_5 = new JLabel(new ImageIcon("resource/photo/6.jpg"));
    getContentPane().add(btnNewButton_5);
    btnNewButton_6 = new JLabel(new ImageIcon("resource/photo/7.jpg"));
    getContentPane().add(btnNewButton_6);
    btnNewButton_7 = new JLabel(new ImageIcon("resource/photo/8.jpg"));
    getContentPane().add(btnNewButton_7);
    btnNewButton_8 = new JLabel(new ImageIcon("resource/photo/9.jpg"));
    getContentPane().add(btnNewButton_8);
    btnNewButton_9 = new JLabel(new ImageIcon("resource/photo/10.jpg"));
    getContentPane().add(btnNewButton_9);
    btnNewButton_10 = new JLabel(new ImageIcon("resource/photo/11.jpg"));
    getContentPane().add(btnNewButton_10);
    btnNewButton_11 = new JLabel(new ImageIcon("resource/photo/12.jpg"));
    getContentPane().add(btnNewButton_11);
    btnNewButton_12 = new JLabel(new ImageIcon("resource/photo/13.jpg"));
    getContentPane().add(btnNewButton_12);
    btnNewButton_13 = new JLabel(new ImageIcon("resource/photo/14.jpg"));
    getContentPane().add(btnNewButton_13);
    btnNewButton_14 = new JLabel(new ImageIcon("resource/photo/15.jpg"));
    getContentPane().add(btnNewButton_14);
    btnNewButton_15 = new JLabel(new ImageIcon("resource/photo/16.jpg"));
    getContentPane().add(btnNewButton_15);
    btnNewButton_16 = new JLabel(new ImageIcon("resource/photo/17.jpg"));
    getContentPane().add(btnNewButton_16);
    btnNewButton_17 = new JLabel(new ImageIcon("resource/photo/18.jpg"));
    getContentPane().add(btnNewButton_17);
    btnNewButton_18 = new JLabel(new ImageIcon("resource/photo/19.jpg"));
    getContentPane().add(btnNewButton_18);
    frameInit(flag);
  }

  public void frameInit(int flag) {

    btnNewButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/1.jpg";
        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }

    });

    btnNewButton_1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/2.jpg";
        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_2.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/3.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_3.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/4.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });
    btnNewButton_4.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/5.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_5.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/6.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_6.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/7.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_7.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/8.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_8.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/9.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_9.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/10.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_10.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/11.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_11.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/12.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_12.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/13.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_13.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/14.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_14.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/15.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_15.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/16.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_16.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/17.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_17.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/18.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

    btnNewButton_18.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String root1 = "resource/photo/19.jpg";

        idi.updateUserImag(root1, user.getName());
        user.setFileName(root1);
        if(flag==1) login();
        else updatePhoto();
      }
    });

  }
  public void login(){
    dispose();
    ClientLoginMsg msg = new ClientLoginMsg(user.getName());
    MyClient.getMyClient().sendMsg(msg);
  }
  public void updatePhoto(){
    dispose();
  }

  /*public static void main(String[] args) {
    new UpdatePicture(new User("czf")).setVisible(true);
		
	}*/

}


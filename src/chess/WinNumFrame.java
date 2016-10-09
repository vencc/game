package chess;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;

import entity.User;
import java.awt.Dimension;

public class WinNumFrame extends JFrame{
	static List<User> userlist = null;
	String str="排名   姓名    胜局    负局 \n\r";
	public WinNumFrame(List<User> userlist) {
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		System.out.println("+++++++++"+userlist);
		getContentPane().setLayout(null);
		this.userlist = userlist;
		getContentPane().setBounds(new Rectangle(0, 0, 434, 300));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(432, 300));
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(432, 300));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 31, 432, 269);
		panel.add(textArea);
		
		JLabel label = new JLabel("战绩排行");
		label.setSize(new Dimension(100, 100));
		label.setBounds(0, 0, 432, 29);
		panel.add(label);
		
		for(int i = 0;i< userlist.size();i++){
			str +="No"+(i+1)+":    "+ userlist.get(i).getName()+"       "+userlist.get(i).getWinNum()+"        "+userlist.get(i).getLoseNum()+"\n\r";
		}
		textArea.setText(str);
		this.setVisible(true);
	}
	   
	  
 }

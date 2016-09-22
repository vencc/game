package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import entity.User;

public class RoomList extends JFrame{
	
	ArrayList<Room> rooms = new ArrayList();
	
	public RoomList(Home home,User user) {
		setSize(new Dimension(1000, 700));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 232, 666);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u5728\u7EBF\u7528\u6237");
		label.setBounds(92, 5, 48, 15);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(232, 45, 761, 621);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 751, 611);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(new GridLayout(6, 2, 0, 0));
		
		JPanel[] jpanel = new JPanel[12];
	    JButton[] jbutton = new JButton[36];
		for(int i = 0;i<12;i++){//房间
	        
	    	jpanel[i] = new JPanel();
	    	jpanel[i].setBackground(new Color(0, 255, 255));
	    	//
	    	jbutton[i*3]= new JButton(new ImageIcon("resource\\imag\\9_meitu_2.jpg"));
	    	jbutton[i*3].setSize(50,50);
	    	jbutton[i*3].setPreferredSize(new Dimension(50,50));
	    	jpanel[i].add(jbutton[i*3]);
	    	jbutton[i*3].addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	         //房间左边座位按钮		
	        		
	        	}
	        });
	    	
	    	jbutton[i*3+1]= new JButton(new ImageIcon("resource\\imag\\\\1.jpg"));
	    	jbutton[i*3+1].setSize(150,150);
	    	jbutton[i*3+1].setPreferredSize(new Dimension(150,150));
	        jpanel[i].add(jbutton[i*3+1]);
	        
	    	jbutton[i*3+2]= new JButton(new ImageIcon("resource\\imag\\\\10_meitu_3.jpg"));
	    	jbutton[i*3+2].setSize(50,50);
	    	jpanel[i].add(jbutton[i*3+2]);
	    	jbutton[i*3+2].setPreferredSize(new Dimension(50,50));
	    	jbutton[i*3+2].addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//房间右边座位按钮
	        		
	        	}
	        });
	    	panel_4.add(jpanel[i]);
	    	
	    }
	       
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(232, 0, 761, 45);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton button = new JButton("\u5FEB\u901F\u8FDB\u5165");
		button.setBounds(291, 5, 81, 30);
		panel_3.add(button);
		
		JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton.setBounds(377, 5, 93, 30);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resource\\imag\\12.jpg"));
		lblNewLabel.setBounds(0, 0, 761, 45);
		panel_3.add(lblNewLabel);
		
		
	  }
	
	
	public void init() {
	    this.setTitle("五子棋");
	    this.setSize(1000, 700);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setVisible(true);
	  }
	
      public static void main(String[] args) {
			new RoomList(null,null).setVisible(true);
     }
}

package entity;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import dao.IUserDaoImp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class UpdatePicture extends JFrame{
	public static User user=null;
	public  UpdatePicture u = this;
	public UpdatePicture(final User user) { 
		
		this.user = user;
		this.setBounds(100, 100, 400, 400);
		this.setResizable(false);
		getContentPane().setLayout(null);
		JButton btnNewButton = new JButton(new ImageIcon("resource\\photo\\10.jpg"));
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
			     String root1 = "resource\\photo\\10.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
		 }	
			
		});
		btnNewButton.setBounds(10, 10, 93, 82);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("resource\\photo\\2.jpg"));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\3.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 102, 93, 76);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(new ImageIcon("resource\\photo\\3.jpg"));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\2.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(10, 191, 93, 74);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton(new ImageIcon("resource\\photo\\5.jpg"));
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\5.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(103, 102, 93, 76);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton(new ImageIcon("resource\\photo\\6.jpg"));
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\6.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_5.setBounds(103, 191, 93, 74);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton(new ImageIcon("resource\\photo\\7.jpg"));
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\7.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				 String root1 = "resource\\photo\\8.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
				
			}
		});
		btnNewButton_6.setBounds(197, 10, 93, 82);
		getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_8 = new JButton(new ImageIcon("resource\\photo\\9.jpg"));
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\9.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_8.setBounds(197, 191, 93, 74);
		getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton(new ImageIcon("resource\\photo\\11.jpg"));
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\11.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_9.setBounds(291, 10, 93, 82);
		getContentPane().add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton(new ImageIcon("resource\\photo\\12.jpg"));
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\12.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_10.setBounds(291, 102, 93, 76);
		getContentPane().add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton(new ImageIcon("resource\\photo\\13.jpg"));
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\13.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_11.setBounds(291, 191, 93, 74);
		getContentPane().add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton(new ImageIcon("resource\\photo\\14.jpg"));
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\14.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_12.setBounds(10, 272, 93, 79);
		getContentPane().add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton(new ImageIcon("resource\\photo\\15.jpg"));
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\15.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_13.setBounds(103, 272, 93, 79);
		getContentPane().add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton(new ImageIcon("resource\\photo\\16.jpg"));
		btnNewButton_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\16.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_14.setBounds(197, 272, 93, 79);
		getContentPane().add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton(new ImageIcon("resource\\photo\\17.jpg"));
		btnNewButton_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\17.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_15.setBounds(291, 272, 93, 79);
		getContentPane().add(btnNewButton_15);
		
		JButton btnNewButton_3 = new JButton(new ImageIcon("resource\\photo\\21.jpg"));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\21.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		//btnNewButton_3.setBounds(103, 10, 93, 82);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_7 = new JButton(new ImageIcon("resource\\photo\\22.jpg"));
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\22.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_7.setBounds(197, 102, 93, 76);
		getContentPane().add(btnNewButton_7);
		
		JButton btnNewButton_16 = new JButton(new ImageIcon("resource\\photo\\23.jpg"));
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String root1 = "resource\\photo\\23.jpg";
			     IUserDaoImp idi = new IUserDaoImp() ;
			     idi.updateUserImag(root1, user.getName());
			     u.setVisible(false);
			}
		});
		btnNewButton_16.setBounds(103, 10, 93, 82);
		getContentPane().add(btnNewButton_16);
	}
	/*public static void main(String[] args) {
		new UpdatePicture(new User("czf")).setVisible(true);
		
	}*/
	
}


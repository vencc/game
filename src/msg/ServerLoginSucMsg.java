package msg;

public class ServerLoginSucMsg extends BaseMsg{

	private String username;
	public ServerLoginSucMsg(String username) {
		super();
		this.username = username;
	}
	public void doBiz() {
<<<<<<< HEAD
		MyClient.getMyClient().getNamedialog().loginSuc(user);
		System.out.println(user.getName());
=======
		// TODO Auto-generated method stub
		
>>>>>>> 84dff651532ad0cd83bf24e1cca02f2077115b3d
	}

}

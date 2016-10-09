package msg;

import java.awt.List;
import java.util.ArrayList;

import dao.IUserDaoImp;
import net.MyServer;
import entity.User;

public class ClientClickWinNumMsg extends BaseMsg{
 
	public void doBiz() {
		// TODO Auto-generated method stub
		 IUserDaoImp iu = new IUserDaoImp();
		 ArrayList<User> userlist = (ArrayList<User>) iu.findAll();
		 User user1 = null;
		 User user2 = null;
		 
         for(int j=1;j<userlist.size();j++){
        	 for(int i=0;i<userlist.size()-j;i++){
         
             if(userlist.get(i).getWinNum()<userlist.get(i+1).getWinNum()){
            	user1 = userlist.get(i);
     
            	userlist.get(i).setName(userlist.get(i+1).getName());
            	userlist.get(i).setFileName(userlist.get(i+1).getFileName());
            	userlist.get(i).setWinNum(userlist.get(i+1).getWinNum());
            	userlist.get(i).setLoseNum(userlist.get(i+1).getLoseNum());
            	userlist.get(i).setTiedNum(userlist.get(i+1).getTiedNum());
            	userlist.get(i+1).setName(user1.getName());
            	userlist.get(i+1).setFileName(user1.getFileName());
            	userlist.get(i+1).setWinNum(user1.getWinNum());
            	userlist.get(i+1).setLoseNum(user1.getLoseNum());
            	userlist.get(i+1).setTiedNum(user1.getTiedNum());
             }
             
             if(userlist.get(i).getWinNum()==userlist.get(i+1).getWinNum()){
            	 if(userlist.get(i).getLoseNum()>userlist.get(i+1).getLoseNum()){
            		 user1 = userlist.get(i);
            	     
                 	userlist.get(i).setName(userlist.get(i+1).getName());
                 	userlist.get(i).setFileName(userlist.get(i+1).getFileName());
                 	userlist.get(i).setWinNum(userlist.get(i+1).getWinNum());
                 	userlist.get(i).setLoseNum(userlist.get(i+1).getLoseNum());
                 	userlist.get(i).setTiedNum(userlist.get(i+1).getTiedNum());
                 	userlist.get(i+1).setName(user1.getName());
                 	userlist.get(i+1).setFileName(user1.getFileName());
                 	userlist.get(i+1).setWinNum(user1.getWinNum());
                 	userlist.get(i+1).setLoseNum(user1.getLoseNum());
                 	userlist.get(i+1).setTiedNum(user1.getTiedNum());
            	 }
             }
            	}
            		
         }
         ServerWinNumMsg msg = new ServerWinNumMsg(userlist);
		 MyServer.getMyServer().sendMsgToAll(msg);
	}

}

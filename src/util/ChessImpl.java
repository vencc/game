package util;


public class ChessImpl implements IChess {
	private static int h=15;
	private static int w=15;
	public static int [][]chess = new int[h][w];
	boolean white = false;
	boolean black = false;
	public void boolean(int x,int y,int type){
		
	}
	public void delete(int x,int y,int type){
		
	}
	public boolean compare(int x,int y,int type){
		chess[x][y]=type;
//		System.out.println(white+""+black);
//		for(int a=8,b=6;b<10;a++,b++){
//			chess[a][b]=-1;
			
//		}
//		System.out.println(chess[5][10]+" 9");
		 
		//纵向判断
		for(int i=x,j= 0;j<10;j++){
			int s=0,z=0;	 
			for(int a=j;a<j+5;a++){
				 if(chess[x][a]==1){
					 s++;	
					
				 }
				 if(chess[x][a]==-1)
				  z++;
			     }
			if(s==5){
				white = true;
				
					
             }
			if(z==5){
				black = true;
				
			}
			
		  }	
//		System.out.println(white);
//		if(white==true){
//				System.out.println("白色");
//			}
		//横向判断
		for(int i=0,j=y;i<10;i++){
			int s=0,z=0;	 
			for(int a=i;a<i+5;a++){
				 if(chess[a][y]==1){
					 s++;
				 }
				 if(chess[a][y]==-1)
				  z++;
			     }
			if(s==5){
				white = true;
			
             }
			if(z==5){
				black = true;
				
			}
		  }	
		//二四象限判断
		if(x>y){
		for(int i=x-y,j= 0;i<15-x+y;i++,j++){
			int s=0,z=0;	 
			for(int a=i,b=j;a<i+5&&a<15;a++,b++){
				 if(chess[a][b]==1){
					 s++;
				 }
				 if(chess[a][b]==-1)
				  z++;
			     }
			if(s==5){
				white = true;
				
             }
			if(z==5){
				black = true;
				
			}
		  }	
		}else {
			for(int i=0,j=y-x ;j<=15-y+x;i++,j++){
				int s=0,z=0;	 
				for(int a=i,b=j;a<i+5&&b<15;a++,b++){
//					System.out.println(a);
					 if(chess[a][b]==1){
						s++;
//						 System.out.println(s);
					 }
					 if(chess[a][b]==-1){
					     z++;
//					  System.out.println(z);
     				 }				     
				}
				if(s==5){
					white = true;
					
	             }
				if(z==5){
					black = true;
					
				}
			  }	
			
		}
		//一三象限判断
		if(x+y<15){
			for(int i=x+y,j= 0;j<=x+y;i--,j++){
				int s=0,z=0;	 
				for(int a=i,b=j;b<j+5&&a>=0;a--,b++){
					 if(chess[a][b]==1){
						 s++;
					 }
					 if(chess[a][b]==-1)
					  z++;
				     }
				if(s==5){
					white = true;
				
	             }
				if(z==5){
					black = true;
					
				}
			  }	
			}else{
				for(int i=14,j=x+y-i ;j<15;i--,j++){
					int s=0,z=0;	 
					for(int a=i,b=j;b<j+5&&b<15;a--,b++){
						 if(chess[a][b]==1){
							 s++;
							
						 }
						 if(chess[a][b]==-1)
						  z++;
					     }
					if(s==5){
						white = true;
						
		             }
					if(z==5){
						black = true;
						
					}
				  }	
			}
		//返回ture，贏了，返回false，沒人贏。
		if(white==true&&type==1){
			return true;
		}
		if(black==true&&type==-1){
		   return true;
		}
		return false;
		
	}
	 

	
}

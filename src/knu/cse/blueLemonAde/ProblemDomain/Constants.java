package knu.cse.blueLemonAde.ProblemDomain;

/**
 * @author jm
 * enum constants and other constants for server
 */
public class Constants {
	// TODO Category survey required.

	public final static int TOP = 0;
	public final static int CATEGORY = 1;
	public final static int BRAND = 2;
	public final static int MENU = 3;
	
	public static enum userState{
		NONE, WAIT_MATCHING, TALKING, WAIT_SELECT, WAIT_PAY, WAIT_DELIVERY;  
	}
	
	public static enum roomState{
		WAIT, 
	}
	
	public static enum orderState{
		WAITING, DELIVERYING, COMPLETE;
	}
	
	public static enum deliveryManState{
		NONE, 
	}
	
	public static enum Category {
		/* BUNSIC, */ 치킨(0), 피자(1); /*, CHAINESE, JOCBO, NIGHT, FAST, DOSIRAC;*/
		public int value;
		
		Category(int value){
			this.value = value;
		}
	}

	/*
	static enum BUNSIC {
		
	}
	 */
	
	public static enum CHICKEN {
		KFC(0), 교촌(1), 네네(2);
		public int value;
		
		CHICKEN(int value){
			this.value = value;
		}
	}

	public static enum PIZZA {
		피자헛(3), 미스터피자(4);
		public int value;
		
		PIZZA(int value){
			this.value = value;
		}
	}
	
	public static enum KFC {
		스파이시(0), 후라이드(1), 양념(2);
		public int value;
		
		KFC(int value){
			this.value = value;
		}
	}
	
	public static enum KYOCHON {
		핫(3), 간장(4), 후라이드(5);
		public int value;
		
		KYOCHON(int value){
			this.value = value;
		}
	}
	
	public static enum NENE {
		핫스파이시(6), 후라이드(7), 스노윙(8);
		public int value;
		
		NENE(int value){
			this.value = value;
		}
	}


	public static enum PIZZAHUT {
		불고기(9), 콤비네이션(10);
		public int value;
		
		PIZZAHUT(int value){
			this.value = value;
		}
	}
	
	public static enum MISTERPIZZA {
		쉬림프(11), 골드(12);
		public int value;
		
		MISTERPIZZA(int value){
			this.value = value;
		}
	}
}

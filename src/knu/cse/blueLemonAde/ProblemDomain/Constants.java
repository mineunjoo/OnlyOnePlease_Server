package knu.cse.blueLemonAde.ProblemDomain;

/**
 * @author jm
 * enum constants and other constants for server
 */
public class Constants {
	// TODO Category survey required.
	// waitingQueue level
	public final static int TOP = 0;
	public final static int CATEGORY = 1;
	public final static int BRAND = 2;
	public final static int MENU = 3;
	
	// user state
	public final static int NONE 			= 0;	// order & delivery also use this 
	public final static int WAIT_MATCHING 	= 1;
	public final static int TALKING 		= 2;
	public final static int WAIT_SELECT 	= 3;
	public final static int WAIT_PAY 		= 4;
	public final static int WAIT_DELIVERY 	= 5;  

	// order & delivery man state
	public final static int WAITING 	= 1;
	public final static int DELIVERYING = 2;
	public final static int COMPLETE 	= 3;

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
		피자헛(0), 미스터피자(1);
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
		핫(0), 간장(1), 후라이드(2);
		public int value;
		
		KYOCHON(int value){
			this.value = value;
		}
	}
	
	public static enum NENE {
		핫스파이시(0), 후라이드(1), 스노윙(2);
		public int value;
		
		NENE(int value){
			this.value = value;
		}
	}


	public static enum PIZZAHUT {
		불고기(0), 콤비네이션(1);
		public int value;
		
		PIZZAHUT(int value){
			this.value = value;
		}
	}
	
	public static enum MISTERPIZZA {
		쉬림프(0), 골드(1);
		public int value;
		
		MISTERPIZZA(int value){
			this.value = value;
		}
	}
}

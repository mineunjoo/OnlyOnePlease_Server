package knu.cse.blueLemonAde.ProblemDomain;

/**
 * @author jm
 * enum constants and other constants for server
 */
public class Constants {
	// TODO Category survey required.
	// waitingQueue level
	public final static int TOP 		= 0;
	public final static int CATEGORY 	= 1;
	public final static int BRAND 		= 2;
	public final static int MENU 		= 3;
	
	
	// user state
	public final static int NONE 			= 0;	// order & delivery also use this 
	public final static int WAIT_MATCHING 	= 1;
	public final static int TALKING 		= 2;
	public final static int WAIT_SELECT 	= 3;
	public final static int WAIT_CHECK		= 4;
	public final static int WAIT_DEPOSIT	= 5;
	public final static int WAIT_DELIVERY 	= 6;  
	
	
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
		BBQ(0, "BBQ"), 네네치킨(1, "네네치킨");
		public int value;
		public String str;
		
		CHICKEN(int value, String str){
			this.value = value;
			this.str = str;
		}
	}

	public static enum PIZZA {
		네오피자(0, "네오피자"), 피자빙고(1, "피자빙고");
		public int value;
		public String str;
		
		PIZZA(int value, String str){
			this.value = value;
			this.str = str;
		}
	}
	
	public static enum BBQ {
		황금올리브치킨(0, "황금올리브치킨", 16000), 시크릿양념치킨 (1, "시크릿양념치킨", 17000);
		public int value;
		public String str;
		public int price;
		
		BBQ(int value,  String str, int price){
			this.value = value;
			this.str = str;
			this.price = price;
		}
	}

	public static enum NENE {
		양념(0, "양념", 16000), 힛블링(1, "핫블링", 18000);
		public int value;
		public String str;
		public int price;
		
		NENE(int value,  String str, int price){
			this.value = value;
			this.str = str;
			this.price = price;
		}
	}

	public static enum NEOPIZZA {
		불고기피자(0, "불고기피자", 13000), 치즈바이트피자(1, "치즈바이트피자", 18000);
		public int value;
		public String str;
		public int price;
		
		NEOPIZZA(int value,  String str, int price){
			this.value = value;
			this.str = str;
			this.price = price;
		}
	}
	
	public static enum PIZZABINGO {
		고구마피자 (0, "고구마피자", 10000), 페파로니피자 (1, "페파로니피자", 8000);
		public int value;
		public String str;
		public int price;
		
		PIZZABINGO(int value,  String str, int price){
			this.value = value;
			this.str = str;
			this.price = price;
		}
	}
}

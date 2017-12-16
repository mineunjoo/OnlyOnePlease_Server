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
	
	public static enum STATE{
		NONE, WAIT,  
	}
	
	public static enum Category {
		/* BUNSIC, */ 치킨, 피자; /*, CHAINESE, JOCBO, NIGHT, FAST, DOSIRAC;*/
	}

	/*
	static enum BUNSIC {
		
	}
	 */
	
	public static enum CHICKEN {
		KFC, 교촌, 네네;
	}

	public static enum KFC {
		스파이시, 후라이드, 양념;
	}
	
	public static enum KYOCHON {
		핫, 간장, 후라이드;
	}
	
	public static enum NENE {
		핫스파이시, 후라이드, 스노윙;
	}
	
	public static enum PIZZA {
		피자헛, 미스터피자;
	}

	public static enum PIZZAHUT {
		불고기, 콤비네이션;
	}
	
	public static enum MISTER {
		쉬림프, 골드;
	}
}

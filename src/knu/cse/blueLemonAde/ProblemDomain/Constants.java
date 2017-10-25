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
		BUNSIC, CHICKEN, PIZZA, CHAINESE, JOCBO, NIGHT, FAST, DOSIRAC;
	}

	static enum BUNSIC {

	}

	public static enum CHICKEN {
		KFC, KYOCHON, NENE;
	}

	public static enum KFC {
		HOT;
	}
}

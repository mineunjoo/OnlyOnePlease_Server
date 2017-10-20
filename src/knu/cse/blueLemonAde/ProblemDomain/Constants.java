package knu.cse.blueLemonAde.ProblemDomain;

public class Constants {
	
	public static final int TOP = 0;

	public static enum Category{
		BUNSIC(1),
		CHICKEN(2),
		PIZZA(3),
		CHAINESE(4),
		JOCBO(5),
		NIGHT(6),
		FAST(7),
		DOSIRAC(8);
		
		public int type;
		
		Category(int type) {
			this.type = type;
		}
	};
	
	static enum BUNSIC{
		
	}
	
	public static enum CHICKEN{
		KFC,
		KYOCHON,
		NENE;
	}
	
	public static enum KFC{
		HOT;
	}
}

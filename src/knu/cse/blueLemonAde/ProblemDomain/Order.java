package knu.cse.blueLemonAde.ProblemDomain;

public class Order {

	int orderNumber;
	int category;
	int brand;
	int menu;
	
	int state;
	
	public Order(int category, int brand, int menu, int orderNumber) {
		// TODO Auto-generated constructor stub
		state = Constants.WAITING;
		
		this.category = category;
		this.brand = brand;
		this.menu = menu;
		
		this.orderNumber = orderNumber;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCategory() {
		return category;
	}

	public int getBrand() {
		return brand;
	}

	public int getMenu() {
		return menu;
	}

	public int getOrderNumber() {
		return orderNumber;
	}
}

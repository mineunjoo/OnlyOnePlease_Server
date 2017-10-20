package knu.cse.blueLemonAde.Foundation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import knu.cse.blueLemonAde.ProblemDomain.Constants;
import knu.cse.blueLemonAde.ProblemDomain.Constants.*;

/**
 * @author jm
 * waiting user queue. if user choose specific category ex Chicken->KFC->HOT,
 * add user into all each applicable category type waiting queue
 */
public class WaitingQueue {
	private int type;									// this waitingQueue type (ex) chicken, KFC chicken
	
	private Queue<Integer> waitingQueue;				// waiting queue of under type and this type 
	
	private ArrayList<WaitingQueue> underWaitingQueueList;	// under type waitingQueue list 
														// (ex) this type is chicken, it has waitingQueue of chicken brand
	
	
	// recursive constructor. create all type waiting queue in tree format
	public WaitingQueue(int type) {
		this.type = type;
		
		waitingQueue = new LinkedList<Integer>();
		underWaitingQueueList = new ArrayList<WaitingQueue>();

		if(this.type == Constants.TOP) {
			for(Category category: Category.values()) {
				underWaitingQueueList.add(new WaitingQueue(category.ordinal()));
			}
		}
		else if(this.type == Category.BUNSIC.ordinal()) {
			
		}
		else if(this.type == Category.CHICKEN.ordinal()) {
			for(CHICKEN chicken: CHICKEN.values()) {
				underWaitingQueueList.add(new WaitingQueue(chicken.ordinal()));
			}
		}
		
		else if(this.type == Category.PIZZA.ordinal()) {
			
		}
		
		// TODO when decide all category,
	}
	
}

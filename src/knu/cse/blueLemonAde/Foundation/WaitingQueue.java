package knu.cse.blueLemonAde.Foundation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import knu.cse.blueLemonAde.ProblemDomain.Constants;
import knu.cse.blueLemonAde.ProblemDomain.Constants.*;

/**
 * @author jm waiting user queue.
 */
public class WaitingQueue {
	private int level; // this waitingQueue level (ex : TOP(0), CATEGORY(1), BRAND(2)...
	private int type; // this is typeID (ex) CATEGORY-CHICKEN, BRAND-KFC...

	private ArrayList<Integer> waitingQueue; // waiting queue of under type and this type
	private ArrayList<WaitingQueue> underWaitingQueueList; // under type waitingQueue list
															// (ex) this type is chicken, it has waitingQueue of chicken
															// brand

	// recursive constructor. create all type waiting queue in tree format
	public WaitingQueue(int level, int type) {
		this.level = level;
		this.type = type;

		waitingQueue = new ArrayList<Integer>();
		underWaitingQueueList = new ArrayList<WaitingQueue>();

		if (level == Constants.TOP) {
			for (Category category : Category.values()) {
				underWaitingQueueList.add(new WaitingQueue(level + 1, category.ordinal()));
			}
		}
		// TODO when decide all category,
	}

	/**
	 * @param userID
	 *            : userID to enter the waiting queue
	 * @param is
	 *            : selected category typeIDs if user choose specific category
	 *            typeID to param is (ex) Chicken, KFC, HOT add user into all each
	 *            applicable category type waiting queue
	 */
	public int offer(int userID, int... is) {
		int otherUserID = -1;

		if (type < is.length) {
			if ((otherUserID = underWaitingQueueList.get(is[type]).offer(userID, is)) < 0) {
				waitingQueue.add(userID);
			}
		} else if (type == is.length) {
			if (waitingQueue.isEmpty()) {
				waitingQueue.add(userID);
				return -1;
			} else {
				otherUserID = waitingQueue.get(0);
			}
		}
		return otherUserID;
	}

	public void poll(int userID, int... is) {
		if (type < is.length) {
			underWaitingQueueList.get(is[type]).poll(userID, is);
		}

		for (int element : waitingQueue) {
			if (element == userID)
				waitingQueue.remove(userID);
		}
	}
}

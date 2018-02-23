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
	private int level; // this is waitingQueue level (ex : TOP(0), CATEGORY(1),
						// BRAND(2)...
	private int type[] = null;

	private ArrayList<Integer> waitingQueue; // waiting queue of under type and
												// this type
	private ArrayList<WaitingQueue> underWaitingQueueList; // under type
															// waitingQueue list
															// (ex) this type is
															// chicken,
															// it has
															// waitingQueue of
															// chicken brand

	// recursive constructor. create all type waiting queue in tree format
	public WaitingQueue(int level, int... is) {
		this.level = level;
		
		if (level > 0) {
			type = new int[level];

			for (int i = 0; i < type.length; i++)
				type[i] = is[i+1];
		}

		waitingQueue = new ArrayList<Integer>();
		underWaitingQueueList = new ArrayList<WaitingQueue>();

		if (level == Constants.TOP) {
			for (Category category : Category.values()) {
				underWaitingQueueList.add(new WaitingQueue(level + 1, 0, category.value));
			}
		} else if (level == Constants.CATEGORY) {
			if (type[0] == Category.치킨.value) {
				for (CHICKEN brand : CHICKEN.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], brand.value));
				}
			} else if (type[0] == Category.피자.value) {
				for (PIZZA brand : PIZZA.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], brand.value));
				}
			}
		} else if (level == Constants.BRAND) {
			if (type[1] == CHICKEN.BBQ.value) {
				for (BBQ menu : BBQ.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == CHICKEN.네네치킨.value) {
				for (NENE menu : NENE.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == PIZZA.네오피자.value) {
				for (NEOPIZZA menu : NEOPIZZA.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == PIZZA.피자빙고.value) {
				for (PIZZABINGO menu : PIZZABINGO.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			}
		}
	}

	/**
	 * @param userID
	 *            : userID to enter the waiting queue
	 * @param is
	 *            : selected category typeIDs if user choose specific category
	 *            typeID to param is (ex) Chicken, KFC, HOT add user into all
	 *            each applicable category type waiting queue
	 * @return : if matching complete return other user id else if failed return
	 *         -1 and enter waiting queue
	 */
	public int offer(int userID, int... is) {
		int otherUserID = -1;

		if (level < is.length) {
			if ((otherUserID = underWaitingQueueList.get(is[level]).offer(userID, is)) < 0) {
				waitingQueue.add(userID);
			}
		} else if (level == is.length) {
			if (waitingQueue.isEmpty()) {
				waitingQueue.add(userID);
				return -1;
			} else {
				otherUserID = waitingQueue.get(0);
			}
		}
		printQueue();

		return otherUserID;
	}

	public void poll(int userID, int... is) {
		if (level < is.length) {
			underWaitingQueueList.get(is[level]).poll(userID, is);
		}

		for (int element : waitingQueue) {
			if (element == userID)
				waitingQueue.remove(userID);
		}
	}

	public void printQueue() {
		ArrayList<WaitingQueue> queue = new ArrayList<WaitingQueue>();

		if (level == Constants.TOP) 
			printEnqueue(queue);
		
		for (int i = 0; i < queue.size(); i++) 
			System.out.println(queue.get(i).toString());
	}

	private void printEnqueue(ArrayList<WaitingQueue> queue) {
		int currentLevel = Constants.TOP;

		queue.add(this);

		for (int i = 0; i < underWaitingQueueList.size(); i++)
			if (!underWaitingQueueList.get(i).waitingQueue.isEmpty())
				underWaitingQueueList.get(i).printEnqueue(queue);
	}

	@Override
	public String toString() {
		String level = "";
		String[] type = { null, null, null };

		if (this.level == Constants.TOP)
			level = "TOP";
		else if (this.level == Constants.CATEGORY)
			level = "CATEGORY";
		else if (this.level == Constants.BRAND)
			level = "BRAND";
		else if (this.level == Constants.MENU)
			level = "MENU";
		
		if (this.type != null) {
			if (this.type[0] == Category.치킨.value) {
				type[0] = "치킨";

				if (this.type.length > 1) {
					if (this.type[1] == CHICKEN.BBQ.value) {
						type[1] = "BBQ";

						if (this.type.length > 2) {
							if (this.type[2] == BBQ.시크릿양념치킨.value)
								type[2] = "시크릿양념치킨";
							else if (this.type[2] == BBQ.황금올리브치킨.value)
								type[2] = "황금올리브치킨";
						}
					} else if (this.type[1] == CHICKEN.네네치킨.value) {
						type[1] = "네네치킨";

						if (this.type.length > 2) {
							if (this.type[2] == NENE.양념.value)
								type[2] = "양념";
							else if (this.type[2] == NENE.힛블링.value)
								type[2] = "힛블링";
						}
					} 
				}
			} else if (this.type[0] == Category.피자.value) {
				type[0] = "피자";

				if (this.type.length > 1) {
					if (this.type[1] == PIZZA.네오피자.value) {
						type[1] = "네오피자";

						if (this.type.length > 2) {
							if (this.type[2] == NEOPIZZA.불고기피자.value)
								type[2] = "불고기피자";
							else if (this.type[2] == NEOPIZZA.치즈바이트피자.value)
								type[2] = "치즈바이트피자";
						}
					} else if (this.type[1] == PIZZA.피자빙고.value) {
						type[1] = "피자빙고";

						if (this.type.length > 2) {
							if (this.type[2] == PIZZABINGO.고구마피자.value)
								type[2] = "고구마피자";
							else if (this.type[2] == PIZZABINGO.페파로니피자.value)
								type[2] = "페파로니피자";
						}
					}
				}
			}
		}
		String temp = "";

		for (int i = 0; i < type.length; i++) {
			if (type[i] != null) {
				if (i == 0)
					temp += " / category : " + type[0];
				else if (i == 1)
					temp += " / brand : " + type[1];
				else if (i == 2)
					temp += " / menu : " + type[2];
			}
		}
		return "WaitingQueue [level=" + level + temp + ", waitingQueue=" + waitingQueue + "]";
	}
}

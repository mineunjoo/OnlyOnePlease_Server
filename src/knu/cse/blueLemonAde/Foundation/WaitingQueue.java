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
			if (type[1] == CHICKEN.KFC.value) {
				for (KFC menu : KFC.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == CHICKEN.교촌.value) {
				for (KYOCHON menu : KYOCHON.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == CHICKEN.네네.value) {
				for (NENE menu : NENE.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == PIZZA.피자헛.value) {
				for (PIZZAHUT menu : PIZZAHUT.values()) {
					underWaitingQueueList.add(new WaitingQueue(level + 1, 0, type[0], type[1], menu.value));
				}
			} else if (type[1] == PIZZA.미스터피자.value) {
				for (MISTERPIZZA menu : MISTERPIZZA.values()) {
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
					if (this.type[1] == CHICKEN.KFC.value) {
						type[1] = "KFC";

						if (this.type.length > 2) {
							if (this.type[2] == KFC.후라이드.value)
								type[2] = "후라이드";
							else if (this.type[2] == KFC.양념.value)
								type[2] = "양념";
							else if (this.type[2] == KFC.스파이시.value)
								type[2] = "스파이시";
						}
					} else if (this.type[1] == CHICKEN.네네.value) {
						type[1] = "네네";

						if (this.type.length > 2) {
							if (this.type[2] == KYOCHON.후라이드.value)
								type[2] = "후라이드";
							else if (this.type[2] == KYOCHON.간장.value)
								type[2] = "간장";
							else if (this.type[2] == KYOCHON.핫.value)
								type[2] = "핫";
						}
					} else if (this.type[1] == CHICKEN.교촌.value) {
						type[1] = "교촌";

						if (this.type.length > 2) {
							if (this.type[2] == NENE.후라이드.value)
								type[2] = "후라이드";
							else if (this.type[2] == NENE.핫스파이시.value)
								type[2] = "핫스파이시";
							else if (this.type[2] == NENE.스노윙.value)
								type[2] = "스노윙";
						}
					}
				}
			} else if (this.type[0] == Category.피자.value) {
				type[0] = "피자";

				if (this.type.length > 1) {
					if (this.type[1] == PIZZA.피자헛.value) {
						type[1] = "피자헛";

						if (this.type.length > 2) {
							if (this.type[2] == PIZZAHUT.불고기.value)
								type[2] = "불고기";
							else if (this.type[2] == PIZZAHUT.콤비네이션.value)
								type[2] = "콤비네이션";
						}
					} else if (this.type[1] == PIZZA.미스터피자.value) {
						type[1] = "미스터피자";

						if (this.type.length > 2) {
							if (this.type[2] == MISTERPIZZA.쉬림프.value)
								type[2] = "쉬림프";
							else if (this.type[2] == MISTERPIZZA.골드.value)
								type[2] = "골드";
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

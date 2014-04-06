package model;

import java.util.LinkedList;

/**
 * @author Robert
 * 
 */
public class State {
	int bigBucket = 0;
	int smallBucket = 0;

	LinkedList<State> followingStates;

	public State() {
		followingStates = new LinkedList<State>();
	}

	public State(State state) {
		this();
		bigBucket = state.bigBucket;
		smallBucket = state.smallBucket;
		
	}

	/**
	 * @return the bigBucket
	 */
	public int getBigBucket() {
		return bigBucket;
	}

	/**
	 * @param bigBucket
	 *            the bigBucket to set
	 */
	public void setBigBucket(int bigBucket) {
		this.bigBucket = bigBucket;
	}

	/**
	 * @return the smallBucket
	 */
	public int getSmallBucket() {
		return smallBucket;
	}

	/**
	 * @param smallBucket
	 *            the smallBucket to set
	 */
	public void setSmallBucket(int smallBucket) {
		this.smallBucket = smallBucket;
	}

	/**
	 * @return the followingStates
	 */
	public LinkedList<State> getFollowingStates() {
		return followingStates;
	}

	/**
	 * @param followingStates
	 *            the followingStates to set
	 */
	public void setFollowingStates(LinkedList<State> followingStates) {
		this.followingStates = followingStates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bigBucket;
		result = prime * result + smallBucket;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (bigBucket != other.bigBucket)
			return false;
		if (smallBucket != other.smallBucket)
			return false;
		return true;
	}

	public void emptyBigBucket() {
		bigBucket = 0;
	}

	public void emptySmallBucket() {
		smallBucket = 0;
	}

	public void pourAllSmallIntoBig() {
		bigBucket += smallBucket;
		emptySmallBucket();
		if (bigBucket > 4) {
			bigBucket = 4;
		}
	}

	public void pourAllBigIntoSmall() {
		smallBucket += bigBucket;
		emptyBigBucket();
		if (smallBucket > 3) {
			smallBucket = 3;
		}
	}

	public void pourSmallIntoBig() {
		int aux = 4 - bigBucket;
		bigBucket += smallBucket;
		if (bigBucket > 4) {
			bigBucket = 4;
		}
		smallBucket -= aux;
		if(smallBucket < 0 ) {
			smallBucket = 0;
		}
	}

	public void pourBigIntoSmall() {
		int aux = 3 - smallBucket;
		if (aux < 0) {
			aux = 0;
		}
		smallBucket += bigBucket;
		if (smallBucket > 3) {
			smallBucket = 3;
		}
		bigBucket -= aux;
		if(bigBucket<0) {
			bigBucket = 0;
		}
	}

	public void fillSmallBucket() {
		smallBucket = 3;
	}

	public void fillBigBucket() {
		bigBucket = 4;
	}

	public void setAllChildStates() {
		State newState = new State(this);

		newState.emptyBigBucket();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}

		newState = new State(this);
		newState.emptySmallBucket();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}

		newState = new State(this);
		newState.fillSmallBucket();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}

		newState = new State(this);
		newState.fillBigBucket();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}


		newState = new State(this);
		newState.pourAllBigIntoSmall();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}


		newState = new State(this);
		newState.pourAllSmallIntoBig();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}


		newState = new State(this);
		newState.pourBigIntoSmall();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}


		newState = new State(this);
		newState.pourSmallIntoBig();
		if(!followingStates.contains(newState)) {
			followingStates.add(newState);
		}

	}

	@Override
	public String toString() {
		return "[" + bigBucket + ", " + smallBucket + "]";
	}

	public boolean isFinal() {
		if (smallBucket == 2 || bigBucket == 2) {
			return true;
		} else {
			return false;
		}
	}
}

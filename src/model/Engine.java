package model;

import java.util.LinkedList;

public class Engine {
	LinkedList<State> states;
	private int[] visited;
	LinkedList<State> solution;

	public Engine() {
		states = new LinkedList<State>();
		solution = new LinkedList<State>();
		getAllStates();
		visited = new int[states.size()];
	}

	private void getAllStates() {
		State initialState = new State();
		states.add(initialState);

		LinkedList<State> backupStates = new LinkedList<>();
		backupStates.add(initialState);

		while (!backupStates.isEmpty()) {
			State poll = backupStates.pollFirst();
			poll.setAllChildStates();

			for (State state : poll.getFollowingStates()) {
				if (!states.contains(state)) {
					states.add(state);
					backupStates.add(state);
				}
			}
		}
	}

	private void DFSSolution(int[] visited, LinkedList<State> solution,
			State initialState) {
		if (initialState.isFinal()) {
			System.out.println(solution);
		} else {
			for (State state : initialState.getFollowingStates()) {
				int index = states.indexOf(state);
				if (visited[index] == 0) {
					visited[index] = 1;
					solution.add(state);
					DFSSolution(visited, solution, state);
					visited[index] = 0;
					solution.remove(state);
				}
			}
		}
	}

	public void showSolution() {
		solution.add(states.getFirst());
		visited[0] = 1;

		DFSSolution(visited, solution, states.getFirst());
	}
}

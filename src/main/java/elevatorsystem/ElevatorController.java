package elevatorsystem;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElevatorController {

	private int elevatorId;
	private int totalFloors;
	private int currentFloor;
	private final Queue<Integer> requests;
	private State state;
	private DoorStatus doorStatus;
	
	public ElevatorController(int elevatorId, int floors) {
		this.elevatorId = elevatorId;
		this.totalFloors = floors;
		this.currentFloor = 0;
		this.requests = new LinkedList<>();
		this.state = State.IDLE;
		this.doorStatus = DoorStatus.CLOSED;
	}

	public void addRequest(int destinationFloor) {
		requests.add(destinationFloor);
		updateDirection();
	}

	private void updateDirection() {
		if(requests.isEmpty()) {
			this.state = State.IDLE;
		}else if(this.requests.peek() > this.currentFloor) {
			this.state = State.MOVING_UP;
		}else {
			this.state = State.MOVING_DOWN;
		}
	}

	public boolean isHeadingTowards(Request currentRequest) {
		if(currentRequest.getState().equals(State.IDLE)) {
			return true;
		}else if(this.state.equals(currentRequest.getState()) &&
				( (currentRequest.getState().equals(State.MOVING_DOWN) && currentRequest.getFloor() <= this.currentFloor) 
				|| currentRequest.getState().equals(State.MOVING_UP) && currentRequest.getFloor() >= this.currentFloor )) {
			return true;
		}
		return false;
	}

	public void move() {
		if(this.requests.isEmpty()) {
			this.state = State.IDLE;
			return;
		}
		int targetFloor = this.requests.peek();
		if(this.currentFloor < targetFloor) {
			this.currentFloor++;
			this.state = State.MOVING_UP;
		}else if(this.currentFloor > targetFloor) {
			this.currentFloor--;
			this.state = State.MOVING_DOWN;
		}else {
			this.requests.poll();
			this.doorStatus = DoorStatus.OPEN;
			System.out.println("Elevator "+this.elevatorId+" is on the "+this.currentFloor+". Door Opened!!!!!");
			this.doorStatus = DoorStatus.CLOSED;
			updateDirection();
		}
	}
}

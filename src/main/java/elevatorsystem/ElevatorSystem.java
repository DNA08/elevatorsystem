package elevatorsystem;

import java.util.HashMap;
import java.util.Map;

public class ElevatorSystem {
	private int floors;
	private Map<Integer, ElevatorController> elevators = new HashMap<>();
	private Dispatcher dispatcher;
	
	public ElevatorSystem(int floors) {
		this.floors = floors;
		this.dispatcher = new Dispatcher();
	}
	
	public void addElevator(int elevatorId) {
		elevators.put(elevatorId, new ElevatorController(elevatorId,floors));
	}
	
	public void removeElevator(int elevatorId) {
		this.elevators.remove(elevatorId);
	}

	public void handleExternalRequest(int floor, State state) {
		this.dispatcher.addRequest(new Request(floor,state));
		this.dispatcher.dispatch(new PanelController(elevators));
	}

	public void handleInternalRequest(int elevatorId, int destinationFloor) {
		if(this.elevators.containsKey(elevatorId)) {
			this.elevators.get(elevatorId).addRequest(destinationFloor);
		}
	}
	
	public void startUpTheElevators() {
		for(ElevatorController elevatorController : this.elevators.values()) {
			elevatorController.move();
		}
	}
}

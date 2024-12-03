package elevatorsystem;

import java.util.Map;

public class PanelController {

	private Map<Integer,ElevatorController> elevators;
	
	public PanelController(Map<Integer, ElevatorController> elevators) {
		this.elevators = elevators;
	}

	public void handleRequest(Request currentRequest) {
		ElevatorController bestElevator = findBestElevator(currentRequest);
		if(null != bestElevator) {
			bestElevator.addRequest(currentRequest.getFloor());
		}
	}

	private ElevatorController findBestElevator(Request currentRequest) {
		ElevatorController bestElevator = null;
		int minDistance = Integer.MAX_VALUE;
		for(ElevatorController elevator : this.elevators.values()) {
			if(elevator.getState().equals(State.IDLE) || elevator.isHeadingTowards(currentRequest)
					) {
				int distance = Math.abs(elevator.getCurrentFloor() - currentRequest.getFloor());
				if(distance < minDistance) {
					minDistance = distance;
					bestElevator = elevator;
				}
				
			}
		}
		return bestElevator;
	}

}

package elevatorsystem;

public class DriverClass {

	public static void main(String[] args) {
		ElevatorSystem elevatorSystem = new ElevatorSystem(10);
		elevatorSystem.addElevator(1);
		elevatorSystem.addElevator(2);
		elevatorSystem.addElevator(3);

		elevatorSystem.handleExternalRequest(5,State.MOVING_UP);
		elevatorSystem.handleInternalRequest(5,10);
		
		elevatorSystem.handleExternalRequest(2,State.MOVING_UP);
		elevatorSystem.handleInternalRequest(2,4);
		
		elevatorSystem.handleExternalRequest(10,State.MOVING_DOWN);
		elevatorSystem.handleInternalRequest(10,5);
		
		for(int i = 0; i < 10; i++) {
			elevatorSystem.startUpTheElevators();
		}
	}

}

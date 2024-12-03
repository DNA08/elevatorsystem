package elevatorsystem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {
	
	private int floor;
	private State state;
	
	public Request(int floor, State state) {
		this.floor = floor;
		this.state = state;
	}
	
}

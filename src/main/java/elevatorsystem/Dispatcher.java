package elevatorsystem;

import java.util.LinkedList;
import java.util.Queue;

public class Dispatcher {

	private final Queue<Request> requests;
	
	public Dispatcher() {
		this.requests = new LinkedList<>();
	}
	
	public void addRequest(Request request) {
		requests.add(request);
	}
	
	public void dispatch(PanelController panelController) {
		while(!requests.isEmpty()) {
			Request currentRequest = this.requests.poll();
			panelController.handleRequest(currentRequest);
		}
	}

}

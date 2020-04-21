package server;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class ServerController {

	/**
	 * Server object
	 */
	Server server;
	
	/**
	 * ServerModel object
	 */
	ServerModel model;
	
	/**
	 * Constructs a ServerController object
	 * @param s Server object
	 * @param m ServerModel object
	 */
	public ServerController(Server s, ServerModel m){
		this.server = s;
		this.model = m;
	}
	
	public static void main(String[] args){
		Server s = new Server(9009);
		s.communicate();
	}
}

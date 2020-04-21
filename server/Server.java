package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Chathula Adikary, Clare Meng
 * @version v0.1
 * @since April 1, 2019.
 *
 */
public class Server {
	
	/**
	 * ServerSocket object
	 */
	private ServerSocket serverSocket;
	
	/**
	 * thread pool
	 */
	private ExecutorService pool;
	
	/**
	 * Constructs a Server object
	 * @param port the port number
	 */
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			System.out.println("Unable to use port!");
		}
		
		System.out.println(new Timestamp(System.currentTimeMillis()) + " Server started successfully on port " + port);
	}
	
	/**
	 * accepts the ServerSocket object
	 * constructs a ServerModel object and executes the thread pool
	 */
	public void communicate() {
		try{
			while(true) {
				Socket s = serverSocket.accept();
				System.out.println(new Timestamp(System.currentTimeMillis()) + " Client connected from: " + s.getInetAddress());
				ServerModel f = new ServerModel(s);
				pool.execute(f);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			pool.shutdown();
		}
	}

}

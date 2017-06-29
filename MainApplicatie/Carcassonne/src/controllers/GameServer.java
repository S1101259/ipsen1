package controllers;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import models.LobbyInterface;
import models.Speler;
import models.LobbyImpl;

public class GameServer {

	public GameServer(){
		try {
			LobbyImpl lobbyImpl = new LobbyImpl(); // create calculator and treat as Calculator
			LobbyInterface lobbySkeleton = 	(LobbyInterface) UnicastRemoteObject.exportObject(lobbyImpl, 0); // cast to remote object
			System.out.println("Lobby skeleton created");
			Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
			System.out.println("RMI Registry starter");
			registry.rebind("Lobby", lobbySkeleton); // bind calculator to RMI registry
			System.out.println("Lobby skeleton bound");
			System.out.println("Server running...");

			// if you'd like to run rmiregistry from the commend line
			//	run it from the project's bin directory, so rmiregistry can find the necessary classes

		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
	}

	private void runServer(){

	}



	public static void main(String[] args) {
		new GameServer().runServer();

	}

}

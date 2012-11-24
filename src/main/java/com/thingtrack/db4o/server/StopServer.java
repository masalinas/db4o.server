package com.thingtrack.db4o.server;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.messaging.MessageSender;

public class StopServer implements ServerInfo {
	    /**
	     * stops a db4o Server started with StartServer.
	     *
	     * @throws Exception
	     */
	    public static void main(String[] args) {
	        ObjectContainer objectContainer = null;
	        try {
	            // connect to the server
	            objectContainer = Db4oClientServer.openClient(Db4oClientServer.newClientConfiguration(), HOST, PORT, USER, PASS);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        if (objectContainer != null) {
	            // get the messageSender for the ObjectContainer
	            MessageSender messageSender = objectContainer.ext().configure().clientServer().getMessageSender();
	            // send an instance of a StopServer object
	            messageSender.send(new StopServer());
	            // close the ObjectContainer
	            objectContainer.close();
	        }
	    }
}

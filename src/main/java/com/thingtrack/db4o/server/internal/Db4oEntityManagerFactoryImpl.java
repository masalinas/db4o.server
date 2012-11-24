package com.thingtrack.db4o.server.internal;

import org.springframework.beans.factory.annotation.Autowired;

import com.db4o.ObjectServer;
import com.db4o.config.ConfigScope;
import com.db4o.config.Configuration;
import com.db4o.messaging.MessageContext;
import com.db4o.messaging.MessageRecipient;
import com.db4o.osgi.Db4oService;

import com.thingtrack.db4o.server.Db4oEntityManagerFactory;

public class Db4oEntityManagerFactoryImpl implements Db4oEntityManagerFactory, MessageRecipient {
	private static final String FILE = "konekti.db4o";
	private static final int PORT = 4488;
	private static final String USER = "db4o";
	private static final String PASS = "db4o";
	
	@Autowired
	private Db4oService db4oService;
	
	private ObjectServer objectServer;
	
	@SuppressWarnings("unused")
	private void start() {
		if (db4oService == null)
			return;
		
		Configuration config = db4oService.newConfiguration();
  		
		// Using the messaging functionality to redirect all messages to this.processMessage
    	config.clientServer().setMessageRecipient(this);
    	
    	// replication config
    	config.generateUUIDs(ConfigScope.GLOBALLY);
    	config.generateCommitTimestamps(true);
    	
    	try {
	    	objectServer = db4oService.openServer(config, FILE, PORT);
	    	objectServer.grantAccess(USER, PASS);
    	}
    	catch (Exception e) {
    		e.getMessage();
		}
	}
	
	/**
     * closes this server.
     */
    @SuppressWarnings("unused")
	private void stop() {
    	try {
			if (objectServer != null)
				objectServer.close();
		}
	  	catch (Exception e) {
	  		e.getMessage();
	  	}
    	    	           
    }
    
	@Override
	public ObjectServer getObjectServer() {
		return objectServer;
		
	}
	
	@Override
	public void processMessage(MessageContext arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}

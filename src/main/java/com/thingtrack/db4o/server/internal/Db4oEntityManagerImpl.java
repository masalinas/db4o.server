package com.thingtrack.db4o.server.internal;

import org.springframework.beans.factory.annotation.Autowired;

import com.db4o.ObjectContainer;
import com.db4o.osgi.Db4oService;

import com.thingtrack.db4o.server.Db4oEntityManager;

public class Db4oEntityManagerImpl implements Db4oEntityManager {
	private static final String HOST = "localhost";
	private static final int PORT = 4488;
	private static final String USER = "db4o";
	private static final String PASS = "db4o";
	
	@Autowired
	private Db4oService db4oService;
	
	private ObjectContainer objectContainer;
	
	
	@SuppressWarnings("unused")
	private void open() {
	  	try {
	  		objectContainer = db4oService.openClient(HOST, PORT, USER, PASS);
	  		
	  	}
	  	catch (Exception e) {
	  		e.getMessage();
	  	}

	}

	@SuppressWarnings("unused")
	private void close() {
		try {
			if (objectContainer != null)
				objectContainer.close();
		}
	  	catch (Exception e) {
	  		e.getMessage();
	  	}
	}
	
	@Override
	public ObjectContainer getEntityManager() {
		return objectContainer;
		
	}

}

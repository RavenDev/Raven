package com.ravendev.raven;

import com.ravendev.raven.server.RavenServer;

public class Raven {

	private static final String version = "0.1";
	private static RavenServer server;

	public static boolean setServer(RavenServer newServer) {
		if (server == null) {
			server = newServer;
			return true;
		}
		return false;
	}
	
	public RavenServer getServer() {
		return server;
	}

	public static void main(String[] args) {
		System.setOut(new ConsoleWrapper());
		System.setErr(new ConsoleWrapper());
		System.out.println("Starting Raven v" + getVersion() + "!");
		setServer(new RavenServer());
	}

	public static String getVersion() {
		return version;
	}
}
//TODO: git commit -m 'Commit message'
//TODO: git push -u origin master
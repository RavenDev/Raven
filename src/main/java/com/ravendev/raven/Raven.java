package com.ravendev.raven;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ravendev.raven.plugin.PluginManager;


public class Raven {

	public static String version;
	public static PluginManager pm;
	
	public static void main(String[] args) {
		System.setOut(new ConsoleWrapper());
		System.setErr(new ConsoleWrapper());
		version = "Unkown";
		initVersion();
		System.out.println("Starting Raven by RavenDev version " + getVersion() + "!");
		System.out.println("Starting PluginManager!");
		pm = new PluginManager();
	}
	
	private static void initVersion() {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(Raven.class.getResourceAsStream("version.ver")));
			version = r.readLine();
			r.close();
		} catch (Exception e) {
			return;
		}
	}
	
	public static String getVersion() {
		return version;
	}
}


//TODO: git commit -m 'Commit'
//TODO: git push -u origin master
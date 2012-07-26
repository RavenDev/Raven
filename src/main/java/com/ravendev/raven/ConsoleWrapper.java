package com.ravendev.raven;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleWrapper extends PrintStream {

	private Date sd;
	private SimpleDateFormat d;
	
	public ConsoleWrapper() {
		super(System.out);
		d = new SimpleDateFormat("HH:mm:ss");
		sd = new Date();
	}

	@Override
	public void print(String s) {
		super.print(">> " + d.format(sd) + ": " + s);
	}
}

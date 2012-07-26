package com.ravendev.raven;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleWrapper extends PrintStream {

	private Date sd;
	private SimpleDateFormat d;
	
	public ConsoleWrapper() {
		super(System.out);
		d = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		sd = new Date();
	}

	@Override
	public void print(String s) {
		super.print(">> " + d.format(sd) + ": " + s);
	}

	@Override
	public PrintStream append(char c) {
		return super.append(">> " + d.format(sd) + ": " + c);
	}

	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		return super.append(">> " + d.format(sd) + ": " + csq, start, end);
	}

	@Override
	public PrintStream append(CharSequence csq) {
		return super.append(">> " + d.format(sd) + ": " + csq);
	}

	@Override
	public void print(boolean b) {
		print(">> " + d.format(sd) + ": " + b);
	}

	@Override
	public void print(char c) {
		print(">> " + d.format(sd) + ": " + c);
	}

	@Override
	public void print(char[] s) {
		print(">> " + d.format(sd) + ": " + s);
	}

	@Override
	public void print(double d) {
		print(">> " + this.d.format(sd) + ": " + d);
	}

	@Override
	public void print(float f) {
		print(">> " + d.format(sd) + ": " + f);
	}

	@Override
	public void print(int i) {
		print(">> " + d.format(sd) + ": " + i);
	}

	@Override
	public void print(long l) {
		print(">> " + d.format(sd) + ": " + l);
	}

	@Override
	public void print(Object obj) {
		print(">> " + d.format(sd) + ": " + obj);
	}

	@Override
	public void println() {
		print("\n");
	}

	@Override
	public void println(boolean x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(char x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(char[] x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(double x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(float x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(int x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(long x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(Object x) {
		println(">> " + d.format(sd) + ": " + x);
	}

	@Override
	public void println(String x) {
		super.println(/*">> " + d.format(sd) + ": " +*/ x);
	}
	
	@Override
	public void write(int b) {
		print(">> " + d.format(sd) + ": " + b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		print(">> " + d.format(sd) + ": " + b);
	}
}

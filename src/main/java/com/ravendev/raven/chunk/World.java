package com.ravendev.raven.chunk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class World {
	private String name;
	private ArrayList<Chunk> loaded;
	
	public World(String name) {
		this.name = name;
		this.loaded = new ArrayList<Chunk>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void saveAll() {
		try {
			for(Chunk c : loaded) {
				DataOutputStream d = new DataOutputStream(new FileOutputStream(new File("worlds/" + name + "/" + c.x + "." + c.y + "." + c.z + ".chunk")));
				for(int i = 0; i != 4096; i++) {
					d.writeShort(c.getBlocks()[i]);
				}
				d.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAll() {
		try {
			for(File f : new File("worlds/" + name).listFiles()) {
				if(f.getName().endsWith(".chunk")) {
					if(f.getName().split(".").length != 4) {
						System.err.println(f.getName() + " had a invalid chunk coordinate!");
						f.delete();
						continue;
					}
					int x = Integer.parseInt(f.getName().split(".")[0]);
					int y = Integer.parseInt(f.getName().split(".")[1]);
					int z = Integer.parseInt(f.getName().split(".")[2]);
					loaded.add(new Chunk(x, y, z, new DataInputStream(new FileInputStream(f))));
					System.out.println("Loading Chunk: " + x + ", " + y + ", " + z + " in World " + name + "!"); 
				}
			}
		} catch(Exception e) {
			
		}
	}
	
	public Chunk getChunk(int x, int y, int z) {
		for(Chunk c : loaded) {
			if(c.x == x && c.y == y && c.z == z) {
				System.out.println("Chunk " + x + ", " + y + ", " + z + " was loaded in World " + name + "!");
				return c;
			}
		}
		Chunk c = new Chunk(x, y, z);
		loaded.add(c);
		System.out.println("Chunk " + x + ", " + y + ", " + z + " was created in World " + name + "!");
		return c;
	}
}

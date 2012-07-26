package com.ravendev.raven.chunk;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Random;

public class Chunk {
	private short[] blocks = new short[4096];// 4096 shorts-> 8192 bytes
	private byte[] lighting = new byte[4096];// >.<
	public int x;
	public int z;
	public int y;

	public Chunk(int cx, int cy, int cz) {
		Random random = new Random();
		x = cx;
		z = cz;
		y = cy;

		Arrays.fill(blocks, (short) 0);
		int by;
		int bz;
		for (int i = 0; i < 4096; i++) {
			bz = (int) (i / 256);
			by = (int) ((i - (256 * bz)) / 16);
			if (y == 0) {
				blocks[i] = (short)7;
			} else if(y > 0 && y < 16) {
				blocks[i] = (short)1;
			} else if(y == 16) {
				blocks[i] = (short)2;
			}
			lighting[i] = (byte) 0xf0;// full day, no torch
		}
	}

	public Chunk(int cx, int cy, int cz, DataInputStream dis) {
		x = cx;
		z = cz;
		y = cy;
		for (int i = 0; i < 4096; i++) {
			try {
				blocks[i] = dis.readShort();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setBlock(int bx, int by, int bz, short block) {
		blocks[bx + (16 * by) + (256 * bz)] = block;
	}
	
	public short[] getBlocks() {
		return blocks;
	}
	
	public byte[] getDataToSend() {
		ByteBuffer byteBuf = ByteBuffer.allocate(12288);
		byteBuf.order(ByteOrder.LITTLE_ENDIAN);

		for (int i = 0; i < 4096; i++) {
			byteBuf.putShort(blocks[i]);
		}
		for (int i = 0; i < 4096; i++) {
			byteBuf.put(lighting[i]);
		}
		byte[] bytearray = byteBuf.array();
		return bytearray;
	}
	
	public byte[] getLightings() {
		return lighting;
	}
}

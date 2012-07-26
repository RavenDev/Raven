package com.ravendev.raven.server;

import java.awt.Color;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.ravendev.raven.Raven;
import com.ravendev.raven.chunk.World;

public class ClientHandler extends SimpleChannelHandler {

	private World myWorld;
	private int id;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
		Channel client = event.getChannel();
		ChannelBuffer readBuffer = (ChannelBuffer) event.getMessage();
		int director = readBuffer.readInt();
//		byte[] bytearr = new byte[readBuffer.readableBytes()];#
//		readBuffer.readBytes(bytearr);
			if(director ==  1) { //Login
				ChannelBuffer wbuff = ChannelBuffers.buffer(256);
				wbuff.writeInt(1);
				id = 1;
				String message="OK";
				wbuff.writeShort((short)message.length());
				for(int i=0;i<message.length(); i++){
					wbuff.writeByte((byte)message.charAt(i));
				}
				client.write(wbuff);
				wbuff.clear();
				wbuff.writeInt(2);
				wbuff.writeFloat(0);
				wbuff.writeFloat(0);
				wbuff.writeFloat(0);
				myWorld = Raven.getServer().getDefaultWorld();
				for(int i = 0; i<10; i++){
					wbuff.writeShort(i+1);
					wbuff.writeShort(i+1);
				}
				client.write(wbuff);
			} else if(director == 3) { //Get Chunk
				Raven.getServer().demandChunk(readBuffer.readInt(), readBuffer.readInt(), readBuffer.readInt(), myWorld, client);
			} else if(director == 19) { //PING
				ChannelBuffer wbuff = ChannelBuffers.buffer(256);
				wbuff.writeInt(19);
				wbuff.writeInt(Color.red.getRGB());
				String message="This Server is running Raven!";
				wbuff.writeShort((short)message.length());
				for(int i=0;i<message.length(); i++){
					wbuff.writeByte((byte)message.charAt(i));
				}
				client.write(wbuff);
				System.out.println("[" + client.getRemoteAddress().toString() + "] pinged!");
			} else if(director == 6) {
				readBuffer.readFloat();
				readBuffer.readFloat();
				readBuffer.readFloat();
				readBuffer.readFloat();
				readBuffer.readFloat();
//				readBuffer.readInt();
			}
		System.out.println(director);
	}
}

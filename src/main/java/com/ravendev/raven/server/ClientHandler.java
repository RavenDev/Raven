package com.ravendev.raven.server;

import java.awt.Color;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
		Channel client = event.getChannel();
		ChannelBuffer readBuffer = (ChannelBuffer) event.getMessage();
		int director = readBuffer.readInt();
		byte[] bytearr = new byte[readBuffer.readableBytes()];
		readBuffer.readBytes(bytearr);
			if(director ==  1) { //Login
				ChannelBuffer wbuff = ChannelBuffers.buffer(256);
				wbuff.writeInt(1);
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
				for(int i = 0; i<10; i++){
					wbuff.writeShort(i+1);
					wbuff.writeShort(i+1);
				}
				client.write(wbuff);
			} else if(director == 3) { //Get Chunk
				;
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
			}
		System.out.println(director);
	}
}

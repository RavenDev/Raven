package com.ravendev.raven.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.ravendev.raven.chunk.Chunk;
import com.ravendev.raven.chunk.World;
import com.ravendev.raven.utils.Utils;

public class RavenServer {

	private ExecutorService threadpool = Executors.newCachedThreadPool();
	private World dworld;
	
	
	public RavenServer() {
//		ServerBootstrap bootstrap = new ServerBootstrap();
//		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//			public ChannelPipeline getPipeline() throws Exception {
//				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("handler", new ClientHandler());
//				return pipeline;
//			}
//		});public void run(){
		ChannelFactory factory =
	            new NioServerSocketChannelFactory(
	                    Executors.newCachedThreadPool(),
	                    Executors.newCachedThreadPool());

	        ServerBootstrap bootstrap = new ServerBootstrap(factory);

	        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
	            public ChannelPipeline getPipeline() {
	                return Channels.pipeline(new ClientHandler());
	            }
	        });

	        bootstrap.setOption("child.tcpNoDelay", true);
	        bootstrap.setOption("child.keepAlive", true);

	        bootstrap.bind(new InetSocketAddress(4242));
	        dworld = new World("World");
		}
	
	public void demandChunk(final int x, final int y, final int z, final World w, final Channel client) {
		threadpool.execute(new Runnable() {
			public void run() {
				Chunk c = w.getChunk(Math.round(x / 16) * 16, Math.round(y / 8) * 8, Math.round(z / 16) * 16);
				byte[] chunk = Utils.deflate(c.getDataToSend());
				ChannelBuffer wbuff = ChannelBuffers.buffer(256);
				wbuff.writeInt(3);
				wbuff.writeInt(x);
				wbuff.writeInt(y);
				wbuff.writeInt(z);
				wbuff.writeInt(chunk.length);
				wbuff.writeBytes(chunk);
				client.write(wbuff);
			}
		});
	}

	public World getDefaultWorld() {
		return dworld;
	}
}

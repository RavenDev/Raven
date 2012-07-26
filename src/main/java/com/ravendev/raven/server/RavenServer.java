package com.ravendev.raven.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class RavenServer {

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
		}
}

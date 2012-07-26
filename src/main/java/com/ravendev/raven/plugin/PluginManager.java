//package com.ravendev.raven.plugin;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PluginManager {
//	 	private List<Plugin> plugins;
//		public void enableAll() {
//			plugins = PluginLoader.loadPlugins(new File("./plugin"));
//			for (Plugin p : plugins) {
//				p.setEnabled(false);
//			}
//		}
//	 	public void disableAll() {
//		    for (Plugin p : plugins) {
//		      p.setEnabled(false);
//		 }
//	 }
//}

//package com.ravendev.raven.plugin;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.jar.JarEntry;
//import java.util.jar.JarInputStream;
//
//public class PluginLoader {
//	private static List<Class<Plugin>> extractClassesFromJAR(File jar, ClassLoader cl) throws IOException {
//	  List<Class<Plugin>> classes = new ArrayList<Class<Plugin>>();
//	  JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
//	  JarEntry ent = null;
//	  while ((ent = jaris.getNextJarEntry()) != null) {
//	    if (ent.getName().toLowerCase().endsWith(".class")) {
//	      try {
//	        Class<?> cls = cl.loadClass(ent.getName().substring(0, ent.getName().length() - 6).replace('/', '.'));
//	        if (PluginLoader.isPluggableClass(cls)) {
//	          classes.add((Class<Plugin>)cls);
//	        }
//	      }
//	      catch (ClassNotFoundException e) {
//	        System.err.println("Can't load Class " + ent.getName());
//	        e.printStackTrace();
//	      }
//	    }
//	  }
//	  jaris.close();
//	  return classes;
//	}
//
//	private static boolean isPluggableClass(Class<?> cls) {
//		cls.
//		return false;
//	}
//}

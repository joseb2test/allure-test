package com.b2.logger;


import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import com.b2.constant.FrameworkCommonConstants;
import com.b2.helper.DataHelper;

public class Logger {
	
				private static boolean isConfigured = false;
	   
				public static org.apache.log4j.Logger getLogger(String logFileName) {
                	
                    if (!isConfigured){
                         	PropertyConfigurator.configure(DataHelper.getTestLoggerDirectory() + File.separatorChar+logFileName);
                         	isConfigured = true;
                    }
                	return org.apache.log4j.Logger.getLogger(getClassName());
                }
                
                public static org.apache.log4j.Logger getLogger() {
                	
                    if (!isConfigured){
                         	PropertyConfigurator.configure(DataHelper.getTestLoggerDirectory() + org.apache.commons.io.FilenameUtils.separatorsToSystem(FrameworkCommonConstants.LOG4J_PROPERTY_FILE));
                         	isConfigured = true;
                    }
                	return org.apache.log4j.Logger.getLogger(getClassName());
                }
                
                public synchronized static String getClassName() {
              		try {
						return Thread.currentThread().getContextClassLoader().loadClass(Thread.currentThread().getStackTrace()[4].getClassName()).getName();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					return "";
	
                }
                
                public static void trace(Object message) {
                	getLogger().trace(message);
                	Reporter.log(message+"");
                }
                
                public static void trace(Object message, Throwable t) {
                	getLogger().trace(message, t);
                }
                
                public static boolean isTraceEnabled() {
                    return getLogger().isTraceEnabled();
                }
                
                public static void debug(Object message) {
                	getLogger().debug(message);Reporter.log(message+"");
                }
                
                public static void debug(Object message, Throwable t) {
                	getLogger().debug(message, t);
                }
                
                public static void error(Object message) {
                	getLogger().error(message);Reporter.log(message+"");
                }
                
                public static void error(Object message, Throwable t) {
                	getLogger().error(message, t);
                }
                
                public static void fatal(Object message) {
                	getLogger().fatal(message);Reporter.log(message+"");
                }
                
                public static void fatal(Object message, Throwable t) {
                	getLogger().fatal(message, t);
                }
                
                public static void info(Object message) {
                	getLogger().info(message);Reporter.log(message+"");
                }
                
                public static void info(Object message, Throwable t) {
                	getLogger().info(message, t);
                }
                
                public static boolean isDebugEnabled() {
                    return getLogger().isDebugEnabled();
                }
                
                public static boolean isEnabledFor(Priority level) {
                    return getLogger().isEnabledFor(level);
                }
                
                public static boolean isInfoEnabled() {
                    return getLogger().isInfoEnabled();
                }
                
                public static void setLevel(Level level) {
                	getLogger().setLevel(level);
                }
                
                public static void warn(Object message) {
                	getLogger().warn(message);Reporter.log(message+"");
                }
                
                public static void warn(Object message, Throwable t) {
                	getLogger().warn(message, t);
                }
 }


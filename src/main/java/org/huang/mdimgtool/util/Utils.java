package org.huang.mdimgtool.util;

import java.awt.image.BufferedImage;
import java.util.Properties;

import org.huang.mdimgtool.provider.PropertiesProviderImpl;

public class Utils {
	
	public final static String FILE_SPLIT = ".";
	public final static String IMG_FORMAT = "png";
	public final static String PROPERTIES_FILE_NAME = "app.properties";
	
	public static BufferedImage screenSelectedImage;
	
	private final static Properties propertiesProvider = new PropertiesProviderImpl().get();
	
	public final static String USER_NAME = propertiesProvider.getProperty("userName");
	public final static String USER_PASSWORD = propertiesProvider.getProperty("password");
	public final static String GIT_LOCAL_LOCATION = propertiesProvider.getProperty("git-local-location");
	public final static String GIT_REMOTE_LOCATION = propertiesProvider.getProperty("git-remote-location");
}

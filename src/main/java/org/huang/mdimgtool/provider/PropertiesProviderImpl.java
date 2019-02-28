package org.huang.mdimgtool.provider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.huang.mdimgtool.util.Utils;

public class PropertiesProviderImpl implements PropertiesProvider{
	
	@Override
	public Properties get() {
		try(FileInputStream in = new FileInputStream(Utils.PROPERTIES_FILE_NAME)){
			Properties pro = new Properties();
			pro.load(in);
			return pro;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

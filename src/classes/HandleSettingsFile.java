package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HandleSettingsFile {
	public static void writeFileIfNotExists() {
		File file = new File(System.getProperty("user.dir") + "\\settings.txt");   
		if (!file.exists()) {
			writeFileData("sound", "true");
			writeFileData("last_score", "0");
			writeFileData("best_score", "0");
		}
	}
	
	public static void writeFileData(String setting_name, String value) {
		try {
			ArrayList<String> settings = HandleSettingsFile.getSettings();
			PrintWriter pw = new PrintWriter(new FileOutputStream("settings.txt"));
			boolean alreadyWrite = false;
			
			for (String setting : settings) {
				String[] temp = setting.split("=");
				if (temp[0].equals(setting_name)) {
					pw.println(setting_name + "=" + value);
					alreadyWrite = true;
				} else {
					pw.println(setting);
				}
			}
			
			if (!alreadyWrite) {
				pw.println(setting_name + "=" + value);
			}
			
		    pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public static ArrayList<String> getSettings() {
    	ArrayList<String> output = new ArrayList<>();
    	
    	try {
    		File file = new File(System.getProperty("user.dir") + "\\settings.txt");   
    		if (file.exists()) {
    			BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));
            	int lines = 0;
            	while (reader.readLine() != null) lines++;
            	reader.close();
            	
            	ArrayList<String> settings = new ArrayList<>();
            	
        		for (int i = 0; i < lines; i++) {
        			settings.add(Files.readAllLines(Paths.get("settings.txt")).get(i));
        		}
      
        		output = settings;
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return output;
    }
    
    public static String getSpecifiedSetting(String _setting) {
    	String output = null;
    	ArrayList<String> settings = HandleSettingsFile.getSettings();
    	
    	for (String setting : settings) {
			String[] temp = setting.split("=");
			if (temp[0].equals(_setting)) {
				output = temp[1];
			}
		}
    	
    	return output;
    }
}

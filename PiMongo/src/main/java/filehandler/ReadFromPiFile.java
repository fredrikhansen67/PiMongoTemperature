package filehandler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author fredrik Hansen
 *
 */

public class ReadFromPiFile {
	
	private String temperaturePath="/sys/bus/w1/devices/";
	private File fil = null;
	
	public ReadFromPiFile(String path, String filename) {
		this.temperaturePath = path;
		this.fil = new File(temperaturePath+"\\"+getDevicePathValue(path, "28")+"/"+filename);
	}
	
	public String getTemperatureFromFile() {
		String temp = "0";
		try {
			List<String> str = Files.readAllLines(fil.toPath());
			String templine = str.get(str.size()-1);
			String lastDigits[] = templine.split("=");
			temp = new DecimalFormat("##.##").format(Double.parseDouble(lastDigits[1])/1000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	/**
	 * This function will take out the actual folder name for the device.
	 * This name is not always unique, therefore this method is implemented
	 * @param path
	 * @param strVal
	 * @return
	 */
	public String getDevicePathValue(String path, String strVal) {
		File file = new File(path);		
		String[] directories = file.list(new DefinedFileNameFilter(strVal) {
		  @Override
		  public boolean accept(File current, String name) {
			return name.startsWith("28");  
		  }
		});
		System.out.println("Folders :" +Arrays.toString(directories));
		return directories[0];
	}
	
	/**
	 * This is a filter to find a unique name among the folderlist
	 *
	 */
	public static class DefinedFileNameFilter implements FilenameFilter {
		private String pattern;
		public DefinedFileNameFilter(String filtrera) {
			this.pattern = filtrera.toLowerCase();
		}
		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(pattern);
		}
	}
	

}

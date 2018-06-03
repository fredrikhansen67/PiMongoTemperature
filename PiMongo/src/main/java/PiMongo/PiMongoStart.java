package PiMongo;

import filehandler.ReadFromPiFile;
import persistant.MongoDBHandler;

/**
 * Main class to start the program
 * @author Fredrik Hansen
 *
 */

public class PiMongoStart {
	
	private static String sensor = "1";
	private static String datebase = "test";
	private static String mongoIP = "192.168.1.189";
	private static String sensorPath = "/sys/bus/w1/devices/";
	private static String sensorFile = "w1_slave";
	private static String collectionName = "TEMPERATURES";
	
    public static void main( String[] args )
    {
        System.out.println( "Hello Mongo!" );
        ReadFromPiFile rr = new ReadFromPiFile(sensorPath,sensorFile); ///sys/bus/w1/devices/ "C:\\aaa\\w1_bus_master1","w1_slave"
        MongoDBHandler mongo = new MongoDBHandler(mongoIP ,datebase, "pi", "texas");
        
        int loop = 0;
        while(true) {
        	
        	try {
        		if(loop==10) {
        			System.out.println("");
        			loop=0;
        		}
        		else {
        			mongo.saveToMongoDB(datebase, collectionName, sensor, rr.getTemperatureFromFile());;
        			System.out.print(rr.getTemperatureFromFile()+", ");
        			loop++;
        		}
				Thread.sleep(15000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
        	
        }
        
        

    }
}

package PiMongo;

import filehandler.ReadFromPiFile;
import persistant.MongoDBHandler;

/**
 * Main class to start the program
 * @author Fredrik Hansen
 *
 */

public class PiMongoStart {
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello Mongo!" );
        ReadFromPiFile rr = new ReadFromPiFile("/sys/bus/w1/devices/","w1_slave"); ///sys/bus/w1/devices/ "C:\\aaa\\w1_bus_master1","w1_slave"
        MongoDBHandler mongo = new MongoDBHandler("192.168.1.189","test", "pi", "texas");
        
        int loop = 0;
        while(true) {
        	
        	try {
        		if(loop==10) {
        			System.out.println("");
        			loop=0;
        		}
        		else {
        			mongo.saveToMongoDB("test","1", rr.getTemperatureFromFile());;
        			System.out.print(rr.getTemperatureFromFile()+", ");
        			loop++;
        		}
				Thread.sleep(1500);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
        	
        }
        
        

    }
}

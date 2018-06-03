package PiMongo;

import filehandler.ReadFromPiFile;

/**
 * Main class to start the program
 * @author Fredrik Hansen
 *
 */
public class PiMongoStart {

	
    public static void main( String[] args )
    {
        System.out.println( "Hello Mongo!" );
        ReadFromPiFile rr = new ReadFromPiFile("C:\\aaa\\w1_bus_master1","w1_slave");
        
        
        while(true) {
        	System.out.println("hej");
        	try {
        		System.out.println("temperature is :" + rr.getTemperatureFromFile());
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
//        	try {
//        	
//        	}catch(ExitProgramException e){System.exit()}
//        }
    }
}

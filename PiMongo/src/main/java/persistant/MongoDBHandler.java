package persistant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBHandler {

	private MongoCollection<Document> collection;
	private static String ip ="";
	public static MongoClient mongoClient;
	public static MongoDatabase db;
	
	public MongoDBHandler(String ip,String dbname, String username, String pwd) {
		MongoDBHandler.ip = ip;
		mongoClient = new MongoClient(new MongoClientURI("mongodb://"+ip+":27017"));
		db = mongoClient.getDatabase(dbname);
	}
	

	public void saveToMongoDB( String dbname,String sensor, String tempValue) {
		MongoDatabase db = mongoClient.getDatabase(dbname);
		this.collection = db.getCollection("TEMPERATURES");
		LocalDate localDate = LocalDate.now();
		LocalTime now = LocalTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
		String tid = now.format(format);
		
		System.out.println("Date :" +localDate +" "+tid);
		
		Document doc = new Document();
		doc.append("time", localDate+" "+tid +"\t"+collection.count())
		.append("sensor", sensor)
		.append("temp", tempValue);
		
		collection.insertOne(doc);
		

	}
	
}

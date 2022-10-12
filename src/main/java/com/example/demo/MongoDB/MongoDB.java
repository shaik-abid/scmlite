package com.example.demo.MongoDB;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import java.util.Vector;

public class MongoDB {
	
//	@Value("${MONGODB_URI}")
//  private static String mongo_uri;
	
///	private static final String uri = "mongodb://localhost:27017";
	
//	@Value("${MONGO_DB}")
//  private static String mongo_db;
	
//	@Value("${Users}")
//    private static String Users;
//	
//	@Value("${shipments}")
//    private static String shipment;
//	
//	@Value("${DeviceDataStream}")
//    private static String DeviceDataStream;
	
///	private static final String database_name = "ScmLite";

//	@Value("${spring.data.mongodb.uri}")
//    private String mongo_uri;
//
//    @Value("${spring.data.mongodb.database}")
//    private String mongo_database;
    
	private static final String uri = "mongodb+srv://user:Scmlite8989@sandbox.rsyc2ih.mongodb.net/?retryWrites=true&w=majority";
	private static final String database_name = "ScmLite";
    private static final MongoDatabase database;
//    private static final MongoCollection<Document> users;
//    private static final MongoCollection<Document> shipments;
//    private static final MongoCollection<Document> devices;
     static {
  	
       MongoClient mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase(database_name);
        
//        users = database.getCollection(Users);
//       shipments   = database.getCollection(shipment1);
//       devices  = database.getCollection("DeviceDataStream");
   }

    private static final MongoCollection<Document> users = database.getCollection("Users");
    private static final MongoCollection<Document> shipments = database.getCollection("Shipments");
    private static final MongoCollection<Document> devices = database.getCollection("DeviceDataStream");

//    public void mongo() {
//   	
//        MongoClient mongoClient = MongoClients.create(mongo_uri);
//        System.out.println("this is mongo uri "+ mongoClient);
//        database = mongoClient.getDatabase(mongo_database);
//   
//  }

    private static String myHash(String s){
        return Integer.toHexString(s.hashCode());
    }

    public static void addUser(String username, String password){
        Document document = new Document();
        document.append("_id", username);
        document.append("Password", myHash(password));
        users.insertOne(document);
    }

    public static void addShipment(String username, String shipment_Number,String container_no,
             String desc,
             String route,
             String goods,
             String device,
             String date,
             String po_no,
             String delivery_no,
             String ndc_no,
             String batch_id,
             String serial_no){
        Document document = new Document();
        document.append("username", username);
        document.append("shipment_Number", shipment_Number);
        document.append("container_no",container_no);
        document.append("desc", desc);
        document.append("route", route);
        document.append("goods", goods);
        document.append("device", device);
        document.append("date", date);
        document.append("po_no", po_no);
        document.append("delivery_no", delivery_no);
        document.append("ndc_no", ndc_no);
        document.append("batch_id", batch_id);
        document.append("serial_no", serial_no);
        shipments.insertOne(document);
    }

    public static boolean isUsernameAvailable(String username) throws Exception {
        for (Document document : users.find(new Document("_id", username))) {
            if (document.get("_id").equals(username))
                throw new Exception("Username already exists");
        }
        return true;
    }

    public static boolean authenticateUser(String username, String password) throws Exception {
        for (Document document : users.find(new Document("_id", username))) {
            if (document.get("Password").equals(myHash(password)))
                return true;
            else
                throw new Exception("Incorrect Password");
        }
        throw new Exception("User not found. Please enter valid Username/Password");
    }

    public static Vector<String> getDeviceIDs(){
        Vector<String> devices = new Vector<>();
        devices.add("001156053070");
        devices.add("001156053071");
        devices.add("001156053072");
        devices.add("001156053073");
        devices.add("001156053074");
        devices.add("001156053075");
        return devices;
    }

    public static Vector<Document> getDevices() {
        Vector<Document> devices = new Vector<>();
        for (Document document : MongoDB.devices.find())
            devices.add(document);
        return devices;
    }

}

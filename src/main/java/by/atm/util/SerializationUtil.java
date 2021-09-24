package main.java.by.atm.util;

import java.io.*;

public class SerializationUtil {
//    public static final String FILENAME = "src/main/resources/serialization/atm.txt";
    public static final String FILENAME = "atm.txt";

    public static void serializeObject(Object object, String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.out.println("An exception occurred during serialization.");
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String filename) {
        Object returnObject = null;
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            returnObject = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An exception occurred during deserialization.");
            e.printStackTrace();
        }
        return returnObject;
    }
}

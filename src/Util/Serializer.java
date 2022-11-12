package Util;

import java.io.*;

/**
 * Helper class to Serialise (Write) and Deserialise (Read) objects into/from .dat files.
 */
public class Serializer {
    /**
     * Serialise object and write into a file.
     * @param file Directory path of the .txt file to read
     * @param object Object to serialise
     */
    public static void serialize(String file, Object object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(object);

        outputStream.close();
    }

    /**
     * De-serialise and return object from a file.
     * @param file Directory path of the .txt file to read
     * @return Returns the object from a file.
     */
    public static Object deSerialize(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

        Object output = inputStream.readObject();
        inputStream.close();

        return output;
    }

}

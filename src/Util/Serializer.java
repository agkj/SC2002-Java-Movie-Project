package Util;

import java.io.*;

public class Serializer {
    public static void serialize(String file, Object object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(object);

        outputStream.close();
    }

    public static Object deSerialize(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

        Object output = inputStream.readObject();
        inputStream.close();

        return output;
    }

}

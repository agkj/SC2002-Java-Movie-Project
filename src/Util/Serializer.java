package Util;

import java.io.*;

public class Serializer {
    private static final long serialVersionUID = 6529685098267757690L;

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

    //// Example
    /*
        // Create Object
        Person abc = new Person("abc", 60);
        Person def = new Person("def", 40);

        Person[] persons = new Person[]{abc, def};

        // Write to .dat file
        try {
            Serializer.serialize("persons.dat", persons);
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Read .dat file
        try {
            Person[] result = (Person[]) Serializer.deSerialize("persons.dat");

            for(int i=0; i < 2; i++) {
                System.out.println(result[i].getName());
            }
            //Person test = (Person) Serializer.deSerialize("persons.dat");
            //System.out.println(test.getName());
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
     */
}

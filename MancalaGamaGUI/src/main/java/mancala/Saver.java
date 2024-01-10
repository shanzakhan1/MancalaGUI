package mancala;

import java.io.*;

public class Saver {
    public static void saveObject(final Serializable toSave, final String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("assets/" + filename))) {
            oos.writeObject(toSave);
        }
    }

    public static Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("assets/" + filename))) {
            return (Serializable) ois.readObject();
        }
    }
}

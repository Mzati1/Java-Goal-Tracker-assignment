import java.io.*;

//functions to load and save data back and forth 
//using serialization
public class GoalManagerSerializationHelper {
    public static void saveGoalManager(goalManager manager, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(manager);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static goalManager loadGoalManager(String filename) {
        goalManager manager = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            manager = (goalManager) objectIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return manager;
    }
}

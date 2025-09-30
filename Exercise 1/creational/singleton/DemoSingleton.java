class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {}
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) instance = new DatabaseConnection();
        return instance;
    }
    public void connect() {
        System.out.println("Connected to database.");
    }
}
public class DemoSingleton {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db1.connect();
        System.out.println("Same instance? " + (db1 == db2));
    }
}
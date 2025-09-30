import java.util.*;
interface Command {
    void execute();
}
interface RoomObserver {
    void update(Room room);
}
class LightSystem implements RoomObserver {
    public void update(Room room) {
        if (room.isOccupied())
            System.out.println(" Lights ON in Room " + room.getId());
        else
            System.out.println(" Lights OFF in Room " + room.getId());
    }
}
class ACSystem implements RoomObserver {
    public void update(Room room) {
        if (room.isOccupied())
            System.out.println(" AC ON in Room " + room.getId());
        else
            System.out.println(" AC OFF in Room " + room.getId());
    }
}
class Room {
    private int id;
    private int capacity;
    private boolean booked = false;
    private boolean occupied = false;
    private List<RoomObserver> observers = new ArrayList<>();

    public Room(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int cap) { this.capacity = cap; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean b) { booked = b; }
    public boolean isOccupied() { return occupied; }

    public void setOccupied(boolean occ) {
        occupied = occ;
        notifyObservers();
    }

    public void addObserver(RoomObserver obs) { observers.add(obs); }
    private void notifyObservers() { for (RoomObserver o : observers) o.update(this); }
}
class OfficeManager {
    private static OfficeManager instance;
    private Map<Integer, Room> rooms = new HashMap<>();

    private OfficeManager() {}

    public static synchronized OfficeManager getInstance() {
        if (instance == null) instance = new OfficeManager();
        return instance;
    }

    public void configureRooms(int count) {
        rooms.clear();
        for (int i=1; i<=count; i++) {
            Room room = new Room(i, 5); // default capacity
            room.addObserver(new LightSystem());
            room.addObserver(new ACSystem());
            rooms.put(i, room);
        }
        System.out.print("Office configured with " + count + " meeting rooms:");
        for (int i=1;i<=count;i++) System.out.print(" Room " + i + (i<count?",":""));
        System.out.println(".");
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }
}
class BookRoomCommand implements Command {
    private Room room;
    private String time;
    private int duration;
    public BookRoomCommand(Room r, String t, int d){room=r;time=t;duration=d;}
    public void execute(){
        if(room==null){ System.out.println("Invalid room number."); return;}
        if(room.isBooked()) {
            System.out.println("Room " + room.getId() + " is already booked during this time. Cannot book.");
        } else {
            room.setBooked(true);
            System.out.println("Room " + room.getId() + " booked from " + time + " for " + duration + " minutes.");
        }
    }
}
class CancelRoomCommand implements Command {
    private Room room;
    public CancelRoomCommand(Room r){room=r;}
    public void execute(){
        if(room==null){System.out.println("Invalid room number."); return;}
        if(!room.isBooked()){
            System.out.println("Room " + room.getId() + " is not booked. Cannot cancel booking.");
        } else {
            room.setBooked(false);
            System.out.println("Booking for Room " + room.getId() + " cancelled successfully.");
        }
    }
}

class OccupancyCommand implements Command {
    private Room room;
    private int people;
    public OccupancyCommand(Room r,int p){room=r;people=p;}
    public void execute(){
        if(room==null){System.out.println("Room does not exist."); return;}
        if(people >= 2){
            room.setOccupied(true);
            System.out.println("Room " + room.getId() + " is now occupied by " + people + " persons. AC and lights turned on.");
        } else {
            room.setOccupied(false);
            System.out.println("Room " + room.getId() + " occupancy insufficient to mark as occupied.");
        }
    }
}
public class SmartOfficeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OfficeManager office = OfficeManager.getInstance();

        System.out.println("=== Smart Office Facility ===");
        System.out.println("Type commands like:");
        System.out.println("Config room count 3");
        System.out.println("Config room max capacity 1 10");
        System.out.println("Block room 1 09:00 60");
        System.out.println("Cancel room 1");
        System.out.println("Add occupant 1 2");
        System.out.println("exit");
        System.out.println("=============================");

        while(true){
            System.out.print("> ");
            String line=sc.nextLine();
            if(line.equalsIgnoreCase("exit")) break;

            String[] parts=line.split(" ");
            try {
                switch(parts[0].toLowerCase()){
                    case "config":
                        if(parts[1].equalsIgnoreCase("room") && parts[2].equalsIgnoreCase("count")){
                            int count=Integer.parseInt(parts[3]);
                            office.configureRooms(count);
                        } else if(parts[1].equalsIgnoreCase("room") && parts[2].equalsIgnoreCase("max") && parts[3].equalsIgnoreCase("capacity")){
                            int roomId=Integer.parseInt(parts[4]);
                            int cap=Integer.parseInt(parts[5]);
                            Room r=office.getRoom(roomId);
                            if(r!=null && cap>0){
                                r.setCapacity(cap);
                                System.out.println("Room " + roomId + " maximum capacity set to " + cap + ".");
                            } else {
                                System.out.println("Invalid capacity. Please enter a valid positive number.");
                            }
                        }
                        break;
                    case "block":
                        int rid=Integer.parseInt(parts[2]);
                        Room br=office.getRoom(rid);
                        Command book=new BookRoomCommand(br,parts[3],Integer.parseInt(parts[4]));
                        book.execute();
                        break;
                    case "cancel":
                        int rid2=Integer.parseInt(parts[2]);
                        Room cr=office.getRoom(rid2);
                        Command cancel=new CancelRoomCommand(cr);
                        cancel.execute();
                        break;
                    case "add":
                        int rid3=Integer.parseInt(parts[2]);
                        int persons=Integer.parseInt(parts[3]);
                        Room or=office.getRoom(rid3);
                        Command occ=new OccupancyCommand(or,persons);
                        occ.execute();
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch(Exception e){
                System.out.println("Invalid input format.");
            }
        }
        sc.close();
    }
}
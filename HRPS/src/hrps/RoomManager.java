/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class RoomManager {

    private static List<Room> rooms =  (List)DBoperations.readSerializedObject("rooms.dat");


    //DONE.
    public void roomOccupancyReport()//no filters
    {
        int i, param = -1;
        Scanner sc = new Scanner(System.in);
        Room r;
        String criteria = "none";

        System.out.println("Search by:");
        System.out.println("\t1. No criteria");
        System.out.println("\t2. Floor");
        System.out.println("\t3. Occupancy");
        i = sc.nextInt();

        switch(i)
        {
            case 1:
                criteria = "none";
                break;
            case 2:
                criteria = "floor";
                System.out.println("Enter floor: ");
                param = sc.nextInt();
                break;
            case 3:
                criteria = "occupancy";
                System.out.println("\t1. Vacant rooms");
                System.out.println("\t2.. Occupied rooms");
                System.out.println("\t3. Reserved rooms");
                System.out.println("\t4. Under-maintenance rooms");
                System.out.println("Enter choice: ");
                int choice = sc.nextInt();
                switch(choice)
                {
                    case 1:
                        param = Room.VACANT;
                        break;
                    case 2:
                        param = Room.OCCUPIED;
                        break;
                    case 3:
                        param = Room.RESERVED;
                        break;
                    case 4:
                        param = Room.UNDER_MAINTENANCE;
                        break;
                    default:
                        System.err.println("Invalid choice.");
                        param = -1;                }

        }


        System.out.println("Room Occupancy:");
        for (i= 0; i<rooms.size();i++)
        {
            r = rooms.get(i);
            if (searchByCriteria(param, criteria, r))
                r.showRoom();
        }
    }

    private boolean searchByCriteria(Object param, String criteria, Room r)
    {
        switch(criteria)
        {
            case "none":
                return true;
            case "floor":
                return (r.getRoomNumber()/100 == (int)param);
            case "occupancy":
                return (r.getAvailability() == (int)param);
            case "bedtype":
                return (r.getRoomType().getBedType() == (int)param);

        }
        return false;
    }

    public void createRoom()
    {
        System.out.println("Creating new room: ("+rooms.size()+" rooms exist)");

        int number = CURmenus.promptRoomNumber();
        int bedType = CURmenus.promptBedType();
        //by default add rooms as available
        int availability = Room.VACANT;
        boolean hasWifi = CURmenus.promptWiFi();
        boolean isSmoking = CURmenus.promptSmoking();

        String facing = CURmenus.promptFacing();
        Room r = new Room(number, isSmoking, hasWifi, facing, bedType, availability);
        rooms.add(r);
        saveRoomDB();
        System.out.println("Room has been added to database:");
        r.showRoom();
    }

    public void editRoom()
    {
        try {
            //TODO: edit room logic
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter room number to edit: ");
            int roomNum = sc.nextInt();

            int roomIndex = findRoom(roomNum);
            if (roomIndex != -1)//if room is found
            {
                //look for room
                Room r = rooms.get(roomIndex);
                System.out.println("Room found:");
                r.showRoom();

                //show edit menu
                System.out.println("Choose what to edit: ");
                System.out.println("1. Smoking");
                System.out.println("2. Has WiFi");
                System.out.println("3. Bed Type");
                System.out.println("4. Availability");
                System.out.println("5. Facing");

                int editChoice = sc.nextInt();
                switch(editChoice)
                {
                    case 1: //edit Type
                        boolean isSmoking = CURmenus.promptSmoking();
                        r.setSmoking(isSmoking);
                        break;
                    case 2:
                        boolean hasWiFi = CURmenus.promptWiFi();
                        r.setHasWiFi(hasWiFi);
                        break;
                    case 3:
                        int bedType = CURmenus.promptBedType();
                        r.setBedType(bedType);
                        break;
                    case 4:
                        int availability = CURmenus.promptAvailability();
                        r.setAvailability(availability);
                        break;
                    case 5:
                        String facing = CURmenus.promptFacing();
                        r.setFacing(facing);
                        break;
                    default:
                        System.err.println("Invalid choice.");
                }
                r.showRoom();
            }

            saveRoomDB();
        } catch (RoomNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void removeRoom()
    {
        try {
            //TODO: remove room logic
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter room number to remove: ");
            int roomNum = sc.nextInt();
            int index = findRoom(roomNum);
            rooms.remove(index);
            System.out.println("Room "+roomNum+" has been removed.");
            saveRoomDB();
        } catch (RoomNotFoundException ex) {
            System.err.println(ex.getMessage());
        }


    }

    public static boolean roomExists(int roomNum)
    {
        try {
            return (findRoom(roomNum)!=-1);
        } catch (RoomNotFoundException ex) {
            return false;
        }
    }

    public  void checkAvailability(int roomNumber)
    {
        try {
            int index = findRoom(roomNumber);
            Room r = rooms.get(index);
            r.getAvailabilityString();
            System.out.println("Room#"+roomNumber+"\t"+r.getAvailabilityString());
        } catch (RoomNotFoundException ex) {
           System.err.println(ex.getMessage());
        }
    }

    public static int findRoom(int roomNum) throws RoomNotFoundException
    {
        int i;
        Room r;
        for (i = 0; i < rooms.size(); i++)
        {
            r= rooms.get(i);
            if (r.getRoomNumber()==roomNum)
                return i;

        }
        throw new RoomNotFoundException();
    }

    public Room chooseRoom()
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter room number: ");
            int rn = sc.nextInt(), index;
            index = findRoom(rn);
            return rooms.get(index);
        } catch (RoomNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }

    private void saveRoomDB()//private because we don't want other objects to affect db
    {
        DBoperations.writeSerializedObject("rooms.dat", rooms);
    }

    void setStatus(int rr, int status) {
        try
        {
            int index = findRoom(rr);
            Room r = rooms.get(index);
            r.setAvailability(status);
            System.out.println("Room #"+rr+" is now "+r.getAvailabilityString()+".");
            saveRoomDB();
        }
        catch (RoomNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    void makeAvailable (int rr)
    {
        setStatus(rr, Room.VACANT);
    }

}

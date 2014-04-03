/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class RoomManager {

    private static ArrayList rooms =  (ArrayList)SerializeDB.readSerializedObject("rooms.dat");


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
            r = (Room)rooms.get(i);
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
                return (r.getBedType() == (int)param);

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
        boolean isSmoking = CURmenus.promptSmoking();
        boolean hasWifi = CURmenus.promptWiFi();
        String facing = CURmenus.promptFacing();
        Room r = new Room(number, isSmoking, hasWifi, facing, bedType, availability);
        saveRoomDB();
        System.out.println("Room has been added to database:");
        r.showRoom();
    }

    public void editRoom()
    {
        //TODO: edit room logic
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to edit: ");
        int roomNum = sc.nextInt();
        int roomIndex = findRoom(roomNum);
        if (roomIndex != -1)//if room is found
        {
            //look for room
            Room r = (Room)rooms.get(roomIndex);
            System.out.println("Room found:");
            r.showRoom();

            //show edit menu
            System.out.println("Choose what to edit: ");
            System.out.println("1. Type");
            System.out.println("2. Bed Type");
            System.out.println("3. Availability");

            int editChoice = sc.nextInt();
            switch(editChoice)
            {
                case 1: //edit Type
                    break;
                case 2:
                    int bedType = CURmenus.promptBedType();
                    break;
                case 3:
                    int availability = CURmenus.promptAvailability();
                    break;
                default:
                    System.err.println("Invalid choice.");
            }
        }
    }

    public void removeRoom()
    {
        //TODO: remove room logic
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to remove: ");
        int roomNum = sc.nextInt();

        int index = findRoom(roomNum);

        if (index != -1)
        {
            rooms.remove(index);
            System.out.println("Room "+roomNum+" has been removed.");
            saveRoomDB();
        }
        else {
            System.out.print("Room does not exist.");
        }


    }

    public static boolean roomExists(int roomNum)
    {
        return (findRoom(roomNum)!=-1);
    }

    private static int findRoom(int roomNum)
    {
        int i;
        Room r;
        for (i = 0; i < rooms.size(); i++)
        {
            r= (Room)rooms.get(i);
            if (r.getRoomNumber()==roomNum);
                return i;

        }
        return -1;
    }

    private void saveRoomDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("rooms.dat", rooms);
    }
}

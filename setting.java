import java.util.*;

class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

class Room {
    private int roomNumber;
    private int capacity;
    private ArrayList<Student> students;

    public Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        if (students.size() < capacity) {
            students.add(student);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", capacity=" + capacity +
                ", students=" + students +
                '}';
    }
}

class SeatingArrangement {
    private ArrayList<Room> rooms;
    private Random random;

    public SeatingArrangement() {
        this.rooms = new ArrayList<>();
        this.random = new Random();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void assignStudentsToRooms(ArrayList<Student> students) throws Exception {
        for (Student student : students) {
            boolean assigned = false;
            for (Room room : rooms) {
                if (room.addStudent(student)) {
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                throw new Exception("No available room for student " + student.getName());
            }
        }
    }

    public void printSeatingPlan() {
        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " - Capacity: " + room.getCapacity());
            for (Student student : room.getStudents()) {
                System.out.println("  " + student.getName() + " (ID: " + student.getId() + ")");
            }
            System.out.println();
        }
    }
}

public class setting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of branches: ");
        int numBranches = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int b = 1; b <= numBranches; b++) {
            System.out.print("Enter the number of students in Branch " + b + ": ");
            int numStudents = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            for (int i = 0; i < numStudents; i++) {
                System.out.print("Enter student name for Branch " + b + ": ");
                String name = scanner.nextLine();
                students.add(new Student(name, i + 1));
            }
        }

        SeatingArrangement seatingArrangement = new SeatingArrangement();

        System.out.print("Enter the number of rooms: ");
        int numRooms = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int i = 0; i < numRooms; i++) {
            System.out.print("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.print("Enter room capacity: ");
            int capacity = scanner.nextInt();
            seatingArrangement.addRoom(new Room(roomNumber, capacity));
        }

        try {
            seatingArrangement.assignStudentsToRooms(students);
            seatingArrangement.printSeatingPlan();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

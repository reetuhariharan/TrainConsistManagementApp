import java.util.*;
import java.util.stream.*;

class Bogie {
    private String name;
    private int capacity;
    private String type;  // Added for UC12
    private String cargo; // Added for UC12

    // Constructor for Seating (UC10)
    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    // Constructor for Goods/Safety (UC12)
    public Bogie(String name, String type, String cargo) {
        this.name = name;
        this.type = type;
        this.cargo = cargo;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public String getType() { return type; }
    public String getCargo() { return cargo; }
}

public class Main {
    public static void main(String[] args) {
        // UC10: Seating Capacity
        List<Bogie> passengerBogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70)
        );

        int totalSeats = passengerBogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalSeats);

        // UC12: Safety Compliance
        List<Bogie> goodsBogies = Arrays.asList(
                new Bogie("B1", "Cylindrical", "Petroleum"),
                new Bogie("B2", "Rectangular", "Coal")
        );

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.getType().equalsIgnoreCase("Cylindrical") ||
                        b.getCargo().equalsIgnoreCase("Petroleum"));

        System.out.println("Train Safety Status: " + (isSafe ? "PASS" : "FAIL"));
    }
}
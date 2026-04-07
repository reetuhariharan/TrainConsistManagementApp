class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

class GoodsBogie {
    private String bogieId;
    private String shape;
    private String currentCargo;

    public GoodsBogie(String bogieId, String shape) {
        this.bogieId = bogieId;
        this.shape = shape;
    }

    public void assignCargo(String cargoType) {
        System.out.println("Validating cargo assignment for " + bogieId + "...");

        try {
            // Rule: Petroleum cannot be assigned to Rectangular bogies
            if (shape.equalsIgnoreCase("Rectangular") && cargoType.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Unsafe cargo assignment! Petroleum cannot be in a Rectangular bogie.");
            }

            this.currentCargo = cargoType;
            System.out.println("Cargo assigned successfully -> " + cargoType);

        } catch (CargoSafetyException e) {
            // Handle the specific safety exception without crashing
            System.out.println("Error: " + e.getMessage());

        } finally {
            // This block always executes for logging or cleanup
            System.out.println("Cargo validation completed for " + shape + " bogie (" + bogieId + ").");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("UC15 - Safe Cargo Assignment");
        System.out.println("=======================================");

        // Case 1: Safe Assignment (Cylindrical + Petroleum)
        GoodsBogie bogie1 = new GoodsBogie("B001", "Cylindrical");
        bogie1.assignCargo("Petroleum");

        System.out.println();

        // Case 2: Unsafe Assignment (Rectangular + Petroleum) [cite: 1]
        GoodsBogie bogie2 = new GoodsBogie("B002", "Rectangular");
        bogie2.assignCargo("Petroleum");

        System.out.println("\nUC15 runtime handling completed...");
    }
}
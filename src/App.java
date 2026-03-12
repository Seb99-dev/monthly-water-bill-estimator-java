import java.util.Scanner;

/**
 * MonthlyWaterBillEstimator
 *
 * This program reads a customer's name and monthly water consumption,
 * validates the input, calculates the estimated bill, and classifies
 * the consumption level.
 *
 * Concepts used:
 * - Variables and data types
 * - Arithmetic operators
 * - Boolean expressions
 * - Conditional structures
 * - Methods
 * - User input with Scanner
 *
 * @author Jhoan Lozano
 * @version 1.0
 */
public class App {

    public static final double PRICE_PER_CUBIC_METER = 2500.0;
    public static final double HIGH_CONSUMPTION_SURCHARGE = 15000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MONTHLY WATER BILL ESTIMATOR ===");

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Error: Customer name cannot be empty.");
            scanner.close();
            return;
        }

        System.out.print("Enter monthly water consumption in cubic meters: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: You must enter a valid numeric value.");
            scanner.close();
            return;
        }

        double consumption = scanner.nextDouble();

        if (!isValidConsumption(consumption)) {
            System.out.println("Error: Consumption must be greater than or equal to 0.");
            scanner.close();
            return;
        }

        String category = classifyConsumption(consumption);
        double baseBill = calculateBaseBill(consumption);
        double surcharge = calculateSurcharge(consumption);
        double totalBill = baseBill + surcharge;

        System.out.println();
        System.out.println("=== BILL SUMMARY ===");
        System.out.println("Customer: " + customerName);
        System.out.println("Consumption: " + consumption + " m3");
        System.out.println("Category: " + category);
        System.out.println("Base bill: $" + baseBill);
        System.out.println("Surcharge: $" + surcharge);
        System.out.println("Total bill: $" + totalBill);

        printSavingMessage(category);

        scanner.close();
    }

    public static boolean isValidConsumption(double consumption) {
        return consumption >= 0;
    }

    public static String classifyConsumption(double consumption) {
        if (consumption <= 10) {
            return "Low";
        } else if (consumption <= 25) {
            return "Normal";
        } else {
            return "High";
        }
    }

    public static double calculateBaseBill(double consumption) {
        return consumption * PRICE_PER_CUBIC_METER;
    }

    public static double calculateSurcharge(double consumption) {
        if (consumption > 25) {
            return HIGH_CONSUMPTION_SURCHARGE;
        }
        return 0;
    }

    public static void printSavingMessage(String category) {
        System.out.println();
        System.out.println("=== RECOMMENDATION ===");

        if (category.equals("Low")) {
            System.out.println("Excellent. Your water consumption is efficient.");
        } else if (category.equals("Normal")) {
            System.out.println("Good. Try to keep a responsible water usage.");
        } else {
            System.out.println("Warning: Your water consumption is high. Consider saving water.");
        }
    }
}
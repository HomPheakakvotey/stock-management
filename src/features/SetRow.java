package features;

import cstad.Products;

import java.util.List;
import java.util.Scanner;

public class SetRow {
    public static int setRow(List<Products> products, int rowsPerPage) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of row(s) you want to display : ");
            int numberOfRows = Integer.parseInt(scanner.nextLine());
            if (numberOfRows >0 && numberOfRows <= products.size()){
                System.out.println("~".repeat(40));
                System.out.println("\tSet Row "+ numberOfRows +" Successfully!");
                System.out.println("~".repeat(40));
                return numberOfRows;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowsPerPage;
    }
}

package pagination;

import cstad.Products;

import java.util.List;

import static features.Display.display;

public class Previous {
    public static int previous(List<Products> products, int currentPage, int rowsPerPage) {
        if (currentPage > 1) {
            currentPage--;
            display(products, currentPage, rowsPerPage);
        } else {
            System.out.println("You are already on the first page.");
        }
        return currentPage;
    }
}

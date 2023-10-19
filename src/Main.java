import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import pagination.*;
import features.*;
import cstad.Logo;
import cstad.Products;
import cstad.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static features.Help.help;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logo logo = new Logo();
        logo.logo();

        int currentPage = 1;
        int rowsPerPage = 2;

        List<Products> products = new ArrayList<>();
        products.add(new Products(1, "Coca", 2000.0, 50, LocalDate.now()));
        products.add(new Products(2, "Vigor", 2500.0, 100, LocalDate.now()));
        products.add(new Products(3, "Sting", 2000.0, 350, LocalDate.now()));
        products.add(new Products(4, "Pepsi", 2000.0, 18, LocalDate.now()));
        products.add(new Products(5, "Vital", 1000.0, 200, LocalDate.now()));
        products.add(new Products(6, "Colgate", 7000.0, 8, LocalDate.now()));

        do {
            Table.table();

            System.out.print("Command ---> ");
            String options = scanner.nextLine();

            switch (options) {
                case "*" -> Display.display(products, currentPage, rowsPerPage);
                case "w", "W" -> Write.write(products);
                case "r", "R" -> Read.read(products);
                case "u", "U" -> Update.update(products);
                case "d", "D" -> Delete.delete(products);
                case "f", "F" -> currentPage = First.first(products, currentPage, rowsPerPage);
                case "n", "N" -> currentPage = Next.next(products, currentPage, rowsPerPage);
                case "p", "P" -> currentPage = Previous.previous(products, currentPage, rowsPerPage);
                case "l", "L" -> currentPage = Last.last(products, currentPage, rowsPerPage);
                case "g", "G" -> currentPage = Goto.goTo(products, currentPage, rowsPerPage);
                case "s", "S" -> Search.search(products, currentPage, rowsPerPage);
                case "se", "Se" -> rowsPerPage = SetRow.setRow(products, rowsPerPage);
                case "h", "H" -> help();
                case "e", "E" -> {
                    System.out.print("Are you sure you want to Exit? [Y/y] or [N/n] : ");
                    String opt = scanner.nextLine();
                    if (opt.equals("Y") || opt.equals("y")) {
                        System.out.println("Good Bye");
                        System.exit(0);
                    }
                }
                default -> {
                    String[] shortCutOpt = options.split("[#-]");
                    switch (shortCutOpt[0]) {
                        case "W", "w" -> {
                            org.nocrala.tools.texttablefmt.Table table = new org.nocrala.tools.texttablefmt.Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            table.addCell(" ID" + " ".repeat(14) + " : " + (products.size() + 1));
                            table.addCell(" Name" + " ".repeat(12) + " : " + shortCutOpt[1]);
                            table.addCell(" Unit Price" + " ".repeat(6) + " : " + Double.parseDouble(shortCutOpt[2]));
                            table.addCell(" Qty" + " ".repeat(13) + " : " + shortCutOpt[3]);
                            table.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                            System.out.println();
                            System.out.println(table.render());
                            System.out.println();
                            while (true) {
                                System.out.print("Are you sure want to add this record? [Y/y] or [N/n]: ");
                                String choice = scanner.nextLine().toLowerCase();
                                if (choice.equals("n")) {
                                    break;
                                } else if (choice.equals("y")) {
                                    products.add(new Products(products.size() + 1, shortCutOpt[1], Double.parseDouble(shortCutOpt[2]), Integer.parseInt(shortCutOpt[3]), LocalDate.now()));
                                    System.out.println();
                                    org.nocrala.tools.texttablefmt.Table table2 = new org.nocrala.tools.texttablefmt.Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                    table2.addCell("  " + (products.size()) + " was added successfully  ");
                                    System.out.println(table2.render());
                                    System.out.println();
                                    break;
                                } else
                                    System.out.println("Invalid Input");
                            }
                        }
                        case "R", "r" -> {
                            for(Products p: products) {
                                if(shortCutOpt[1].equalsIgnoreCase(p.getId().toString())) {
                                    org.nocrala.tools.texttablefmt.Table table = getTable(p);
                                    System.out.println();
                                    System.out.println(table.render());
                                    System.out.println();
                                }
                            }
                        }
                        case "D", "d" -> {
                            for(Products p: products) {
                                if (shortCutOpt[1].equals(p.getId().toString())) {
                                    org.nocrala.tools.texttablefmt.Table table = getTable(p);
                                    table.addCell(" ID" + " ".repeat(14) + " : " + p.getId());
                                    table.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                                    table.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                                    table.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                                    table.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getImportedDate() + " ".repeat(6));
                                    System.out.println();
                                    System.out.println(table.render());
                                    System.out.println();

                                    System.out.print("Are you sure want to delete this record? [Y/y] or [N/n]: ");
                                    String choice = scanner.nextLine().toLowerCase();
                                    if (choice.equals("n")){
                                        break;
                                    }
                                    else if (choice.equals("y")) {
                                        products.remove(p);
                                        System.out.println();
                                        org.nocrala.tools.texttablefmt.Table table4 = getTable(p);
                                        table4.addCell(" ".repeat(5)+ "Product was removed" + " ".repeat(5));
                                        System.out.println(table4.render());
                                        System.out.println();
                                        break;
                                    }
                                }
                            }
                        }
                        default -> {
                            System.out.println("~".repeat(30));
                            System.out.println("\tInvalid Input");
                            System.out.println("~".repeat(30));
                        }
                    }
                }
            }
        } while (true) ;
    }

    private static org.nocrala.tools.texttablefmt.Table getTable(Products p) {
        org.nocrala.tools.texttablefmt.Table table = new org.nocrala.tools.texttablefmt.Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
        table.addCell(" ID" + " ".repeat(14) + " : " + p.getId());
        table.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
        table.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
        table.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
        table.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getImportedDate() + " ".repeat(6));
        return table;
    }
}



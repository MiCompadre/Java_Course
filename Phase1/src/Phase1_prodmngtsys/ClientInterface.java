package Phase1_prodmngtsys;

import Phase1_prodmngtsys.database.ProductDatabase;
import Phase1_prodmngtsys.entity.Product;
import Phase1_prodmngtsys.service.ProductService;

import java.util.Scanner;

public class ClientInterface {

    public static void displayMenu()
    {
        System.out.println("Welcome To Browns Store");
        System.out.println("Choose one of the options");
        System.out.println("1.Display All products");
        System.out.println("2.Edit a Product");
        System.out.println("3.Add a new product");
        System.out.println("4.Delete a product");
        System.out.println("5.Search for a product");
        System.out.println("6.Logout");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase database = new ProductDatabase(5);
        ProductService service = new ProductService(database);
        while (true)
        {
            displayMenu();
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    // iterate and display the list

                    if (database.getCount() == 0)
                        System.out.println("No products added!!\n");
                    else {
                        System.out.println("\n\nId\tName\tDescription\tPrice");
                        Product products[] = database.getAllProducts();
                        for (Product product : products) {
                            if (product != null)
                                System.out.println(product.getPid() + "\t" + product.getPname() + "\t" + product.getDesc() + "\t" + product.getPrice());
                        }
                    }
                    System.out.println();
                    break;
                case 2: // ask the admin to enter product id and
                    // ask to enter name, desc or price
                    // create the product object and pass to update method
                    Product product = new Product();
                    // setters
                    database.updateProduct(product);
                    break;
                case 3:

                    if (service.isStockFull())
                        System.out.println("Stock full");
                    else {
                        System.out.println("\nEnter product details to add");
                        System.out.println("Enter id");
                        int id = sc.nextInt();
                        System.out.println("Enter name");
                        String name = sc.next();
                        sc.nextLine();
                        System.out.println("Enter desc");
                        String desc = sc.nextLine();
                        System.out.println("Enter price");
                        double price = sc.nextDouble();
                        Product p = new Product(id, name, desc, price);
                        if (service.addProduct(p))
                            System.out.println("Product added");
                        else {
                            System.out.println("Product could not be added , stock full");
                        }
                    }
                    break;
                case 4:// // ask for product id to delete
                    int id1 = 0;
                    database.deleteProduct(id1);
                    break;
                case 5:
                    // ask for product id
                    //database.getProductById(id);
                    break;
                case 6:
                    System.out.println();
                    // System.exit(0);
                    break;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class BillsBurgerUserInterface {
    
    public record Order(ArrayList<Menu> menus) {

        private double getTotal() {
            double orderTotal = 0;
            for (var m : menus) orderTotal += m.calculateTotalPrice();
            return orderTotal;
        }

        @Override
        public String toString() {
            StringBuilder orderPrint = new StringBuilder("\n");
            for (var m : menus) orderPrint.append(m.toString());
            orderPrint.append("\nOrder Total: ").append(this.getTotal());
            return orderPrint.toString();
        }
    }

    private static ArrayList<Order> dailyOrders;
    private static Scanner scanner;
    private final String separator = "\n-----------------------------------\n";
    
    public BillsBurgerUserInterface() {
        System.out.println("");
        System.out.println("Starting Bill's Burger App...");
        System.out.println(separator);
        dailyOrders = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void quit() {
        System.out.println("");
        double dailyTotal = 0;
        for (var o : dailyOrders) dailyTotal += o.getTotal();
        if (dailyTotal > 0) System.out.println("Total revenue of the day: $" + dailyTotal);
        System.out.println("Thank you for using Bill's Burger App!");
        System.out.println(separator);
    }

    public void printBaseScreenOptions(boolean includeShowOrders) {
        System.out.println("< Base Screen >");
        System.out.println("");
        System.out.println(
            (includeShowOrders ? " 2 - Print processed orders\n" : "") +
            " 1 - Create new order\n" + 
            " 0 - Quit the application\n" +
            "Choose an option (0 - " + (includeShowOrders ? "2" : "1") + "):"
        );
    }

    public void baseScreen() {
        OUTER: 
        while (true) {
            printBaseScreenOptions(!dailyOrders.isEmpty());
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                switch (number) {
                    case 0 -> {
                        break OUTER;
                    }
                    case 1 -> testRun();
                    case 2 -> {
                        System.out.println("Processed Orders:");
                        for (int i=0; i<dailyOrders.size(); i++) {
                            var o = dailyOrders.get(i);
                            System.out.println("");
                            System.out.println("> Order #" + (i + 1));
                            System.out.println(o.toString());
                        }   System.out.println(separator);
                    }
                    default -> System.out.println("Invalid input.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please insert an Integer number.");
            }
        }
    }

    public void testRun() {
        System.out.println("");  
        System.out.println("Test Run...");
        System.out.println(separator);

        var orderList = new ArrayList<Menu>();

        // Example of creating a base Menu
        Menu baseMenu = new Menu();
        System.out.println("Base " + baseMenu);
        baseMenu.changeBurgerType("Cheeseburger");
        baseMenu.changeSideItemType("Salad");
        baseMenu.changeDrinkType("Pepsi");
        baseMenu.changeDrinkSize('M');
        System.out.println("Updated " + baseMenu);        
        System.out.println("Total Price: $" + baseMenu.calculateTotalPrice());
        orderList.add(baseMenu);
        System.out.println("Menu added to current order");
        System.out.println(separator);

        // Example of creating a Custom Menu
        Menu customMenu = new Menu(
                new Burger("Bacon Cheeseburger"),
                new SideItem("Onion Rings"),
                new Drink("Sprite", 'L')
        );
        customMenu.addBurgerTopping(new Topping("Lettuce"));
        customMenu.addBurgerTopping(new Topping("Tomato"));
        customMenu.addBurgerTopping(new Topping("Mustard"));
        System.out.println("Custom " + customMenu);
        System.out.println("Total Price: $" + customMenu.calculateTotalPrice());
        orderList.add(customMenu);
        System.out.println("Menu added to current order");
        System.out.println(separator);

        var order = new Order(orderList);
        System.out.println("Total order price: " + order.getTotal());
        dailyOrders.add(order);
        System.out.println(separator);

        orderList = new ArrayList<>();

        // Example of creating a Deluxe Menu
        DeluxeMenu deluxeMenu = new DeluxeMenu();
        System.out.println(deluxeMenu);
        System.out.println("Total Price: $" + deluxeMenu.calculateTotalPrice());
        deluxeMenu.changeDeluxeBurgerType("Chicken");
        deluxeMenu.changeSideItemType("Ice Cream");
        deluxeMenu.changeDrinkTypeAndSize("Pepsi", 'M');
        deluxeMenu.addBurgerTopping(new Topping("Pickles"));
        deluxeMenu.addBurgerTopping(new Topping("Ketchup"));
        deluxeMenu.addBurgerTopping(new Topping("Mayo"));
        deluxeMenu.addBurgerTopping(new Topping("Jalapenos"));
        deluxeMenu.addBurgerTopping(new Topping("Avocado"));
        System.out.println("\nUpdated " + deluxeMenu);
        System.out.println("Total Price after change: $" + deluxeMenu.calculateTotalPrice());
        orderList.add(deluxeMenu);
        System.out.println("Deluxe menu added to current order");
        System.out.println(separator);

        var order2 = new Order(orderList);
        System.out.println("Total order price: " + order2.getTotal());
        dailyOrders.add(order2);
        System.out.println(separator);

    }

}

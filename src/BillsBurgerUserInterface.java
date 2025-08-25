import java.util.ArrayList;
import java.util.Scanner;

public class BillsBurgerUserInterface {

    public enum ScreenType {
        BASE, ORDER, MENU, RMENU, EMENU, BURGER, DRINK, SDRINK, SIDE, TOPPING;

        private void printScreen() {
            switch (this) {
                case BASE    -> printBaseScreenOptions(!dailyOrders.isEmpty());
                case ORDER   -> printOrderScreenOptions();
                case MENU    -> printMenuSelectionOptions();
                case RMENU   -> printRemoveMenuOptions();
                case EMENU   -> printEditMenuOptions();
                case BURGER  -> printBurgerOptions();
                case DRINK   -> printDrinkOptions();
                case SDRINK  -> printDrinkSizeOptions();
                case SIDE    -> printSideItemOptions();
                case TOPPING -> printToppingOptions();
            }
        }

        private boolean screenSelection(int number) {
            return switch (this) {
                case BASE    -> baseScreenSelection(number);
                case ORDER   -> orderScreenSelection(number);
                case MENU    -> addMenuSelection(number);
                case RMENU   -> removeMenuSelection(number);
                case EMENU   -> editMenuSelection(number);
                case BURGER  -> burgerSelection(number);
                case DRINK   -> drinkSelection(number);
                case SDRINK  -> drinkSizeSelection(number);
                case SIDE    -> sideItemSelection(number);
                case TOPPING -> toppingSelection(number);
            };
        }
    }
    
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

    private static Menu currentMenu;
    private static ArrayList<Menu> currentOrder;
    private static ArrayList<Order> dailyOrders;
    private static Scanner scanner;
    private static final String SEPARATOR = "\n-----------------------------------\n";
    
    public BillsBurgerUserInterface() {
        System.out.println("");
        System.out.println("Starting Bill's Burger App...");
        dailyOrders = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void screen() { screen(ScreenType.BASE); }
    public static void screen(ScreenType page) {
        boolean stay = true;
        while (stay) {
            System.out.println(SEPARATOR);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // restore interrupt flag
            }
            page.printScreen();
            String input = scanner.nextLine();
            System.out.println("");
            try {
                int number = Integer.parseInt(input);
                stay = page.screenSelection(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please insert an Integer number.");
            }
        }
    }

    public void quit() {
        System.out.println(SEPARATOR);
        double dailyTotal = 0;
        for (var o : dailyOrders) dailyTotal += o.getTotal();
        if (dailyTotal > 0) System.out.println("Total revenue of the day: $" + dailyTotal);
        System.out.println("Thank you for using Bill's Burger App!");
        System.out.println(SEPARATOR);
    }

    public static void printBaseScreenOptions(boolean includeShowOrders) {
        System.out.println("< Base Screen >");
        System.out.println("");
        System.out.println(
            (includeShowOrders ? " 2 - Print processed orders\n" : "") +
            " 1 - Create new order\n" + 
            " 0 - Quit the application\n" +
            "Choose an option (0 - " + (includeShowOrders ? "2" : "1") + "):"
        );
    }

    public static boolean baseScreenSelection(int number) {
        boolean stay = true;
        switch (number) {
            case 0 -> stay = false; //Quit
            case 1 -> { //Create new order
                currentOrder = new ArrayList<>();
                screen(ScreenType.ORDER);
            }
            case 2 -> { //Print processed orders (if they exist)
                if (dailyOrders.isEmpty()) System.out.println("Invalid input.");
                else {
                    System.out.println("Processed Orders:");
                    for (int i=0; i<dailyOrders.size(); i++) {
                        var o = dailyOrders.get(i);
                        System.out.println("");
                        System.out.println("> Order #" + (i + 1));
                        System.out.println(o.toString());
                    }
                }
            }
            case 1234 -> testRun(); //Hidden test command
            default -> System.out.println("Invalid input.");
        }
        return stay;
    }

    public static void printOrderScreenOptions() {
        System.out.println("< Order Screen >");
        System.out.println("");
        if (!currentOrder.isEmpty()) {
            System.out.println("Current order:");
            for (int i=0; i<currentOrder.size(); i++) {
                var m = currentOrder.get(i);
                System.out.print("#" + (i + 1) + " - ");
                System.out.println(m.toString());
            }
            System.out.println("""
                            3 - Finalize current order
                            2 - Remove Menu
                            1 - Add Menu
                            0 - Cancel
                           Choose an option (0 - 3):""");
        } else {
            System.out.println("""
                            1 - Add Menu
                            0 - Cancel
                           Choose an option (0 - 1):""");
        }
    }

    public static boolean orderScreenSelection(int number) {
        boolean stay = true;
        switch (number) {
            case 0 -> stay = false; //Cancel
            case 1 -> screen(ScreenType.MENU); //Add menu
            case 2 -> { //Remove menu
                if (currentOrder.isEmpty()) System.out.println("Invalid input.");
                else screen(ScreenType.RMENU);
            }
            case 3 -> { //Finalize order
                if (currentOrder.isEmpty()) System.out.println("Invalid input.");
                else {
                    var order = new Order(currentOrder);
                    System.out.println("Total order price: " + order.getTotal());
                    dailyOrders.add(order);
                    stay = false;
                }
            }
            default -> System.out.println("Invalid input.");
        }
        return stay;
    }

    public static void printMenuSelectionOptions() {
        System.out.println("< Menu Screen >");
        System.out.println("");
        System.out.println("""
                            3 - Deluxe Menu
                            2 - Custom Menu
                            1 - Base Menu
                            0 - Cancel
                           Choose an option (0 - 3):""");
    }

    public static boolean addMenuSelection(int number) {
        boolean stay = true;
        switch (number) {
            case 0 -> stay = false; //Cancel
            case 1 -> { //Base menu
                currentMenu = new Menu();
                screen(ScreenType.EMENU);
                stay = false;
            }
            case 2 -> { //Custom menu
                currentMenu = new Menu();
                screen(ScreenType.BURGER);
                screen(ScreenType.SIDE);
                screen(ScreenType.DRINK);
                screen(ScreenType.SDRINK);
                screen(ScreenType.EMENU);
                stay = false;
            }
            case 3 -> { //Deluxe menu
                currentMenu = new DeluxeMenu();
                screen(ScreenType.BURGER);
                screen(ScreenType.SIDE);
                screen(ScreenType.DRINK);
                screen(ScreenType.SDRINK);
                screen(ScreenType.EMENU);
                stay = false;
            }
            default -> System.out.println("Invalid input.");
        }
        return stay;
    }

    public static void printRemoveMenuOptions() {
        System.out.println("Choose index of the menu to remove: (1 - " + currentOrder.size() + ", 0 to cancel)");
    }

    public static boolean removeMenuSelection(int number) {
        if (number > 0 && number <= currentOrder.size()) {
            currentOrder.remove(number - 1);
            System.out.println("Removed menu #" + number);
        } else if (number != 0) System.out.println("Invalid menu index.");
        return false;
    }

    public static void printEditMenuOptions(){
        System.out.println("< Edit Menu Screen >");
        System.out.println("");
        System.out.println("Current Menu:");
        System.out.println(currentMenu.toString());
        System.out.println("");
        System.out.println("""
                            5 - Use current menu
                            4 - Change burger
                            3 - Add burger toppings
                            2 - Change side item
                            1 - Change drink
                            0 - Cancel
                           Choose an option (0 - 4):""");
    }

    public static boolean editMenuSelection(int number) {
        boolean stay = true;
        switch (number) {
            case 0 -> stay = false;               //Cancel
            case 1 -> {                           //Change Drink
                screen(ScreenType.DRINK);   
                screen(ScreenType.SDRINK);
            }
            case 2 -> screen(ScreenType.SIDE);    //Change Side Item
            case 3 -> screen(ScreenType.TOPPING); //Add Topping
            case 4 -> screen(ScreenType.BURGER);  //Change Burger
            case 5 -> {                           //Use current menu
                currentOrder.add(currentMenu);
                stay = false;
            }
            default -> System.out.println("Invalid input.");
        }
        return stay;
    }

    public static void printBurgerOptions() {
        System.out.println("< Burger Screen >");
        System.out.println("");
        System.out.println("Current Burger: " + currentMenu.getBurger().toString());
        System.out.println("");
        System.out.println("Possible options:");
        int len = Burger.VALID_TYPES.length;
        for (int i=len; i > 0; i--) System.out.println(" " + i + " - " + Burger.VALID_TYPES[len - i]);
        System.out.println(" 0 - Cancel");
        System.out.println("Choose an option (0 - " + len + "):");
    }

    public static boolean burgerSelection(int number) {
        int len = Burger.VALID_TYPES.length;
        if (number > 0 && number <= len) {
            String burgerType = Burger.VALID_TYPES[len - number];
            currentMenu.changeBurgerType(burgerType);
            System.out.println("Changed Burger to " + burgerType);
        } else if (number != 0) {
            System.out.println("Invalid input.");
            return true;
        }
        return false;
    }

    public static void printToppingOptions() {
        System.out.println("< Topping Screen >");
        System.out.println("");
        System.out.println("Possible options:");
        int len = Topping.VALID_TYPES.length;
        for (int i=len; i > 0; i--) System.out.println(" " + i + " - " + Topping.VALID_TYPES[len - i]);
        System.out.println(" 0 - Cancel");
        System.out.println("Choose an option (0 - " + len + "):");
    }

    public static boolean toppingSelection(int number) {
        int len = Topping.VALID_TYPES.length;
        if (number > 0 && number <= len) {
            String toppingType = Topping.VALID_TYPES[len - number];
            currentMenu.addBurgerTopping(new Topping(toppingType));
            System.out.println("Added topping " + toppingType);
        } else if (number != 0) {
            System.out.println("Invalid input.");
            return true;
        }
        return false;
    }

    public static void printSideItemOptions() {
        System.out.println("< Side Item Screen >");
        System.out.println("");
        System.out.println("Current Side Item: " + currentMenu.getSideItem().toString());
        System.out.println("");
        System.out.println("Possible options:");
        int len = SideItem.VALID_TYPES.length;
        for (int i=len; i > 0; i--) System.out.println(" " + i + " - " + SideItem.VALID_TYPES[len - i]);
        System.out.println(" 0 - Cancel");
        System.out.println("Choose an option (0 - " + len + "):");
    }

    public static boolean sideItemSelection(int number) {
        int len = SideItem.VALID_TYPES.length;
        if (number > 0 && number <= len) {
            String sideItemType = SideItem.VALID_TYPES[len - number];
            currentMenu.changeSideItemType(sideItemType);
            System.out.println("Changed Side Item to " + sideItemType);
        } else if (number != 0) {
            System.out.println("Invalid input.");
            return true;
        }
        return false;
    }

    public static void printDrinkOptions() {
        System.out.println("< Drink Screen >");
        System.out.println("");
        System.out.println("Current Drink: " + currentMenu.getDrink().toString());
        System.out.println("");
        System.out.println("Possible options:");
        int len = Drink.VALID_TYPES.length;
        for (int i=len; i > 0; i--) System.out.println(" " + i + " - " + Drink.VALID_TYPES[len - i]);
        System.out.println(" 0 - Cancel");
        System.out.println("Choose an option (0 - " + len + "):");
    }

    public static boolean drinkSelection(int number) {
        int len = Drink.VALID_TYPES.length;
        if (number > 0 && number <= len) {
            String drinkType = Drink.VALID_TYPES[len - number];
            currentMenu.changeDrinkType(drinkType);
            System.out.println("Changed Drink to " + drinkType);
        } else if (number != 0) {
            System.out.println("Invalid input.");
            return true;
        }
        return false;
    }

    public static void printDrinkSizeOptions() {
        System.out.println("Drink Size:");
        System.out.println(" 3 - Large");
        System.out.println(" 2 - Medium");
        System.out.println(" 1 - Small");
        System.out.println(" 0 - Cancel");
        System.out.println("Choose an option (0 - 3):");
    }

    public static boolean drinkSizeSelection(int number) {
        if (number > 0 && number <= 3) {
            char drinkSize = switch (number) {
                case 3  -> 'L';
                case 2  -> 'M';
                default -> 'S';
            };
            currentMenu.changeDrinkSize(drinkSize);
            System.out.println("Changed Drink Size to " + drinkSize);
        } else if (number != 0) {
            System.out.println("Invalid input.");
            return true;
        }
        return false;
    }

    public static void testRun() {
        System.out.println(SEPARATOR);
        System.out.println("Test Run...");
        System.out.println(SEPARATOR);

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
        System.out.println(SEPARATOR);

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
        System.out.println(SEPARATOR);

        var order = new Order(orderList);
        System.out.println("Total order price: " + order.getTotal());
        dailyOrders.add(order);
        System.out.println(SEPARATOR);

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
        System.out.println(SEPARATOR);

        var order2 = new Order(orderList);
        System.out.println("Total order price: " + order2.getTotal());
        dailyOrders.add(order2);

    }

}


public class BillsBurgerApp {

    private static BillsBurgerUserInterface ui;

    public static void main(String[] args) throws Exception {
        
        ui = new BillsBurgerUserInterface();

        // Ctrl+C handler
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nQuitting the application...");
            ui.quit();
        }));

        //ui.testRun();
        ui.screen();
        
    }
    
}

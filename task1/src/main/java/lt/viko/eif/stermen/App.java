package lt.viko.eif.stermen;

import lt.viko.eif.stermen.project1.ui.Ui;

/**
 * The main initialization point for Task 1 startup.
 */
public class App {

    /**
     * Creates a new Ui object and calls begin() method to utilize the user interface.
     * @param args not needed.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.begin();
    }
}

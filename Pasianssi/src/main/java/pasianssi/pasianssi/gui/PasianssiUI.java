package pasianssi.pasianssi.gui;


import java.io.IOException;

/**
 *
 * @author mikko
 */
public class PasianssiUI implements Runnable {

    private MainFrame main;

    public PasianssiUI() throws IOException {
        main = new MainFrame();
    }

    @Override
    public void run() {
        main.setVisible(true);
    }

}

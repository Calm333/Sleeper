import javax.swing.*;

public class Sleeper {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ui();
            }
        });
    }
}

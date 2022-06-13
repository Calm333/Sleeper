import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Shutdown {
    private int time;
    private Timer timer;

    public void shutdownOff(int time) {
        this.time = time;

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("shutdown /s /t " + 0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        timer = new Timer(time * 1000 + 100, listener);
        timer.start();

        if (time == 0) {
            timer.stop();
        }

    }

    public void shutdownReset(int time) {
        this.time = time;

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("shutdown /r /t " + 0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        timer = new Timer(time * 1000 + 100, listener);
        timer.start();

        if (time == 0) {
            timer.stop();
        }
    }

    public static void shutdownNow() {
        try {
            Runtime.getRuntime().exec("shutdown /s /t " + 0).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void shutdownReload() {
        try {
            Runtime.getRuntime().exec("shutdown /r /t " + 0).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdownCancel() {
        timer.stop();
    }

    public int getTime() {
        return time;
    }
}

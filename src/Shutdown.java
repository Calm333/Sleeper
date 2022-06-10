import java.io.IOException;

public class Shutdown {
    private int time;

    public void shutdownOff(int time) {
        this.time = time;
        String shutdownCommand, t = time == 0 ? "now" : String.valueOf(time);

        shutdownCommand = "shutdown.exe /s /t " + t;

        try {
            Runtime.getRuntime().exec(shutdownCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdownReset(int time) {
        this.time = time;
        String shutdownCommand, t = time == 0 ? "now" : String.valueOf(time);

        shutdownCommand = "shutdown.exe /r /t " + t;

        try {
            Runtime.getRuntime().exec(shutdownCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdownCancel() {

        try {
            Runtime.getRuntime().exec("shutdown -a").waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTime() {
        return time;
    }
}

import java.awt.*;

public class Tray {
    private SystemTray tray;
    private Image image;
    private PopupMenu popupMenu;
    protected MenuItem show;
    protected MenuItem exit;
    protected TrayIcon trayIcon;

    public Tray() {

        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported! ");
        }
        tray = SystemTray.getSystemTray();
        image = Toolkit.getDefaultToolkit().getImage("assets//tray.png");
        popupMenu = new PopupMenu();
        show = new MenuItem("Show Program");
        exit = new MenuItem("Exit");

        popupMenu.add(show);
        popupMenu.add(exit);

        trayIcon = new TrayIcon(image,"Sleeper",popupMenu);

        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

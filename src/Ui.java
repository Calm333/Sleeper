import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ui extends JFrame {
    private JButton exit;
    private JButton iconFIed;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JSlider slider1;
    private JSlider slider2;
    private JButton startButton;
    private JPanel generalPanel;
    private JPanel top;

    private int px;
    private int py;

    public Ui() {
        setUndecorated(true);

        setVisible(true);
        setSize(400, 350);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(generalPanel);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(new Color(139, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(new Color(38, 40, 55));
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        iconFIed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                iconFIed.setBackground(new Color(92, 100, 120));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                iconFIed.setBackground(new Color(38, 40, 55));
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setBackground(new Color(250, 128, 114));
                startButton.setText("Stop");
            }
        });

        top.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });

        top.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - px, y - py);
            }
        });
    }
}

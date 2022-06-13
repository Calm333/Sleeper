import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogWindow extends JDialog {
    private JPanel contentPane;
    private JButton OK;
    private JButton Cancel;
    private JLabel complete;
    private JButton exit;

    private int px;
    private int py;

    public DialogWindow() {
        setContentPane(contentPane);
        setUndecorated(true);
        setVisible(true);
        setSize(300, 150);
        setLocationRelativeTo(null);

        setModal(true);
        getRootPane().setDefaultButton(OK);

        OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Shutdown.shutdownNow();
            }
        });

        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(new Color(175, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(new Color(139, 0, 0));
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();

                setLocation(x - px, y - py);
            }
        });
    }

}

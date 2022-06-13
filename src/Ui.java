import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Ui extends JFrame {
    private JButton exit;
    private JButton iconFIed;
    private JComboBox resOff;
    private JComboBox selected;
    private JSlider sliderH;
    private JSlider sliderM;
    private JButton startButton;
    private JPanel generalPanel;
    private JPanel top;
    private JLabel timeS;
    private JLabel timeM;
    private JLabel timeH;

    private final Shutdown shutdown;
    private Tray tray;

    private int px;
    private int py;

    private Timer timer;

    private int s;
    private int m;
    private int h;

    private int trayInfo;

    public Ui() {
        shutdown = new Shutdown();
        tray = new Tray();
        setUndecorated(true);
        setVisible(true);
        setSize(300, 290);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(generalPanel);

        s = 59;

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
                System.exit(0);
                shutdown.shutdownCancel();
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

        iconFIed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });

        sliderH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!sliderH.isEnabled()) {
                    timeH.setText(String.valueOf(h));
                } else
                    timeH.setText(String.valueOf(sliderH.getValue()));
//                selected.setEnabled(false);
            }
        });

        sliderH.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!sliderH.isEnabled()) {
                    timeH.setText(String.valueOf(h));
                } else
                    timeH.setText(String.valueOf(sliderH.getValue()));
//                selected.setEnabled(false);
            }
        });

        sliderM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!sliderM.isEnabled()) {
                    timeM.setText(String.valueOf(m));
                } else
                    timeM.setText(String.valueOf(sliderM.getValue()));
            }
        });

        sliderM.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!sliderM.isEnabled()) {
                    timeM.setText(String.valueOf(m));
                } else
                    timeM.setText(String.valueOf(sliderM.getValue()));
            }
        });
//        selected.addActionListener(e -> {
//            if (selected.getSelectedIndex() != 0) {
//                sliderM.setEnabled(false);
//                sliderH.setEnabled(false);
//            } else {
//                sliderM.setEnabled(true);
//                sliderH.setEnabled(true);
//            }
//        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.isDefaultCapable()) {
                    startButton.setDefaultCapable(false);
                    startButton.setBackground(new Color(250, 128, 114));
                    startButton.setText("Stop");

                    if (resOff.getSelectedIndex() == 0) {
                        switch (selected.getSelectedIndex()) {
                            case 1:
                                shutdown.shutdownOff(900);
                                timeM.setText(String.valueOf(900 / 60));
                                break;
                            case 2:
                                shutdown.shutdownOff(1800);
                                timeM.setText(String.valueOf(1800 / 60));
                                break;
                            case 3:
                                shutdown.shutdownOff(2700);
                                timeM.setText(String.valueOf(2700 / 60));
                                break;
                            case 4:
                                shutdown.shutdownOff(3600);
                                timeM.setText(String.valueOf(3600 / 60));
                                timeH.setText(String.valueOf(1));
                                break;
                            case 5:
                                shutdown.shutdownOff(5400);
                                timeM.setText(String.valueOf(1800 / 60));
                                timeH.setText(String.valueOf(1));
                                break;
                            case 6:
                                shutdown.shutdownOff(7200);
                                timeM.setText(String.valueOf(3600 / 60));
                                timeH.setText(String.valueOf(2));
                                break;
                            default:
                                shutdown.shutdownOff(Integer.parseInt(timeH.getText()) * 60 * 60
                                        + Integer.parseInt(timeM.getText()) * 60);
                        }

                    } else if (resOff.getSelectedIndex() == 1) {
                        switch (selected.getSelectedIndex()) {
                            case 1:
                                shutdown.shutdownReset(900);
                                timeM.setText(String.valueOf(900 / 60));
                                break;
                            case 2:
                                shutdown.shutdownReset(1800);
                                timeM.setText(String.valueOf(1800 / 60));
                                break;
                            case 3:
                                shutdown.shutdownReset(2700);
                                timeM.setText(String.valueOf(2700 / 60));
                                break;
                            case 4:
                                shutdown.shutdownReset(3600);
                                timeM.setText(String.valueOf(3600 / 60));
                                break;
                            case 5:
                                shutdown.shutdownReset(5400);
                                timeM.setText(String.valueOf(5400 / 60));
                                break;
                            case 6:
                                shutdown.shutdownReset(7200);
                                timeM.setText(String.valueOf(7200 / 60));
                                break;
                            default:
                                shutdown.shutdownReset(Integer.parseInt(timeH.getText()) * 60 * 60
                                        + Integer.parseInt(timeM.getText()) * 60);


                        }

                    }
                    if (selected.getSelectedIndex() == 1 || selected.getSelectedIndex() == 2 || selected.getSelectedIndex() == 3) {
                        timeH.setText(String.valueOf(0));

                    }
                    if (selected.getSelectedIndex() != 0) {
                        sliderH.setValue(0);
                        sliderM.setValue(0);
                    }
                    h = Integer.parseInt(timeH.getText());
                    m = Integer.parseInt(timeM.getText());
                    trayInfo = shutdown.getTime() / 60;

                    if (m > 0) {
                        m -= 1;
                    } else if (m == 0) {
                        m = 59;
                    }
                    if (m == 59) {
                        h -= 1;
                    }

                    ActionListener taskPerformer = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            timeS.setText(String.valueOf(s));
                            timeM.setText(String.valueOf(m));
                            timeH.setText(String.valueOf(h));
                            s--;
                            if (s < 0) {
                                s = 59;
                                m--;
                            }
                            if (m < 0) {
                                m = 59;
                                h--;
                            }
                            if (h < 0) {
                                h = 0;
                            }
                            if (h == 0 && m == 4 && s == 58) {
                                tray.trayIcon.displayMessage("The system will shut down after 5 minutes",
                                        null, TrayIcon.MessageType.WARNING);
                            }

                            if (h == 0 && m == 0 && s == 58 && resOff.getSelectedIndex()==0) {
                                new DialogWindow();

                            }else if (h == 0 && m == 0 && s == 58 && resOff.getSelectedIndex()==1){
                                new DialogWindowReload();
                            }
                        }
                    };

                    timer = new Timer(1000, taskPerformer);
                    timer.start();


                    if (selected.getSelectedIndex() == 0 && sliderH.getValue() == 0 && sliderM.getValue() == 0) {
                        timer.stop();
                    }
                    sliderH.setEnabled(false);
                    sliderM.setEnabled(false);

                    if (selected.getSelectedIndex() != 0 || sliderH.getValue() != 0 || sliderM.getValue() != 0) {
                        tray.trayIcon.displayMessage("Sign Out", "work will be completed in " + trayInfo + " minute",
                                TrayIcon.MessageType.WARNING);
                    }

                    sliderH.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (selected.getSelectedIndex() == 0 && sliderH.getValue() == 0 && sliderM.getValue() == 0)
                                timeH.setText(String.valueOf(0));
                        }
                    });

                    sliderH.addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            if (selected.getSelectedIndex() == 0 && sliderH.getValue() == 0 && sliderM.getValue() == 0)
                                timeH.setText(String.valueOf(0));
                        }
                    });

                    sliderM.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (selected.getSelectedIndex() == 0 && sliderH.getValue() == 0 && sliderM.getValue() == 0)
                                timeM.setText(String.valueOf(0) + String.valueOf(0));
                        }
                    });

                    sliderM.addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            if (selected.getSelectedIndex() == 0 && sliderH.getValue() == 0 && sliderM.getValue() == 0)
                                timeM.setText(String.valueOf(0) + String.valueOf(0));
                        }
                    });

                } else {
                    if (selected.getSelectedIndex() != 0 || sliderH.getValue() != 0 || sliderM.getValue() != 0) {
                        tray.trayIcon.displayMessage("Logout canceled", null, TrayIcon.MessageType.INFO);
                    }
                    startButton.setDefaultCapable(true);
                    startButton.setBackground(new Color(94, 237, 181));
                    startButton.setText("Start");
                    shutdown.shutdownCancel();
                    timer.stop();
                    timeS.setText(String.valueOf(0) + String.valueOf(0));
                    timeM.setText(String.valueOf(0) + String.valueOf(0));
                    timeH.setText(String.valueOf(0));
                    s = 59;
                    selected.setSelectedIndex(0);
                    sliderH.setEnabled(true);
                    sliderM.setEnabled(true);
                    sliderH.setValue(0);
                    sliderM.setValue(0);
                    shutdown.shutdownCancel();
                }
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
        tray.show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });
        tray.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shutdown.shutdownCancel();
                System.exit(0);
            }
        });
        tray.trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(true);
            }
        });

    }
}


package trab1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by João on 25/09/2014.
 */
public class Test_GUI {
    private JButton upButton;
    private JButton downButton;
    private JButton rightButton;
    private JButton leftButton;
    private JButton inButton;
    private JButton outButton;
    private JButton stopXButton;
    private JButton emergencyButton;
    private JButton stopYButton;
    private JButton stopZButton;
    private JPanel gui_frame;
    private JButton resetCOMButton;

    public Test_GUI() {
        final Hardware h = new Hardware();
        final BufferData bufferData = new BufferData(h);/*


        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_z_up();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_x_left();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_x_right();
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_z_down();
            }
        });
        inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_y_in();
            }
        });
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.move_y_out();
            }
        });
        stopXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.stop_x();
            }
        });
        stopYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.stop_y();
            }
        });
        stopZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.stop_z();
            }
        });
        emergencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                h.stop_emergency();
            }
        });
        resetCOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*try {
                    int x = 6;
                    int z = 4;
                    Class c = Class.forName("trab1.GotoPosition");
                    Action a = (Action) c.getConstructor(Integer.TYPE, Integer.TYPE, BufferData.class)
                            .newInstance(x, z, bufferData);
                    new Thread(a).start();


                } catch (ClassNotFoundException e) {
                   e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }*//*
            }
        });*/
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test_GUI");
        frame.setContentPane(new Test_GUI().gui_frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

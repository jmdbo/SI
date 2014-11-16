package si.GUI;

import javax.swing.*;

/**
 * Created by João Barata on 16-11-2014.
 */
public class Teste {
    private JPanel panel1;
    private JTextField textField1;
    private JRadioButton leftRadioButton;
    private JRadioButton rightRadioButton;
    private JRadioButton takeFromRadioButton;
    private JRadioButton putInRadioButton;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton goButton;
    private JButton stopButton;
    private JLabel to_hidepx;
    private JLabel to_hidepz;
    private JLabel cell2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teste");
        frame.setContentPane(new Teste().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


/*
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt){
        to_hidepz.setVisible(true);
        to_hidepx.setVisible(true);
        textField3.setVisible(true);
        textField4.setVisible(true);
        cell2.setVisible(true);

    }
    */




}



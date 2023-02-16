package datastructureproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrontEnd extends JFrame {

    public JButton Conversion;
    public JLabel Important_text;
    public JLabel Last_Infix;
    public JLabel Last_Prefix;
    public JLabel Last_Postfix;
    public JTextField Input_Field;
    public String Wrong_Charecter = "! @ $ & _ = | ' { }  [ ] % ; . , ` ~  \" : ? < > # \\  ریال‌‌ ٪ ، ؤ»«؟ ْ ٌ ٍ ً ُ ِ َ ّة ك ئ ي ";
    public String Main_Phrase = "";

///////////////////////////////////////////////// Simple Frame ////////////////////////////////////
    public FrontEnd(JPanel panel, String Infix_to_Show, String Prefix_to_Show, String Postfix_to_Show) {

        super("Data Structure Project");

        setSize(700, 800);

        setLocationRelativeTo(null);

        Image icon = null;

        try {
            icon = ImageIO.read(getClass().getResource("Icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIconImage(icon);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FrontEnd_Components(Infix_to_Show, Prefix_to_Show, Postfix_to_Show);
        add(panel);

        getContentPane().setBackground(new Color(208, 171, 23));

        setLayout(null);

        setVisible(true);

    }

    ////////////////////////////////////////   FrontEnd Components    ///////////////////////////////
    public void FrontEnd_Components(String Infix, String Prefix, String Postfix) {

        Conversion = new JButton("تبدیل");
        Conversion.setFont(new Font("homa ", Font.BOLD, 25));

        Important_text = new JLabel("معادله را دراین قسمت وارد کنید");
        Important_text.setFont(new Font("homa ", Font.BOLD, 25));

        Last_Infix = new JLabel(Infix);
        Last_Infix.setBackground(Color.red);
        Last_Infix.setForeground(Color.red);
        Last_Infix.setFont(new Font("homa ", Font.BOLD, 28));

        Last_Prefix = new JLabel(Prefix);
        Last_Prefix.setBackground(Color.GREEN);
        Last_Prefix.setForeground(Color.GREEN);
        Last_Prefix.setFont(new Font("homa ", Font.BOLD, 28));

        Last_Postfix = new JLabel(Postfix);
        Last_Postfix.setBackground(Color.MAGENTA);
        Last_Postfix.setForeground(Color.MAGENTA);
        Last_Postfix.setFont(new Font("homa ", Font.BOLD, 28));

        if (!"".equals(Infix)) {
            Input_Field = new JTextField("");
        } else {
            Input_Field = new JTextField("hello");
        }
        Input_Field.setFont(new Font("homa ", Font.BOLD, 25));

        Conversion.setBounds(300, 140, 100, 50);
        Important_text.setBounds(370, 55, 300, 50);
        Last_Postfix.setBounds(60, 315, 250, 50);
        Last_Prefix.setBounds(460, 315, 250, 50);
        Last_Infix.setBounds(290, 255, 250, 50);
        Input_Field.setBounds(30, 55, 300, 60);

        Conversion.setFocusable(false);

        add(Conversion);
        add(Important_text);
        add(Last_Infix);
        add(Last_Prefix);
        add(Last_Postfix);
        add(Input_Field);

        Conversion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean For_More_Sure = true;

                for (int p = 0; p < Wrong_Charecter.length(); p++) {
                    if (Input_Field.getText().contains(Character.toString(Wrong_Charecter.charAt(p)))) {
                        For_More_Sure = false;
                        break;
                    }
                }

                if (Input_Field.getText().isEmpty()) {
                    For_More_Sure = false;
                }

                if (For_More_Sure) {

                    try {
                        Main_Phrase = Input_Field.getText().replace(" ", "");
                        new BackEnd(Main_Phrase);
                        setVisible(false);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });

    }
}

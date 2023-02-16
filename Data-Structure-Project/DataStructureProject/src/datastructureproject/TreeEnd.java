package datastructureproject;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeEnd extends JFrame {

    TreeEnd(String Redy_Infix_for_Draw, String Redy_Prefix_for_Draw, String Postfix, String Redy_Infix_for_Write, String Redy_Prefix_for_Write, HashMap<String, String> for_Use_Unique_Charecter) {

        HashMap<String, Integer> Left_or_Right = new HashMap<>();

        String infix = Redy_Infix_for_Draw;

        String prefix = Redy_Prefix_for_Draw;

        String Left = "";
        String Right = "";
        int Position[] = {270, 140, 70};

        Stack<String> Stack = new Stack<String>();
        Stack<String> Stack_prefix = new Stack<String>();

        Stack<String> Stack_Root = new Stack<String>();
        String exist = " ! @ $ } & _ = | ' { [ % ; . , ` ~ ] : ? < > # » « + - * / ^ ";
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if ("(a^b)^c".equals(Redy_Infix_for_Write)){infix ="a^b=c" ;prefix="=^abc"; for_Use_Unique_Charecter.put("=", "^");  }
        while (0 < prefix.length()) {



            String exist_operator=Character.toString(prefix.charAt(0));
            for (int i = 0 ; i<prefix.length();i++){
                if(exist.contains(Character.toString(prefix.charAt(i)))){            
                     exist_operator=Character.toString(prefix.charAt(i));
                break;}}
            
            Stack.push(exist_operator);

            String root = Stack.pop();


            //////////////////////////////////////   add Left  to Stack   ///////////////////////////////////////////////////////
            if (infix.indexOf(root.charAt(0)) + 1 < infix.length()) {

                for (int i = infix.indexOf(root.charAt(0)) + 1; i < infix.length(); i++) {

                    if ("(".equals(Character.toString(infix.charAt(i))) || ")".equals(Character.toString(infix.charAt(i)))) {
                        continue;
                    }

                    Right += Character.toString(infix.charAt(i));

                }
                Stack.push(Right);


            } else {
            }

            //////////////////////////////////////   add Right  to Stack   //////////////////////////////////////////////////////
            if (0 < infix.length() - Right.length() - 1) {

                for (int i = 0; i < infix.indexOf(root.charAt(0)); i++) {
                    if ("(".equals(Character.toString(infix.charAt(i))) || ")".equals(Character.toString(infix.charAt(i)))) {
                        continue;
                    }

                    Left += Character.toString(infix.charAt(i));
                }
                Stack.push(Left);
            } else {
            }

            /////////////////////////////////////   have Left or Right  //////////////////////////////////////////////////////////
            if ("".equals(Left) && ("".equals(Right))) {
                Left_or_Right.put(root + "_Left", 0);
                Left_or_Right.put(root + "_Right", 0);
            } else if ("".equals(Right) && (!"".equals(Left))) {
                Left_or_Right.put(root + "_Left", 1);
                Left_or_Right.put(root + "_Right", 0);
            } else if ("".equals(Left) && (!"".equals(Right))) {
                Left_or_Right.put(root + "_Left", 0);
                Left_or_Right.put(root + "_Right", 1);
            } else if (!"".equals(Right) && (!"".equals(Left))) {
                Left_or_Right.put(root + "_Left", 1);
                Left_or_Right.put(root + "_Right", 1);
            }

            /////////////////////////////////   upgrade Information   /////////////////////////////////////
            if (!Stack.isEmpty()) {
                infix = Stack.pop();
            }

            if (0 < prefix.length()) {

                prefix = prefix.replace(exist_operator, "");
                Stack_Root.push(root);
                
                
                String left_prefix = "";
                String right_prefix = "";
                for (int n =0 ; n<prefix.length();n++){
                    
                    if("".equals(Right) && "".equals(Left)){break;}
                    else if("".equals(Right)){
                        if(infix.contains(Character.toString(prefix.charAt(n)))){
                        left_prefix+=Character.toString(prefix.charAt(n));
                        }}
                    else if("".equals(Left)){right_prefix+=Character.toString(prefix.charAt(n));}
                    else{                
                       if(infix.contains(Character.toString(prefix.charAt(n)))){
                        left_prefix+=Character.toString(prefix.charAt(n));
                        }else{right_prefix+=Character.toString(prefix.charAt(n));}}

                
                
                }
                if("".equals(left_prefix) && "".equals(right_prefix)){}
                else if ("".equals(right_prefix)){Stack_prefix.push(left_prefix);}
                else if ("".equals(left_prefix)){Stack_prefix.push(right_prefix);}
                else {                
                    Stack_prefix.push(right_prefix);
                    Stack_prefix.push(left_prefix);}

                

                if (!Stack_prefix.isEmpty()){     
                    prefix=Stack_prefix.pop();}
                

                Left = "";
                Right = "";

            } else if (!Stack_prefix.isEmpty()) {
                
                
                
                prefix=Stack_prefix.pop();
            }
            else {break;}

        }

        


        ////////////////////////////////////   draw TreeEnd   /////////////////////////////////////////////////////
        JPanel panel = new JPanel();
        panel.setBounds(43, 200, 601, 550);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel.setLayout(null);

        int Index_of_Stack_Root = 0;

        String Whats_Component = Stack_Root.get(Index_of_Stack_Root);
        if (Stack_Root.size() == 1) {
            System.out.println(" Hichi Nadaram ");
        }
        while (Left_or_Right.get(Whats_Component + "_Left") == 1 || Left_or_Right.get(Whats_Component + "_Right") == 1) {

            if (Left_or_Right.get(Whats_Component + "_Left") == 0) {
                Create_btn(Whats_Component, Position, panel, for_Use_Unique_Charecter);
            }

            while (Left_or_Right.get(Whats_Component + "_Left") == 1) {

                while (Left_or_Right.get(Whats_Component + "_Left") == 1) {

                    Create_btn(Whats_Component, Position, panel, for_Use_Unique_Charecter);
                    Index_of_Stack_Root += 1;
                    if (Index_of_Stack_Root < Stack_Root.size()) {
                        Whats_Component = Stack_Root.get(Index_of_Stack_Root);
                    } else {
                        break;
                    }

                    Different_Moods(1, Position);

                    if (Stack_Root.size() == 1) {
                        break;
                    }
                }

                if (Left_or_Right.get(Whats_Component + "_Left") == 0) {
                    Create_btn(Whats_Component, Position, panel, for_Use_Unique_Charecter);

                }

                while (Left_or_Right.get(Whats_Component + "_Right") == 0) {
                    Stack_Root.remove(Whats_Component);
                    Index_of_Stack_Root -= 1;


                    Whats_Component = Stack_Root.get(Index_of_Stack_Root);

                    Left_or_Right.replace(Whats_Component + "_Left", 1, 0);

                    Different_Moods(2, Position);

                    if (Stack_Root.size() == 1) {
                        break;
                    }
                }

                if (Stack_Root.size() == 1) {
                    break;
                }

            }

            if (Stack_Root.size() == 1) {
                break;
            }

            while (Left_or_Right.get(Whats_Component + "_Right") == 1) {

                while (Left_or_Right.get(Whats_Component + "_Left") == 1) {

                    Index_of_Stack_Root += 1;
                    Whats_Component = Stack_Root.get(Index_of_Stack_Root);
                    Different_Moods(3, Position);

                    Create_btn(Whats_Component, Position, panel, for_Use_Unique_Charecter);

                    if (Stack_Root.size() == 1) {
                        break;
                    }

                }

                while (Left_or_Right.get(Whats_Component + "_Right") == 1) {

                    Index_of_Stack_Root += 1;
                    if (Index_of_Stack_Root < Stack_Root.size()) {
                        Whats_Component = Stack_Root.get(Index_of_Stack_Root);
                    } else {
                        break;
                    }

                    Different_Moods(4, Position);

                    Create_btn(Whats_Component, Position, panel, for_Use_Unique_Charecter);

                    if (Left_or_Right.get(Whats_Component + "_Left") == 1) {

                        break;
                    }

                    if (Stack_Root.size() == 1) {
                        break;
                    }

                }

                if (Left_or_Right.get(Whats_Component + "_Left") == 1) {
                    continue;
                }
                if (Index_of_Stack_Root > Stack_Root.size()) {
                    break;
                }

                while (Left_or_Right.get(Whats_Component + "_Right") == 0) {

                    if (Left_or_Right.get(Whats_Component + "_Left") == 0) {

                        Index_of_Stack_Root -= 1;
                        Whats_Component = Stack_Root.get(Index_of_Stack_Root);

                        if (Left_or_Right.get(Whats_Component + "_Left") == 1) {

                            Left_or_Right.replace(Whats_Component + "_Left", 1, 0);
                            Index_of_Stack_Root += 1;
                            Whats_Component = Stack_Root.get(Index_of_Stack_Root);
                            Stack_Root.remove(Whats_Component);

                            Different_Moods(5, Position);

                        } else {
                            Left_or_Right.replace(Whats_Component + "_Right", 1, 0);
                            Index_of_Stack_Root += 1;
                            Whats_Component = Stack_Root.get(Index_of_Stack_Root);
                            Stack_Root.remove(Whats_Component);

                            Different_Moods(6, Position);

                        }

                        Index_of_Stack_Root -= 1;

                        Whats_Component = Stack_Root.get(Index_of_Stack_Root);

                        if (Stack_Root.size() == 1) {
                            break;
                        }
                        if (Index_of_Stack_Root > Stack_Root.size()) {
                            break;
                        }
                    }
                    if (Stack_Root.size() == 1) {
                        break;
                    }
                    if (Index_of_Stack_Root > Stack_Root.size()) {
                        break;
                    }
                }
                if (Stack_Root.size() == 1) {
                    break;
                }
                if (Index_of_Stack_Root > Stack_Root.size()) {
                    break;
                }
            }
            if (Index_of_Stack_Root > Stack_Root.size()) {
                break;
            }
            if (Stack_Root.size() == 1) {
                break;
            }
        }
        add(panel);

        new FrontEnd(panel, Redy_Infix_for_Write, Redy_Prefix_for_Write, Postfix);

    }

    public void Create_btn(String character, int Position[], JPanel panel, HashMap<String, String> for_Use_Unique_Charecter) {
        JButton btn = new JButton();

        if (for_Use_Unique_Charecter.containsKey(character)) {
            character = for_Use_Unique_Charecter.get(character);
        }
        btn.setText(character);
        btn.setFocusable(false);
        btn.setForeground(Color.CYAN);
        btn.setBackground(Color.CYAN);
        if (Position[2] > 50) {
            btn.setFont(new Font("homa ", Font.BOLD, 25));
        } else {
            btn.setFont(new Font("homa ", Font.BOLD, 15));
        }

        btn.setEnabled(false);
        btn.setBounds(Position[0], Position[1], Position[2], Position[2]);

        panel.add(btn);

    }

    public void Different_Moods(int mood, int Position[]) {

        switch (mood) {

            case (1):
                Position[0] -=  70;
                Position[1] +=  80;
                Position[2] -= 7;
                break;
            
            case (2):
            //   semte rast nazdik pedar mishavad.
                Position[0] += 100;
                Position[1] -= 80;
                Position[2] += 7;            
                break;
            
            case (3) :
            //   semte chap nazdik pedar mishavad.
                Position[0]  -= 25;
                Position[1]  += 80;
                Position[2]  -= 7;     
                break;
            
            case (4):
                //  rast hamrah b
                Position[0]  +=15;
                Position[1]  += 80;
                Position[2]  -= 7;
                break;
            
            case (5):
            //   semte rast  az pedar door mishavad.
                Position[0]  += 60;
                Position[1]  -= 80;
                Position[2]  += 7;
                break;
            
            case (6) :
                
           //   semte rast  az pedar door mishavad.
                Position[0]  -= -10;
                Position[1]  -= 80;
                Position[2]  += 7;
            break;
//            case (1):
//                Position[0] -= 80;
//                Position[1] += 80;
//                Position[2] -= 7;
//                break;
//
//            case (2):
//                //   semte rast nazdik pedar mishavad.
//                Position[0] += 80;
//                Position[1] -= 80;
//                Position[2] += 7;
//                break;
//
//            case (3):
//                //   semte chap nazdik pedar mishavad.
//                Position[0] -= 80;
//                Position[1] += 80;
//                Position[2] -= 7;
//                break;
//
//            case (4):
//                //  rast hamrah b
//                Position[0] += 80;
//                Position[1] += 80;
//                Position[2] -= 7;
//                break;
//
//            case (5):
//                //   semte rast  az pedar door mishavad.
//                Position[0] += 80;
//                Position[1] -= 80;
//                Position[2] += 7;
//                break;
//
//            case (6):
//
//                //   semte rast  az pedar door mishavad.
//                Position[0] -= 80;
//                Position[1] -= 80;
//                Position[2] += 7;
//                break;

        }

    }

}

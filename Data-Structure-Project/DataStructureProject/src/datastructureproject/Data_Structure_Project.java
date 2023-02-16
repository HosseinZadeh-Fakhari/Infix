package datastructureproject;

import javax.swing.JPanel;

public class Data_Structure_Project {

    public static void main(String[] args) {
        
           JPanel panel=new JPanel();  
           String Infix = "";
           String Prefix = "";
           String Postfix = "";
          
           new FrontEnd( panel, Infix, Prefix, Postfix );
            
    }

}

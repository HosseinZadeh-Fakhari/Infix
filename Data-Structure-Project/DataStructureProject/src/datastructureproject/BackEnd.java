package datastructureproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class BackEnd {

    public String Unique_Charecter = "!@$}&_=|'{[%;.,`~]:?<>#»«";

    public String Exam_is_Infix_Post_Pre = " - + * ^ / ";
    public String Phrase_Recognise_for_Stack = " - + * ^ /  ( ) ";
    public String Redy_Infix_for_Write = "";
    public String Redy_Prefix_for_Write = "";
    public String Redy_Infix_for_Draw = "";
    public String Redy_Prefix_for_Draw = "";
    public String Last_Postfix = "";

    public BackEnd(String Phrase_Logged) throws InterruptedException {

        HashMap<String, Integer> Like_Dectionery_Python = new HashMap<>();
        HashMap<String, String> for_Use_Unique_Charecter = new HashMap<>();

        HashMap_Java(Like_Dectionery_Python);

        Phrase_is_Infix_Post_Pre(Phrase_Logged, Like_Dectionery_Python, for_Use_Unique_Charecter);

        System.out.println("////////////////////////////////////////");
        System.out.println("Infix_for_Draw : " + Redy_Infix_for_Draw);
        System.out.println("Prefix_for_Draw : " + Redy_Prefix_for_Draw);
        System.out.println("////////////////////////////////////////");

        new TreeEnd(Redy_Infix_for_Draw, Redy_Prefix_for_Draw, Last_Postfix, Redy_Infix_for_Write, Redy_Prefix_for_Write, for_Use_Unique_Charecter);

    }

///////////////////////////////////////////  Recognize Infix or Postfix or Prefix     ///////////////////////////////////////////
    public void Phrase_is_Infix_Post_Pre(String Phrase_Logged, HashMap<String, Integer> Like_Dectionery_Python, HashMap<String, String> for_Use_Unique_Charecter) throws InterruptedException {

        if (Exam_is_Infix_Post_Pre.contains(Character.toString(Phrase_Logged.charAt(0)))) {

            System.out.println("prefix");

            Convert_Prefix_To_Infix(Phrase_Logged, Like_Dectionery_Python);

            Convert_Infix_To_Postfix(Redy_Infix_for_Write, Like_Dectionery_Python);

            Convert_Infix_To_Prefix(Redy_Infix_for_Write, Like_Dectionery_Python, for_Use_Unique_Charecter);

            Redy_Prefix_for_Write = Phrase_Logged;

        } else if (Exam_is_Infix_Post_Pre.contains(Character.toString(Phrase_Logged.charAt(Phrase_Logged.length() - 1)))) {

            System.out.println("postfix");

            Convert_Postfix_To_Infix(Phrase_Logged, Like_Dectionery_Python);

            Convert_Infix_To_Prefix(Redy_Infix_for_Write, Like_Dectionery_Python, for_Use_Unique_Charecter);

            Last_Postfix = Phrase_Logged;

        } else {

            System.out.println("infix");

            Convert_Infix_To_Prefix(Phrase_Logged, Like_Dectionery_Python, for_Use_Unique_Charecter);

            Convert_Infix_To_Postfix(Phrase_Logged, Like_Dectionery_Python);

            Redy_Infix_for_Write = Phrase_Logged;

        }

    }

//////////////////////////////////////////////////////  Convert Infix To Postfix     //////////////////////////////////////////////////
    public void Convert_Infix_To_Postfix(String Redy_to_Convert, HashMap<String, Integer> Like_Dectionery_Python) {

        String Converted_Phrase = "";

        Stack<String> Stack = new Stack<String>();

        for (int i = 0; i < Redy_to_Convert.length(); i++) {

//            1:
            if (!Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i)))) {
                Converted_Phrase += Character.toString(Redy_to_Convert.charAt(i));
            } //            2:
            else if ("(".equals(Character.toString(Redy_to_Convert.charAt(i)))) {
                Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
            } //            3:
            else if (")".equals(Character.toString(Redy_to_Convert.charAt(i)))) {

                while (Stack.empty() || !"(".equals(Stack.peek())) {
                    Converted_Phrase += Stack.pop();
                }

                if ("(".equals(Stack.peek())) {
                    Stack.pop();
                }
            } //            4.:
            else if (Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i)))) {

//                    4.1:
                if (Stack.isEmpty()) {

                    Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
                } //                    4.2:
                else if (!Stack.isEmpty()) {

//                            4.2.1:
                    if (Like_Dectionery_Python.get(Character.toString(Redy_to_Convert.charAt(i))) > Like_Dectionery_Python.get(Stack.peek())) {
                        Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
                    } //                            4.2.2:
                    else if ((Objects.equals(Like_Dectionery_Python.get(Character.toString(Redy_to_Convert.charAt(i))), Like_Dectionery_Python.get(Stack.peek()))) && ("^".equals(Character.toString(Redy_to_Convert.charAt(i))))) {
                        Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
                    } //                             4.2.3:
                    else {

//                                          4.2.3.1:
                        while (!Stack.isEmpty() && Like_Dectionery_Python.get(Character.toString(Redy_to_Convert.charAt(i))) <= Like_Dectionery_Python.get(Stack.peek())) {
                            Converted_Phrase += Stack.pop();
                        }

//                                          4.2.3.2:
                        Stack.push(Character.toString(Redy_to_Convert.charAt(i)));

                    }

                }

            }
        }

        while (!Stack.isEmpty()) {
            Converted_Phrase += Stack.pop();
        }

        Last_Postfix = Converted_Phrase;

    }

    ////////////////////////////////////////////////  Convert Infix To Prefix     /////////////////////////////////////////////////
    public void Convert_Infix_To_Prefix(String Redy_to_Convert, HashMap<String, Integer> Like_Dectionery_Python, HashMap<String, String> for_Use_Unique_Charecter) {

        String Redy_to_Convert_Reversed = "";

        for (int i = Redy_to_Convert.length() - 1; -1 < i; i--) {
            switch (Character.toString(Redy_to_Convert.charAt(i))) {
                case ")":
                    Redy_to_Convert_Reversed += "(";
                    break;
                case "(":
                    Redy_to_Convert_Reversed += ")";
                    break;
                default:
                    Redy_to_Convert_Reversed += Character.toString(Redy_to_Convert.charAt(i));
                    break;
            }
        }

        Redy_Infix_for_Draw = Redy_to_Convert_Reversed;

        String Converted_Phrase = "";
        Stack<String> Stack = new Stack<String>();

        for (int i = 0; i < Redy_to_Convert.length(); i++) {

            if (!Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert_Reversed.charAt(i)))) {
                Converted_Phrase += Character.toString(Redy_to_Convert_Reversed.charAt(i));
            } else if ("(".equals(Character.toString(Redy_to_Convert_Reversed.charAt(i)))) {
                Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));
            } else if (")".equals(Character.toString(Redy_to_Convert_Reversed.charAt(i)))) {

                while (Stack.empty() || !"(".equals(Stack.peek())) {

                    Converted_Phrase += Stack.pop();
                }

                if ("(".equals(Stack.peek())) {
                    Stack.pop();
                }
            } else if (Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert_Reversed.charAt(i))) == true) {

                if (Stack.isEmpty()) {
                    Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));
                } else if (!Stack.isEmpty()) {

                    if (Like_Dectionery_Python.get(Character.toString(Redy_to_Convert_Reversed.charAt(i))) > Like_Dectionery_Python.get(Stack.peek())) {
                        Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));
                    } else if ((Objects.equals(Like_Dectionery_Python.get(Character.toString(Redy_to_Convert_Reversed.charAt(i))), Like_Dectionery_Python.get(Stack.peek()))) && ("^".equals(Character.toString(Redy_to_Convert_Reversed.charAt(i))))) {
                        Converted_Phrase += Stack.pop();
                        Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));

                    } else if (Objects.equals(Like_Dectionery_Python.get(Character.toString(Redy_to_Convert_Reversed.charAt(i))), Like_Dectionery_Python.get(Stack.peek()))) {
                        Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));
                    } else {

                        while (!Stack.isEmpty() && Like_Dectionery_Python.get(Character.toString(Redy_to_Convert_Reversed.charAt(i))) <= Like_Dectionery_Python.get(Stack.peek())) {
                            Converted_Phrase += Stack.pop();
                        }

                        Stack.push(Character.toString(Redy_to_Convert_Reversed.charAt(i)));

                    }

                }
            }
        }

        while (!Stack.isEmpty()) {
            Converted_Phrase += Stack.pop();
        }

        Redy_Prefix_for_Draw = Converted_Phrase;

        Redy_Prefix_for_Write = new StringBuilder(Converted_Phrase).reverse().toString();


///////////////////////////////////////////////////////  Unique  Charecter /////////////////////////////////////////////////////////////////
        String Remove_Repeated_Elements = Arrays.asList(Redy_Prefix_for_Write.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());

        if (Remove_Repeated_Elements.length() < Redy_Prefix_for_Write.length()) {

            for (int i = 0; i < Redy_Infix_for_Draw.length(); i++) {
                if (Character.toString(Redy_Infix_for_Draw.charAt(i)) == "+") {
                    Redy_Infix_for_Draw = Redy_Infix_for_Draw.replaceAll("\\+", "\\\\+");
                }
            }

            List<Integer> Save_Random_Number = new ArrayList<Integer>();
            int z = 0;
            Redy_Prefix_for_Draw = Redy_Prefix_for_Write;
            for (int j = 0; j < Redy_Infix_for_Draw.length(); j++) {

                int Random_Number = (int) (Math.random() * (Unique_Charecter.length()));

                char Char = Redy_Infix_for_Draw.charAt(j);
                int counts = 0;
                if ("(".equals(Character.toString(Redy_Infix_for_Draw.charAt(j))) || ")".equals(Character.toString(Redy_Infix_for_Draw.charAt(j)))) {
                    continue;
                }
                
                
                for (int i = 0; i < Redy_Infix_for_Draw.length(); i++) {
                    if (Redy_Infix_for_Draw.charAt(i) == Char) {
                        counts++;
                    }
                }
                if (1 < counts) {
                    if ("+".equals(Character.toString(Char)) || "*".equals(Character.toString(Char)) || "^".equals(Character.toString(Char))) {
                        String NewChar = "";
                        Save_Random_Number.add(Random_Number);

                        if ("+".equals(Character.toString(Char))) {
                            NewChar = "\\+";
                            Redy_Infix_for_Draw = Redy_Infix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Random_Number)));

                        }
                        if ("*".equals(Character.toString(Char))) {
                            NewChar = "\\*";
                            Redy_Infix_for_Draw = Redy_Infix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Random_Number)));

                        }
                        if ("^".equals(Character.toString(Char))) {
                            NewChar = "\\^";  
                            Redy_Infix_for_Draw = Redy_Infix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Random_Number)));

                        }

                        for_Use_Unique_Charecter.put(Character.toString(Unique_Charecter.charAt(Random_Number)), Character.toString(Char));
                        Unique_Charecter = Unique_Charecter.replace(Character.toString(Unique_Charecter.charAt(Random_Number)), "");

                    } else {
                        Save_Random_Number.add(Random_Number);
                        Redy_Infix_for_Draw = Redy_Infix_for_Draw.replaceFirst(Character.toString(Char), Character.toString(Unique_Charecter.charAt(Random_Number)));

                        for_Use_Unique_Charecter.put(Character.toString(Unique_Charecter.charAt(Random_Number)), Character.toString(Char));
                        Unique_Charecter = Unique_Charecter.replace(Character.toString(Unique_Charecter.charAt(Random_Number)), "");

                    }
                }
            }

            Unique_Charecter = "!@$}&_=|'{[%;.,`~]:?<>#»«";
            for (int j = 0; j < Redy_Prefix_for_Draw.length(); j++) {
                char Char = Redy_Prefix_for_Draw.charAt(j);
                int counts = 0;
                if ("(".equals(Character.toString(Char)) || ")".equals(Character.toString(Char))) {
                    continue;
                }
                for (int i = 0; i < Redy_Prefix_for_Draw.length(); i++) {
                    if (Redy_Prefix_for_Draw.charAt(i) == Char) {

                        counts++;
                    }
                }

                if (1 < counts) {
                    if ("+".equals(Character.toString(Char)) || "*".equals(Character.toString(Char)) || "^".equals(Character.toString(Char))) {
                        String NewChar = "";
                        if ("+".equals(Character.toString(Char))) {
                            NewChar = "\\+";
                            Redy_Prefix_for_Draw = Redy_Prefix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))));

                        }
                        if ("*".equals(Character.toString(Char))) {
                            NewChar = "\\*";
                            Redy_Prefix_for_Draw = Redy_Prefix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))));

                        }
                        if ("^".equals(Character.toString(Char))) {
                            NewChar = "\\^";
                            
                               Redy_Prefix_for_Draw = new StringBuilder(Redy_Prefix_for_Draw).reverse().toString();
                               Redy_Prefix_for_Draw = Redy_Prefix_for_Draw.replaceFirst(NewChar, Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))));
                               Redy_Prefix_for_Draw = new StringBuilder(Redy_Prefix_for_Draw).reverse().toString();

                        }
                        Unique_Charecter = Unique_Charecter.replace(Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))), "");
                        Save_Random_Number.remove(0);
                    } else {
                        Redy_Prefix_for_Draw = Redy_Prefix_for_Draw.replaceFirst(Character.toString(Char), Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))));
                        Unique_Charecter = Unique_Charecter.replace(Character.toString(Unique_Charecter.charAt(Save_Random_Number.get(0))), "");
                        Save_Random_Number.remove(0);
                    }
                }
            }

            Redy_Prefix_for_Draw = new StringBuilder(Redy_Prefix_for_Draw).reverse().toString();
           
        }

        Redy_Prefix_for_Draw = new StringBuilder(Redy_Prefix_for_Draw).reverse().toString();
        Redy_Infix_for_Draw = new StringBuilder(Redy_Infix_for_Draw).reverse().toString();
        String po = Redy_Infix_for_Draw;
        Redy_Infix_for_Draw = "";

        for (int i = 0; i < po.length(); i++) {
            switch (Character.toString(po.charAt(i))) {
                case ")":
                    Redy_Infix_for_Draw += "(";
                    break;
                case "(":
                    Redy_Infix_for_Draw += ")";
                    break;
                default:
                    Redy_Infix_for_Draw += Character.toString(po.charAt(i));
                    break;
            }
        }


    }

//////////////////////////////////////////////////  Convert Postfix To Infix     /////////////////////////////////////////////////
    public void Convert_Postfix_To_Infix(String Redy_to_Convert, HashMap<String, Integer> Like_Dectionery_Python) {

        String Converted_Phrase = "";

//              1:
        Stack<String> Stack = new Stack<String>();

//              2:
        for (int i = 0; i < Redy_to_Convert.length(); i++) {

//              2.1:
            if (Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i))) == false) //                    2.1.1:       
            {
                Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
            } //                    2.2:
            else if (Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i))) == true) {

//                       2.2.1:   &   2.2.2:
                String First_Pop = Stack.pop();

//                      2.2.3:   &   2.2.4:
                String Second_Pop = Stack.pop();

//                      2.2.5:
                String Push_to_Stack = "(" + Second_Pop + Character.toString(Redy_to_Convert.charAt(i)) + First_Pop + ")";

//                      2.2.6:
                Stack.push(Push_to_Stack);

            }

        }
        Converted_Phrase = Stack.peek();
        Redy_Infix_for_Write = Converted_Phrase;

    }

//////////////////////////////////////////////////  Convert Prefix To Infix     /////////////////////////////////////////////////
    public void Convert_Prefix_To_Infix(String Redy_to_Convert, HashMap<String, Integer> Like_Dectionery_Python) {


        String Converted_Phrase = "";

//              1:
        Stack<String> Stack = new Stack<String>();

//              2:
        for (int i = Redy_to_Convert.length() - 1; -1 < i; i--) {

//              2.1:
            if (!Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i)))) //                    2.1.1:       
            {
                Stack.push(Character.toString(Redy_to_Convert.charAt(i)));
            } //                    2.2:
            else if (Phrase_Recognise_for_Stack.contains(Character.toString(Redy_to_Convert.charAt(i)))) {

//                       2.2.1:   &   2.2.2:
                String First_Pop = Stack.pop();

//                      2.2.3:   &   2.2.4:
                String Second_Pop = Stack.pop();

//                      2.2.5:
                String Push_to_Stack = "(" + First_Pop + Character.toString(Redy_to_Convert.charAt(i)) + Second_Pop + ")";

//                      2.2.6:
                Stack.push(Push_to_Stack);

            }

        }

        Converted_Phrase = Stack.peek();
        Redy_Infix_for_Write = Converted_Phrase;

    }

//////////////////////////////////////////////////  For  Operators Comparison    /////////////////////////////////////////////////
    public void HashMap_Java(HashMap<String, Integer> Like_Dectionery_Python) {
        Like_Dectionery_Python.put("(", 0);
        Like_Dectionery_Python.put("+", 1);
        Like_Dectionery_Python.put("-", 1);
        Like_Dectionery_Python.put("*", 2);
        Like_Dectionery_Python.put("/", 2);
        Like_Dectionery_Python.put("^", 3);
    }

}

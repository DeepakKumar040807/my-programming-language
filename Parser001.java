import java.util.ArrayList;
import java.util.*;

public class Parser001 {
    static List<String> Token = Lexer003.Token;
    static List<String> UserToken = Lexer003.UserToken;
    static List <String> DataType = new ArrayList<>();
    static {
        DataType.add("INT");
        DataType.add("FLOAT");
        DataType.add("STRING");
        DataType.add("BOOLEAN");
    }

    Parser001(ArrayList<String> P_token, ArrayList<String> P_userToken){
        if (P_token.isEmpty() || P_userToken.isEmpty()){
            return;
        }
            int i = 0;
            int x = 1;
            int lastProcessed = 0;
            while(i < P_token.size() && lastProcessed != P_token.size()){
                boolean identifier = false;
                boolean dataType = false;
                boolean terminator = false;
                dataType = CheckDataType(P_token.get(i));
                if (dataType){
                    i++;
                }
                if (i < P_token.size() ) {
                    identifier = CheckIdentifier(P_token.get(i));
                   if (identifier){
                        i++;
                    }
                }else if (identifier){
                i++;
                }

                if (i < P_token.size()) {
                    terminator = CheckTerminator(P_token.get(i));

                    if (terminator){
                        i++;
                    }
                }else if (terminator){
                    i++;
                }
                if (lastProcessed == 0){lastProcessed++;}
                lastProcessed = lastProcessed +1;

                if (identifier && dataType && terminator){
                    System.out.println("--- THE CONDITION WAS MET SUCCESSFULLY FOR SET " + x +"---");
                    x++;
                }else{
                    int y = (identifier ? 4 : 0) | (dataType ? 2 : 0) | (terminator ? 1 : 0);
                    switch (y) {
                        case 0:
                            System.out.println("EVERYTHING NOT FOUND");
                            break;
                        case 1:
                            System.out.println("IDENTIFIER AND DATATYPE NOT FOUND");
                            break;
                        case 2:
                            System.out.println("IDENTIFIER AND TERMINATOR");
                            break;
                        case 3:
                            System.out.println("IDENTIFIER NOT FOUND");
                            break;
                        case 4:
                            System.out.println("DATATYPE AND TERMINATOR NOT FOUND");
                            break;
                        case 5:
                            System.out.println("DATATYPE NOT FOUND");
                            break;
                        case 6:
                            System.out.println("TERMINATOR NOT FOUND");
                            break;
                    }

//                    switch (y){
//                        case 1:
//                            System.out.println(" IDENTIFIER AND DATATYPE CAN NOT BE FOUND");
//                            break;
//                        case 2 :
//                            System.out.println("IDENTIFIER AND TERMINATOR NOT FOUND");
//                            break;
//                        case 4:
//                            System.out.println("DATATYPE AND TERMINATOR NOT FOUND");
//                            break;
//                        case 3:
//                            System.out.println("IDENTIFIER NOT FOUND");
//                            break;
//                        case 5:
//                            System.out.println("DATATYPE NOT FOUND");
//                            break;
//                        case 6:
//                            System.out.println("TERMINATOR NOT FOUND");
//                            break;
//                    }


                }
            }

    }

    boolean CheckIdentifier(String P_token){
        if (P_token.equals("IDENTIFIER")){
            return true;
        }else {
            return false;
        }
    }

    boolean CheckTerminator(String P_token){
        if (P_token.equals("END")){
            return true;
        }else{
            return false;
        }
    }

    boolean CheckDataType(String P_token){
        if (DataType.contains(P_token)){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        new Lexer003("int age;");
        new Lexer003("float price;");
        new Lexer003("string name;");
        new Lexer003("boolean active;");
        new Lexer003("int ;");
        new Lexer003("int age");

       new Lexer003("age;");
        new Lexer003("");
        new Lexer003("age;");
        new Lexer003("int");
        new Parser001((ArrayList<String>) Token, (ArrayList<String>) UserToken);
    }
}

import java.util.*;

public class Lexer003 {
    Map<String, String> keyWordsMap = new HashMap<>();
    Map<Character, String> symbolsMap = new HashMap<>();
    List<String> identifiers = new ArrayList<>();
    static Map<String,String> Tokens = new LinkedHashMap<>();
    static List<String> Token = new ArrayList<>();
    static List<String> UserToken = new ArrayList<>();

    {
        symbolsMap.put(';', "END");
        keyWordsMap.put("int", "INT");
        keyWordsMap.put("float", "FLOAT");
        keyWordsMap.put("string", "STRING");
        keyWordsMap.put("boolean", "BOOLEAN");
        keyWordsMap.put("let", "LET");
        keyWordsMap.put("if", "IF");
        keyWordsMap.put("else", "ELSE");
        keyWordsMap.put("while", "WHILE");
        keyWordsMap.put("function", "FUNCTION");
        keyWordsMap.put("return", "RETURN");
        keyWordsMap.put("true", "TRUE");
        keyWordsMap.put("false", "FALSE");
        keyWordsMap.put("and", "AND");
        keyWordsMap.put("or", "OR");
        keyWordsMap.put("not", "NOT");
        keyWordsMap.put("printd", "PRINT");
        keyWordsMap.put("inputd", "INPUT");
    }


    Lexer003(String input){
        int start = 0;
        int curr = 0;

        while(curr < input.length()) {
            if (curr != input.length() -1){
                if (Character.isAlphabetic(input.charAt(curr))) {
                    curr++;
                } else if (input.charAt(curr) == ' ') {
                    boolean flag = ProcessKeyWord(input.substring(start, curr));
                    if (flag == false) {
                        ProcessIdentifiers(input.substring(start, curr));
                    }
                    start = curr + 1;
                    curr = start;
                } else if (symbolsMap.containsKey(input.charAt(curr))) {
                    boolean flag = ProcessKeyWord(input.substring(start, curr));
                    if (flag == false) {
                        ProcessIdentifiers(input.substring(start, curr));
                    }
                    ProcessSymbol(input.charAt(curr));

                    start = curr + 1;
                    curr = start;
                }
            }else if(curr == input.length() -1){
                boolean elseFlag = Character.isAlphabetic(input.charAt(curr));
                if(elseFlag){
                    if (keyWordsMap.containsKey(input.substring(start, curr + 1))){
                        ProcessKeyWord(input.substring(start, curr + 1));
                    }else {
                        ProcessIdentifiers(input.substring(start, curr + 1));
                    }
                }else{
                    String lastProcessing = input.substring(start, curr);
                    if(!lastProcessing.isBlank()){
                        if (keyWordsMap.containsKey(lastProcessing)){
                            ProcessKeyWord(lastProcessing);
                        }else{

                            ProcessIdentifiers(lastProcessing);

                        }
                        if (symbolsMap.containsKey(input.charAt(curr))){
                            ProcessSymbol(input.charAt(curr));
                            curr++;
                            break;
                        }



                    }
                    if (symbolsMap.containsKey(input.charAt(curr))){
                        ProcessSymbol(input.charAt(curr));
                        break;
                    }else{
                        break;
                    }

                }
                break;
            }
        }
    }
    boolean ProcessIdentifiers(String identifieString) {
        if(identifieString != ""){
//            identifiers.add(identifieString);
//            int x = identifiers.indexOf(identifieString);
//            System.out.println(identifiers.get(x));
//            Tokens.put(identifiers.get(x), "IDENTIFIER");
            Token.add("IDENTIFIER");
            UserToken.add(identifieString);

            return true;
        }else {
            return false;
        }
    }

    void ProcessSymbol(Character symbol) {
//        System.out.println(symbolsMap.get(symbol));
//        Tokens.put(symbol.toString(), symbolsMap.get(symbol));
        Token.add(symbolsMap.get(symbol));
        UserToken.add(symbol.toString());
    }

    boolean ProcessKeyWord(String s) {
        if (keyWordsMap.containsKey(s)) {
//            System.out.println(keyWordsMap.get(s));
//            Tokens.put(s,keyWordsMap.get(s));
            Token.add(keyWordsMap.get(s));
            UserToken.add(s);
            return true;
        } else {
            return false;
        }
    }



    public static void main(String[] args){



            String input =
                    "int age; " +
                            "float price; " +
                            "string name; " +
                            "boolean active; " +
                            "let age; " +
                            "if age; " +
                            "else age; " +
                            "while age; " +
                            "function test; " +
                            "return age; " +
                            "true; " +
                            "false; " +
                            "and; " +
                            "or; " +
                            "not; " +
                            "printd; " +
                            "inputd;";

            Lexer003 lexer = new Lexer003(input);

        for (int i = 0;i < Token.size(); i++){
            System.out.println(Token.get(i) + " = " + UserToken.get(i));
        }



    }
}

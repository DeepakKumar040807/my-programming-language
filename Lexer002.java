import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer002 {
    Map<String, String> keyWordsMap = new HashMap<>();
    Map<Character, String> symbolsMap = new HashMap<>();
    List<String> identifiers = new ArrayList<>();

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

    Lexer002(String input){
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
                            ;
                            ProcessKeyWord(lastProcessing);
                           if (symbolsMap.containsKey(input.charAt(curr))){
                               ProcessSymbol(input.charAt(curr));
                               curr++;
                               break;
                           }
                        }

                        else{
                            curr++;
                            ProcessIdentifiers(lastProcessing);
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
            identifiers.add(identifieString);
            int x = identifiers.indexOf(identifieString);
            System.out.println(identifiers.get(x));
            return true;
        }else {
            return false;
        }
    }

    void ProcessSymbol(Character symbol) {

        System.out.println(symbolsMap.get(symbol));
    }

    boolean ProcessKeyWord(String s) {
        if (keyWordsMap.containsKey(s)) {
            System.out.println(keyWordsMap.get(s));
            return true;
        } else {
            return false;
        }
    }



    public static void main(String[] args){

        Lexer002 l = new Lexer002("  int  age; float price; string name; boolean active; " +
                "let if else while function return true false and or not printd inputd;");
    }
}

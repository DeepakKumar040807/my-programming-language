
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Lexer001 {

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

    public Lexer001(String input) {
        int start = 0;
        int curr = 0;
        while (curr < input.length()) {

            if (Character.isLetter(input.charAt(curr)) && curr != input.length() - 1) {
                curr++;
            } else if (input.charAt(curr) == ' ' || curr == input.length() - 1) {
                if (input.charAt(curr) == ' ') {
                    if (keyWordsMap.containsKey(input.substring(start, curr))) {
                        ProcessKeyWord(input.substring(start, curr));
                    } else {
                        ProcessIdentifiers(input.substring(start, curr));
                    }
                } else {
                    if (keyWordsMap.containsKey(input.substring(start, curr))) {
                        ProcessKeyWord(input.substring(start, curr + 1));
                    } else {
                        ProcessIdentifiers(input.substring(start, curr + 1));
                    }
                }
                start = curr + 1;
                curr = start;
            } else if (symbolsMap.containsKey(input.charAt(curr))) {
                if (keyWordsMap.containsKey(input.substring(start, curr))) {
                    ProcessKeyWord(input.substring(start, curr));
                } else {
                    ProcessIdentifiers(input.substring(start, curr));
                }
                ProcessSymbol(input.charAt(curr));
                start = curr + 1;
                curr = start;
            } else {
                ProcessIdentifiers(input.substring(start, curr));
                start = curr + 1;
                curr = start;
            }

        }
    }

    boolean ProcessKeyWord(String s) {
        if (keyWordsMap.containsKey(s)) {
            System.out.println(keyWordsMap.get(s));
            return true;
        } else {
            return false;
        }
    }

    void ProcessIdentifiers(String identifieString) {
        identifiers.add(identifieString);
        int x = identifiers.indexOf(identifieString);
        System.out.println(identifiers.get(x));
    }

    void ProcessSymbol(Character symbol) {
        System.out.println(symbolsMap.get(symbol));
    }

    public static void main(String[] args) {
        new Lexer001("int  age;");
    }
}

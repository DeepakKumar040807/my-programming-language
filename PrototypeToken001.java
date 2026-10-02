import java.util.*;

class PrototypeToken001{
    Map<String, String> keyWord = new HashMap<>();

    ArrayList<String> identifiers = new ArrayList<>();

    Map<Character, String> symbols = new HashMap<>();

    {
        keyWord.put("int", "INT");
        keyWord.put("float", "FLOAT");
        keyWord.put("string", "STRING");
        keyWord.put("let", "LET");
        keyWord.put("boolean", "BOOL");

        symbols.put(';', "END");
        symbols.put(' ', "WHITESPACE");
    }

    public PrototypeToken001(String compString) {
        int start = 0;
        int curr = 0;
        while (curr < compString.length()) { 
            boolean lastIndex = false;
            if(curr  == compString.length() -1){
                lastIndex = true;
            }
            int x = curr;
            if (symbols.containsKey(compString.charAt(curr)) || lastIndex) {
                if(ProcessKeyWord(compString.substring(start, curr))){
                    start = curr +1;
                    curr = start;
                }else{
                   Identifier(compString.substring(start, curr));
                   start = curr +1;
                    curr = start;
                }
                System.out.println(symbols.get(compString.charAt(x)));
            }else{
                curr++;
            }
        }
    }

    boolean ProcessKeyWord(String s){
        if(keyWord.containsKey(s)){
            System.out.println(keyWord.get(s));
            return true;
        }else{
            return false;
        }
    }
    void Identifier(String a){
        identifiers.add(a);
        int x = identifiers.indexOf(a);
        System.out.println(identifiers.get(x));
    }
    public static void main(String[] args) {
        PrototypeToken001 t = new PrototypeToken001("int x; boolean y;");
    }
}



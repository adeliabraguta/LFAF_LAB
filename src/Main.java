import Lexer.*;
import Parser.*;
import java.util.List;
public class Main {
    public static void main(String[] args) {
//        Set<String> VN = new HashSet<>(Arrays.asList("S", "D", "E", "F", "L"));
//        Set<String> VT = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
//        Map<String, List<String>> productions = new HashMap<>();
//        productions.put("S", List.of("aD"));
//        productions.put("D", List.of("bE"));
//        productions.put("E", Arrays.asList("cF", "dL"));
//        productions.put("F", List.of("dD"));
//        productions.put("L", Arrays.asList("aL", "bL", "c"));
//        Grammar grammar = new Grammar(VN, VT, productions, "S");
//        List<String> validStrings = grammar.generateValidStrings(5);
//        System.out.println("5 Valid strings: " + validStrings);
//
//        Set<String> states = new HashSet<>(Arrays.asList("q0", "q1", "q2", "q3", "q4"));
//        Set<String> acceptingStates = new HashSet<>(List.of("q0"));
//        Map<String, Map<String, String>> transitions = new HashMap<>();
//        transitions.put("q0", new HashMap<>(Map.of("a", "q1")));
//        transitions.put("q1", new HashMap<>(Map.of("b", "q0", "a", "q2")));
//        transitions.put("q2", new HashMap<>(Map.of("b", "q3")));
//        transitions.put("q3", new HashMap<>(Map.of("a", "q0", "b", "q2", "c", "q0")));
//
//        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, acceptingStates, transitions, "q0");
//        List<String> testInputs = Arrays.asList("baba", "aabaaa", "ab", "babc", "ababababababababa");
//        for (String input : testInputs) {
//            System.out.println("\"" + input + "\" is: " + finiteAutomaton.accepts(input));
//        }
//
//        System.out.println("------------------------------------");
//        System.out.println("According to Chomsky hierarchy this is a " + grammar.classifyGrammar() + " grammar");
//
//
//        Set<String> Q = new HashSet<>(Arrays.asList("q0", "q1", "q2", "q3"));
//        Set<String> Sigma = new HashSet<>(Arrays.asList("a", "b"));
//        Map<Pair<String, String>, String> delta = new HashMap<>();
//        delta.put(new Pair<>("q0", "a"), "q1");
//        delta.put(new Pair<>("q1", "b"), "q2");
//        delta.put(new Pair<>("q2", "b"), "q3");
//        delta.put(new Pair<>("q3", "a"), "q1");
//        delta.put(new Pair<>("q2", "b"), "q2");
//        delta.put(new Pair<>("q1", "a"), "q1");
//        String q0 = "q0";
//        Set<String> F = new HashSet<>(List.of("q3"));
//
//        FA fa = new FA(Q, Sigma, delta, q0, F);
//
//        // Convert the FA to a regular grammar
//        Grammar rg = fa.toRegularGrammar();
//        System.out.println("Regular Grammar:");
//        System.out.println(rg);
//
//        // Check if the FA is deterministic or non-deterministic
//        if (fa.isDeterministic()) {
//            System.out.println("The FA is deterministic.");
//        } else {
//            System.out.println("The FA is non-deterministic.");
//        }
//
//        // Convert the FA to a DFA
//        DFA dfa = fa.toDFA();
//        System.out.println("DFA:");
//        System.out.println(dfa);


//        String input = "1+2*3-4/2";
//        input = "23500$";
//        Lexer lexer = new Lexer(input);
//        try {
//            List<Token> tokens = lexer.tokenize();
//            for (Token token : tokens) {
//                System.out.println(token);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        try {
            // Input expression
            String input = "4 + 2 * 3 - 8 / 2";

            // Lexer
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            // Parser
            Parser parser = new Parser(tokens);
            int result = parser.parseExpression();

            // Output
            System.out.println("Expression: " + input);
            System.out.println("Expression passed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
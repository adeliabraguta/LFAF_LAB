import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> VN = new HashSet<>(Arrays.asList("S", "D", "E", "F", "L"));
        Set<String> VT = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
        Map<String, List<String>> productions = new HashMap<>();
        productions.put("S", Arrays.asList("aD"));
        productions.put("D", Arrays.asList("bE"));
        productions.put("E", Arrays.asList("cF", "dL"));
        productions.put("F", Arrays.asList("dD"));
        productions.put("L", Arrays.asList("aL", "bL", "c"));
        Grammar grammar = new Grammar(VN, VT, productions, "S");
        List<String> validStrings = grammar.generateValidStrings(5);
        System.out.println("5 Valid strings: " + validStrings);

        Set<String> states = new HashSet<>(Arrays.asList("q0", "q1", "q2", "q3", "q4"));
        Set<String> acceptingStates = new HashSet<>(Arrays.asList("q0"));
        Map<String, Map<String, String>> transitions = new HashMap<>();
        transitions.put("q0", new HashMap<>(Map.of("a", "q1")));
        transitions.put("q1", new HashMap<>(Map.of("b", "q0", "a", "q2")));
        transitions.put("q2", new HashMap<>(Map.of("b", "q3")));
        transitions.put("q3", new HashMap<>(Map.of("a", "q0", "b", "q2", "c", "q0")));

        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, acceptingStates, transitions, "q0");
        List<String> testInputs = Arrays.asList("baba", "aabaaa", "ab", "babc", "ababababababababa");
        for (String input : testInputs) {
            System.out.println("\"" + input + "\" is: " + finiteAutomaton.accepts(input));
        }
    }
}

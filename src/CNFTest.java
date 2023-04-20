import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CNFTest {

    @Test
    public void testEliminateEpsilon() {
        CNF cnf = new CNF("S",
                Arrays.asList("S", "A", "B", "C"),
                Arrays.asList("a", "b"),
                new HashMap<>() {{
                    put("S", Arrays.asList("A", "B", "ε"));
                    put("A", Arrays.asList("aA", "b"));
                    put("B", Arrays.asList("AC", "bS", "aA"));
                    put("C", Arrays.asList("ε", "AB"));
                }});

        cnf.eliminateEpsilon();

        Map<String, List<String>> productions = cnf.getProductions();
        for (Map.Entry<String, List<String>> entry : productions.entrySet()) {
            String variable = entry.getKey();
            List<String> variableProductions = entry.getValue();
            for (String production : variableProductions) {
                assertFalse(production.contains("ε"));
            }
        }
    }

    @Test
    public void testEliminateInaccessibleSymbols() {
        CNF cnf = new CNF("S",
                Arrays.asList("S", "A", "B", "C"),
                Arrays.asList("a", "b"),
                new HashMap<>() {{
                    put("S", Arrays.asList("AB", "BA", "a"));
                    put("A", Arrays.asList("BC", "CB", "b"));
                    put("B", Arrays.asList("AB", "BA", "a"));
                    put("C", Arrays.asList("AC", "CA", "b"));
                }});

        cnf.eliminateInaccessibleSymbols();

        CNF expected = new CNF("S",
                Arrays.asList("S", "A", "B", "C"),
                Arrays.asList("a", "b"),
                new HashMap<>() {{
                    put("S", Arrays.asList("AB", "BA", "a"));
                    put("A", Arrays.asList("BC", "CB", "b"));
                    put("B", Arrays.asList("AB", "BA", "a"));
                    put("C", Arrays.asList("AC", "CA", "b"));
                }});

        assertEquals(expected.getStartSymbol(), cnf.getStartSymbol());
        assertEquals(expected.getNonTerminal(), cnf.getNonTerminal());
        assertEquals(expected.getTerminals(), cnf.getTerminals());
        assertEquals(expected.getProductions(), cnf.getProductions());
    }

    @Test
    public void testEliminateNonproductive() {
        CNF cnf = new CNF("S",
                Arrays.asList("S", "A", "B", "C", "D"),
                Arrays.asList("a", "b"),
                new HashMap<>() {{
                    put("S", Arrays.asList("A", "aB"));
                    put("A", Arrays.asList("aA", "bS"));
                    put("B", Arrays.asList("AC", "bS", "aA"));
                    put("C", Arrays.asList("ε", "AD"));
                    put("D", Arrays.asList("ε", "BC"));
                }});

        cnf.eliminateNonproductive();

        Map<String, List<String>> productions = cnf.getProductions();
        Set<String> nonproductive = new HashSet<>(Arrays.asList("D"));
        for (String symbol : nonproductive) {
            assertFalse(productions.containsKey(symbol));
        }
        for (Map.Entry<String, List<String>> entry : productions.entrySet()) {
            String variable = entry.getKey();
            List<String> variableProductions = entry.getValue();
            for (String production : variableProductions) {
                for (char s : production.toCharArray()) {
                    assertTrue(cnf.getNonTerminal().contains(String.valueOf(s)) || cnf.getTerminals().contains(String.valueOf(s)));
                }
            }
        }
    }
}

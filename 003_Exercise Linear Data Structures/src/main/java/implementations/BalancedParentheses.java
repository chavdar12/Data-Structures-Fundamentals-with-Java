package implementations;

import interfaces.Solvable;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BalancedParentheses implements Solvable {
    private static final Map<String, String> brackets = new LinkedHashMap<>(6);
    private static final Set<String> opening = new HashSet<>(3);
    private static final Set<String> closing = new HashSet<>(3);
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
        brackets.put("[", "]");
        brackets.put("]", "[");
        brackets.put("{", "}");
        brackets.put("}", "{");
        brackets.put("(", ")");
        brackets.put(")", "(");
        opening.add("[");
        opening.add("(");
        opening.add("{");
        closing.add("]");
        closing.add("}");
        closing.add(")");
    }

    @Override
    public Boolean solve() {

        if (this.parentheses.length() == 0 || parentheses.length() % 2 != 0) {
            return false;
        }

        String[] toCheck = parentheses.split("");

        ArrayDeque<String> stack = new ArrayDeque<>();

        for (String bracket : toCheck) {
            if (!brackets.containsKey(bracket)) return false;

            if (opening.contains(bracket)) {
                stack.push(bracket);
                continue;
            }

            String openingBracket = stack.pop();

            if (openingBracket == null) return false;

            String closingBracket = brackets.get(openingBracket);

            if (closingBracket.compareTo(bracket) != 0) return false;
        }

        return stack.isEmpty();
    }
}

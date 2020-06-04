public class Calculate {
    public int[][] conjunction = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 1, 0, 0, 0 } };
    public int[][] disjunction = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 1, 1, 1, 0 } };
    public int[][] exclusive = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 } };
    public int[][] conditional = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 1, 0, 1, 1 } };
    public int[][] bicondi = { { 1, 1, 0, 0 }, { 1, 0, 1, 0 }, { 1, 0, 0, 1 } };

    public int calcProb(String mathType, Integer first, Integer second) {
        int[][] catalog;
        if (mathType == "-") {
            if (second == 1) {
                return 0;
            }
            return 1;
        } else {
            switch (mathType) {
                case "-":
                    if (second == 1) {
                        return 0;
                    }
                    return 1;
                case "^":
                    catalog = conjunction;
                    break;
                case "v":
                    catalog = disjunction;
                    break;
                case "+":
                    catalog = exclusive;
                    break;
                case ">":
                    catalog = conditional;
                    break;
                case "=":
                    catalog = bicondi;
                    break;
                default:
                    catalog = conjunction;
                    break;
            }
            for (int i = 0; i < 4; ++i) {
                for (int j = i; j < 4; ++j) {
                    if (first == catalog[0][i] && second == catalog[1][i]) {
                        return catalog[2][i];
                    }
                }
            }
        }
        return -1;
    }
}
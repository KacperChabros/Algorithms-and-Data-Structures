package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class LongestCommonSubsequence {

    public String findLcs(String topText, String leftText) {
        validateParameters(topText, leftText);
        int n = leftText.length();
        int m = topText.length();

        Node[][] matrix = initializeFirstRowAndColumn(m, n);

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (leftText.charAt(i - 1) == topText.charAt(j - 1)) {
                    int value = matrix[i - 1][j - 1].getValue() + 1;
                    matrix[i][j] = new Node(value, Arrow.DIAGONAL);
                } else if (matrix[i][j - 1].getValue() > matrix[i - 1][j].getValue()) {
                    int value = matrix[i][j - 1].getValue();
                    matrix[i][j] = new Node(value, Arrow.LEFT);
                } else {
                    int value = matrix[i - 1][j].getValue();
                    matrix[i][j] = new Node(value, Arrow.TOP);
                }
            }
        }
        
        return traceBackSteps(matrix, n, m, topText);
    }

    private void validateParameters(String topText, String leftText) {
        if (topText == null || leftText == null) {
            throw new IllegalArgumentException("Parameters cannot be null!");
        }
    }

    private Node[][] initializeFirstRowAndColumn(int m, int n) {
        Node[][] matrix = new Node[n + 1][m + 1];

        for (int i = 0; i < m + 1; i++) {
            matrix[0][i] = new Node(0, null);
        }

        for (int i = 1; i < n + 1; i++) {
            matrix[i][0] = new Node(0, null);
        }

        return matrix;
    }

    private String traceBackSteps(Node[][] matrix, int n, int m, String topText) {
        int currentRow = n;
        int currentColumn = m;
        StringBuilder sb = new StringBuilder();

        while (currentRow > 0 && currentColumn > 0) {
            if (matrix[currentRow][currentColumn].getArrow() == Arrow.DIAGONAL) {
                sb.append(topText.charAt(currentColumn - 1));
                currentRow--;
                currentColumn--;
            } else if (matrix[currentRow][currentColumn].getArrow() == Arrow.LEFT) {
                currentColumn--;
            } else {
                currentRow--;
            }
        }
        sb.reverse();
        return sb.toString();
    }

}

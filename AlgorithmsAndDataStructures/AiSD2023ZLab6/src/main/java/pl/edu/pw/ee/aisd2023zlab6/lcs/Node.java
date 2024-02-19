package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class Node {
    private int value;
    private Arrow arrow;

    public Node(int value, Arrow arrow) {
        this.value = value;
        this.arrow = arrow;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
   
    
}

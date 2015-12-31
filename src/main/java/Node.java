import java.util.Collections;
import java.util.List;

class Node {

    public final String op;
    public final String arg;
    public final List<Node> children;

    public Node(String op) {
        this(op, null, Collections.<Node>emptyList());
    }

    public Node(String op, String arg) {
        this(op, arg, Collections.<Node>emptyList());
    }

    public Node(String op, String arg, List<Node> children) {
        this.op = op;
        this.arg = arg;
        this.children = children;
    }
}
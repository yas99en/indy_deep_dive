import java.util.ArrayList;
import java.util.List;

class Parser {

    List<Node> parse(String script) {
        String[] lines = script.split("\n");

        return parse(lines, 0);
    }

    List<Node> parse(String[] lines, int offset) {
        List<Node> ast = new ArrayList<>(lines.length);

        for (; offset < lines.length; offset++) {
            String line = lines[offset];

            // blank lines an comments become nops
            if (line.length() == 0 || line.startsWith("#")) {
                ast.add(new Node("nop"));
                continue;
            }

            // check for an argument
            int space = line.indexOf(' ');

            if (space == -1) {
                // no argument

                if (line.equals("end")) {
                    // end of function, return ast
                    return ast;
                } else {
                    ast.add(new Node(line));
                }
            } else {
                // one argument
                String op = line.substring(0, space);
                String arg = line.substring(space + 1);

                if (op.startsWith("def")) {
                    // recurse to get children, advance offset past end
                    List<Node> children = parse(lines, ++offset);
                    offset += children.size();
                    ast.add(new Node(op, arg, children));
                } else {
                    // normal operation
                    ast.add(new Node(op, arg));
                }
            }
        }

        return ast;
    }
}
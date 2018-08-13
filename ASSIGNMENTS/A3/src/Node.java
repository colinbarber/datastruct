public class Node {

    // instantiates a 2- or 3-node with one or two values and two or three children.
    public Node parent;
    public int val;
    public int val2;
    public Node lChild;
    public Node mChild;
    public Node rChild;

    // instantiates a root node.
    public Node (int v) {
        val = v;
        val2 = -1;
    }

    // instantiates a new root node with children.
    public Node (int v, Node l, Node r) {
        val = v;
        val2 = -1;
        lChild = l;
        rChild = r;
    }

    // instantiates a leaf 2-node.
    public Node (Node p, int v) {
        parent = p;
        val = v;
        val2 = -1;
    }

    // instantiates a leaf 3-node.
    public Node (Node p, int v, int v2) {
        parent = p;
        val = v;
        val2 = v2;
    }

    // instantiates an internal 2-node.
    public Node (Node p, int v, Node l, Node r) {
        parent = p;
        val = v;
        val2 = -1;
        lChild = l;
        rChild = r;
    }

    // instantiates an internal 3-node.
    public Node (Node p, int v, int v2, Node l, Node m, Node r) {
        parent = p;
        val = v;
        val2 = v2;
        lChild = l;
        mChild = m;
        rChild = r;
    }

    public Node getParent() {
        return parent;
    }
}

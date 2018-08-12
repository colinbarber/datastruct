public class Tree {

    // forms a balanced Two-Three Tree from a given set of add and find commands.
    Node root;
    int comparisons = 0;
    int threeNodes = 0;

    // new empty Tree.
    public Tree() {}

    // add a node to the tree.
    public void add(int i) {
        if (root == null) {
            root = new Node(i);
            System.out.println("new root added: "+Integer.toString(i));
        }
        // call when node's target destination is non-null.
        else {
            compareAdd(i, root, null);
        }
    }

    public void compareAdd(int i, Node self, Node parent) {

        if (parent == null) {
            System.out.println("parent is null");
        }

        else {
            System.out.println("my parent is: "+Integer.toString(parent.val));
        }

        // node value is less than the target's left value.
        if (i < self.val) {
            comparisons += 1;

            // if leaf 2-node, transforms to 3-node with i in leftmost position.
            if (self.val2 == -1 && self.lChild == null) {
                self.val2 = self.val;
                self.val = i;
                threeNodes += 1;
                System.out.println("3-node created: "+Integer.toString(self.val)+" "+Integer.toString(self.val2));
            }
            // if leaf 3-node, split and promote target's left value.
            else if (self.lChild == null) {
                splitPromote(parent, self, i, self.val, self.val2, null, null);
            }
            // if internal node, compares to the leftmost child.
            else {
                compareAdd(i, self.lChild, self);
            }
        }

        // node value is greater than the target's left value.
        else if (i > self.val) {
            comparisons += 1;

            // if leaf 2-node, transforms to 3-node with i in rightmost position.
            if (self.val2 == -1 && self.lChild == null) {
                self.val2 = i;
                threeNodes += 1;
                System.out.println("3-node created: "+Integer.toString(self.val)+" "+Integer.toString(self.val2));
            }
            // if leaf 3-node, compare against val2.
            else if (self.lChild == null) {

                // if node value is less than the target's right value, split and promote node value.
                if (i < self.val2) {
                    comparisons += 1;

                    splitPromote(parent, self, self.val, i, self.val2, null, null);
                }
                // if node value is more than the target's right value, split and promote node value.
                else if (i > self.val2) {
                    comparisons += 1;
                    splitPromote(parent, self, self.val, self.val2, i, null, null);
                }
                // node value is equal to the target's right value.
                else {
                    self.val2 = i;
                }
            }

            // if internal 2-node, compares to the rightmost child.
            else if (self.val2 == -1) {
                System.out.println("to the right");
                compareAdd(i, self.rChild, self);
            }

            // if internal 3-node, compare against val2.
            else {

                // if node value is less than the target's right value, compare to the middle child.
                if (i < self.val2) {
                    comparisons += 1;
                    compareAdd(i, self.mChild, self);
                }
                // if node value is more than the target's right value, compare to the right child.
                else if (i > self.val2) {
                    comparisons += 1;
                    compareAdd(i, self.rChild, self);
                }
                // node value is equal to the target's right value.
                else {
                    self.val2 = i;
                }
            }
        }

        // node value is equal to the target value.
        else {
            self.val = i;
        }
    }

    // call when adding node to a 3-node.
    public void splitPromote(Node parent, Node self, int lVal, int mVal, int rVal, Node newL, Node newR) {

        // create new root if splitting the root node.
        if (parent == null) {

            System.out.println("splitPromote: my parent is null");

            // if leaf node.
            if (newL == null) {
                System.out.println("new root + children");
                root = new Node(mVal, new Node(root, lVal), new Node(root, rVal));
            }
            // if internal node.
            else {
                root = new Node(mVal, newL, newR);
            }
        }

        // split a 3-node and promote to a 2-node.
        else if (parent.val2 == -1) {

            System.out.println("splitPromote: my parent is a 2-node - "+Integer.toString(parent.val));

            // coming from the left child.
            if (self == parent.lChild) {
                parent.val2 = parent.val;
                parent.val = mVal;

                // if leaf node.
                if (newL == null) {
                    parent.lChild = new Node(parent, lVal);
                    parent.mChild = new Node(parent, rVal);
                    // parent.rChild stays the same.
                }
                // if internal node.
                else {
                    parent.lChild = newL;
                    parent.mChild = newR;
                    // parent.rChild stays the same.
                }

            }

            // coming from the right child.
            else {
                parent.val2 = mVal;

                // if leaf node.
                if (newL == null) {
                    // parent.lChild stays the same.
                    parent.mChild = new Node(parent, lVal);
                    parent.rChild = new Node(parent, rVal);
                }
                // if internal node.
                else {
                    // parent.lChild stays the same.
                    parent.mChild = newL;
                    parent.rChild = newR;
                }
            }
        }

        // split a 3-node and promote to a 3-node parent by splitting and assigning children.
        else {
            Node splitL;
            Node splitR;

            System.out.println("splitPromote: my parent is a 3-node - "+Integer.toString(parent.val));

            // if leaf node.
            if (newL == null) {

                // coming from the left child.
                if (self == parent.lChild) {
                    splitL = new Node(parent.parent, mVal, new Node(self, lVal), new Node(self, rVal));
                    splitR = new Node(parent.parent, parent.val2, parent.mChild, parent.rChild);
                    if (parent.parent != null) {
                        splitPromote(parent.parent, parent, mVal, parent.val, parent.val2, splitL, splitR);
                    }
                    else {
                        splitPromote(null, parent, mVal, parent.val, parent.val2, splitL, splitR);

                    }
                }
                // coming from the middle child.
                else if (self == parent.mChild) {
                    splitL = new Node(parent.parent, parent.val, parent.lChild, new Node(self, lVal));
                    splitR = new Node(parent.parent, parent.val2, new Node(self, rVal), parent.rChild);
                    if (parent.parent != null) {
                        splitPromote(parent.parent, parent, parent.val, mVal, parent.val2, splitL, splitR);

                    }
                    else {
                        splitPromote(null, parent, parent.val, mVal, parent.val2, splitL, splitR);
                    }
                }
                // coming from the right child.
                else {
                    splitL = new Node(parent.parent, parent.val, parent.lChild, parent.mChild);
                    splitR = new Node(parent.parent, mVal, new Node(self, lVal), new Node(self, rVal));
                    if (parent.parent != null) {
                        splitPromote(parent.parent, parent, parent.val, parent.val2, mVal, splitL, splitR);
                    }
                    else {
                        splitPromote(null, parent, parent.val, parent.val2, mVal, splitL, splitR);

                    }
                }
            }

            // if internal node.
            else {

                // coming from the left child.
                if (self == parent.lChild) {
                    splitL = new Node(parent.parent, mVal, newL, newR);
                    splitR = new Node(parent.parent, parent.val2, parent.mChild, parent.rChild);
                    if (parent.parent != null) {
                        splitPromote(parent.parent, parent, mVal, parent.val, parent.val2, splitL, splitR);
                    }
                    else {
                        splitPromote(null, parent, mVal, parent.val, parent.val2, splitL, splitR);
                    }

                }
                // coming from the middle child.
                else if (self == parent.mChild) {
                    splitL = new Node(parent.parent, parent.val, parent.lChild, newL);
                    splitR = new Node(parent.parent, parent.val2, newR, parent.rChild);
                    if (parent.parent != null) {
                        splitPromote(parent.parent, self, parent.val, mVal, parent.val2, splitL, splitR);
                    }
                    else {
                        splitPromote(null, self, parent.val, mVal, parent.val2, splitL, splitR);
                    }
                }
                // coming from the right child.
                else {
                    splitL = new Node(parent.parent, parent.val, parent.lChild, parent.mChild);
                    splitR = new Node(parent.parent, mVal, newL, newR);
                    if (parent.parent != null) {
                        splitPromote(parent.parent, self, parent.val, parent.val2, mVal, splitL, splitR);

                    }
                    else {
                        splitPromote(null, self, parent.val, parent.val2, mVal, splitL, splitR);

                    }
                }
            }
        }
    }

    // find a value in the Tree.
    public void find(int i) {
        compareFind (i, root);
    }

    public void compareFind(int i, Node n) {

        // node value is less than the target's left value, check left child.
        if (i < n.val) {
            comparisons += 1;
            if (n.lChild == null) {
                System.out.println("Error, Target not in tree: "+Integer.toString(i));
            }
            else {
                compareFind(i, n.lChild);
            }
        }

        // node value is greater than the target's left value.
        else if (i > n.val) {
            comparisons += 1;

            // if 3-node, compare against val2.
            if (n.val2 != -1) {

                // if node value is less than the target's right value, check middle child.
                if (i < n.val2) {
                    comparisons += 1;
                    if (n.mChild == null) {
                        System.out.println("Error, Target not in tree: "+Integer.toString(i));
                    }
                    else {
                        compareFind(i, n.mChild);
                    }
                }
                // if node value is more than the target's right value, check right child.
                else if (i > n.val2) {
                    comparisons += 1;
                    if (n.rChild == null) {
                        System.out.println("Error, Target not in tree: "+Integer.toString(i));
                    }
                    else {
                        compareFind(i, n.rChild);
                    }
                }
                // node value is equal to the target's right value.
                else {
                    System.out.println("Target Found: "+ Integer.toString(n.val2));
                }
            }

            // if 2-node, compares to the rightmost child.
            else {
                if (n.rChild == null) {
                    System.out.println("Error, Target not in tree: "+Integer.toString(i));
                }
                else {
                    compareFind(i, n.rChild);
                }
            }
        }

        // node value is equal to the target value.
        else {
            System.out.println("Target Found: "+ Integer.toString(n.val));
        }
    }

    // prints the pre-order traversal of the Tree.
    public void traversal(Node n) {
        System.out.print(Integer.toString(n.val)+" ");

        // if 2-node.
        if (n.val2 == -1) {
            if (n.lChild != null) {
                traversal(n.lChild);
                traversal(n.rChild);
            }
        }
        // if 3-node.
        else {
            System.out.print(Integer.toString(n.val2)+" ");
            if (n.lChild != null) {
                traversal(n.lChild);
                traversal(n.mChild);
                traversal(n.rChild);
            }
        }
    }
}

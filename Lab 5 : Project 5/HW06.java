/**
 * Di Luo
 * CS 231
 * Project 5
 * HW06.java
 */

public class HW06 {
    Node root;
    int size;

    public HW06(){

    }

    private void insert(int value, Node root) {
        if( value <= root.getContents() ){
            if( root.getLeft() == null ) {root.setLeft( new Node(value, null, null) );}
            else { insert( value, root.getLeft() ); }
        }
        else {
            if( root.getRight() == null ) {root.setRight( new Node(value, null, null) );}
            else { insert( value, root.getRight() ); }
        }
    }

    public void insert(int value) {
        if( this.root == null ) {this.root = new Node(value, null, null); this.size++; return;}
        this.insert(value, this.root); this.size++;
    }

    public void traverseOrder() {
    // this function should check for special cases and then call the other traverse
        if (this.root == null) {return;}
        else{
            this.traverseOrder(this.root);
        }
    }

    public void traverseOrder( Node root) {
    // this function should do a recursive in-order traversal.
        if (root == null) {return;}
        traverseOrder(root.getLeft());
        System.out.println(root.getContents());
        traverseOrder(root.getRight());
    }

    public void traversePre() {
    // this function should check for special cases and then call the other traverse
        if (this.root == null) {return;}
        else{
            this.traversePre(this.root);
        }   
    }

    public void traversePre( Node root) {
    // this function should do a recursive pre-order traversal.
        if (root == null) {return;}
        System.out.println(root.getContents());
        traversePre(root.getLeft());
        traversePre(root.getRight());
    }

    public void traverseReverseOrder() {
        // this function should check for special cases and then call the other traverse
            if (this.root == null) {return;}
            else{
                this.traverseReverseOrder(this.root);
            }
        }
    
    public void traverseReverseOrder( Node root) {
    // this function should do a recursive in-order traversal.
        if (root == null) {return;}
        traverseReverseOrder(root.getRight());
        System.out.println(root.getContents());
        traverseReverseOrder(root.getLeft());

    }

    private class Node {
        int bucket;
        Node left, right;

        public Node( int value, Node newLeft, Node newRight ) {
        this.bucket = value; this.left = newLeft;	this.right = newRight; }
        public Node getLeft() { return this.left; }
        public void setLeft(Node newLeft) { this.left = newLeft; }
        public Node getRight() { return this.right; }
        public void setRight( Node newRight ) { this.right = newRight; }
        public int getContents() { return this.bucket; }
    }

    public static void main(String[] argv) {
        HW06 bt = new HW06();
        bt.insert(10); bt.insert(20); bt.insert(30);
        bt.insert(5); bt.insert(7); bt.insert(2);
        // call the in order and pre-order traversal functions
        bt.traverseOrder();
        System.out.println("");
        bt.traversePre();
        System.out.println("");
        bt.traverseReverseOrder();
    }
}

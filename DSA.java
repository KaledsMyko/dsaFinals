package treenode;

// Myko Madrilejos, Kharl Repato, Christian Lexus Nace 

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

// Binary Tree Class
class BinaryTree {
    static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    TreeNode root;

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private TreeNode addRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else {
            node.right = addRecursive(node.right, value);
        }
        return node;
    }

    public java.util.List<Integer> inOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.value);
            inOrderRecursive(node.right, result);
        }
    }

    public java.util.List<Integer> preOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrderRecursive(node.left, result);
            preOrderRecursive(node.right, result);
        }
    }

    public java.util.List<Integer> postOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            postOrderRecursive(node.left, result);
            postOrderRecursive(node.right, result);
            result.add(node.value);
        }
    }
}

// Binary Search Tree (BST) Class
class BinarySearchTree {
    static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    TreeNode root;

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private TreeNode addRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        }
        return node;
    }

    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        return value < node.value
                ? searchRecursive(node.left, value)
                : searchRecursive(node.right, value);
    }

    public int findMin() {
        if (root == null) throw new IllegalStateException("BST is empty");
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public int findMax() {
        if (root == null) throw new IllegalStateException("BST is empty");
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public java.util.List<Integer> inOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.value);
            inOrderRecursive(node.right, result);
        }
    }
}

// Min-Heap Class
class MinHeap {
    private final PriorityQueue<Integer> heap;

    public MinHeap() {
        heap = new PriorityQueue<>();
    }

    public void add(int value) {
        heap.add(value);
    }

    public int remove() {
        return heap.poll();
    }

    public java.util.List<Integer> getHeapElements() {
        ArrayList<Integer> sortedList = new ArrayList<>(heap);
        sortedList.sort(Integer::compareTo);
        return sortedList;
    }
}

// GUI Class
public class DSA extends JFrame {
    private final BinaryTree binaryTree;
    private final BinarySearchTree binarySearchTree;
    private final MinHeap minHeap;
    private final JTextArea outputArea;

    public TreeGUI() {
        binaryTree = new BinaryTree();
        binarySearchTree = new BinarySearchTree();
        minHeap = new MinHeap();

        // GUI Setup
        setTitle("Binary Tree, Binary Search Tree, and Min-Heap Interactive System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JTextField inputField = new JTextField(10);
        JButton addTreeButton = new JButton("Add to Binary Tree");
        JButton addBSTButton = new JButton("Add to BST");
        JButton addHeapButton = new JButton("Add to Min-Heap");
        JButton showOrderButton = new JButton("Show Orders");
        JButton searchBSTButton = new JButton("Search BST");
        JButton minMaxBSTButton = new JButton("Min/Max in BST");
        inputPanel.add(new JLabel("Enter Value: "));
        inputPanel.add(inputField);
        inputPanel.add(addTreeButton);
        inputPanel.add(addBSTButton);
        inputPanel.add(addHeapButton);
        inputPanel.add(showOrderButton);
        inputPanel.add(searchBSTButton);
        inputPanel.add(minMaxBSTButton);

        // Output Area
        outputArea = new JTextArea(25, 70);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Adding Components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        addTreeButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                binaryTree.add(value);
                outputArea.append("Added " + value + " to Binary Tree\n");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        addBSTButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                binarySearchTree.add(value);
                outputArea.append("Added " + value + " to BST\n");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        addHeapButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                minHeap.add(value);
                outputArea.append("Added " + value + " to Min-Heap\n");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        showOrderButton.addActionListener(e -> {
            outputArea.append("\nBinary Tree (In-Order): " + binaryTree.inOrderTraversal() + "\n");
            outputArea.append("Binary Tree (Pre-Order): " + binaryTree.preOrderTraversal() + "\n");
            outputArea.append("Binary Tree (Post-Order): " + binaryTree.postOrderTraversal() + "\n");
            outputArea.append("BST (In-Order): " + binarySearchTree.inOrderTraversal() + "\n");
            outputArea.append("Min-Heap (Sorted Order): " + minHeap.getHeapElements() + "\n");
        });

        searchBSTButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                boolean found = binarySearchTree.search(value);
                outputArea.append("Search " + value + " in BST: " + (found ? "Found\n" : "Not Found\n"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        minMaxBSTButton.addActionListener(e -> {
            try {
                int min = binarySearchTree.findMin();
                int max = binarySearchTree.findMax();
                outputArea.append("BST Minimum: " + min + ", Maximum: " + max + "\n");
            } catch (IllegalStateException ex) {
                outputArea.append("BST is empty.\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TreeGUI gui = new TreeGUI();
            gui.setVisible(true);
        });
    }
}
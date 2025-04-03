package p4.Trees;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ChromaticTree_HarrisD implements Iterable<String> {

    private static class Node{
        String city;
        String color;
        Node left, right, parent;

        public Node(String city, String color) {
            this.city = city;
            this.color = color;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private Node root;

    // Constructor to build the tree from the input file
    public ChromaticTree_HarrisD(String inputFile) throws IOException {
        root = null;
        FileInputStream fileStream = new FileInputStream(inputFile);
        Scanner fileScnr = new Scanner(fileStream);

        while (fileScnr.hasNextLine()) {
            String line = fileScnr.nextLine();
            String[] parts = line.split(" ");
            String city = parts[0];
            String color = parts[1];
            String path = parts.length > 2 ? parts[2] : "";
            addNode(city, color, path);
        }
        fileStream.close();
    }

    // Helper method to add a node based on its path
    private void addNode(String city, String color, String path) {
        if (root == null) {
            root = new Node(city, color);
            return;
        }
        Node current = root;
        for (int i = 0; i < path.length() - 1; i++) {
            char direction = path.charAt(i);
            if (direction == 'L') {
                if (current.left == null) current.left = new Node(null, null);
                current = current.left;
            } else {
                if (current.right == null) current.right = new Node(null, null);
                current = current.right;
            }
        }
        Node newNode = new Node(city, color);
        newNode.parent = current;
        if (path.charAt(path.length() - 1) == 'L') {
            current.left = newNode;
        } else {
            current.right = newNode;
        }
    }

    // Method to count trichromatic groups
    public int countTriChromaticGroups() {
        return countTriChromaticGroups(root);
    }

    private int countTriChromaticGroups(Node node) {
        if (node == null || node.left == null || node.right == null) return 0;
        boolean isTrichromatic = !node.color.equals(node.left.color) &&
                !node.color.equals(node.right.color) &&
                !node.left.color.equals(node.right.color);
        int count = isTrichromatic ? 1 : 0;
        return count + countTriChromaticGroups(node.left) + countTriChromaticGroups(node.right);
    }

    // Method to get reverse elevator order traversal as a String
    public String getReverseElevatorOrder() {
        if (root == null) return "";
        Queue<Node> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            sb.append(curr.city);

            if (curr.right != null) queue.add(curr.right);
            if (curr.left != null) queue.add(curr.left);
        }



        return sb.toString().trim();
    }

    // Method to compute the height of the tree
    public int computeHeight() {
        return computeHeight(root);
    }

    private int computeHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(computeHeight(node.left), computeHeight(node.right));
    }

    private String getPathToRoot(Node node) {
        StringBuilder path = new StringBuilder();
        while (node != null) {
            path.insert(0, node.city + " ");
            node = node.parent;
        }
        return path.toString().trim();
    }

    // Method to find the first common city between two given cities
    public String findFirstCommonCity(String cityA, String cityB) {
        Node nodeA = findNode(root, cityA);
        Node nodeB = findNode(root, cityB);
        if (nodeA == null || nodeB == null) return null;
        Set<Node> pathA = new HashSet<>();
        while (nodeA != null) {
            pathA.add(nodeA);
            nodeA = nodeA.parent;
        }
        while (nodeB != null) {
            if (pathA.contains(nodeB)) return nodeB.city;
            nodeB = nodeB.parent;
        }
        return null;
    }

    private Node findNode(Node node, String city) {
        if (node == null) return null;
        if (node.city.equals(city)) return node;
        Node leftResult = findNode(node.left, city);
        if (leftResult != null) return leftResult;
        return findNode(node.right, city);
    }

    // Iterable implementation for in-order traversal
    @Override
    public Iterator<String> iterator() {
        List<String> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes.iterator();
    }

    private void inOrderTraversal(Node node, List<String> nodes) {
        if (node == null) return;
        inOrderTraversal(node.left, nodes);
        nodes.add(node.city + " " + node.color);
        inOrderTraversal(node.right, nodes);
    }
}

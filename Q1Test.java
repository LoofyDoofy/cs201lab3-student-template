public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     
      // what if two traversal same? like 1-2-3 inorder and preorder etc.
      public static String verify(String traversal1, String traversal2, String traversal3){
          String[] parts1 = traversal1.split("-");
          String[] parts2 = traversal2.split("-");
          String[] parts3 = traversal3.split("-");

          if (parts1.length != parts2.length || parts1.length != parts3.length) {
               return "Invalid traversals";
          }

          int[] nums1 = new int[parts1.length];
          int[] nums2 = new int[parts2.length];
          int[] nums3 = new int[parts3.length];

          for (int i = 0; i < parts1.length; i++) {
               nums1[i] = Integer.parseInt(parts1[i]);
          }

          for (int i = 0; i < parts2.length; i++) {
               nums2[i] = Integer.parseInt(parts2[i]);
          }

          for (int i = 0; i < parts3.length; i++) {
               nums3[i] = Integer.parseInt(parts3[i]);
          }

          if (!sameValues(nums1, nums2, nums3)) {
               return "Invalid traversals";
          }

          // Traversal 1 is the possible preorder
          if (matches(nums1, traversal1, traversal2, traversal3)) {
               return "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder";
          }

          if (matches(nums1, traversal1, traversal3, traversal2)) {
               return "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder";
          }

          // Traversal 2 is the possible preorder
          if (matches(nums2, traversal2, traversal1, traversal3)) {
                return "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder";
          }

          if (matches(nums2, traversal2, traversal3, traversal1)) {
               return "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder";
          }

          // Traversal 3 is the possible preorder
          if (matches(nums3, traversal3, traversal1, traversal2)) {
               return "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder";
          }

          if (matches(nums3, traversal3, traversal2, traversal1)) {
               return "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder";
          }

          return "Invalid traversals";
     }

     // check if the given traversal node values are the same
     public static boolean sameValues(int[] nums1, int[] nums2, int[] nums3) {
          int[] copy1 = nums1.clone();
          int[] copy2 = nums2.clone();
          int[] copy3 = nums3.clone();

          java.util.Arrays.sort(copy1);
          java.util.Arrays.sort(copy2);
          java.util.Arrays.sort(copy3);

          return java.util.Arrays.equals(copy1, copy2)
               && java.util.Arrays.equals(copy1, copy3);
     }

     static class Node {
          int value;
          Node left;
          Node right;

          Node(int value) {
               this.value = value;
          }
     }

     // BST insert method
     public static Node insert(Node root, int value) {
          if (root == null) {
               return new Node(value);
          }

          if (value < root.value) {
               root.left = insert(root.left, value);
          }
          else if (value > root.value) {
               root.right = insert(root.right, value);
          }

          return root;
     }

     // generate a inorder traversal based on bst
     public static String inorder(Node root) {
          if (root == null) {
               return "";
          }

          return inorder(root.left) + root.value + "-" + inorder(root.right);
     }

     // generate a preorder traversal based on bst
     public static String preorder(Node root) {
          if (root == null) {
               return "";
          }

          return root.value + "-" + preorder(root.left) + preorder(root.right);
     }

     // generate a postorder traversal based on bst
     public static String postorder(Node root) {
          if (root == null) {
               return "";
          }

          return postorder(root.left) + postorder(root.right) + root.value + "-";
     }

     // generate bst with possible preorder, and check if the new tree matches the different orders
     public static boolean matches(int[] possiblePreorder,
                              String expectedPreorder,
                              String expectedInorder,
                              String expectedPostorder) {
          Node root = null;

          for (int i = 0; i < possiblePreorder.length; i++) {
               root = insert(root, possiblePreorder[i]);
          }

          String pre = preorder(root);
          String in = inorder(root);
          String post = postorder(root);

          // Remove the final dash from each generated traversal
          pre = pre.substring(0, pre.length() - 1);
          in = in.substring(0, in.length() - 1);
          post = post.substring(0, post.length() - 1);

          return pre.equals(expectedPreorder)
               && in.equals(expectedInorder)
               && post.equals(expectedPostorder);
     }

}
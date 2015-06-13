package binaryTree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This is the main class of the logic of the BinaryTree app.
 * The methods implemented in this class are as follows:
 * 1. insert(Node node, int val)
 * 2. insertNoRec(Node node, int val)
 * 3. preorderTraverse(Node root)
 * 4. inorderTraverse(Node root)
 * 5. postorderTraverse(Node root)
 * 6. preorderTraverseDC(Node root)
 * 7. preorderTraverseNoRec(Node root)
 * 8. maxDepth(Node root)
 * 9. maxDepthDC(Node root)
 * 10. isHeightBalanced(Node root)
 * 11: isHeightBalanced2(Node root)
 * 12. maxPathSum(Node root)
 * 
 * @author Guru
 */
public class BinaryTree {
	
	Node root;
	
	public BinaryTree() {
		root = null;
	}
	
	/**
	 * 1. insert method can be used to build a binary tree
	 * @param node: a node to be inserted into the binary tree
	 * @param val: the value of the node to be inserted
	 */
	public void insert(Node node, int val) {
		if (root == null) {
			root = new Node(val); 
		} else {
			if (val < node.val) {
				if (node.left == null) {
					node.left = new Node(val);
				} else {
					insert(node.left, val);
				}
			} else {
				if (node.right == null) {
					node.right = new Node(val);
				} else {
					insert(node.right, val);
				}
			}
		}
	}
	
	/**
	 * 2. insert method's non-recursive version
	 * two pointers
	 */
	public void insertNoRec(Node node, int val) {
		Node newNode = new Node(val);
		if (root == null) {
			root = newNode; 
		} else {
			Node cur = root;
			Node parent;
			while (true) {
				parent = cur;
				if (val < cur.val) {
					cur = cur.left;
					if (cur == null) {
						parent.left = newNode;
						return;
					}
				} else {
					cur = cur.right;
					if (cur == null) {
						parent.right = newNode;
						return;
					}
				}
			} //while
		}//else root != null
	}
	
	/**
	 * 3. 
	 */
	public void preorderTraverse(Node root) {
		if (root != null) {
			System.out.print(" " + root.val + " ");
			preorderTraverse(root.left);
			preorderTraverse(root.right);
		}
	}
	
	/**
	 * 4. 
	 */
	public void inorderTraverse(Node root) {
		if (root != null) {
			inorderTraverse(root.left);
			System.out.print(" " + root.val + " ");
			inorderTraverse(root.right);
		}
	}
	
	/**
	 * 5. 
	 */
	public void postorderTraverse(Node root) {
		if (root != null) {
			postorderTraverse(root.left);
			postorderTraverse(root.right);
			System.out.print(" " + root.val + " ");
		}
	}
	
	/**
	 * 6. Use Divide and Conquer to solve preorder traversal
	 * @param root
	 */
	public void preorderTraverseDC(Node root) {
		ArrayList<Integer> ret = traverseDC(root);
		for (int element : ret) {
			System.out.print(" " + element + " ");
		}
	}
	private ArrayList<Integer> traverseDC(Node root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (root != null) {
			// Divide
			ArrayList<Integer> left = traverseDC(root.left);
			ArrayList<Integer> right = traverseDC(root.right);
			// Conquer
			ret.add(root.val);
			ret.addAll(left);
			ret.addAll(right);
		}
		return ret;
	}
	
	/**
	 * 7. The non-recursive version of preorderTraverse
	 */
	public void preorderTraverseNoRec(Node root) {
		Stack<Node> stack = new Stack<Node>();
		if (root != null) {
			stack.push(root);
			while (!stack.isEmpty()) {
				root = stack.pop();
				System.out.print(" " + root.val + " ");
				if (root.right != null) {
					stack.push(root.right);
				} 
				if (root.left != null) {
					stack.push(root.left);
				}
			}
		}
	}	
	
	/**
	 * 8. Traverse to find the maxDepth
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(Node root) {
        if (root == null) {
        	return 0;
        }
        int maxDep = findMaxDepth(root, 1);
        return maxDep;
    }
    private int findMaxDepth(Node root, int curDepth) {
    	int leftDepth = curDepth, rightDepth = curDepth;
    	if (root.left != null) {
    		leftDepth = findMaxDepth(root.left, curDepth + 1) ;
    	}
    	if (root.right != null) {
    		rightDepth = findMaxDepth(root.right, curDepth + 1);
    	}
    	//return (leftDepth > rightDepth) ? leftDepth : rightDepth;
    	return Math.max(leftDepth, rightDepth);
    }
    
    
    /**
	 * 9. Use D&C to find the maxDepth
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepthDC(Node root) {
    	if (root == null) {
    		return 0;
    	}
    	int left = maxDepthDC(root.left);
    	int right = maxDepthDC(root.right);
    	return Math.max(left, right) + 1;
    }
    
    /**
     * 10. http://www.lintcode.com/en/problem/balanced-binary-tree/
     * @param root
     * @return
     */
    public boolean isHeightBalanced(Node root) {
    	return maxDepthWithBalanceDetermine(root) != -1;
    }
    private int maxDepthWithBalanceDetermine(Node root) {
    	if (root == null) {
    		return 0;
    	}
    	int left = maxDepthWithBalanceDetermine(root.left);
    	int right = maxDepthWithBalanceDetermine(root.right);
    	if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
    		return -1;
    	}
    	return Math.max(left, right) + 1;
    }
    
    /**
     * 11. isHeightBalanced(Node root)
     * engineering coding style 
     */
    public boolean isHeightBalanced2(Node root) {
    	return heightBal(root).isBal;
    }
    private ResultType heightBal(Node root) {
    	if (root == null) {
    		return new ResultType(true, 0);
    	}
    	ResultType left = heightBal(root.left);
    	ResultType right = heightBal(root.right);
    	if (!left.isBal || !right.isBal || Math.abs(left.maxDepth - right.maxDepth) > 1) {
    		return new ResultType(false, Math.max(left.maxDepth, right.maxDepth) + 1);
    	}
    	return new ResultType(true, Math.max(left.maxDepth, right.maxDepth) + 1);
    }
    private class ResultType {
    	boolean isBal;
    	int maxDepth;
    	ResultType(boolean isBal, int maxDepth) {
    		this.isBal = isBal;
    		this.maxDepth = maxDepth;
    	}
    }
    
    /**
     * http://www.lintcode.com/en/problem/binary-tree-maximum-path-sum/
     * Assume the input binary tree may contain negative nodes. 
     * @param root: The root of binary tree.
     * @return: An integer.
     */
//    public int maxPathSum(Node root) {
//        
//    }
    
}

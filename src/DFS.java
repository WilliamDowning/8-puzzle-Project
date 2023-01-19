import java.util.*;
import java.util.Stack;

public class DFS
{

    public int movescounter = 0;
    public int[][] goal  = {{1,2,3}, {8,0,4}, {7,6,5}};
    public ArrayList<Node> close = new ArrayList<>();

    //Checks if the current node is not already found within the closed array. if not then add it on and continue
    public boolean checkClosed(Node curNode)
    {
        for (Node n : close)
        {
            if (curNode.isEqual(n) || curNode.getState() == n.getState())
            {
                return true;
            }
        }
        return false;
    }

    //Main functionality that searches the Nodes in an iterative fashion to create goal state
    public Node search(Node rootNode)
    {
        int moveCounter=0;
        Stack<Node> stack = new Stack<>();
        Node goalNode = new Node(this.goal);
        ArrayList<ArrayList<Integer>> options;
        stack.add(rootNode);




        while(stack.size() > 0)
        {
            Node currentNode = stack.pop();
            Node child;
            options = currentNode.getOptions();
            for(var opt : options)
            {
                child = currentNode.clone();
                child.swap(opt);

                if(checkClosed(child) == false)
                {
                    stack.push(child);
                    moveCounter++;
                    close.add(child);

                }


                if (child.isEqual(goalNode))
                {

                    System.out.println("States visited:" + moveCounter);
                    System.out.println();
                    return child;
                }
            }
        }
        movescounter = moveCounter;
        return null;
    }




}

import java.util.*;
import java.util.Stack;

public class UCS
{
    public int movecounters = 0;
    public int[][] goal  = {{1,2,3}, {8,0,4}, {7,6,5}};
    public ArrayList<Node> closed = new ArrayList<>();

    
    public boolean checkClosed(Node curNode)
    {
        for (Node n : closed)
        {
            if (curNode.isEqual(n) || curNode.getState() == n.getState())
            {
                return true;
            }
        }
        return false;
    }


    public Node search(Node rootNode)
    {
        int moveCounter = 0;
        ArrayList<Node> priorityQue = new ArrayList<>();
        Node goalNode = new Node(this.goal);
        ArrayList<ArrayList<Integer>> options;
        priorityQue.add(rootNode);


        while(priorityQue.size() > 0)
        {

            Node currentNode = priorityQue.remove(smallestCost(priorityQue));
            Node child;
            options = currentNode.getOptions();


            for(var x : options)
            {    
                child = currentNode.clone();
                child.swap(x);

                if(checkClosed(child) == false)
                {

                    priorityQue.add(child);
                    closed.add(child);
                    moveCounter++;
                }

                if (child.isEqual(goalNode))
                {

                    System.out.println("States visited:" + moveCounter);
                    System.out.println();
                    return child;
                }
            }
        }
        movecounters = moveCounter;
        return null;
    }

    //Checks to see based on every Node in the ArrayList that has the lowest gn
    public int smallestCost(ArrayList<Node> priorityQue){
        int smallest = 1000;
        int pos =-1;
        int index = 0;
        
        for(Node N : priorityQue){

           if(N.getGn() < smallest){

               smallest = N.getGn();
               pos = index;
           }
            index++;
        }

        return pos;
    }


    
}

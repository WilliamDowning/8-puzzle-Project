import java.util.*;
import java.util.Stack;

public class BFS
{
    int movecounters = 0;
    public int[][] goal  = {{1,2,3}, {8,0,4}, {7,6,5}};
    public ArrayList<Node> closed = new ArrayList<Node>();

    
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


            Node curNode = priorityQue.remove(smallestCost(priorityQue));
            Node child;
            options = curNode.getOptions();

            for(var opt : options)
            {

                child = curNode.clone();
                child.swap(opt);
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
    //finds the smallest value of the Arraylist that is hn
    public int smallestCost(ArrayList<Node> priorityQue){
        int smallest = 1000;
        int pos =-1;
        int index = 0;


        
        for(Node N : priorityQue){
           if(N.getHn() < smallest){

               smallest = N.getHn();
               pos = index;

           }
            index++;
        }

        return pos;
    }


    
}

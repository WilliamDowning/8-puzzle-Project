import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String [] args){
        CreatePuzzle npuzzle = new CreatePuzzle();

        //System.out.println(npuzzle.printArray());

        //int [] [] randarr = npuzzle.sendRandomArray();
        int [] [] randarr = {{0, 5, 2}, {6, 1, 8}, {7, 3, 4}};
        int [] [] goalarr = npuzzle.sendFinishedArray();
        initiateDFS(randarr);
        //npuzzle.resetNums();
//        runA(randarr);
//        runBFS(randarr);
//        runUCS(randarr);
    }


    public static void initiateDFS(int [] [] start){

        Node startnode = new Node(start);
        startnode.print();
        DFS dfs = new DFS();
        long initalTime = System.currentTimeMillis();

        Node testNode = dfs.search(startnode);
        long endTime = System.currentTimeMillis();


        if(testNode==null){

            System.out.println("failed");
            System.out.println("Time taken: " + (endTime - initalTime));
            System.out.println("Nodes Visited: " + dfs.movescounter);

        }else{
            System.out.println("succeeded\n");
            testNode.print();
            System.out.print("Time taken: " + (endTime - initalTime));
        }
    }

    public static void runUCS(int [] [] start)
    {
        Node startNode = new Node(start);
        startNode.print();

        UCS ucs = new UCS();
        long initalTime = System.currentTimeMillis();
        Node solvedNode = ucs.search(startNode);
        long endTime = System.currentTimeMillis();
        if (solvedNode==null)
        {
            System.out.println("Algorithm unable to solve puzzle.");
            System.out.println("Time taken: " + (endTime - initalTime));
            System.out.println("Nodes Visited: " + ucs.movecounters);
        }
        else
        {
            System.out.println("succeeded\n");
            solvedNode.print();
            System.out.print("Time taken: " + (endTime - initalTime));
        }
    }
    public static void runBFS(int [] [] start)
    {
        Node startNode = new Node(start);
        startNode.print();

        BFS bfs = new BFS();
        long initalTime = System.currentTimeMillis();
        Node solvedNode = bfs.search(startNode);
        long endTime = System.currentTimeMillis();
        if (solvedNode==null)
        {
            System.out.println("Algorithm unable to solve puzzle.");
            System.out.print("Time taken: " + (endTime - initalTime));
            System.out.print("Nodes Visited: " + bfs.movecounters);
        }
        else
        {
            System.out.println("succeeded\n");
            solvedNode.print();
            System.out.print("Time taken: " + (endTime - initalTime));
        }
    }

    public static void runA(int [] [] start)
    {
        Node startNode = new Node(start);
        startNode.print();

        A Astar = new A();
        long initalTime = System.currentTimeMillis();
        Node solvedNode = Astar.search(startNode);
        long endTime = System.currentTimeMillis();
        if (solvedNode==null)
        {
            System.out.println("Algorithm unable to solve puzzle.");
            System.out.print("Time taken: " + (endTime - initalTime));
            System.out.print("Nodes Visited: " + Astar.movescounter);
        }
        else
        {
            System.out.println("succeeded\n");
            solvedNode.print();
            System.out.print("Time taken: " + (endTime - initalTime));
        }
    }

}

import java.util.*;


public class Node {
    public int[][] finishedprod  = {{1,2,3}, {8,0,4}, {7,6,5}};

    public Node parent;
    private int[][] state;
    private int currX;
    private int currY;
    private int gn;
    private int hn;

    // First constructor to initialize Node with only the puzzle and parent information
    public Node(int[][] state, Node parent) {

        this.state = state;
        this.parent = parent;
        this.gn = parent.gn + 1;
        this.hn = this.calculate_ManhattanD(finishedprod) + this.calculate_Misplaced(finishedprod);

        // Searches for the empty (zero) array point to start
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (this.state[y][x] == 0) {

                    this.currX = x;
                    this.currY = y;
                }
            }
        }
    }

    //This constructor used when creating the first root node with only the start state
    public Node(int[][] state) {
        this.state = state;
        this.parent = null;
        this.gn = 0;
        this.hn = 0;

        // Once again searches to find blank spot
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (this.state[y][x] == 0) {

                    this.currX = x;
                    this.currY = y;
                }
            }
        }

    }

    //Constructor for Nodes that have their blank spot coordinates and Node parents
    public Node(int[][] state, int zeroX, int zeroY, Node parent) {
        this.state = state;
        this.parent = parent;
        this.currX = zeroX;
        this.currY = zeroY;

        //These two are reserved for UCS, BFS, A* as BFS uses hn, UCS uses gn, and A* is the combination of both
        this.gn = parent.gn + 1;
        this.hn = this.calculate_ManhattanD(finishedprod) + this.calculate_Misplaced(finishedprod);

    }

    //Grabs the current block at coordinates
    public int get(int x, int y) {
        return this.state[y][x];
    }
    //Sets the current x and y to specific value
    public void set(int x, int y, int value) {
        this.state[y][x] = value;
    }
    //Gets gn
    public int getGn() {
        return this.gn;
    }
    //Sets new gn based on their parent nodes value + 1
    public int setGn() {
        this.gn = parent.gn + 1;
        return this.gn;
    }
    public int[] [] getState(){
        return state;
    }

    //gets hn
    public int getHn(){
        return this.hn;
    }


    //checks if Node in question is the similar to current created Node
    public boolean isEqual(Node target) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (this.get(i, j) != target.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Performs same method above but creates the new Node first based off current state.
    public boolean isEqual(int[][] array) {
        Node tempNode = new Node(array);
        return this.isEqual(tempNode);
    }

    //Used to create a child Node process from parent and link it to past Node
    public Node clone() {

        int[][] stateCopy = new int[3][3];
        // deep copy
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                stateCopy[i][j] = this.state[i][j];
            }
        }
        return new Node(stateCopy, currX, currY, this);
    }

    //used to interchange Node variable values
    public void swap(int x, int y) {


        int value = this.get(x, y);
        this.set(x, y, 0);
        this.set(currX, currY, value);
        this.currX = x;
        this.currY = y;


    }

    //Used with Arraylist to swap Node values based on their location in the state
    public void swap(ArrayList<Integer> options) {
        this.swap(options.get(0), options.get(1));
    }


    //Explores options that current blank coordinate can explore without repetitions
    public ArrayList<ArrayList<Integer>> getOptions() {

        ArrayList<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> option;

        int[] incre = { -1, +1 };

        for (int  inc : incre) {

            int x = this.currX;
            int y = this.currY;
            int xd = x + inc;
            int yd = y + inc;

            //Iterates either up or down the x coordinate as long in between 0 and 2
            if (xd >= 0 && xd <= 2) {
                option = new ArrayList<Integer>(Arrays.asList(xd, y));
                options.add(option);
            }
            //Iterates either up or down the y coordinate as long in between 0 and 2
            if (yd >= 0 && yd <= 2) {
                option = new ArrayList<Integer>(Arrays.asList(x, yd));
                options.add(option);
            }
        }
        return options;
    }

    //Returns how many blocks are not in their goal spots
    public int calculate_Misplaced(int goal[][]){
        int count = 0;
        for(int i = 0; i < 0; i++){
            for(int j =0; j < 0; j++){
                if(this.state[i][j] != goal[i][j]){
                    count ++;
                }
            }
        }
        return count;
    }
    //Calculates the  distance of the specific block to its designated goal spot
    public int calculate_ManhattanD(int goal[][]){

        HashMap<Integer,Integer> rowMap = new HashMap<Integer, Integer>();
        HashMap<Integer,Integer> colMap = new HashMap<Integer, Integer>();


        for(int r = 0; r <3; r++){
            for(int c = 0; c< 3; c++){
                rowMap.put(goal[r][c],r);
                colMap.put(goal[r][c],c);
            }
        }

        int manhattanDist =0;

        for(int r = 0; r <3; r++){
            for(int c = 0; c< 3; c++){
                if(this.state[r][c] != 0){
                    int val = this.state[r][c];
                    int correctRow = rowMap.get(val);
                    int correctCol = colMap.get(val);

                    manhattanDist += Math.abs(correctRow -r) + Math.abs(correctCol -c);
                }
            }
        }

        return manhattanDist;


    }

    //Prints the entire state and shows where the blank coordinate
    public void print() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(this.get(x, y));
            }
            if (y == 0) {
                System.out.print("    blank coordinate is: ");
                System.out.print(this.currX);
                System.out.print(",");
                System.out.print(this.currY);
            }
            System.out.println();
        }
        System.out.println();
    }

}
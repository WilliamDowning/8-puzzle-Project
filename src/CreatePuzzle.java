import java.util.Arrays;

public class CreatePuzzle
{
    private int[][] finishedarray = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
    };
    private int [] [] startarray = new int[3][3];
    int[] usednums = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int index = 0;
    public CreatePuzzle(){
        buildArray();
    }

    private int initializeRandomVariable(){
        double randomint = Math.random()*(100 + 1);
        int finalint = (int) randomint % 9;
        return finalint;

    }

    public void resetNums(){
        for(int i = 0; i < usednums.length; i++){
            usednums[i] = -1;
        }
    }

    private void buildArray(){
        boolean isIn = false;
        while(true) {
            int newvar = initializeRandomVariable();
            for (int x : usednums) {
                if (x == newvar) {
                    isIn = true;
                    break;
                }
            }
            if(isIn != true){
                usednums[index] = newvar;
                index++;
            }
            if (index >= 9) {
                break;
            }
            isIn = false;
        }
        index = 0;
        int secindex = 0;
        for(int y : usednums){
            startarray[index][secindex] = y;
            secindex++;
            if(secindex >= 3){
                secindex = 0;
                index++;
            }
        }
    }

    public String printArray(){
        return Arrays.deepToString(startarray) + "\n" + Arrays.deepToString(finishedarray);
        //return Arrays.toString(usednums);
    }

    public int[][] sendRandomArray() {
        return this.startarray;
    }

    public int[][] sendFinishedArray(){
        return this.finishedarray;
    }


}

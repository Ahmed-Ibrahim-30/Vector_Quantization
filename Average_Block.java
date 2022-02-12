import java.util.ArrayList;

public class Average_Block {
    private final double[][] Block;
    ArrayList<Vector> Blocks_one = new ArrayList<>();
    ArrayList<Vector> Blocks_two = new ArrayList<>();
    ArrayList<Vector> Final      = new ArrayList<>();
    private final int [][] High; //High
    private final int [][] Low ; //Low
    Average_Block(double[][]b,int [][] high,int [][] low){
        Block=b;
        High=high;
        Low=low;
    }

    public double[][] getBlock() {
        return Block;
    }

    public int[][] getHigh() {
        return High;
    }

    public int[][] getLow() {
        return Low;
    }

    public void setBlocks_one(Vector blocks_one) {
        Blocks_one.add(blocks_one);
    }

    public void setBlocks_two(Vector blocks_two) {
        Blocks_two.add(blocks_two);
    }

    public ArrayList<Vector> getBlocks_one() {
        return Blocks_one;
    }

    public ArrayList<Vector> getBlocks_two() {
        return Blocks_two;
    }

    public void setFinal(Vector blocks_one) { Final.add(blocks_one) ;}

    public ArrayList<Vector> getFinal() { return Final; }
}

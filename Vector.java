import java.util.ArrayList;

public class Vector{
    private int x;
    private int y;
    private int[][] Pixels;
    ArrayList<Difference> difference=new ArrayList<>();
    public void setVector_Size(int Width,int Height){x=Width; y=Height;}
    public int getWidth(){return x;}
    public int getHeight(){return y;}
    public void setPixels(int[][] pixels) { Pixels = pixels; }
    public int[][] getPixels() { return Pixels; }

    public void Saved(int diff,int blockName,int index) {
        Difference difference1=new Difference();
        difference1.difference=diff;
        difference1.BlockName=blockName;
        difference1.index=index;
        difference.add(difference1);
    }
    public Difference get_TrueAverage(){
        int lower=difference.get(0).difference;
        Difference difference1=difference.get(0);
        for (Difference diff:difference){
            if (lower>diff.difference){
                lower=diff.difference;
                difference1=diff;
            }
        }
        return  difference1;
    }
}
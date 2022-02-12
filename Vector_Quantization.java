import java.util.ArrayList;

public class Vector_Quantization {
    private Image_details image_details = new Image_details();
    int Vector_height;
    int Vector_width;
    private int Blocks_Number = 0;
    private int CodeBook_Size;//// عددهم
    ArrayList<Vector> Blocks = new ArrayList<>();
    ArrayList<int[][]>Compress_Block=new ArrayList<>();

    double[][] Block_Average;
    ArrayList<Average_Block> FinalBlock = new ArrayList<>();

    public ArrayList<Average_Block> getFinalBlock() {
        return FinalBlock;
    }

    public void setImage_details(Image_details image_details) {
        this.image_details = image_details;
        Vector_width = image_details.getVector_Size().getWidth();
        Vector_height = image_details.getVector_Size().getHeight();
    }

    public Image_details getImage_details() {
        return image_details;
    }

    public int getBlocks_Number() {
        return Blocks_Number;
    }

    public void setCodeBook_Size(int codeBook_Size) {
        CodeBook_Size = codeBook_Size;
    }

    public int getCodeBook_Size() {
        return CodeBook_Size;
    }
    /*
                  1  3  4  10 4  4
                  2  4  9  10 3  5
                  7  6  15 20 17 18
                  9  6  14 18 16 18
                  4  12 9  8  1  5
                  11 12 9  8  4  6
     */

    public ArrayList<Vector> getBlocks() {
        int Image_height = image_details.getImage_Pixel().getHeight();//6
        int Image_width = image_details.getImage_Pixel().getWidth();//6
        int[][] Block = new int[Vector_width][Vector_height];
        int StartWidth = 0, StartHeight = 0, w = 0, h = 0, comp_i = Vector_width, count = 0;
        for (int i = StartWidth; i < comp_i; i++) {//row
            int comp_g = StartHeight + Vector_height;
            for (int g = StartHeight; g < comp_g; g++) {
                if (i >= Image_width || g >= Image_height) {
                    Block[w][h] = 0;
                } else Block[w][h] = image_details.getImage_Pixel().getPixels()[i][g];
                if (w == Vector_width - 1 && h == Vector_height - 1 && StartHeight != Image_height) {
                    StartHeight = g + 1;
                    if (StartHeight >= Image_height) {
                        StartHeight = 0;
                        comp_i = comp_i + Vector_width;
                    } else i = i - Vector_width;
                    Vector vector = new Vector();
                    vector.setPixels(Block);
                    Block = new int[Vector_width][Vector_height];
                    vector.setVector_Size(Vector_width, Vector_height);
                    Blocks.add(vector);
                    if (comp_i > Image_width + 1 && i >= Image_width - 1) i = comp_i; //to End Function
                }
                h++;
            }
            h = 0;
            //if (w==0)w=1;else w=0;
            if (w < Vector_width - 1) w++;
            else w = 0;
        }
        //print_vector(Blocks);
        Blocks_Number = Blocks.size();
        return Blocks;
    }

    public double[][] getBlock_Average(ArrayList<Vector> Blocks) {
        //System.out.println("//////////////////ahmed/////////////////////////");
        Block_Average = new double[Vector_width][Vector_height];
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                for (Vector block : Blocks) {
                    Block_Average[i][g] += block.getPixels()[i][g];
                }
                Block_Average[i][g] /= Blocks.size();
                Block_Average[i][g] = (Math.round(Block_Average[i][g] * 10)) / 10.0;
            }
        }
//        for (int i = 0; i < Vector_width; i++) {
//            for (int g = 0; g < Vector_height; g++) {
//                System.out.println(Block_Average[i][g]);
//            }
//        }
//        System.out.println("//////////////");
        return Block_Average;
    }
    public int[][] getHigherAverage(double[][] Average) {
        int index1=0,index2=0;
        int[][] HigherAverage = new int[Vector_width][Vector_height];
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                HigherAverage[i][g]=(int) (Math.round(Average[i][g]+0.5));
            }
        }
        return HigherAverage;
    }
    public int[][] getLowerAverage(double[][] Average){
        int index1=0,index2=0;
        int[][] LowerAverage = new int[Vector_width][Vector_height];
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                LowerAverage[i][g]=(int) (Math.abs(Average[i][g]));
                //System.out.println(LowerAverage[i][g]);
            }
        }
        return LowerAverage;
    }
    public int getDifference(int[][]average, int[][]block){
        int difference=0;
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                difference+=Math.pow(average[i][g]-block[i][g],2);
                //difference+=((average[i][g]-block[i][g])*(average[i][g]-block[i][g]));
            }
        }
        return difference;
    }

    public int getDifference1(double[][]average, int[][]block){
        int difference=0;
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                difference+=Math.pow(average[i][g]-block[i][g],2);
                //difference+=((average[i][g]-block[i][g])*(average[i][g]-block[i][g]));
            }
        }
        return difference;
    }

    public void print_vector(ArrayList<Vector>blocks){
        //System.out.println("/////////////////////////////////////");
        for (Vector vector:blocks){
            for (int i = 0; i < Vector_width; i++) {
                for (int g = 0; g < Vector_height; g++) {
                    System.out.print(vector.getPixels()[i][g]+"  ");
                }
                System.out.println();
            }
            System.out.println("///////////////////////////////////////");
        }
    }
    public void print_2dArray_double(double[][]a){
        //System.out.println("/////////////////////////////////////");
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                System.out.print(a[i][g]+"  ");
            }
            System.out.println();
        }
        System.out.println("///////////////////////////////////////");
    }
    public void print_2dArray_int(int[][]a){
        //System.out.println("/////////////////////////////////////");
        for (int i = 0; i < Vector_width; i++) {
            for (int g = 0; g < Vector_height; g++) {
                System.out.print(a[i][g]+"  ");
            }
            System.out.println();
        }
        System.out.println("///////////////////////////////////////");
    }
    public void Print_Compressed_Blocks(ArrayList<int[][]>compress){
        for (int[][]c:compress){
            print_2dArray_int(c);
        }
    }
    public boolean Compare_Final_Blocks(ArrayList<Average_Block>one,ArrayList<Average_Block>two){
        if (one.size()==getCodeBook_Size() && two.size()==getCodeBook_Size()){
            for (int i=0;i<one.size();i++){
                for (int w = 0; w < Vector_width; w++) {
                    for (int g = 0; g < Vector_height; g++) {
                        if (one.get(i).getBlock()[w][g]!=two.get(i).getBlock()[w][g])return false;
                    }
                }
            }
        }
        return true;
    }

    public void Splitting(double[][] Average) {
        ArrayList<Average_Block>Average_List=new ArrayList<>();
        int [][]high=getHigherAverage(Average); //High
        int [][]low=getLowerAverage(Average); //Low
        Average_Block average=new Average_Block(Average,high,low);
        int check=0,size=0,Smallest=0,i_copy=0,count=0;

        Average_List.add(average);
        while (true){
            for (Vector Block : Blocks) {
                if (Average_List.size()==getCodeBook_Size()){
                    Smallest=getDifference1(Average_List.get(0).getBlock(), Block.getPixels());
                    i_copy=0;
                    Block.Saved(Smallest,0,0);
                }
                for (int i=0;i<Average_List.size();i++) { //0..1
                    if(Average_List.size()!=getCodeBook_Size())
                    {
                        if (getDifference(Average_List.get(i).getHigh(), Block.getPixels()) <= getDifference(Average_List.get(i).getLow(), Block.getPixels())) {
                            Block.Saved(getDifference(Average_List.get(i).getHigh(), Block.getPixels()),1,i);
                        }
                        else {
                            Block.Saved(getDifference(Average_List.get(i).getLow(), Block.getPixels()),2,i);
                        }
                    }
                    else{
                        if (Smallest>getDifference1(Average_List.get(i).getBlock(), Block.getPixels())){
                            Smallest=getDifference1(Average_List.get(i).getBlock(), Block.getPixels());
                            i_copy=i;
                        }
                    }
                }
                int index= Block.get_TrueAverage().index;;

                if (Block.get_TrueAverage().BlockName==1 ){//high
                    Average_List.get(index).setBlocks_one(Block);
                }
                else if (Block.get_TrueAverage().BlockName==2 ){//low
                    Average_List.get(index).setBlocks_two(Block);
                }
                else {
                    Average_List.get(i_copy).setFinal(Block);
                }
                Block.difference=new ArrayList<>();

            }
            size=Average_List.size();

            FinalBlock =new ArrayList<>(Average_List);
            ArrayList<Average_Block>Average_lost_copy=new ArrayList<>(Average_List);
            Average_List=new ArrayList<>();
            while (check<getCodeBook_Size()/2 && check<size && size*2<getCodeBook_Size()){//2
                high=getHigherAverage(getBlock_Average(Average_lost_copy.get(check).getBlocks_one()));
                low=getLowerAverage(getBlock_Average(Average_lost_copy.get(check).getBlocks_one()));
                average=new Average_Block(getBlock_Average(Average_lost_copy.get(check).getBlocks_one()),high,low);
                Average_List.add(average);

                high=getHigherAverage(getBlock_Average(Average_lost_copy.get(check).getBlocks_two()));
                low=getLowerAverage(getBlock_Average(Average_lost_copy.get(check).getBlocks_two()));
                average=new Average_Block(getBlock_Average(Average_lost_copy.get(check).getBlocks_two()),high,low);
                Average_List.add(average);
                check++;
            }


            while ((size*2==getCodeBook_Size()) && check<size){
                average=new Average_Block(getBlock_Average(Average_lost_copy.get(check).getBlocks_one()),null,null);
                Average_List.add(average);
                average=new Average_Block(getBlock_Average(Average_lost_copy.get(check).getBlocks_two()),null,null);
                Average_List.add(average);
                check++;
            }
            while (size==getCodeBook_Size() &&check<size){
                average=new Average_Block(getBlock_Average(Average_lost_copy.get(check).getFinal()),null,null);
                Average_List.add(average);
                check++;
            }
            check=0;
            count++;
           if (size==getCodeBook_Size() &&Compare_Final_Blocks(Average_List,Average_lost_copy))break;

        }
        //return Blocks_one;
    }
    public int[][] ConvertBlocktotint(double[][] block){
        int [][]cast = new int[Vector_width][Vector_height];
        for (int i = 0; i < Vector_width ; i++) {
            for (int g = 0; g < Vector_height; g++) {
                cast[i][g]=(int)block[i][g];
            }
        }
        return cast;
    }


    public ArrayList<int[][]> Compress(){
        ArrayList<int[][]>Compress_Blocks=new ArrayList<>();
        for (Vector vector:Blocks){
            for (int i=0;i<getFinalBlock().size();i++){
                for (int g=0;g<getFinalBlock().get(i).getFinal().size();g++){
                    if (vector==getFinalBlock().get(i).Final.get(g)){
                        Compress_Blocks.add(ConvertBlocktotint(getFinalBlock().get(i).getBlock()));
                    }
                }
            }
        }
        return Compress_Blocks;
    }
    public int[][] getCompressPixels(){
        int[][]CompressPixels=new int[image_details.getImage_Pixel().getWidth()][image_details.getImage_Pixel().getHeight()];
        Compress_Block=Compress();
        int width=0,height=0,Width_First=0,Height_First=0;
        for (int[][]block:Compress_Block){
            for (int i=0;i<Vector_width;i++){//width
                for (int g=0;g<Vector_height;g++){//height
                    CompressPixels[width][height++]=block[i][g];
                    if (g+1==Vector_height && i+1!=Vector_width)height=Height_First;
                }
                width++;
            }
            if (height!=image_details.getImage_Pixel().getHeight()){
                width=Width_First;
                Height_First=height;
            }
            else {
                Width_First=width;
                height=0;
                Height_First=0;
            }
        }
        return CompressPixels;
    }
    public void Print_Compress_Pixels(){
        int[][]compress=getCompressPixels();
        for (int i = 0; i < image_details.getImage_Pixel().getWidth(); i++) {
            for (int g = 0; g < image_details.getImage_Pixel().getHeight(); g++) {
                System.out.print(compress[i][g]+"  ");
            }
            System.out.println();
        }
        System.out.println("///////////////////////////////////////");
    }

}

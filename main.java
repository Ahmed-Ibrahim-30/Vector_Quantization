import java.util.Scanner;

public class main{
    public static void main(String[]args) {
        int[][] w =getImagePixel.readImage("E:\\الكليه\\Third Level\\Information Theory and Data Compression\\enviroment.jpg");
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter Vector Width and height?");
        int vectorWidth=input.nextInt();
        int vectorHeight=input.nextInt();
        System.out.println("Enter CodeBook Size?");
        int CodeBookSize=input.nextInt();
        Image_details image_details=new Image_details();
        image_details.setImageVector(getImagePixel.Width,getImagePixel.height,w);
        image_details.setVector_Size(vectorWidth,vectorHeight);
        Vector_Quantization vectorQuantization =new Vector_Quantization();
        vectorQuantization.setImage_details(image_details);
        vectorQuantization.setCodeBook_Size(CodeBookSize);
        double[][]Average=vectorQuantization.getBlock_Average(vectorQuantization.getBlocks());
        vectorQuantization.Splitting(Average);
        getImagePixel.writeImage(vectorQuantization.getCompressPixels(),getImagePixel.Width,getImagePixel.height,"E:\\الكليه\\Third Level\\Information Theory and Data Compression\\bear2.jpg");
    }

}
import java.util.ArrayList;

public class Image_details {
    private Vector Image_Pixel;
    private Vector Vector_Size;
    public int BlocksCount(){
        return (Image_Pixel.getWidth()*Image_Pixel.getHeight())/(Vector_Size.getWidth()*Vector_Size.getHeight());
    }

    public void setImageVector(int x,int y,int[][] image_Pixel) {
        Image_Pixel=new Vector();
        Image_Pixel.setVector_Size(x,y);
        Image_Pixel.setPixels(image_Pixel);
    }
    public void setVector_Size(int x,int y) {
        Vector_Size=new Vector();
        Vector_Size.setVector_Size(x,y);
    }

    public Vector getImage_Pixel() { return Image_Pixel; } //return (Width,Height,2D Array)

    public Vector getVector_Size() { return Vector_Size; }

    public void print(){
        for (int i=0;i<Image_Pixel.getWidth();i++){
            System.out.print("[");
            for (int g=0;g<Image_Pixel.getHeight();g++){
                System.out.print(Image_Pixel.getPixels()[i][g]+" ");
            }
            System.out.println("]");
        }
    }
}

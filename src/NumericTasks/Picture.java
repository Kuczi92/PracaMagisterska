/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.CvSVM;

/**
 *
 * @author Quchi
 */
public final class Picture   {
    
    //////////////////////////// VARIABLES /////////////////////////////////////
    public double stosunekDługościOryginału = 1.0;
    public double ProcentXWzględemOryginału = 1.0;
    public double ProcentYWzględemOryginału = 1.0;
    
    public ArrayList<MarkedRect> MarkedRect = new ArrayList<>();
    /** Store the Image reference */
    private  BufferedImage image;
    
    /** Store the image width and height */
    private int width, height;
    
    /** Pixels value - BGR  */
    private int pixels[];
    
    /** Total number of pixel in an image*/
    private int totalPixels;
  

    public Picture(int[] pixels, int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        this.width = width;
        this.height = height;
        this.totalPixels = this.width * this.height;
        this.pixels = pixels;
        setPixelArray(pixels);
    }

    public Picture(){
        
    }
    
    
    /** 
     * Image type example: jpg|png 
     * JPG does not support alpha (transparency is lost) while PNG supports alpha.
     * @return 
     */
    
    
   public BufferedImage Image(){
       return image;
   }
    
    
  
    
    ////////////////////////////////// CONSTRUCTORS ////////////////////////////
    
    /** Default constructor
     * @param image Buffered image obraz*/
    public Picture(BufferedImage image){
        
        
       
        if(!(image.getType()==BufferedImage.TYPE_3BYTE_BGR)){
             BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
             convertedImg.getGraphics().drawImage(image, 0, 0, null);
             this.image=convertedImg;
             this.width = convertedImg.getWidth();
             this.height = convertedImg.getHeight();
             this.totalPixels = this.width * this.height;
             this.pixels = new int[this.totalPixels];
             initPixelArray();
       
        }
        else{
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.totalPixels = this.width * this.height;
        this.pixels = new int[this.totalPixels];
        this.image=image;
        initPixelArray();
        }
        
    }
    
    public Picture(String filePath){
       readImage(filePath);
    }
    
    
    /** 
     * Constructor to create a new image object
     * 
     * @param width width of the image passed by the user
     * @param height height of the image passed by the user
     */
    public Picture(int width, int height){
        
        this.width = width;
        this.height = height;
        this.totalPixels = this.width * this.height;
        this.pixels = new int[this.totalPixels];
        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_3BYTE_BGR);
        initPixelArray();
    }
    
    /** 
     * Constructor to create a copy of a previously created image object.
     * 
     * @param img The image object whose copy is created.
     */
    public Picture(Picture img){
        this.width = img.getImageWidth();
        this.height = img.getImageHeight();
        this.totalPixels = this.width * this.height;
        this.pixels = new int[this.totalPixels];
        
     
        //copy original image pixels value to new image and pixels array
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                this.image.setRGB(x, y, img.getPixel(x, y));
                this.pixels[x+y*this.width] = img.getPixel(x, y);
            }
        }
    }
    
    /////////////////////////////////////// METHODS ////////////////////////////
    
    /**
     * This method will modify the image object.
     * 
     * @param width The width of the new image.
     * @param height The height of the new image.
     * @param bi The new image that will replace the old image.
     */
    public void modifyImageObject(int width, int height, BufferedImage bi){
        this.width = width;
        this.height = height;
        this.totalPixels = this.width * this.height;
        this.pixels = new int[this.totalPixels];
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = this.image.createGraphics();
        g2d.drawImage(bi, 0, 0, null);
        g2d.dispose();
        initPixelArray();
    }
    
    
    
    
    
    public void SetPicture(int[] pixels, int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        this.width = width;
        this.height = height;
        this.totalPixels = this.width * this.height;
        this.pixels = pixels;
        setPixelArray(pixels);
    }
    /** 
     * Read the image using the image file path passed
     * 
     * @param filePath the path of the image file
     * Example filePath = "D:\\LogoJava.jpg"
     */
    public void readImage(String filePath){
        try{
            
           
            image = ImageIO.read(Files.newInputStream(Paths.get(filePath)));
            String fileType = filePath.substring(filePath.lastIndexOf('.')+1);
            if("jpg".equals(fileType)){
                
            }
            else
            {
            BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
	    newBufferedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            setImage(newBufferedImage);     
            }
           
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.totalPixels = this.width * this.height;
            this.pixels = new int[this.totalPixels];
            initPixelArray();
        }catch(IOException e){
            System.out.println("Error Occurred!\n"+e);
        }
    }
    
    /**
     * Write the content of the BufferedImage object 'image' to a file
     * 
     * @param filePath complete file path of the output image file to be created
     * Example filePath = "D:\\Output.jpg"
     */
    public void writeImage(String filePath){
        try{
            File f = new File(filePath);
            String fileType = filePath.substring(filePath.lastIndexOf('.')+1);
            ImageIO.write(image, fileType, f);
        }catch(IOException e){
            System.out.println("Error Occurred!\n"+e);
        }
    }
    public BufferedImage DetectEdges(double threshold){
        
        Mat grayImage = new Mat();
        Mat Image = new Mat(height, width, CvType.CV_8UC3);
        BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        convertedImg.getGraphics().drawImage(image, 0, 0, null);
        Image.put(0, 0, ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData());
        Imgproc.cvtColor(Image, grayImage, Imgproc.COLOR_BGR2GRAY);
        
        
        Imgproc.Canny(grayImage, grayImage, threshold, threshold*3, 3, false);
       
        
        
        return matToBufferedImage(grayImage,new BufferedImage(image.getWidth(),image.getHeight(),image.getType()));
    }
    /**
     * Initialize the pixel array
     * Image origin is at coordinate (0,0)
     * (0,0)--------> X-axis
     *     |
     *     |
     *     |
     *     v
     *   Y-axis
     * 
     * This method will store the value of each pixels of a 2D image in a 1D array.
     */
    private void initPixelArray(){
        PixelGrabber pg = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
        try{
            pg.grabPixels();
        }catch(InterruptedException e){
            System.out.println("Error Occurred: "+e);
        }
    }
    public void setImage(BufferedImage image){
        
        if(!(image.getType()==BufferedImage.TYPE_3BYTE_BGR)){
             BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
             convertedImg.getGraphics().drawImage(image, 0, 0, null);
             this.image=convertedImg;
             this.width = convertedImg.getWidth();
             this.height = convertedImg.getHeight();
             this.totalPixels = this.width * this.height;
             this.pixels = new int[this.totalPixels];
             initPixelArray();
       
        }
        else{
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.totalPixels = this.width * this.height;
        this.pixels = new int[this.totalPixels];
        this.image=image;
        initPixelArray();
        }
        
        
    }
    /**
     * This method will check for equality of two images.
     * If we have two image img1 and img2
     * Then calling img1.isEqual(img2) will return TRUE if img1 and img2 are equal
     * else it will return FALSE.
     * 
     * @param img The image to check with.
     * @return TRUE if two images are equal else FALSE.
     */
    public boolean isEqual(Picture img){
        //check dimension
        if(this.width != img.getImageWidth() || this.height != img.getImageHeight()){
            return false;
        }
        
        //check pixel values
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                if(this.pixels[x+y*this.width] != img.getPixel(x, y)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /////////////////////////// GET, SET AND UPDATE METHODS ////////////////////
    
    /**
     * Return the image width
     * 
     * @return the width of the image
     */
    public int getImageWidth(){
        return width;
    }
    
    /**
     * Return the image height
     * 
     * @return the height of the image
     */
    public int getImageHeight(){
        return height;
    }
    
    /**
     * Return total number of pixels in the image
     * 
     * @return the total number of pixels
     */
    public int getImageTotalPixels(){
        return totalPixels;
    }
    
    /**
     * This method will return the amount of alpha value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the amount of alpha (transparency)
     * 
     * 0 means transparent
     * 255 means opaque
     */
    public int getAlpha(int x, int y){
        return (pixels[x+(y*width)] >> 24) & 0xFF;
    }
    
    
    public int[] getAlphaTable(){
        
        int[] Tablica = new int[getImageTotalPixels()];
       for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Tablica[x+width*y]= (pixels[x+(y*width)] >> 24) & 0xFF;
                }
            }
          return Tablica;
        } 
    
    /**
     * This method will return the amount of red value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the amount of red
     * 
     * 0 means none
     * 255 means fully red
     */
    public int getRed(int x, int y){
        return (pixels[x+(y*width)] >> 16) & 0xFF;
    }
    
    
    
    
    public int[] getRedTable(){
        
        int[] Tablica = new int[getImageTotalPixels()];
       for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Tablica[x+width*y]= (pixels[x+(y*width)] >> 16) & 0xFF;
                }
            }
          return Tablica;
        } 
       
       
    
    /**
     * This method will return the amount of green value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel 
     * @return the amount of green
     * 
     * 0 means none
     * 255 means fully green
     */
    public int getGreen(int x, int y){
        return (pixels[x+(y*width)] >> 8) & 0xFF;
    }
    
    
    
    public int[] getGreenTable(){
        
        int[] Tablica = new int[getImageTotalPixels()];
       for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Tablica[x+width*y]= (pixels[x+(y*width)] >> 8) & 0xFF;
                }
            }
          return Tablica;
        }
    
    /**
     * This method will return the amount of blue value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the amount of blue
     * 
     * 0 means none
     * 255 means fully blue
     */
    public int getBlue(int x, int y){
        return pixels[x+(y*width)] & 0xFF;
    }
    
    public int[] getBlueTable(){
        
        int[] Tablica = new int[getImageTotalPixels()];
       for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Tablica[x+width*y]= pixels[x+(y*width)] & 0xFF;
                }
            }
          return Tablica;
        }
    
    /**
     * This method will return the pixel value of the pixel at the coordinate (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the pixel value in integer [Value can be negative and positive.]
     */
    public int getPixel(int x, int y){
        return pixels[x+(y*width)];
    }
    
    /**
     * This method will return the pixel value of the image as 1D array.
     * 
     * @return 1D array containing value of each pixels of the image.
     */
    public int[] getPixelArray(){
        return pixels;
    }
        
    /**
     * This method will set the amount of alpha value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param alpha the alpha value to set
     * 
     * 0 means transparent
     * 255 means opaque
     */
    public void setAlpha(int x, int y, int alpha){
        pixels[x+(y*width)] = (alpha<<24) | (pixels[x+(y*width)] & 0x00FFFFFF);
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set the amount of red value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param red the red value to set
     * 
     * 0 means none
     * 255 means fully red
     */
    public void setRed(int x, int y, int red){
        pixels[x+(y*width)] = (red<<16) | (pixels[x+(y*width)] & 0xFF00FFFF);
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set the amount of green value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param green the green value to set
     * 
     * 0 means none
     * 255 means fully green
     */
    public void setGreen(int x, int y, int green){
        pixels[x+(y*width)] = (green<<8) | (pixels[x+(y*width)] & 0xFFFF00FF);
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set the amount of blue value between 0-255 at the pixel (x,y)
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param blue the blue value to set
     * 
     * 0 means none
     * 255 means fully blue
     */
    public void setBlue(int x, int y, int blue){
        pixels[x+(y*width)] = blue | (pixels[x+(y*width)] & 0xFFFFFF00);
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set pixel(x,y) to ARGB value.
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param alpha the alpha value to set [value from 0-255]
     * @param red the red value to set [value from 0-255]
     * @param green the green value to set [value from 0-255]
     * @param blue the blue value to set  [value from 0-255]
     */
    public void setPixel(int x, int y, int alpha, int red, int green, int blue){
        pixels[x+(y*width)] = (alpha<<24) | (red<<16) | (green<<8) | blue;
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set pixel (x,y) to pixelValue.
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param pixelValue the pixel value to set
     */
    public void setPixelToValue(int x, int y, int pixelValue){
        pixels[x+(y*width)] = pixelValue;
        updateImagePixelAt(x,y);
    }
    
    /**
     * This method will set the image pixel array to the value of the 1D pixelArray.
     * 
     * @param pixelArray 1D array containing values that is set to the image pixel array.77
     */
    public void setPixelArray(int pixelArray[]){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                pixels[x+y*width] = pixelArray[x+y*width];
                updateImagePixelAt(x,y);
            }
        }
    }
    
    /**
     * This method will update the image pixel at coordinate (x,y)
     * 
     * @param x the x coordinate of the pixel that is set
     * @param y the y coordinate of the pixel that is set
     */
    private void updateImagePixelAt(int x, int y){
        image.setRGB(x, y, pixels[x+(y*width)]);
    }
    
    public void  ZmieńRozmiarObrazu( int newW, int newH) { 
    image = resize(image,newW,newH);
}
    
    
    private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);
    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}
    public BufferedImage changeBlurr(int x , int y){  
                                                      
                                                      
                                                      if(x<=0&&y<=0){
                                                            return image;   
                                                      }
                                                      else
                                                      {
                                                        BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                                                        convertedImg.getGraphics().drawImage(image, 0, 0, null);
                                                        byte[] data = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
                                                        Mat opencv = new Mat(convertedImg.getHeight(), convertedImg.getWidth(), CvType.CV_8UC3);
                                                        opencv.put(0, 0, data);
                                                        Imgproc.blur(opencv, opencv, new Size(x, y));
                                                            return matToBufferedImage(opencv,new BufferedImage(image.getWidth(),image.getHeight(),image.getType()));
                                                      }
    }
    
    
    public BufferedImage Zoom(double procent){
        if(!(procent==0)){
        BufferedImage Output =  PobierzWycinekObrazu(image,(int)(procent/100*image.getWidth()),(int)(procent/100*image.getHeight()),(int)(image.getWidth()-(procent/100*image.getWidth())*2),(int)(image.getHeight()-(procent/100*image.getHeight())*2));  
        Image tmp = Output.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
        }
        else{
            return image;
        }
    }
    
    
    
    public BufferedImage PobierzWycinekObrazu(BufferedImage Obraz ,int startX, int startY, int Width,int  Height){
        
        int WidthImg = Obraz.getWidth();
        int HeightImg = Obraz.getHeight();
        if(Width>0&&Height>0&&WidthImg>=startX+Width&&HeightImg>=startY+Height){
            BufferedImage img = Obraz.getSubimage(startX, startY, Width, Height); //fill in the corners of the desired crop location here
            BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = copyOfImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            return copyOfImage;
        }
        
        else if (WidthImg<startX+Width&&HeightImg<startY+Height){
            int newSizeWidth = WidthImg - startX;
            int newSizeHeight = HeightImg - startY;
            BufferedImage img = Obraz.getSubimage(startX, startY, newSizeWidth, newSizeHeight); //fill in the corners of the desired crop location here
            BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = copyOfImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            return copyOfImage;
        }
        else if(WidthImg<startX+Width){
            int newSizeWidth = WidthImg - startX;
            BufferedImage img = Obraz.getSubimage(startX, startY, newSizeWidth, Height); //fill in the corners of the desired crop location here
            BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = copyOfImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            return copyOfImage;
        }
        else if(HeightImg<startY+Height){
            int newSizeHeight = HeightImg - startY;
            BufferedImage img = Obraz.getSubimage(startX, startY, Width, newSizeHeight); //fill in the corners of the desired crop location here
            BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = copyOfImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            return copyOfImage;
        }
        else{
            return null; 
        }
        
       
   }
    
    public BufferedImage Negative(BufferedImage Input){
         BufferedImage convertedImg = new BufferedImage(Input.getWidth(), Input.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
         convertedImg.getGraphics().drawImage(Input, 0, 0, null);              
         byte[] data = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
         Mat opencv = new Mat(Input.getHeight(), Input.getWidth(), CvType.CV_8UC3);
         opencv.put(0, 0, data);
         Mat invertcolormatrix= new Mat(Input.getHeight(), Input.getWidth(), CvType.CV_8UC3,new Scalar(255,255,255));
         Mat output = new Mat(Input.getHeight(), Input.getWidth(),CvType.CV_8UC3);
         Core.subtract(invertcolormatrix, opencv,output);
         return  matToBufferedImage(output,new BufferedImage(image.getWidth(),image.getHeight(),image.getType()));
    }
    
    
    
    
    
    
public ArrayList<BufferedImage> FindContorous(int blurr,int threshold, int min_wielkoscx, int min_wielkoscy, int max_wielkoscy, int max_wielkoscx,
         boolean drawContorous,BufferedImage original,boolean originalview, boolean Zoom){
        
         ArrayList<Double> PointX = new ArrayList<>();
        
         ArrayList<MatOfPoint> contours = new ArrayList<>(); 
         Mat hierarchy = new Mat();
         BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
         convertedImg.getGraphics().drawImage(image, 0, 0, null);              
         byte[] data = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
         
         Mat opencv = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
         opencv.put(0, 0, data);
         //ładowanie oryginalu   
         Mat originalImage = null;
         if(originalview)
            {
                      data = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();  
                      originalImage = new Mat(original.getHeight(), original.getWidth(), CvType.CV_8UC3);  
                      originalImage.put(0, 0, data);                                                           
             }
       
        
        
           if(!(blurr==0))
                         {
                          Imgproc.blur(opencv, opencv, new Size(blurr, blurr));
                         }
                                                                                      
                                                                                     
                // macierz w skali szarosci          
                Mat grayscaleMat  = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1); 
                //konwert z koloru do szarosci
                Imgproc.cvtColor(opencv, grayscaleMat, Imgproc.COLOR_BGR2GRAY);  
                 //inicjacja maski binarnej
                Mat maskaobrazu = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
                // fuckja tresholde wykorzystywana do wyodrebnienia obiektow         
                Imgproc.threshold(grayscaleMat,maskaobrazu , threshold,  threshold, Imgproc.THRESH_BINARY);
                 // funkcja find contours do znajodownia punktow oraz obiektow      
                Imgproc.findContours(maskaobrazu, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);          
                                                                            int MinX = image.getWidth();
                                                                            int MinY = image.getHeight();
                                                                            int MaxX=0;
                                                                            int MaxY=0;                   
                                                                          
                   
                                                                            int PoczatekX,PoczatekY,Wysokosc,Szerokosc;
                                                                            
                                                                            
                                                                            if(drawContorous){
                                                                                for(int i = 0 ; i<contours.size();i++){
                                                                                    Imgproc.drawContours(opencv, contours, i,  new Scalar(255,0,0));
                                                                                }                                                                                    
                                                                            }
                                                                            
                                                                            if(drawContorous&&originalview)
                                                                            {
                                                                                for(int i = 0 ; i<contours.size();i++){
                                                                                    Imgproc.drawContours(originalImage, contours, i,  new Scalar(255,0,0));
                                                                                } 
                                                                            }
                                                                            
                                                                            
                  ArrayList<BufferedImage> PicturesWithNumbers  = new ArrayList<>();
                               for( MatOfPoint mop: contours )
                                    {
                                       
                                     for( Point p: mop.toList() )
                                            {
                                                        if(MaxX<p.x)
                                                        {
                                                        MaxX=(int)p.x;
                                               
                                                        }
                                                        if(MaxY<p.y)
                                                        {
                                                        MaxY=(int)p.y;
                                                
                                                        }
                                                        if(MinX>p.x)
                                                        {
                                                        MinX=(int)p.x;
                                                     
                                                        }
                                                        if(MinY>p.y)
                                                        {
                                                        MinY=(int)p.y;
                                                      
                                                        }                                                      
                                            } 
                                            PoczatekX=MinX;
                                            PoczatekY=MinY;
                                            Szerokosc=MaxX-MinX;
                                            Wysokosc=MaxY-MinY;

                                           
                                           if((min_wielkoscx<Szerokosc&&min_wielkoscy<Wysokosc)&&(max_wielkoscy>Wysokosc&&max_wielkoscx>Szerokosc)&&(Szerokosc<Wysokosc))
                                                   {                
                                                       Scalar color = new Scalar(0,0,255);
                                                                     if(originalview)
                                                                      {
                                                                       PointX.add((double)PoczatekX);
                                                                       Core.rectangle(originalImage, new Point(PoczatekX,PoczatekY), new Point(PoczatekX+Szerokosc,PoczatekY+Wysokosc), color);
                                                                       if(Zoom){
                                                                           PicturesWithNumbers.add(PobierzWycinekObrazu(original,PoczatekX+1,PoczatekY+1,Szerokosc-1,Wysokosc-1)); 
                                                                       }
                                                                       else{
                                                                           PicturesWithNumbers.add(PobierzWycinekObrazu(original,PoczatekX,PoczatekY,Szerokosc,Wysokosc)); 
                                                                       }
                                                                       
                                                                      }
                                                                       else
                                                                      {
                                                                       PointX.add((double)PoczatekX);
                                                                       Core.rectangle(opencv, new Point(PoczatekX,PoczatekY), new Point(PoczatekX+Szerokosc,PoczatekY+Wysokosc), color);
                                                                       if(Zoom){
                                                                           PicturesWithNumbers.add(PobierzWycinekObrazu(image,PoczatekX+1,PoczatekY+1,Szerokosc-1,Wysokosc-1)); 
                                                                       }
                                                                       else{
                                                                           PicturesWithNumbers.add(PobierzWycinekObrazu(image,PoczatekX,PoczatekY,Szerokosc,Wysokosc)); 
                                                                       }
                                                                      }
                                                                    
                                                                     
                                                    }
                                                                          
                                          MinX = image.getWidth();
                                          MinY = image.getHeight();
                                          MaxX=0;
                                          MaxY=0;                                
                                                                          
                                     }
        if(originalview){
            image = matToBufferedImage(originalImage,new BufferedImage(original.getWidth(),original.getHeight(),original.getType()));
        }
        else{
            image = matToBufferedImage(opencv,new BufferedImage(image.getWidth(),image.getHeight(),image.getType()));
        }


        ArrayList<BufferedImage> SortByX =  new ArrayList<>();
        while(0<PicturesWithNumbers.size())
        {
            int smallestIMG = 0;
            double min = image.getWidth();
            for(int otherImg = 0 ;otherImg<PointX.size();otherImg++)
              {
                if(min>PointX.get(otherImg))
                    {
                      min =  PointX.get(otherImg);
                      smallestIMG = otherImg;
                    }
              }
            
            SortByX.add(PicturesWithNumbers.get(smallestIMG));
            PicturesWithNumbers.remove(smallestIMG);
            PointX.remove(smallestIMG);
        }
        
        
        
        
        return SortByX;
    }
    
    public static BufferedImage matToBufferedImage(Mat matrix, BufferedImage bimg)
    {
            if ( matrix != null ) { 
                int cols = matrix.cols();  
                int rows = matrix.rows();  
                int elemSize = (int)matrix.elemSize();  
                byte[] data = new byte[cols * rows * elemSize];  
                int type;  
                matrix.get(0, 0, data);  
                switch (matrix.channels()) {  
                case 1:  
                    type = BufferedImage.TYPE_BYTE_GRAY;  
                    break;  
                case 3:  
                    type = BufferedImage.TYPE_3BYTE_BGR;  
                    // bgr to rgb  
                    byte b;  
                    for(int i=0; i<data.length; i=i+3) {  
                        b = data[i];  
                        data[i] = data[i+2];  
                        data[i+2] = b;  
                    }  
                    break;  
                default:  
                    return null;  
                }  

                // Reuse existing BufferedImage if possible
                if (bimg == null || bimg.getWidth() != cols || bimg.getHeight() != rows || bimg.getType() != type) {
                    bimg = new BufferedImage(cols, rows, type);
                }        
                bimg.getRaster().setDataElements(0, 0, cols, rows, data);
            } else { // mat was null
                bimg = null;
            }
            return bimg;  
    }  
    BufferedImage obrazOryginalny() {
     return  image;
    }
    
    public double[] convertTo2DWithoutUsingGetRGB(int threshold) {
      if(image.getType()!=BufferedImage.TYPE_3BYTE_BGR){
      BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
      convertedImg.getGraphics().drawImage(image, 0, 0, null);
      image = convertedImg;
      }
      
      
      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      
      double[] ListOfRGBValues = new double[width*height];
         final int pixelLength = 3;
         for (int pixel = 0, current= 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += -16777216;
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            int  red   = (argb & 0x00ff0000) >> 16;
            int  green = (argb & 0x0000ff00) >> 8;
            int  blue  =  argb & 0x000000ff;
            
           // double value = 0.2989 * red + 0.5870 * green + 0.1140 * blue;
            if((red+green+blue)/3 >threshold){
                ListOfRGBValues[current] = 1.0;
            }
            else{
                ListOfRGBValues[current] = 0.0;
            }
            
            current++;
         }
      return ListOfRGBValues;
   }
    
    public void train(){
        
        CvSVM CvSVM = new CvSVM();
       
    }
  public void  setPicture(int widthStart,int heightStart,int width ,int height){
       if(widthStart<=width&&heightStart<=height){
            stosunekDługościOryginału = 1.0;
            ProcentXWzględemOryginału = widthStart;
            ProcentYWzględemOryginału =heightStart;
           
         }
        else if(widthStart>=heightStart)
                {
                    stosunekDługościOryginału =(double) heightStart/widthStart;
                    int NowyY = (int) (width*stosunekDługościOryginału);

                    if(NowyY>height){

                        int NowyX =  (int) (height*stosunekDługościOryginału);   
                        
                        ProcentXWzględemOryginału =NowyX;
                        ProcentYWzględemOryginału =height;
                        stosunekDługościOryginału= ProcentYWzględemOryginału;
                        }
                    else{
                       
                        ProcentXWzględemOryginału = width;
                        ProcentYWzględemOryginału =NowyY;
                    }
           
        }
        
        else if(heightStart>=widthStart)
                {
                    stosunekDługościOryginału =(double) widthStart/heightStart;
                    int NowyX =  (int) (height*stosunekDługościOryginału);
                        if(NowyX>width){
                            int NowyY = (int) (width*stosunekDługościOryginału);
                            
                            ProcentXWzględemOryginału =width;
                            ProcentYWzględemOryginału =NowyY;
                        }
                        else{
                            
                             ProcentXWzględemOryginału =NowyX;
                             ProcentYWzględemOryginału =height;
                        }
           
        }
  }
   public  BufferedImage  setPicture(BufferedImage img,int width ,int height){
    BufferedImage Out = null;
         
         if(img.getWidth()<=width&&img.getHeight()<=height){
            stosunekDługościOryginału = 1.0;
            ProcentXWzględemOryginału = img.getWidth();
            ProcentYWzględemOryginału =img.getHeight();
            Out = img;
         }
        else if(img.getWidth()>=img.getHeight())
                {
                    stosunekDługościOryginału =(double) img.getHeight()/img.getWidth();
                    int NowyY = (int) (width*stosunekDługościOryginału);

                    if(NowyY>height){

                        int NowyX =  (int) (height*stosunekDługościOryginału);   
                        Out = resize(img,NowyX,height);
                        ProcentXWzględemOryginału =NowyX;
                        ProcentYWzględemOryginału =height;
                        stosunekDługościOryginału= ProcentYWzględemOryginału;
                        }
                    else{
                        Out = resize(img,width,NowyY);
                        ProcentXWzględemOryginału = width;
                        ProcentYWzględemOryginału =NowyY;
                    }
           
        }
        
        else if(img.getHeight()>=img.getWidth())
                {
                    stosunekDługościOryginału =(double) img.getWidth()/img.getHeight();
                    int NowyX =  (int) (height*stosunekDługościOryginału);
                        if(NowyX>width){
                            int NowyY = (int) (width*stosunekDługościOryginału);
                            Out = resize(img,width,NowyY);
                            ProcentXWzględemOryginału =width;
                            ProcentYWzględemOryginału =NowyY;
                        }
                        else{
                             Out = resize(img,NowyX,height);
                             ProcentXWzględemOryginału =NowyX;
                             ProcentYWzględemOryginału =height;
                        }
           
        }
         return Out;
     }
}

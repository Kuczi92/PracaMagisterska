/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.CvSVM;
import org.opencv.ml.CvSVMParams;

/**
 *
 * @author Quchi
 */
public class Training implements Runnable {
    
        protected static String PATH_POSITIVE;
        protected static String PATH_NEGATIVE;
        protected static String XML ;
        protected static String FILE_TEST;

        private static Mat trainingImages;
        private static Mat trainingLabels;
        private static Mat trainingData;
        private static Mat classes;
        private static CvSVM clasificador;
       
        static Label percent;
        static Label Title;
        static ProgressBar Progress;
        
       static int PosX;
       static int PosY;
       static int NegX;
       static int NegY;
        
        @SuppressWarnings("static-access")
       public Training (String pathPos, String pathNeg, String XML, String FileTest,Label percent,Label Title,ProgressBar Progress,
       int sizePosX , int sizePosY, int sizeNegX,int sizeNegY){
           this.NegX = sizeNegX;
           this.NegY = sizeNegY;
           this.PosX = sizePosX;
           this.PosY = sizePosY;
           PATH_POSITIVE = pathPos;
           PATH_NEGATIVE = pathNeg;
           Training.XML = XML;
           Training.FILE_TEST = FileTest;
           this.Progress = Progress;
           this.Title = Title;
           this.percent= percent;
           this.trainingImages = new Mat();
           this.trainingLabels = new Mat();
           this.trainingData = new Mat();
           this.classes = new Mat();
           Platform.runLater(() -> {  
                            this.Progress.setVisible(true);
                            this.Title.setVisible(true);
                            this.percent.setVisible(true);
                            });
        }
        
        
        
        
        
 protected static void test() {
    Mat in = Highgui.imread( new File( FILE_TEST ).getAbsolutePath(), Highgui.CV_LOAD_IMAGE_GRAYSCALE );
    clasificador.load( new File( XML ).getAbsolutePath() );
    System.out.println( clasificador );
    Mat out = new Mat();
    in.convertTo( out, CvType.CV_32FC1 );
    out = out.reshape( 1, 1 );
    System.out.println( out );
    System.out.println( clasificador.predict( out ) );
}

protected static void train() {
    trainingImages.copyTo( trainingData );
    trainingData.convertTo( trainingData, CvType.CV_32FC1 );
    trainingLabels.copyTo( classes );
    CvSVMParams params = new CvSVMParams();
    params.set_kernel_type( CvSVM.LINEAR );
    clasificador = new CvSVM( trainingData, classes, new Mat(), new Mat(), params );
    clasificador.save( XML );
}

protected static void trainPositive() throws IOException {
    
     final int  size = new File( PATH_POSITIVE ).listFiles().length;
     
    Platform.runLater(() -> { 
                             Title.setText("Proces tronowania obrazów pozytywnch:");
                            });
    int count = 0 ;
    for ( File file : new File( PATH_POSITIVE ).listFiles() ) 
    {
        String  filename = file.getAbsolutePath();
        if(!(filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith(".bmp")||filename.endsWith(".jpeg")
           ||filename.endsWith(".JPG")||filename.endsWith(".PNG")||filename.endsWith(".BMP")||filename.endsWith(".JPEG")))
                {
                    continue;
                }
        
        count ++;
        Mat img = getMat( file.getAbsolutePath(),true );
        trainingImages.push_back( img.reshape( 1, 1 ) );
        trainingLabels.push_back( Mat.ones( new Size( 1, 1 ), CvType.CV_32FC1 ) );
        final int currentcount = count;
        
        
        Platform.runLater(() -> {  
                                Progress.setProgress((double)currentcount/size);
                                percent.setText(format("%.2f",(double)currentcount/size*100)+"%");
                            });
       }
    
       Platform.runLater(() -> {  
                                Progress.setProgress(1);
                                percent.setText(100+"%");
                            });
}

protected static void trainNegative() throws IOException {
    
    final int  size = new File( PATH_NEGATIVE ).listFiles().length;
     
    Platform.runLater(() -> {  
                             Title.setText("Proces trenowania obrazów negatywnych:");
                            });
    int count = 0 ;
     
        for ( File file : new File( PATH_NEGATIVE ).listFiles() ) {
            count ++;
            Mat img = getMat( file.getAbsolutePath(),false );
            trainingImages.push_back( img.reshape( 1, 1 ) );
            trainingLabels.push_back( Mat.zeros( new Size( 1, 1 ), CvType.CV_32FC1 ) );
            final int currentcount = count;
            Platform.runLater(() -> {  
                                 Progress.setProgress((double)currentcount/size);
                                 percent.setText(format("%.2f",(double)currentcount/size*100)+"%");
                                });
        }
    
       Platform.runLater(() -> {  
                                Progress.setProgress(1);
                                percent.setText(100+"%");
                               });
}

protected static Mat getMat( String path ,boolean positive) throws IOException {
    BufferedImage Road;
    
    if(positive){
        Picture Pic = new Picture(ImageIO.read(new File(path)));
        Pic.ZmieńRozmiarObrazu(PosX, PosY);
        Road = Pic.Image();
    }
    
    else{
        Picture Pic = new Picture(ImageIO.read(new File(path)));
        Pic.ZmieńRozmiarObrazu(NegX, NegY);
        Road = Pic.Image();  
    }
    
    BufferedImage convertedImg = new BufferedImage(Road.getWidth(), Road.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
    convertedImg.getGraphics().drawImage(Road, 0, 0, null);
 
    byte[] data = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
    Mat opencv = new Mat(Road.getHeight(), Road.getWidth(), CvType.CV_8UC3);
    opencv.put(0, 0, data);
     
    Mat grayscaleMat  = new Mat(Road.getHeight(), Road.getWidth(), CvType.CV_8UC1); 
    Imgproc.cvtColor(opencv, grayscaleMat, Imgproc.COLOR_BGR2GRAY); 
     
    Mat img = new Mat();
    grayscaleMat.convertTo( img, CvType.CV_32FC1, 1.0 / 255.0 );
    return img;
} 

    @Override
    public void run() {
            try 
            {
                trainPositive();
            } catch (IOException ex) {
                Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                trainNegative();
            } catch (IOException ex) {
                Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        train();
       
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.io.File;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.ml.CvSVM;
import org.opencv.ml.CvSVMParams;

/**
 *
 * @author Quchi
 */
public class Training implements Runnable {
        protected static final String PATH_POSITIVE = "data/positivo/";
        protected static final String PATH_NEGATIVE = "data/negativo/";
        protected static final String XML = "data/test.xml";
        protected static final String FILE_TEST = "data/negativo/1.jpg";

        private static Mat trainingImages;
        private static Mat trainingLabels;
        private static Mat trainingData;
        private static Mat classes;
        private static CvSVM clasificador;
        
        
        
        
        
        
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

protected static void trainPositive() {
    for ( File file : new File( PATH_POSITIVE ).listFiles() ) {
        Mat img = getMat( file.getAbsolutePath() );
        trainingImages.push_back( img.reshape( 1, 1 ) );
        trainingLabels.push_back( Mat.ones( new Size( 1, 1 ), CvType.CV_32FC1 ) );
    }
}

protected static void trainNegative() {
    for ( File file : new File( PATH_NEGATIVE ).listFiles() ) {
        Mat img = getMat( file.getAbsolutePath() );
        trainingImages.push_back( img.reshape( 1, 1 ) );
        trainingLabels.push_back( Mat.zeros( new Size( 1, 1 ), CvType.CV_32FC1 ) );
    }
}

protected static Mat getMat( String path ) {
    Mat img = new Mat();
    Mat con = Highgui.imread( path, Highgui.CV_LOAD_IMAGE_GRAYSCALE );
    con.convertTo( img, CvType.CV_32FC1, 1.0 / 255.0 );
    return img;
} 

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

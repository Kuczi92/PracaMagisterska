/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
/**
 *
 * @author Quchi
 */
public final class ArrayUtils {
    public int[] argsort(final float[] a) {
        return argsort(a, true);
    }
   
    
    public int[] argsort(final int[][] a,boolean g) {
        float[] b = new float[a.length];
        for(int i = 0 ; i< b.length;i++){
            b[i]=(int)a[i][1];
        }
        return argsort(b,g);
    }
    
    
    public static boolean isNumeric(String str)  
        {  
          try  
          {  
             Double.parseDouble(str);  
          }  
          catch(NumberFormatException nfe)  
          {  
            return false;  
          }  
          return true;  
        }
    
    
    public int[] argsort(final int[] a,boolean g) {
        float[] b = new float[a.length];
        for(int i = 0 ; i< b.length;i++){
            b[i]=(int)a[i];
        }
        return argsort(b,g);
    }
    
    
    public int[] argsort(final float[] a, final boolean ascending) {
        Integer[] indexes = new Integer[a.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (final Integer i1, final Integer i2) -> (ascending ? 1 : -1) * Float.compare(a[i1], a[i2]));
        return asArray(indexes);
    }

    public static <T extends Number> int[] asArray(final T... a) {
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i].intValue();
        }
        return b;
    }

    public static double[] castOf(final float[] x) {
        double[] y = new double[x.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = x[i];
        }
        return y;
    }

    public static int[] castOf(final long[] original) {
        return castOf(original, original.length);
    }

    public static int[] castOf(final long[] original, final int newLength) {
        int[] cast = new int[newLength];
        int length = Math.min(cast.length, original.length);
        for (int i = 0; i < length; i++) {
            long o = original[i];
            if (o < Integer.MIN_VALUE || o > Integer.MAX_VALUE) {
                throw new IllegalArgumentException();
            }
            cast[i] = (int) o;
        }
        return cast;
    }

    public static float[][] copyOf(final float[][] x, final int newLength) {
        float[][] y = new float[newLength][];
        for (int i = 0; i < y.length; i++) {
            if (x[i] != null) {
                y[i] = Arrays.copyOf(x[i], x[i].length);
            }
        }
        return y;
    }

    /**
     * Assigns a random value to each element of the specified array of doubles.
     * @param x
     * @param rng
     */
    public static void fillRandom(final double[] x, final Random rng) {
        for (int i = 0; i < x.length; i++) {
            x[i] = rng.nextDouble();
        }
    }

    public int[] argsort(double[] area, boolean b) {
        float[] input = new float[area.length];
        for(int i = 0 ; i < area.length;i++){
         input[i]=   (float)area[i];
        }
        return argsort(input, b);
    }

    public double[] minimum(double[] table1,double[] table2){
         double[] output;
         if(table1.length==table2.length)
         output = new double[table1.length];
         else if(table1.length>table2.length)
         output = new double[table1.length]; 
         else
         output = new double[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.min(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    public double[] maximum(double[] table1,double[] table2){
         double[] output;
         if(table1.length==table2.length)
         output = new double[table1.length];
         else if(table1.length>table2.length)
         output = new double[table1.length]; 
         else
         output = new double[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.max(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    public double[] minimum(int[] table1,int[] table2){
         double[] output;
         if(table1.length==table2.length)
         output = new double[table1.length];
         else if(table1.length>table2.length)
         output = new double[table1.length]; 
         else
         output = new double[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.min(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    public double[] maximum(int[] table1,int[] table2){
         double[] output;
         if(table1.length==table2.length)
         output = new double[table1.length];
         else if(table1.length>table2.length)
         output = new double[table1.length]; 
         else
         output = new double[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.max(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    public int[] maximumINT(int[] table1,int[] table2){
         int[] output;
         if(table1.length==table2.length)
         output = new int[table1.length];
         else if(table1.length>table2.length)
         output = new int[table1.length]; 
         else
         output = new int[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.max(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    public int[] minimumINT(int[] table1,int[] table2){
         int[] output;
         if(table1.length==table2.length)
         output = new int[table1.length];
         else if(table1.length>table2.length)
         output = new int[table1.length]; 
         else
         output = new int[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = Math.min(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = table2[index];
             }
             else{
                 output[index] = table1[index];
             }
             
         }
        return output;
    }
    
    
    public int[] minimumINT(double[] table1,double[] table2){
         int[] output;
         if(table1.length==table2.length)
         output = new int[table1.length];
         else if(table1.length>table2.length)
         output = new int[table1.length]; 
         else
         output = new int[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = (int) Math.min(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = (int) table2[index];
             }
             else{
                 output[index] = (int) table1[index];
             }
             
         }
        return output;
    }
    
    
    public int[] maximumINT(double[] table1,double[] table2){
         int[] output;
         if(table1.length==table2.length)
         output = new int[table1.length];
         else if(table1.length>table2.length)
         output = new int[table1.length]; 
         else
         output = new int[table2.length];
         
         int maxsize = Math.max(table2.length, table1.length);
         int minsize = Math.min(table2.length, table1.length);
         for(int index = 0 ; index<maxsize ; index ++ ){
             if(minsize<index){
                 output[index] = (int) Math.max(table1[index], table2[index]);
             }
             else if(index>table1.length){
                 output[index] = (int) table2[index];
             }
             else{
                 output[index] = (int) table1[index];
             }
             
         }
        return output;
    }
    
    public int[] SubArray(int[] table,int index){
        int[] out = new int[index];
        System.arraycopy(table, 0, out, 0, index);
        return out;
    }
    public int MAX(int[] a , int[] b){
        int max = 0;
        for(int i = 0 ; i< a.length;i++){
            max = Math.max(a[i]-b[i]+1,max);
        } 
        
        return max;
    }
    
    public ArrayList<Integer> ToArrayList(int[] a){
    ArrayList<Integer> out = new ArrayList<>();
    
    for (int i = 0;i< a.length; i++){
        out.add(a[i]);
    }
    return out;    
    }

    public double[] calculate_distance(int[] x2, int[] y2) {
        
        double[] out = new double [x2.length];
            for(int i = 0 ; i<x2.length;i++)
                {
                 out[i]=Math.sqrt(Math.pow(0.0 - x2[i],2)+Math.pow(0.0-y2[i],2));
                }
        return out;
    }
    
    public double[] calculate_area(MatOfRect Boxes){
        Rect Box[] = Boxes.toArray();
        double[] Out = new double[Box.length];
        for (int box = 0 ; box<Box.length;box++) {
            Out[box] = Box[box].height*Box[box].width;
        }
        return Out;
    }
    
    public boolean IsInclude(Rect a , Rect b){
        boolean CoveringX = ((a.x+a.width)>b.x)&&(b.x>a.x)||((b.x+b.width)>a.x)&&(a.x>b.x);
        boolean CoveringY = ((a.y+a.height)>b.y)&&(b.y>a.y)||((b.y+b.height)>a.y)&&(a.y>b.y);
        return CoveringX&&CoveringY; 
    }
    
     public ArrayList<String> ListaPlikówWFolderze(String Ściezka) throws IOException{
        ArrayList<String> ListaPlików = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Paths.get(Ściezka))) {
          paths.forEach((Path filePath) -> {
           if (Files.isRegularFile(filePath)) {
            String temp = filePath.toString();
                if((temp.endsWith("jpg")||temp.endsWith("png")||temp.endsWith("jpeg")||temp.endsWith("JPEG")||temp.endsWith("JPG")||temp.endsWith("BMP")||temp.endsWith("bmp")))
                {
                    ListaPlików.add(filePath.toString());
                }
           }
          });
         }
        return ListaPlików;
    }
}

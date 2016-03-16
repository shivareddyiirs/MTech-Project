*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;

/**
 *
 * @author Group-8
 */
public class ImageFilter {
    
    
    Dataset Imagebands = gdal.Open("C://Java_project//pan_subset.img");
    int r_size =Imagebands.GetRasterXSize();
    int c_size =Imagebands.GetRasterYSize();
    
                 int in_array_one[]=new int[r_size*c_size];
                 int[][] in_array =new int[r_size][c_size];
                 int[][] out_array=new int[r_size][c_size];
                 int[] out_array_one=new int[r_size*c_size];
                
              
                 
           
        
 public void edgeEnhancement_Laplace(){ 
             
 Dataset out_destn=Imagebands.GetDriver().Create("C://Java_project//edge_enhancement.img", r_size,c_size,1,gdalconstConstants.GDT_Byte);    
              
                                   
              Band band =Imagebands.GetRasterBand(1);
              Band bandOut=out_destn.GetRasterBand(1);
              
                  
              band.ReadRaster(0, 0, r_size,c_size,in_array_one);
            
                       int kernel[][]= {
                       {0,-1,0},
                       {-1,5,-1},
                       {0,-1,0} };
          
              
                  for(int i=0; i<(in_array_one.length);i++){
                      in_array[i/c_size][i%c_size]= in_array_one[i];
                  }
                      
                    out_array=in_array;
                   
                   int r=0;
               for(int i=0;i<(r_size-2);i++){
                  for(int j=0;j<(c_size-2);j++){
                     for(int s=0;s<3;s++){
                        for(int t=0;t<3;t++){
                      
                          r= r+((kernel[s][t]*in_array[i+s][j+t]));
                       }
                     }
                 
                  int p= r/9;
                 out_array[i+1][j+1]=p;
                  }
                  
          }
              
               
                 for(int i=0;i<(r_size);i++){
                             for(int j=0;j<c_size;j++){
                                 out_array_one[((i*c_size)+j)]= out_array[i][j];}
                                  }
              
              bandOut.WriteRaster(0, 0, r_size, c_size,out_array_one);
              bandOut.ReadRaster(0, 0, r_size, r_size,out_array_one);
              
                                      
              out_destn.SetProjection(Imagebands.GetProjection());
              
              System.out.println( "Output spatial reference:"+out_destn.GetProjection());
                     
 }
 
public void edgeDectector_Diagonal(){
   
         
 Dataset out_destn=Imagebands.GetDriver().Create("C://Java_project//edge_detector_diagonal.img", r_size,c_size,1,gdalconstConstants.GDT_Byte);    
              
                                   
              Band band =Imagebands.GetRasterBand(1);
              Band bandOut=out_destn.GetRasterBand(1);
              
                 
                  
              band.ReadRaster(0, 0, r_size,c_size,in_array_one);
            
                       int kernel[][]= {
                       {-1,-1,2},
                       {-1,2,-1},
                       {2,-1,-1} };
          
              
                  for(int i=0; i<(in_array_one.length);i++){
                      in_array[i/c_size][i%c_size]= in_array_one[i];
                  }
                      
                    out_array=in_array;
                   
                   int r=0;
               for(int i=0;i<(r_size-2);i++){
                  for(int j=0;j<(c_size-2);j++){
                     for(int s=0;s<3;s++){
                        for(int t=0;t<3;t++){
                      
                          r= r+((kernel[s][t]*in_array[i+s][j+t]));
                       }
                     }
                 
                  int p= r/9;
                 out_array[i+1][j+1]=p;
                  }
                  
          }
               
              
               
                   for(int i=0;i<(r_size);i++){
                             for(int j=0;j<c_size;j++){
                                 out_array_one[((i*c_size)+j)]= out_array[i][j];}
                                  }
              
              bandOut.WriteRaster(0, 0, r_size, c_size,out_array_one);
              bandOut.ReadRaster(0, 0, r_size, r_size,out_array_one);
              
               out_destn.SetProjection(Imagebands.GetProjection());
              
              System.out.println( "Output spatial reference:"+out_destn.GetProjection());
            
             
 }
 
public void edgeDectector_Vertical(){
   
         
 Dataset out_destn=Imagebands.GetDriver().Create("C://Java_project//edge_dectector_vertical.img", r_size,c_size,1,gdalconstConstants.GDT_Byte);    
              
                                   
              Band band =Imagebands.GetRasterBand(1);
              Band bandOut=out_destn.GetRasterBand(1);
              
                  
              band.ReadRaster(0, 0, r_size,c_size,in_array_one);
            
                       int kernel[][]= {
                       {-1,2,-1},
                       {-1,2,-1},
                       {-1,2,-1} };
          
              
                  for(int i=0; i<(in_array_one.length);i++){
                      in_array[i/c_size][i%c_size]= in_array_one[i];
                  }
                      
                    out_array=in_array;
                   
                   int r=0;
               for(int i=0;i<(r_size-2);i++){
                  for(int j=0;j<(c_size-2);j++){
                     for(int s=0;s<3;s++){
                        for(int t=0;t<3;t++){
                      
                          r= r+((kernel[s][t]*in_array[i+s][j+t]));
                       }
                     }
                 
                  int p= r/9;
                 out_array[i+1][j+1]=p;
                  }
                  
          }
                 
                   for(int i=0;i<(r_size);i++){
                             for(int j=0;j<c_size;j++){
                                 out_array_one[((i*c_size)+j)]= out_array[i][j];}
                                  }
              
              bandOut.WriteRaster(0, 0, r_size, c_size,out_array_one);
              bandOut.ReadRaster(0, 0, r_size, r_size,out_array_one);
              
               out_destn.SetProjection(Imagebands.GetProjection());
              
              System.out.println( "Output spatial reference:"+out_destn.GetProjection());
              
            
           
                     
 }
 

}
 


    
 
 
 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rastovec;

import org.gdal.gdal.*;
import org.gdal.gdalconst.*;
import org.gdal.ogr.*;
import org.gdal.osr.*;
        

/**
 *
 
 */
public class RasToVec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String inputsrc = "F:\\Education\\M. Tech Documents\\Java assignment\\Satellite Data\\Converted\\nehru.tif";
        String output = "F:\\Education\\M. Tech Documents\\Java assignment\\Satellite Data\\Converted\\polygonized.shp";
        
        gdal.AllRegister();
        Dataset ds = gdal.Open(inputsrc);
//        Dataset shp = gdal.Open("F:\\Education\\M. Tech Documents\\Module 2\\python slides\\Shapefiles\\Polygon1.shp");
        ogr.RegisterAll();      
        DataSource dest = ogr.GetDriverByName("ESRI Shapefile").CreateDataSource(output);
        SpatialReference sr = new SpatialReference();
//        osr.GetUserInputAsWKT(ds.GetProjectionRef());
        sr.ImportFromWkt(ds.GetProjectionRef());
        Layer dst_layer = dest.CreateLayer("Value", sr);
        
        String dst_fldnm = "DN";
//        FieldDefn fd 
        dst_layer.CreateField(new FieldDefn(dst_fldnm,ogr.OFTInteger)); 
        int dst_field = 0;
//        gdal.GetDriverByName("ESRI Shapefile");
        Band b = ds.GetRasterBand(ds.getRasterCount());
//        System.out.println(ds.getRasterCount());
//        System.out.println(ds.GetProjection());
        gdal.Polygonize(b, null, dst_layer, dst_field);
//        int res = gdal.Polygonize(b, null, null, 1);
//        Layer l;
//        l.CreateField(null);
    }
    
}

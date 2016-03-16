package rastovec;

import org.gdal.gdal.*;
import org.gdal.gdalconst.*;
import org.gdal.ogr.*;
import org.gdal.osr.*;
import java.io.File;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
        

/**
 *
 * @author Group 6
 */
public class RasToVec {
    String inputsrc;
    String output;

    public RasToVec() {
        String [] types = {"shp", "img", "png", "jpg", "tiff", "tif"};
        File file = JFileDataStoreChooser.showOpenFile(types, null);
        
        inputsrc = file.getAbsolutePath();
        output = file.getPath()+"Vectorized.shp";
        try {
            vectorize();
            display();
        } catch (Exception e) {}
    }
    
    public void display () throws Exception{        
        File file = new File(output);

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
 // Now display the map
        JMapFrame.showMap(map);
    }
    
    public void vectorize() {
        gdal.AllRegister();
        Dataset ds = gdal.Open(inputsrc);
        Band b = ds.GetRasterBand(1);
        ogr.RegisterAll();      
        DataSource dest = ogr.GetDriverByName("ESRI Shapefile").CreateDataSource(output);
        SpatialReference sr = new SpatialReference();
        if (ds.GetProjectionRef() == null) {
            sr = null;
        }
        org.gdal.ogr.Layer dst_layer = dest.CreateLayer("Value", sr);
        
        String dst_fldnm = "DN";
        dst_layer.CreateField(new FieldDefn(dst_fldnm,ogr.OFTInteger)); 
        int dst_field = 0;
        gdal.Polygonize(b, null, dst_layer, dst_field);
        dest.delete();
        ds.delete();        
    }
    
    public static void main(String args[]){
        
        RasToVec rv = new RasToVec();
    }
    
}

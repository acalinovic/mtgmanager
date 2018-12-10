/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.utils;

import java.net.URL;

/**
 *
 * @author boris
 */
public class ResourcesHelper {
    
    public static String VIEW_DIRECTORY;
    
    public ResourcesHelper() {
        VIEW_DIRECTORY = "/be/bbs/mtgmanager/view/fxml/";
    }
    
    public void setViewDirectory(String path) {
    	VIEW_DIRECTORY = path;
    }
    public URL getFxmlView(String fxmlFile) {

        URL viewFile = this.getClass().getResource(VIEW_DIRECTORY + fxmlFile + ".fxml");
        //System.out.println(viewFile.getFile());
        return viewFile;
    }
    
}

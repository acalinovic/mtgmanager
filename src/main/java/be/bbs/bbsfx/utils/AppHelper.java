/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.utils;

/**
 *
 * @author boris
 */
public class AppHelper {

    public static DatabaseHelper DATABASE_HELPER;
    public static ControllerHelper CONTROLLER_MANAGER;
    public static StageHelper STAGE_MANAGER;
    public static HibernateUtil HIBERNATE;
    public static HttpHelper HTTP_HELPER;

    public AppHelper() {
        DATABASE_HELPER = new DatabaseHelper();
        CONTROLLER_MANAGER = new ControllerHelper();
        STAGE_MANAGER = new StageHelper();
        HIBERNATE = new HibernateUtil();
        HTTP_HELPER = new HttpHelper();
    }
}

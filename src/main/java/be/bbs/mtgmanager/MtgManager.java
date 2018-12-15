package be.bbs.mtgmanager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.JsonNode;

import be.bbs.bbsfx.repository.GenericRepositoryInterface;
import be.bbs.bbsfx.utils.AppHelper;
import be.bbs.bbsfx.utils.DatabaseHelper;
import be.bbs.bbsfx.utils.HttpHelper;
import be.bbs.bbsfx.utils.JsonLoader;
import be.bbs.bbsfx.utils.StageHelper;
import be.bbs.mtgmanager.model.entity.*;
import be.bbs.mtgmanager.model.html.CardHtml;
import be.bbs.mtgmanager.repositoy.GenericRepositoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class MtgManager extends Application {

   public static AppHelper HELPER;
   
   public MtgManager() {
       HELPER = new AppHelper();
   }

   /**
    *
    * @param stage
    * @throws java.lang.Exception
    */

   @Override
   public void start(Stage stage) throws Exception {
	   StageHelper.RESOURCES_MANAGER.setViewDirectory("/be/bbs/mtgmanager/view/fxml/");
       AppHelper.STAGE_MANAGER.renderDefaultView(stage);
       AppHelper.STAGE_MANAGER.buildStage("CardList", false);
       AppHelper.STAGE_MANAGER.renderView("CardList", "CardList", true);
       
       JsonLoader.load();
       

   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       launch(args);
   }

}

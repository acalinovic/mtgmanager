package be.bbs.mtgmanager;

import be.bbs.bbsfx.utils.AppHelper;
import be.bbs.bbsfx.utils.HttpHelper;
import be.bbs.bbsfx.utils.StageHelper;
import be.bbs.mtgmanager.entity.html.CardHtml;
import be.bbs.mtgmanager.model.entity.*;
import be.bbs.mtgmanager.repositoy.GenericRepositoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

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
       /*DEBUG
       //CardHtml cardh = new CardHtml(HttpHelper.getMCMPage("en", "Tempest", "Altar of Dementia"));
       //System.out.println(cardh.getName());
       
       CardController controller = new CardController();
       controller.findAll();
       controller.findBy("id", 1);
       controller.findBy("name", "Mox Sapphire");
       System.out.println(controller.findBy("type", 1));
       
       
       */
       ForeignData entity = new ForeignData();
       entity.setForeigndataName("toto");
       GenericRepositoryImpl<ForeignData> gr = new GenericRepositoryImpl<ForeignData>();
       gr.save(entity);
       
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       launch(args);
   }

}

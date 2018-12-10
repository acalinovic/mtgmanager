/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.control;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import be.bbs.bbsfx.utils.DatabaseHelper;
import be.bbs.bbsfx.utils.StageHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author boris
 */
public class GenericController implements Initializable{
	
	public SessionFactory SESSION_FACTORY;

	@FXML
	public void closeApplication() {
		Map<String, Stage> stages = StageHelper.STAGES;
		//System.out.println(stages);
		Iterator<Entry<String, Stage>> i = stages.entrySet().iterator();
		while(i.hasNext()) {
			i.next().getValue().close();
			
		}
	       Session session = DatabaseHelper.getSession();
	       //System.out.println(session);
	       
	       session.close();
	       System.exit(0);

	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}

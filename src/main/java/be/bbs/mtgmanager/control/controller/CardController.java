package be.bbs.mtgmanager.control.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.query.Query;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import be.bbs.bbsfx.control.GenericController;
import be.bbs.bbsfx.utils.DatabaseHelper;
import be.bbs.mtgmanager.model.entity.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CardController extends GenericController {
	
	@FXML
	AnchorPane cardPane;
	@FXML
	JFXTreeTableView<?> cardWiewList;
	@FXML
	Label cardListLabel;
	@FXML
	JFXButton findByIdButton;
	@FXML
	JFXButton findByFieldButton;
	@FXML
	JFXTextField idField;
	@FXML
	JFXTextField fieldFieldField;
	@FXML
	JFXTextField fieldFieldValue;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unchecked")
	public List<be.bbs.mtgmanager.model.entity.Card> findAll() {
		Query<Card> query = DatabaseHelper.SESSION_FACTORY.openSession().createQuery("from Card");
        List<Card> list = query.getResultList();
        System.out.println("Number of Cards present--> "+list.size());
        for (Card card : list) {
           
            System.out.println(card.getId());
            System.out.println(card.getCardName());
        }
        DatabaseHelper.SESSION_FACTORY.getCurrentSession().close();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	public Card findById(int id) {
		Query<Card> query = DatabaseHelper.SESSION_FACTORY.openSession().createQuery("from Card c where c.id = :id").setParameter("id", id);
        Card card = query.getSingleResult();
        System.out.println("card " + id + ": " + card.getCardName());
       
        DatabaseHelper.SESSION_FACTORY.getCurrentSession().close();
        return card;
	}
	@SuppressWarnings("unchecked")
	public Card findBy(String field, Object value) {
		String hql = "from Card c where c." + field + " =:" + field;
		Query<Card> query = DatabaseHelper.SESSION_FACTORY.openSession().createQuery(hql).setParameter(field, value);
        Card card = query.getSingleResult();
        System.out.println("card " + field + ": " + card.getCardName());
       
        DatabaseHelper.SESSION_FACTORY.getCurrentSession().close();
        cardListLabel.setText(card.getCardName());
        return card;
	}
	public void execFindById() {
		int i = Integer.parseInt(idField.getText());
		
		try {
			Card card = findById(i);			
			cardListLabel.setText(card.getCardName());			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			cardListLabel.setText("Nothing found");
		}
	}
	public void execFindBy() {
		String field = fieldFieldField.getText();
		Object value = fieldFieldValue.getText();
		try {
			int i = Integer.parseInt(fieldFieldValue.getText());
			value = (int) i;
		}catch(Exception e) {
			value = (String) value;			
		}

		try {
			Card card = findBy(field,value);
			cardListLabel.setText(card.getCardName());			
		} catch(Exception e) {
			cardListLabel.setText("Nothing found");						
		}
	}
	
	//String s = (String) DatabaseHelper.SESSION_FACTORY.openSession().createNamedQuery("get_card_name_by_id").setParameter("id", 1).getSingleResult();
	//List<Card> list = DatabaseHelper.SESSION_FACTORY.openSession().createNamedQuery("get_all_cards").getResultList();

}

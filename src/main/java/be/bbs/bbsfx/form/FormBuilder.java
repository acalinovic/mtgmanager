/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.form;

import com.jfoenix.controls.JFXButton;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author boris
 */
public class FormBuilder {

    private Form form;
    private Map<String, Object> formFields;

    public FormBuilder(Class<?> entityClass) {
        this.formFields = new HashMap<String, Object>();
        this.form = new Form(entityClass);
    }

    public FormBuilder add(String field) {
        if (this.form.getFields().containsKey(field)) {
            Object value = this.form.getFields().get(field);
            this.formFields.put(field, value);
        } else {
            System.out.println("not found");
        }
        return this;
    }

    public void renderForm(Pane pane) {
        GridPane gp = new GridPane();
        int col = 0;
        
        Iterator<String> i = formFields.keySet().iterator();
        while (i.hasNext()) {
            String current = i.next().toString();
            Label label = new Label(current);
            TextField textField = new TextField();
            textField.setId(current+"Field");
            gp.add(label, 0, col);
            gp.add(textField, 1, col);
            pane.getChildren().clear();
            pane.getChildren().addAll(gp);
            col++;

        }
        JFXButton saveButton = new JFXButton("Save");
        saveButton.setId(this.form.getEntityClass().getName()+"saveButton");
        JFXButton cancelButton = new JFXButton("Cancel");
        cancelButton.setId(this.form.getEntityClass().getName()+"cancelButton");
        gp.add(saveButton, 0, col);
        gp.add(cancelButton, 1, col);
    }
}

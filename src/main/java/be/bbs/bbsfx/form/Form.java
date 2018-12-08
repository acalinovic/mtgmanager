/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author boris
 */
public class Form {

    private Class<?> entityClass;

    private Map<String, String> fields;

    public Form(Class<?> entityClass) {
        this.entityClass = entityClass;

        this.fields = new HashMap<String, String>();
        Field[] classFields = entityClass.getFields();
        int cnt = 0;
        while (cnt < classFields.length) {
            
            String regex = "public |static |private |" + ".*." + this.entityClass.getName() + ".";
            String fieldType = classFields[cnt].toString().replaceAll("public |static |private ", "").split(" ")[0];
            String fieldName = classFields[cnt].toString().replaceAll(regex, "").split(" ")[0].replace("." + fieldType, fieldType);
            this.fields.put(fieldName, fieldType);
            cnt++;
        }
    }
    
    public Class<?> getEntityClass() {
        return entityClass;
    }
    
    public Map<String, String> getFields() {
        return this.fields;
    }
}

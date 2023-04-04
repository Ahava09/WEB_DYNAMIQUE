/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1985.framework.servlet;
import java.util.HashMap;

/**
 *
 * @author itu
 */
public class ModelView {
    String nameview;
    HashMap<String,Object> data = new HashMap<String, Object>();;

    public String getNameview() {
        return nameview;
    }

    public void setNameview(String nameview) {
        this.nameview = nameview;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public ModelView() {
    }

    public ModelView(String nameview) {
        this.nameview = nameview;
    }
    
    public void addItem(String key,Object value){
        data.put(key,value);
    }
    
}

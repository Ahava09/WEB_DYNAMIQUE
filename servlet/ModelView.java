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
    HashMap<String,Object> data = new HashMap<String, Object>();
    HashMap<String,Object> session = new HashMap<String,Object>();
    public String getNameview() {
        return nameview;
    }

    public void setNameview(String nameview) {
        this.nameview = nameview;
    }

    public HashMap<String,Object> getData() {
        return data;
    }

    public void setData(HashMap<String,Object> data) {
        this.data = data;
    }

    public HashMap<String,Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String,Object> session) {
        this.session = session;
    }
    public ModelView() {
    }

    public ModelView(String nameview) {
        this.nameview = nameview;
    }
    
    public void addItem(String key,Object value){
        this.getData().put(key,value);
    }
    
    public void addSession(String key,Object value){
        this.getSession().put(key,value);
    }
    
}

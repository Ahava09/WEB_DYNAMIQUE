/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1985.framework.servlet;
import etu1985.model.Mapping;
import etu1985.model.Url;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author itu
 */
@WebServlet(name = "frontservlet_1")
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> MappingUrls;
    public void init (){
        MappingUrls = new HashMap<>();
          try {
            String directory ="C://S4_20_02_23//Mr_Naina_Web_Dynamique//WEB_DYNAMIQUE//Web//src//java//etu1985//model";
            String [] classe = reset(directory);
            for(int i =0 ;i< classe.length; i++){
                 String className = classe[i];
                String name = classe[i];
                className = "etu1985.model." +className;
                Class<?> clazz;
                clazz = Class.forName(className);
                Method [] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                     Annotation[] an = method.getAnnotations();
                     if(an.length!=0){
                         Url annotation = method.getAnnotation(Url.class);
                         MappingUrls.put(annotation.url(),new Mapping(name,method.getName()));
                     }
                }
            }
         } catch (Exception ex) {
              ex.printStackTrace();
         }
    }
    public String[] reset(String Directory){
        ArrayList<String> rar=new ArrayList<>();
        File dossier = new File(Directory);
        String[] contenu = dossier.list();
        for(int i=0; i<contenu.length; i++){
            String fe=FilenameUtils.getExtension(contenu[i]);
            if(fe.equalsIgnoreCase("java")){
                String [] value = contenu[i].split("[.]");
                rar.add(value[0]);
            }
        }
       return rar.toArray(new String[rar.size()]); 
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
     PrintWriter out = response.getWriter();
     out.println("Nombre de method avec @Url "+MappingUrls.size());
//          String url=request.getRequestURI();
//        RequestDispatcher dispat=request.getRequestDispatcher("url.jsp");
//        dispat.forward(request,response);
}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
     
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
     
}

}

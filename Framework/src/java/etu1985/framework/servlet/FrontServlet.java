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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import etu1985.framework.Mapping;



/**
 *
 * @author itu
 */
@WebServlet(name = "frontservlet_1")
public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrls;
    
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
        }catch (Exception ex) {
            ex.printStackTrace();
         }
    }

    private String getNameServlet(HttpServletRequest request,PrintWriter out){
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String[] uriParts = uri.split(context);
        out.print("uri:  "+uri);
        if (uriParts.length > 1) {
            return uriParts[1];
        } else {
            return "";
        }

    }

    public void load (HttpServletRequest request,PrintWriter out){
        String nameServlet = this.getNameServlet(request, out);
        for (String cle : MappingUrls.keySet()) {
            if (cle.equals(nameServlet)) {
                Mapping mapping = MappingUrls.get(cle);
                try {
                    Class<?> classe = Class.forName(mapping.getClassname());
                    Object instance = classe.newInstance();
                    // Utiliser l'objet instance ici
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception {
        PrintWriter out = response.getWriter();
        out.println("Nombre de method avec @Url "+MappingUrls.size());
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

  

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}

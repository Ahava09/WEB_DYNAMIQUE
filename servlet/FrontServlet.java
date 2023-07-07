
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1985.framework.servlet;

import java.io.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.relation.RoleResult;

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
import etu1985.framework.Url;
import etu1985.framework.servlet.*;
import jakarta.servlet.ServletConfig;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.security.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.Predicate;
import jakarta.servlet.http.Part;

/**
 *
 * @author itu
 */
@WebServlet("/FrontServlet")
@MultipartConfig
public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> MappingUrls = new HashMap<String, Mapping>();

    @SuppressWarnings("Unchecked")
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MappingUrls = new HashMap<>();
        try {
            String directory = getServletContext().getRealPath("\\WEB-INF\\classes\\etu1985\\model");
            String[] classe = reset(directory);
            for (int i = 0; i < classe.length; i++) {
                String className = classe[i];
                String name = classe[i];
                className = "etu1985.model." + className;
                Class<?> clazz;
                clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] an = method.getAnnotations();
                    if (an.length != 0) {
                        Url annotation = method.getAnnotation(Url.class);
                        MappingUrls.put(annotation.url(), new Mapping(name, method.getName()));
                    }
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    public String[] reset(String Directory) {
        ArrayList<String> rar = new ArrayList<>();
        File dossier = new File(Directory);
        String[] contenu = dossier.list();
        for (int i = 0; i < contenu.length; i++) {
            String fe = FilenameUtils.getExtension(contenu[i]);
            if (fe.equalsIgnoreCase("class")) {
                String[] value = contenu[i].split("[.]");
                rar.add(value[0]);
            }
        }
        return rar.toArray(new String[rar.size()]);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String url = getUrlArray(request);
            Mapping mapping = getMappingUrls(url);
            String directory = getServletContext().getRealPath("\\WEB-INF\\classes\\etu1985\\model");

            String[] classe = reset(directory);
            out.println(classe.length);
            if (mapping != null) {
                getDataNameView(url, request, response);
            }
            out.println(MappingUrls.size());
            out.close();
        } catch (Exception ex) {
            // Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(out);
            out.println(ex);
            throw new ServletException(ex);
            // Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            // Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(out);
            out.println(ex);
            throw new ServletException(ex);
        }
    }

    private String getUrlArray(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length() + 1);
    }

    private ModelView getUrlDispatcher(String key) throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Mapping mapping = getMappingUrls(key);
        ModelView m = null;
        if (mapping != null) {
            // out.println(mapping.getMethod());
            // out.println(mapping.getClassname());
            String className = "etu1985.model." + mapping.getClassname();
            Class<?> clazz;
            clazz = Class.forName(className);
            m = (ModelView) clazz.getDeclaredMethod(mapping.getMethod(), (Class) null)
                    .invoke(clazz.getConstructor().newInstance(), (Object[]) null);
        }
        return m;
    }

    private void loadView(ModelView view, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        PrintWriter out = response.getWriter();
        try {
            if (view != null) {
                if (view.getData().size() != 0) {
                    for (Map.Entry<String, Object> entry : view.getData().entrySet()) {
                        String key1 = entry.getKey();
                        Object value = entry.getValue();
                        request.setAttribute(key1, value);
                    }
                }
                RequestDispatcher dispat = request.getRequestDispatcher(view.getNameview());
                dispat.forward(request, response);
            } else {
                processRequest(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println(e);
            // e.printStackTrace(response.getWriter());
        }
    }

    public static String capitalize(String word) {
        if (word == null || word.length() == 0) {
            return word;
        }
        char firstChar = Character.toUpperCase(word.charAt(0));
        return firstChar + word.substring(1);
    }

    // Cast
    public double to_double(String a) {
        return Double.parseDouble(a);
    }

    public int to_int(String a) {
        return Integer.parseInt(a);
    }

    public Integer to_Integer(String a) {
        return Integer.valueOf(a);
    }

    public float to_float(String a) {
        return Float.parseFloat(a);
    }

    public String to_string(String a) {
        return a;
    }

    public Date to_date(String date) {
        int index_scape = date.lastIndexOf(" ");
        return Date.valueOf(date);
    }

    private Part hasFileInput(HttpServletRequest request, String name) throws IOException, ServletException {
        try {
            for (Part part : request.getParts()) {
                // if (part != null && part.getContentType() != null &&
                // part.getContentType().startsWith("multipart/form-data")) {
                if (part.getName().equalsIgnoreCase(name))
                    return part; // L'input est de type file
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // L'input n'est pas de type file
    }

    private Mapping getMappingUrls(String key) {
        Mapping mapping = MappingUrls.get(key);
        return mapping;
    }

    private Class getClasse(Mapping mapping) throws ClassNotFoundException {
        String className = "etu1985.model." + mapping.getClassname();
        return Class.forName(className);
    }

    private void getDataNameView(String key, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        PrintWriter out = response.getWriter();
        try {

            Enumeration<String> parameterNames = request.getParameterNames(); // Names dans view
            Mapping mapping = getMappingUrls(key);
            String className = "etu1985.model." + mapping.getClassname();
            Class<?> clazz = getClasse(mapping);
            Object ci = clazz.getConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Method methode = null;
            for (Method method : methods) {
                out.println(method.getName());
                if (method.getName().equals(mapping.getMethod())) {
                    methode = method;
                    break;
                }
            }
            // Obtention des types de paramètres de la méthode
            Class<?>[] parameterTypes = methode.getParameterTypes();
            // Obtenez les noms des paramètres en itérant sur les types de paramètres
            Parameter[] parameters = methode.getParameters();
            Object[] paramValues = new Object[parameterTypes.length];
            int paramCount = methode.getParameterCount();
            out.println(paramCount);
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType() == UploadFile.class) {
                        String setterMethodName = "set" + capitalize(field.getName());
                        Method m = clazz.getDeclaredMethod(setterMethodName, field.getType());
                        Part part = hasFileInput(request, field.getName());
                        if (part != null && part.getSize() > 0) {
                            UploadFile uploadFile = new UploadFile(part);
                            m.invoke(ci, uploadFile);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(out);
                e.printStackTrace();
            }
            if (paramCount == 0) {

                while (parameterNames.hasMoreElements()) {
                    String value = parameterNames.nextElement();
                    String paramName = capitalize(value);
                    String paramValue = request.getParameter(value);
                    String nameM = "set" + paramName;
                    for (Method method : methods) {
                        out.println(method.getName());

                        if (method.getName().equals(nameM)) {
                            if (Arrays.toString(method.getParameterTypes()).contains("String")) {
                                method.invoke(ci, paramValue);
                            } else if (Arrays.toString(method.getParameterTypes()).contains("int")) {
                                method.invoke(ci, to_int(paramValue));
                            } else if (Arrays.toString(method.getParameterTypes()).contains("double")) {
                                method.invoke(ci, to_double(paramValue));
                            } else if (Arrays.toString(method.getParameterTypes()).contains("float")) {
                                method.invoke(ci, to_float(paramValue));
                            } else if (Arrays.toString(method.getParameterTypes()).contains("Date")
                                    || Arrays.toString(method.getParameterTypes()).contains("date")) {
                                method.invoke(ci, to_date(paramValue));
                            }
                            break;
                        }
                    }
                }

            } else {

                String queryString = request.getQueryString(); // Récupère la chaîne de requête dans l'URL
                String[] queryParams = queryString.split("&");
                for (int k = 0; k < queryParams.length; k++) {
                    String[] paramTokens = queryParams[k].split("="); // Divise chaque chaîne de paramètre en un tableau
                                                                      // de chaînes séparées par des "="
                    String pName = paramTokens[0]; // Le premier élément est le nom du paramètre
                    String pValue = paramTokens[1]; // Le deuxième élément est la valeur du paramètre
                    out.println(pName + " ------------ " + pValue);
                    // Utiliser le nom et la valeur du paramètre ici

                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        Class<?> parameterType = parameterTypes[i];

                        out.println(parameterName + " " + pName);
                        if (parameterName.equalsIgnoreCase(pName)) {
                            if (parameterType == String.class) {
                                paramValues[i] = pValue;
                            } else if (parameterType == Integer.class) {
                                paramValues[i] = to_Integer(pValue);
                            } else if (parameterType == Double.class) {
                                paramValues[i] = to_double(pValue);
                            } else if (parameterType == Float.class) {
                                paramValues[i] = to_float(pValue);
                            } else if (parameterType == Date.class) {
                                paramValues[i] = to_date(pValue);
                            }
                            break;
                        }
                        if (i == parameters.length - 1) {
                            paramValues[i] = null;
                            out.println("Param " + i + " name: " + parameterName);
                            out.println("Param " + i + " type: " + parameterType.getSimpleName());
                        }
                    }
                }
            }
            if (methode.getReturnType() == ModelView.class) {
                if (paramCount == 0) {
                    ci = clazz.getDeclaredMethod(methode.getName()).invoke(ci, (Object[]) null);
                } else {
                    ci = clazz.getDeclaredMethod(methode.getName(), parameterTypes).invoke(ci, paramValues);
                }
                loadView((ModelView) ci, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
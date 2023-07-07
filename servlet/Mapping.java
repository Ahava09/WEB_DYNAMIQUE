package etu1985.framework;

public class Mapping{
    String classname;
    String method;

    public String getMethod() {
        return method;
    }
    public String getClassname() {
        return classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    
    public Mapping (String classname,String method){
        this.setClassname(classname);
        this.setMethod(method);
    }
}
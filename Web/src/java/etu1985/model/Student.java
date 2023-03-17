package etu1985.model;
import etu1985.model.Url;

public class Student {
    String firstame;
    String name;
    
    public String getFirstame() {
        return firstame;
    }
    public void setFirstame(String firstame) {
        this.firstame = firstame;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Student(String firstame,String name){
        this.setFirstame(firstame);
        this.setName(name);
    }

    @Url(url="findAllEmp")
    public Student findAll(){
       Student student = new Student(getName(),getFirstame());
        return student;
    }
    
}

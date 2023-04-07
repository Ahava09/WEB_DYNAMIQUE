package etu1985.model;
import etu1985.framework.Url;
import etu1985.framework.servlet.ModelView;

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

    public Student() {
    }
    
    

    @Url(url="findAllStudent")
    public ModelView findAll(){
       Object[] all = new Object[]{"1","Mino",6,0.2}; 
       
       ModelView mv = new ModelView("/page/all.jsp");
       mv.addItem("list",all);
       return  mv;
    }
    
}

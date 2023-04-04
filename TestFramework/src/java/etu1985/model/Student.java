package etu1985.model;
import etu1985.framework.Url;
import etu1985.framework.servlet.ModelView;
// url=student
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

    @Url(url="findAllStudent")
    public ModelView findAllS(){
        Object value = {1,2,3,9};
        ModelView mv = new ModelView("/page/all.jsp");
        return  mv;
    }

    public Student() {
    }
    
    
}

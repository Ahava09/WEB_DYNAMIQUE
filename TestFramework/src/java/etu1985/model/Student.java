package etu1985.model;
import etu1985.framework.Url;
import etu1985.framework.servlet.ModelView;
import java.lang.reflect.Field;
import java.sql.Date;

public class Student {
    int id;
    String firstame;
    String name;
    Date dtn;
    
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

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public Student(int id, String firstame,String name,Date date){
        this.setId(id);
        this.setFirstame(firstame);
        this.setName(name);
        this.setDtn(date);
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
    
    @Url(url="saveStudent")
    public ModelView save(){
        System.out.println("--------------------------------");
        System.out.println(this.getFirstame());
        System.out.println("--------------------------------");
        System.out.println(this.getName());
        System.out.println("--------------------------------");
         System.out.println(this.getDtn());
        System.out.println("--------------------------------");
        ModelView view = new ModelView("/page/input.jsp");
        Object[] all = new Object[]{this.getName(),this.getFirstame(),this.getDtn()}; 
        view.addItem("object", all);
        return view;
    }
    
    @Url(url="addStudent")
    public ModelView add(Integer id, String name,Date date){
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println(id);
        ModelView view = new ModelView("/page/input.jsp");
        Object[] all = { id , name, date}; 
        view.addItem("object", all);
        return view;
    }
}

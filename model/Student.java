package etu1985.model;

import etu1985.framework.IsSingleton;
import etu1985.framework.Url;
import etu1985.framework.servlet.ModelView;
import etu1985.framework.servlet.UploadFile;

import java.sql.Date;

@IsSingleton
public class Student {
    private int id;
    private String firstName;
    private String name;
    private Date dateOfBirth;
    private UploadFile myfile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UploadFile getMyfile() {
        return this.myfile;
    }

    public void setMyfile(UploadFile myfile) {
        this.myfile = myfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Student(int id, String firstName, String name, Date dateOfBirth, UploadFile myfile) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setName(name);
        this.setDateOfBirth(dateOfBirth);
        this.setMyfile(myfile);
    }

    public Student() {
    }

    @Url(url = "findAllStudent")
    public ModelView findAll() {
        Object[] all = new Object[]{"1", "Mino", 6, 0.2};
        ModelView mv = new ModelView("/page/all.jsp");
        mv.addItem("list", all);
        return mv;
    }

    @Url(url = "saveStudent")
    public ModelView save() {
        ModelView view = new ModelView("/page/input.jsp");
        view.addItem("object", this);
        return view;
    }

    @Url(url = "addStudent")
    public void add(Integer id, String name) {
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
    }
}

package etu1985.model;

import etu1985.framework.Url;

public class Prof {
    String name;
    String matiere;

    public String getMatiere() {
        return matiere;
    }
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Prof(String name, String matiere){
        this.setName(name);
        this.setMatiere(matiere);
    }
    @Url(url="findAllProf")
    public String findAll(){
        return "./page/all";
    }
}

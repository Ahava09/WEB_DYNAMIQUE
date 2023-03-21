package etu1985.model;

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
    @Url(url="findAllDept")
    public Prof findAll(){
        Prof prof = new Prof(getName(),getMatiere());
        return prof;
    }
}

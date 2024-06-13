package tpo.task_3.disease;

public class Disease {
    private String name;
    private TypeOfDisease type;

    public Disease(String name, TypeOfDisease type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfDisease getType() {
        return type;
    }

    public void setType(TypeOfDisease type) {
        this.type = type;
    }
}

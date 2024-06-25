package entity;

import java.util.FormattableFlags;

public class Pension {

    private int id;
    private String name;


    // constr.
    public Pension(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Pension(String name) {
        this.name = name;
    }

    public Pension() {
    }


    // getter and setter method
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

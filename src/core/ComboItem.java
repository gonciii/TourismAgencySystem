package core;

public class ComboItem {
    private int key;
    private String value;
    private int price;

    // constructor method - 3 parameters
    public ComboItem(int key,String value,int price) {
        this.key = key;
        this.value = value;
        this.price = price;
    }

    // constr. -- 2 parameters
    public ComboItem(int key,String value) {
        this.key = key;
        this.value = value;
    }

    // getter and setter methods
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

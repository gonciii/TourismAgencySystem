package core;

public class ListItem {
    private int key;
    private  String value;

   // const.
    public ListItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // getter and setter


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

package view;

import business.FeatureManager;
import business.RoomManager;
import core.Helper;
import entity.Room;

import javax.swing.*;

public class RoomView extends Layout {
    private JPanel container;
    private JLabel lbl_room;
    private JTextField fld_room_name;
    private JTextField fld_room_price;
    private JTextField fld_room_stock;
    private JButton btn_room_save;
    private JLabel lbl_room_name;
    private JLabel lbl_room_price;
    private JLabel lbl_room_stock;

    private Room room;
    private RoomManager roomManager;
    private FeatureManager featureManager;


    public RoomView(Room room){
        this.roomManager=new RoomManager();
        this.featureManager=new FeatureManager();
        this.add(container);
        this.guiInitilaze(300,400,"Room Panel");

        if(room != null){
            this.fld_room_name.setText(room.getName());
            this.fld_room_price.setText(String.valueOf(room.getPrice()));
            this.room.setStock(Integer.parseInt(fld_room_stock.getText()));
        }

        btn_room_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_room_name) || Helper.isFieldEmpty(this.fld_room_price)){
                Helper.showMsg("fill");
            }else {
                boolean result = true;
                if(this.room == null){
                    Room obj = new Room(fld_room_name.getText(),Integer.parseInt(fld_room_price.getText()),Integer.parseInt(fld_room_stock.getText()));
                    result = this.roomManager.save(obj);
                }else {
                    this.room.setName(fld_room_name.getText());
                    this.room.setPrice(Integer.parseInt(fld_room_price.getText()));
                    this.room.setStock(Integer.parseInt(fld_room_stock.getText()));
                    result = this.roomManager.update(this.room);
                }

                if (result){
                    Helper.showMsg("Room saved successfully");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });

    }
}

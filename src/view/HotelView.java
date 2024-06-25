package view;

import business.FeatureManager;
import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import dao.FeatureDao;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;
import entity.Room;

import javax.swing.*;

public class HotelView extends  Layout{

    private JPanel container;
    private JLabel lbl_hotel;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JLabel lbl_hotel_address;
    private JTextField fld_hotel_mail;
    private JLabel lbl_hotel_mail;
    private JTextField fld_hotel_phoneno;
    private JTextField fld_hotel_star;
    private JLabel lbl_hotel_phoneno;
    private JLabel lbl_hotel_star;
    private JLabel lbl_hotel_pensiontype;
    private JComboBox cmb_hotel_pensiontype;
    private JComboBox cmb_hotel_room;
    private JTextField fld_hotel_features;
    private JButton btn_hotel_save;
    private JLabel lbl_hotel_room;
    private JLabel lbl_hotel_features;
    private JList list_features;

    private Hotel hotel;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private FeatureManager featureManager;
    private RoomManager roomManager;
    private PensionDao pensionDao;
    private FeatureDao featureDao;


    public HotelView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.featureManager = new FeatureManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.hotel = hotel;
        this.guiInitilaze(600,600,"Hotel Panel");


        if(hotel != null) {
            this.fld_hotel_name.setText(hotel.getName());
            this.fld_hotel_address.setText(hotel.getAddress());
            this.fld_hotel_mail.setText(hotel.getMail());
            this.fld_hotel_phoneno.setText(hotel.getPhoneno());
            this.fld_hotel_star.setText(String.valueOf(hotel.getStar()));
            this.cmb_hotel_pensiontype.addItem(new ComboItem(hotel.getPensiontype().getId(),hotel.getPensiontype().getName()));
            this.cmb_hotel_room.addItem(new ComboItem(hotel.getRoomtype().getId(),hotel.getRoomtype().getName(),hotel.getRoomtype().getPrice()));
            this.fld_hotel_features.setText(String.valueOf(hotel.getFeatures()));
        }

        for(Pension pension : this.pensionManager.findAll()){
            this.cmb_hotel_pensiontype.addItem(new ComboItem(pension.getId(),pension.getName()));
        }

        for(Room room : this.roomManager.findAll()){
            this.cmb_hotel_room.addItem(new ComboItem(room.getId(),room.getName(),room.getPrice()));
        }


        btn_hotel_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_hotel_name)) {
                Helper.showMsg("Please fill in the hotel name.");
            } else {
                String name = this.fld_hotel_name.getText();
                String address = this.fld_hotel_address.getText();
                String mail = this.fld_hotel_mail.getText();
                String phoneno = this.fld_hotel_phoneno.getText();
                int star = Integer.parseInt(this.fld_hotel_star.getText());

                ComboItem selectedPensionItem = (ComboItem) this.cmb_hotel_pensiontype.getSelectedItem();
                int pensionTypeId = selectedPensionItem.getKey();
                Pension selectedPension = this.pensionManager.findById(pensionTypeId);

                // Seçilen özelliklerle bir Otel nesnesi oluşturalım

                ComboItem selectedRoomItem = (ComboItem) this.cmb_hotel_room.getSelectedItem();
                int roomId = selectedRoomItem.getKey();
                Room selectedRoom = this.roomManager.findById(roomId);
                String features = this.fld_hotel_features.getText();

                Hotel newHotel = new Hotel(name, address, mail, phoneno, star, selectedPension, selectedRoom,features);
                boolean result;

                if (this.hotel == null) {
                    // yeni hotel nesnesini kaydet
                    result = this.hotelManager.save(newHotel);
                } else {
                    // hotel nesnesini güncelle
                    newHotel.setId(this.hotel.getId()); // Güncelleme için mevcut Otel'in ID'sini ayarla
                    result = this.hotelManager.update(newHotel);
                }

                if (result) {
                    Helper.showMsg("Hotel saved successfully.");
                    dispose(); // close window
                } else {
                    Helper.showMsg("Error occurred while saving hotel.");
                }

            }
        });
    }



}

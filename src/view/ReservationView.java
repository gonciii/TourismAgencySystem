package view;

import business.HotelManager;
import business.ReservationManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Reservation;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class ReservationView extends Layout{
    private JPanel container;
    private JTextField fld_customer_name;
    private JLabel lbl_reservation;
    private JComboBox cmb_hotel;
    private JTextField fld_start_date;
    private JTextField fld_fnsh_date;
    private JTextField fld_adult_number;
    private JTextField fld_child_number;
    private JButton btn_reservation_save;
    private JLabel lbl_customer_name;
    private JLabel lbl_hotel;
    private JLabel lbl_start_date;
    private JLabel lbl_fnsh_date;
    private JLabel lbl_adult_number;
    private JLabel lbl_child_number;

    private ReservationManager reservationManager;
    private HotelManager hotelManager;
    private Reservation reservation;

    public ReservationView(Reservation reservation) {
        this.reservation =reservation;
        this.reservationManager=new ReservationManager();
        this.hotelManager=new HotelManager();
        this.add(container);
        this.guiInitilaze(500,500,"Reservation Panel");

        for(Hotel otel :this.hotelManager.findAll()){
            this.cmb_hotel.addItem(otel.getComboItem());
        }

        if(reservation != null){
            this.fld_customer_name.setText(reservation.getCustomer_name());
            ComboItem selectedItem = reservation.getOtel().getComboItem();
            this.cmb_hotel.getModel().setSelectedItem(selectedItem);
            this.fld_start_date.setText(String.valueOf(Date.valueOf(reservation.getStrt_date())));
            this.fld_fnsh_date.setText(String.valueOf(Date.valueOf(reservation.getFnsh_date())));
            this.fld_adult_number.setText(Integer.toString(reservation.getAdult_number()));
            this.fld_child_number.setText(Integer.toString(reservation.getChild_number()));
        }

        this.btn_reservation_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_customer_name,fld_adult_number})){
                Helper.showMsg("fill");
            }else {
                String customer_name = this.fld_customer_name.getText();
                LocalDate strt_date = LocalDate.parse(this.fld_start_date.getText());
                LocalDate fnsh_date = LocalDate.parse(this.fld_fnsh_date.getText());
                String adult_number = this.fld_adult_number.getText();
                String child_number = this.fld_child_number.getText();

                ComboItem selectedHotelItem = (ComboItem) this.cmb_hotel.getSelectedItem();
                int hotelId = selectedHotelItem.getKey();
                Hotel selectedHotel = this.hotelManager.findById(hotelId);
                System.out.println(selectedHotel);


                Reservation newReservation = new Reservation(customer_name,selectedHotel, strt_date, fnsh_date, adult_number, child_number);
                boolean result;

                if (this.reservation == null) {
                    // Yeni Otel nesnesini kaydet
                    result = this.reservationManager.save(newReservation);
                } else {
                    // Var olan Otel nesnesini güncelle
                    newReservation.setId(this.reservation.getId()); // Güncelleme için mevcut Otel'in ID'sini ayarla
                    result = this.reservationManager.update(newReservation);
                }

                if (result) {
                    Helper.showMsg("Reservation saved successfully.");
                    dispose();
                } else {
                    Helper.showMsg("Error occurred while saving hotel.");
                }

            }
        });
    }
}

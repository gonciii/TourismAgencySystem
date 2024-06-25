package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class SeasonView extends Layout {
    // variables
    private JPanel contanier;
    private JLabel lbl_heading;
    private JLabel lbl_start_date;
    private JFormattedTextField fld_strt_day;
    private JLabel lbl_finish_date;
    private JFormattedTextField fld_finish_date;
    private JTextField fld_season_hotel_id;
    private JLabel lbl_season_hotel_id;
    private JButton btn_save;
    private JTextField fld_season_factor;
    private JLabel lbl_season_factor;
    private JComboBox cmb_hotel_name;
    private Season season;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;
    private int hotelId;

    // constructor
    public SeasonView(Season season) {
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.add(contanier);
        this.guiInitiliaze(300, 400);

        List<String> otelName = hotelManager.getTumOtelIsimleri();
        cmb_hotel_name.setModel(new DefaultComboBoxModel<>(otelName.toArray(new String[0])));

        cmb_hotel_name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String secilenOtelAdi = (String) cmb_hotel_name.getSelectedItem();
                hotelId = hotelManager.getByHotelId(secilenOtelAdi);
            }
        });


        if (this.season.getSeasonId() != 0) {
            String hotelName = hotelManager.getByName(this.season.getHotelId());
            this.cmb_hotel_name.setSelectedItem(hotelName);
            this.fld_strt_day.setText(String.valueOf(this.season.getStrt_date()));
            this.fld_finish_date.setText(String.valueOf(this.season.getFnsh_date()));
            this.fld_season_factor.setText(String.valueOf(this.season.getSeason_factor()));
        }


        // renk
        contanier.setBackground(Color.ORANGE);

        // Değerlendirme 11
        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_strt_day, this.fld_finish_date,this.fld_season_factor})) {
                Helper.showMsg("fill");
            } else {
                boolean result;


                this.season.setHotelId(hotelId);
                this.season.setStrt_date(LocalDate.parse(fld_strt_day.getText()));
                this.season.setFnsh_date(LocalDate.parse(fld_finish_date.getText()));
                this.season.setSeason_factor(Double.parseDouble(fld_season_factor.getText()));


                if (this.season.getSeasonId() != 0) {
                    result = this.seasonManager.update(this.season);

                } else {
                    result = this.seasonManager.save(this.season);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });


    }
}



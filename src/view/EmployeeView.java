package view;

import business.*;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JPanel pnl_reservation;
    private JPanel pnl_hotel;
    private JPanel pnl_pension;
    private JPanel pnl_feature;
    private JPanel pnl_season;
    private JPanel pnl_user;
    private JPanel pnl_room;
    private JButton btn_logout;
    private JScrollPane scrl_reservation;
    private JTable tbl_reservation;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JScrollPane scrl_pension;
    private JTable tbl_pension;
    private JTable tbl_feature;
    private JScrollPane scrl_feature;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JScrollPane scrl_room;
    private JTable tbl_room;

    private User user;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pension= new DefaultTableModel();
    private DefaultTableModel tmdl_feature = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();

    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private FeatureManager featureManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private UserManager userManager;
    private RoomManager roomManager;
    private JPopupMenu pensionMenu;
    private JPopupMenu featureMenu;
    private JPopupMenu hotelMenu;
    private JPopupMenu seasonMenu;
    private JPopupMenu userMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;


    public EmployeeView(User user){
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.featureManager = new FeatureManager();
        this.seasonManager=new SeasonManager();
        this.userManager = new UserManager();
        this.roomManager=new RoomManager();
        this.reservationManager = new ReservationManager();

        this.add(container);
        this.guiInitilaze(1000,500,"Employee Panel");
        this.user = user;

        if (user == null){
            dispose();    // close
        }

        this.lbl_welcome.setText("Welcome :" + this.user.getUserFullName());

        //---------------------------------------------------------------------------------------------

        loadOtelTable();
        loadOtelComponent();


        //---------------------------------------------------------------------------------------------
        loadPensionTable();
        loadPensionComponent();
        //---------------------------------------------------------------------------------------------
        loadFeatureTable();
        loadFeatureComponent();
        //---------------------------------------------------------------------------------------------
        loadSeasonTable();
        loadSeasonComponent();
        //---------------------------------------------------------------------------------------------
        loadRoomTable();
        loadRoomComponent();
        //---------------------------------------------------------------------------------------------
        loadReservationTable();
    }


    public void loadPensionTable(){
        Object[] col_pension = {"ID","Pension Name"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length);
        this.createTable(tmdl_pension,tbl_pension,col_pension,pensionList);
    }
    public void loadPensionComponent(){
        this.tbl_pension.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_pension.rowAtPoint(e.getPoint());
                tbl_pension.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.pensionMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.pensionMenu.add("Yeni").addActionListener(e -> {
            PensionView pensionView = new PensionView(null);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Güncelle").addActionListener(e -> {
            int selectPensionId = Integer.parseInt(tbl_pension.getValueAt(tbl_pension.getSelectedRow(),0).toString());
            PensionView pensionView = new PensionView(this.pensionManager.findById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Sil").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selecPensionId = this.getTableSelectedRow(tbl_pension,0);
                if(this.pensionManager.delete(selecPensionId)){
                    loadPensionTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_pension.setComponentPopupMenu(pensionMenu);
    }


    public void loadFeatureTable(){
        Object[] col_feature = {"ID","Facility Feature Name"};
        ArrayList<Object[]> featureList = this.featureManager.getForTable(col_feature.length);
        this.createTable(tmdl_feature,tbl_feature,col_feature,featureList);
    }
    public void loadFeatureComponent(){
        this.tbl_feature.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_feature.rowAtPoint(e.getPoint());
                tbl_feature.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.featureMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.featureMenu.add("Yeni").addActionListener(e -> {
            FeatureView featureView = new FeatureView(null);
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.featureMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_feature.getValueAt(tbl_feature.getSelectedRow(),0).toString());
            FeatureView featureView = new FeatureView(this.featureManager.findById(selectFeatureId));
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.featureMenu.add("Sil").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selectFeatureId = this.getTableSelectedRow(tbl_feature,0);
                if(this.featureManager.delete(selectFeatureId)){
                    loadFeatureTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_feature.setComponentPopupMenu(featureMenu);
    }
    public void loadOtelTable(){
        Object[] col_otel = {"Hotel ID", "Hotel Name", "Hotel Address", "Mail", "Phone Number", "Star", "Pension Type", "Room Type", "Facility Features"};
        ArrayList<Object[]> otelList = this.hotelManager.getForTable(col_otel.length, this.hotelManager.findAll());
        this.createTable(tmdl_hotel,tbl_hotel,col_otel,otelList);
    }
    public void loadOtelComponent(){
        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.hotelMenu = new JPopupMenu();

        JMenuItem newUserItem = new JMenuItem("Yeni");
        this.hotelMenu.add("Yeni").addActionListener(e -> {
            HotelView hotelView = new HotelView(null);
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable();
                }
            });
        });
        this.hotelMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString());
            HotelView otelView = new HotelView(this.hotelManager.findById(selectFeatureId));
            otelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.hotelMenu.add("Sil");

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
    }
    public void loadSeasonTable(){
        Object[] col_season = {"ID","Season Name","Starting Date","End Date","Price Coefficient"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length);
        this.createTable(tmdl_season,tbl_season,col_season,seasonList);
    }
    public void loadSeasonComponent(){
        this.tbl_season.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_season.rowAtPoint(e.getPoint());
                tbl_season.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.seasonMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.seasonMenu.add("Yeni").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(null);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable();
                }
            });
        });
        this.seasonMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(),0).toString());
            SeasonView seasonView = new SeasonView(this.seasonManager.findById(selectFeatureId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.seasonMenu.add("Sil");

        this.tbl_season.setComponentPopupMenu(seasonMenu);
    }
    public void loadRoomTable(){
        Object[] col_room= {"ID","Room Name","Room Price","Room Stock"};
        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length);
        this.createTable(tmdl_room,tbl_room,col_room,roomList);
    }
    public void loadRoomComponent(){
        this.tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.roomMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.roomMenu.add("Yeni").addActionListener(e -> {
            RoomView roomView = new RoomView(null);
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_room,0);
            RoomView roomView = new RoomView(this.roomManager.findById(selectUserId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Sil").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_room,0);
                if(this.roomManager.delete(selectUserId)){
                    loadRoomTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_room.setComponentPopupMenu(roomMenu);
    }
    public void loadReservationTable(){
        Object[] col_reservation = {"ID","Customer Name","Hotel Informaiton","Starting Date","End Date","Number Of Adult ","Number Of Child","Total Price"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length);
        this.createTable(tmdl_reservation,tbl_reservation,col_reservation,reservationList);
    }




}

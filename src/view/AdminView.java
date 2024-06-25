package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminView extends Layout {

    private JPanel container;
    private JTabbedPane tab_menu;
    private JPanel pnl_reservation;
    private JPanel pnl_hotel;
    private JPanel pnl_pension;
    private JPanel pnl_feature;
    private JPanel pnl_season;
    private JPanel pnl_user;
    private JPanel pnl_room;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JScrollPane scrl_reservation;
    private JTable tbl_reservation;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_pension;
    private JTable tbl_pension;
    private JScrollPane scrl_feature;
    private JTable tbl_feature;
    private JScrollPane scrl_season;
    private JTable tbl_season;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JScrollPane scrl_room;
    private JTable tbl_room;
    private JTable tbl_hotel;
    private JComboBox cmb_pensiontype;
    private JComboBox cmb_roomtype;
    private JButton btn_search_hotel;
    private JButton btn_cancel_hotel;


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
    private  Object[] col_hotel;


    public AdminView(User user){
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.featureManager = new FeatureManager();
        this.seasonManager=new SeasonManager();
        this.userManager = new UserManager();
        this.roomManager=new RoomManager();
        this.reservationManager = new ReservationManager();

        this.add(container);
        this.guiInitilaze(1000,500," Admin Panel");
        this.user = user;
        if (user == null){
            dispose();
        }

        this.lbl_welcome.setText(" Welcome : " + this.user.getUserFullName());



        loadOtelTable(null);
        loadOtelComponent();
        loadOtelFliter();


        loadPensionTable();
        loadPensionComponent();

        loadFeatureTable();
        loadFeatureComponent();

        loadSeasonTable();
        loadSeasonComponent();

        loadUserTable();
        loadUserComponent();

        loadRoomTable();
        loadRoomComponent();

        loadReservationTable();
        loadReservationComponent();

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

        JMenuItem newPensionItem = new JMenuItem("New");
        this.pensionMenu.add("New").addActionListener(e -> {
            PensionView pensionView = new PensionView(null);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Update").addActionListener(e -> {
            int selectPensionId = Integer.parseInt(tbl_pension.getValueAt(tbl_pension.getSelectedRow(),0).toString());
            PensionView pensionView = new PensionView(this.pensionManager.findById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Delete").addActionListener(e -> {
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

        JMenuItem newPensionItem = new JMenuItem("New");
        this.featureMenu.add("New").addActionListener(e -> {
            FeatureView featureView = new FeatureView(null);
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.featureMenu.add("Update").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_feature.getValueAt(tbl_feature.getSelectedRow(),0).toString());
            FeatureView featureView = new FeatureView(this.featureManager.findById(selectFeatureId));
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();

                }
            });
        });
        this.featureMenu.add("Delete").addActionListener(e -> {
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
    public void loadOtelTable(ArrayList<Object[]> otelList){
        this.col_hotel = new Object[]{"Hotel ID", "Hotel Name", "Hotel Address", "Mail", "Phone Number", "Star", "Pension Type", "Room Type", "Facility Features"};
        if(otelList == null){
            otelList = this.hotelManager.getForTable(this.col_hotel.length,this.hotelManager.findAll());
        }
        createTable(this.tmdl_hotel,this.tbl_hotel,col_hotel,otelList);
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

        JMenuItem newUserItem = new JMenuItem("New");
        this.hotelMenu.add("New").addActionListener(e -> {
            HotelView hotelView = new HotelView(null);
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                    loadOtelFliter();
                }
            });
        });
        this.hotelMenu.add("Update").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString());
            HotelView otelView = new HotelView(this.hotelManager.findById(selectFeatureId));
            otelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                    loadOtelFliter();
                }
            });
        });
        this.hotelMenu.add("Delete").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selectOtelId = this.getTableSelectedRow(tbl_hotel,0);
                if(this.hotelManager.delete(selectOtelId)){
                    loadOtelTable(null);
                    loadOtelFliter();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);

        // search butonu
        this.btn_search_hotel.addActionListener(e -> {
            ComboItem selectedPension = (ComboItem) this.cmb_pensiontype.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) this.cmb_roomtype.getSelectedItem();

            ArrayList<Hotel> hotelListBySearch = this.hotelManager.searchForTable(selectedPension.getKey(),selectedRoom.getKey());
            System.out.println(hotelListBySearch);

            ArrayList<Object[]> hotelRowListBySearch = this.hotelManager.getForTable(this.col_hotel.length,hotelListBySearch);
            loadOtelTable(hotelRowListBySearch);
        });

        // reset butonu
        this.btn_cancel_hotel.addActionListener(e -> {
            this.cmb_pensiontype.setSelectedItem(null);
            this.cmb_roomtype.setSelectedItem(null);
            loadOtelTable(null);
        });


    }
    // sezon tablosu
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

        JMenuItem newPensionItem = new JMenuItem("New");
        this.seasonMenu.add("New").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(null);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                }
            });
        });
        this.seasonMenu.add("Update").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(),0).toString());
            SeasonView seasonView = new SeasonView(this.seasonManager.findById(selectFeatureId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.seasonMenu.add("Delete");

        this.tbl_season.setComponentPopupMenu(seasonMenu);
    }

    // kullanıcı tablosu
    public void loadUserTable(){
        Object[] col_user = {"ID","User Name","Password","Role","Name Surname"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length);
        this.createTable(tmdl_user,tbl_user,col_user,userList);
    }
    public void loadUserComponent(){
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.userMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("New");
        this.userMenu.add("New").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.userMenu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user,0);
            UserView userView = new UserView(this.userManager.findById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.userMenu.add("Delete").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_user,0);
                if(this.userManager.delete(selectUserId)){
                    loadUserTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(userMenu);
    }

    // oda tablosu
    public void loadRoomTable(){
        Object[] col_room= {"ID","Room Name","Room Price","Stock"};
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

        JMenuItem newPensionItem = new JMenuItem("New");
        this.roomMenu.add("New").addActionListener(e -> {
            RoomView roomView = new RoomView(null);
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_room,0);
            RoomView roomView = new RoomView(this.roomManager.findById(selectUserId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Delete").addActionListener(e -> {
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

    // rezervasyon tablosu
    public void loadReservationTable(){
        Object[] col_reservation = {"ID","Customer Name","Hotel Informaiton","Starting Date","End Date","Number Of Adult","Number Of Child","Total Price"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length);
        createTable(this.tmdl_reservation,this.tbl_reservation,col_reservation,reservationList);
    }



    public void loadReservationComponent(){
        this.tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_reservation.rowAtPoint(e.getPoint());
                tbl_reservation.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.reservationMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("New");
        this.reservationMenu.add("New").addActionListener(e -> {
            ReservationView reservationView = new ReservationView(null);
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.reservationMenu.add("Update").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation,0);
            ReservationView reservationView = new ReservationView(this.reservationManager.findById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.reservationMenu.add("Delete").addActionListener(e -> {
            if(Helper.showMassageSure("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_reservation,0);
                if(this.reservationManager.delete(selectUserId)){
                    loadReservationTable();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(reservationMenu);
    }

    public void loadOtelFliter(){
        this.cmb_pensiontype.removeAllItems();
        for(Pension obj : pensionManager.findAll()){
            this.cmb_pensiontype.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_pensiontype.setSelectedItem(null);
        this.cmb_roomtype.removeAllItems();
        for(Room obj : roomManager.findAll()){
            this.cmb_roomtype.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_roomtype.setSelectedItem(null);

    }



}

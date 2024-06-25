package dao;

import core.DB;
import entity.Hotel;
import entity.Pension;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    // variables
    private final Connection con;
    private final PensionDao pensionDao;
    private final FeatureDao featureDao;
    private final RoomDao roomDao;

    // constructor
    public HotelDao() {
        this.con = DB.getInstance();
        this.pensionDao = new PensionDao();
        this.featureDao = new FeatureDao();
        this.roomDao = new RoomDao();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();

        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhoneno(rs.getString("hotel_phoneno"));
        obj.setStar(rs.getInt("hotel_star"));

        int pensionId = rs.getInt("hotel_pensiontype_id");
        Pension pension = pensionDao.findById(pensionId);
        obj.setPensiontype(pension);

        int roomId = rs.getInt("hotel_room_id");
        Room room = roomDao.findById(roomId);
        obj.setRoomtype(room);

        obj.setFeatures(rs.getString("hotel_features_id"));
        return obj;
    }

    public Hotel findById(int id) {
        Hotel hotel = null;  // default
        String sql = "SELECT * FROM public.hotel WHERE hotel_id=?";

        try {
            PreparedStatement pr = this.con.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hotel = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public boolean save(Hotel hotel) {
        String sql = "INSERT INTO hotel (" +
                "hotel_name," +
                "hotel_address," +
                "hotel_mail," +
                "hotel_phoneno," +
                "hotel_star," +
                "hotel_pensiontype_id," +
                "hotel_room_id," +
                "hotel_features " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getMail());
            ps.setString(4, hotel.getPhoneno());
            ps.setInt(5, hotel.getStar());
            ps.setInt(6, hotel.getPensiontype().getId()); // Pension type ID
            ps.setInt(7, hotel.getRoomtype().getId()); // Feature IDs
            ps.setString(8, hotel.getFeatures()); // Room type ID
            return ps.executeUpdate() != -1;


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String sql = "UPDATE hotel " +
                "SET hotel_name = ? " +
                "hotel_address = ?" +
                "hotel_mail = ?" +
                "hotel_phoneno = ?" +
                "hotel_star = ?" +
                "hotel_pensiontype_id = ? " +
                "hotel_room_id = ?" +
                "hotel_features = ?" +
                "WHERE hotel_id = ? ";

        try {
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getMail());
            ps.setString(4, hotel.getPhoneno());
            ps.setInt(5, hotel.getStar());
            ps.setInt(6, hotel.getPensiontype().getId());
            ps.setInt(7, hotel.getRoomtype().getId());
            ps.setString(8, hotel.getFeatures());
            ps.setInt(9, hotel.getId());
            return ps.executeUpdate() != -1;


        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }


    }

    public boolean delete(int id) {
        String sql = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(sql);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    public ArrayList<Hotel> getByListPensionId(int pensionId) {
        return this.selectByQuery("SELECT * FROM public.hotel WHERE hotel_pensiontype_id = " + pensionId);

    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelArrayList.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelArrayList;
    }



}

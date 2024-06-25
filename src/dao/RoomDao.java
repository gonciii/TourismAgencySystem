package dao;

import core.DB;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection conn;

    public RoomDao() {
        this.conn = DB.getInstance();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room ORDER BY room_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Room findById(int id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                room = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    public boolean save(Room room){
        String query = "INSERT INTO public.room (room_name,room_price,room_stock) VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1,room.getName());
            pr.setInt(2,room.getPrice());
            pr.setInt(3,room.getStock());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Room room){
        String query = "UPDATE public.room SET room_name = ? WHERE room_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1,room.getName());
            pr.setInt(2,room.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try{
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setName(rs.getString("room_name"));
        room.setPrice(rs.getInt("room_price"));
        room.setStock(rs.getInt("room_stock"));
        return room;
    }
}

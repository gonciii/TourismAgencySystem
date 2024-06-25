package dao;

import core.DB;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection con;
    private static UserDao instance = null;

    public UserDao() {
        this.con = DB.getInstance();

    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }
    public boolean save(User user) {
        String sqlQuery = "INSERT INTO public.user (user_name,user_password,user_role,user_full_name) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(sqlQuery);
            pr.setString(1,user.getUserName());
            pr.setString(2,user.getUserPassword());
            pr.setString(3,user.getUserRole());
            pr.setString(4,user.getUserFullName());
            return pr.executeUpdate() != -1;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(User user) {
        String query = "UPDATE public.user SET user_name = ?,user_password = ?,user_role = ?,user_fullname = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,user.getUserName());
            pr.setString(2,user.getUserPassword());
            pr.setString(3,user.getUserRole());
            pr.setString(4,user.getUserFullName());
            pr.setInt(5,user.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // user tablosunda ki bütün bilgileri getirir !
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;

    }
    public User findById(int id) {
        User user = null;
        String sql = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = this.match(rs);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public User findByLogin(String username,String password) {
        User obj =  new User();
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);  // 1.soru  işaretini parametreden gelen username ile değiştir.
            pr.setString(2,password);  // 2.soru işaretini parametreden gelen password ile değiştir.
            ResultSet rs = pr.executeQuery();       // ve eğer bu işlem varsa resulset'e döndür.
            if(rs.next()) {
                // veri tabanından gelen veriyi içeride oluşturulan objeye atadık ve return objeyi sağladık.
                obj = this.match(rs);   // veriyi otomatik modelere çevirdik
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;

    }


    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUserName(rs.getString("user_name"));
        obj.setUserPassword(rs.getString("user_password"));
        obj.setUserRole(rs.getString("user_role"));
        obj.setUserFullName(rs.getString("user_full_name"));

        return obj;

    }

}

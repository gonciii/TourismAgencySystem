package dao;

import core.Db;
import entity.Room;
import entity.Season;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonDao {
    private final Connection conn;

        public SeasonDao() {
            this.conn = Db.getInstance();
        }

        public ArrayList<Season> findAll() {
            ArrayList<Season> seasonList = new ArrayList<>();
            String query = "SELECT * FROM public.season ORDER BY season_id ASC";
            try {
                ResultSet rs = this.conn.createStatement().executeQuery(query);
                while (rs.next()) {
                    seasonList.add(this.match(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return seasonList;
        }

        public Season match(ResultSet rs) throws SQLException {
            Season obj = new Season();
            obj.setSeasonId(rs.getInt("season_id"));
            obj.setHotelId(rs.getInt("hotel_id"));
            obj.setStrt_date(LocalDate.parse(rs.getString("baslangic")));
            obj.setFnsh_date(LocalDate.parse(rs.getString("bitis")));
            obj.setSeason_factor(rs.getDouble("season_factor"));


            return obj;

        }

        public boolean save(Season season) {
            String query = "INSERT INTO public.season " +
                    "(" +
                    "hotel_id," +
                    "baslangic," +
                    "bitis," +
                    "season_factor" +
                    ")" +
                    " VALUES (?, ?, ?, ?)";


            try {
                PreparedStatement pr = this.conn.prepareStatement(query);
                pr.setInt(1, season.getHotelId());
                pr.setDate(2, Date.valueOf(season.getStrt_date()));
                pr.setDate(3, Date.valueOf(season.getFnsh_date()));
                pr.setDouble(4, season.getSeason_factor());
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }

        public boolean update(Season season) {
            String query = "UPDATE public.season SET " +
                    "hotel_id = ? ," +
                    "baslangic = ? , " +
                    "bitis = ?,  " +
                    "season_factor = ? " +
                    "WHERE season_id = ?";


            try {

                PreparedStatement pr = this.conn.prepareStatement(query);
                pr.setInt(1, season.getHotelId());
                pr.setDate(2, Date.valueOf(season.getStrt_date()));
                pr.setDate(3, Date.valueOf(season.getFnsh_date()));
                pr.setDouble(4, season.getSeason_factor());

                pr.setInt(5, season.getSeasonId());
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }

        public boolean delete(int season_id) {
            String query = "DELETE FROM public.season WHERE season_id = ?";
            try {
                PreparedStatement pr = this.conn.prepareStatement(query);
                pr.setInt(1, season_id);
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        public Season getById(int id) {
            Season obj = null;
            String query = "SELECT * FROM public.season WHERE season_id = ?";
            try {
                PreparedStatement pr = this.conn.prepareStatement(query);
                pr.setInt(1, id);
                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    obj = this.match(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return obj;
        }

    public List<Season> getSeasonsByHotelId(int hotelId) {
        List<Season> seasons = new ArrayList<>();
        try {
            // Veritabanından otel id ye göre Sezonları sorgula
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM season WHERE hotel_id = ?");
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            // Sorgu sonucunda dönen sezonları listeye ekle
            while (resultSet.next()) {
                Season season = new Season();
                season.setSeasonId(resultSet.getInt("season_id"));
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda boş bir liste döndür
            return seasons;
        }
        // Sezonları içeren listeyi döndür
        return seasons;

    }

    }


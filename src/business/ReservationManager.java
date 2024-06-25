package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.*;
import entity.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {

    private  ReservationDao reservationDao;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private Room room ;
    private Hotel hotel ;

    public ReservationManager() {

        this.reservationDao = new ReservationDao();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.room = new Room();
        this.hotel = new Hotel();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation obj : reservations) {
            Object[] rowObject = new Object[size];

            int i = 0;
            this.room = roomManager.getById(obj.getRoom_id());
            this.hotel = hotelManager.getById(room.getHotel_id());

            rowObject[i++] = obj.getReservation_id();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = room.getPension_type();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = obj.getCheckinDate();
            rowObject[i++] = obj.getCheckoutDate();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getGuestCount();
            rowObject[i++] = obj.getGuestName();
            rowObject[i++] = obj.getGuestId();
            rowObject[i++] = obj.getGuestMail();
            rowObject[i++] = obj.getGuestPhone();


            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public boolean save(Reservation reservation) {
        if (reservation.getReservation_id() != 0) {
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) == null) {
            Helper.showMsg("notfound");
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.reservationDao.delete(id);
    }

    public double searchForSeasonFactor(int hotel_id, String giris, String cikis) {
        return this.reservationDao.searchForSeasonFactor(hotel_id, giris, cikis);
    }

    public double searchForPensionFactor(int hotel_id, String type) {
        return this.reservationDao.searchForPensionFactor(hotel_id, type);
    }

    public List<Integer> getReservationIdsByRoomIds(List<Room> roomsToDelete) {
        return this.reservationDao.getReservationIdsByRoomIds(roomsToDelete);
    }

    public List<Reservation> getReservationByRoomId(int roomId) {
        return this.reservationDao.getReservationByRoomId(roomId);
    }
}

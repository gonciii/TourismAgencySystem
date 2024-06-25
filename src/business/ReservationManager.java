package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public  Reservation findById(int id){
        return this.reservationDao.findById(id);
    }

    public boolean save(Reservation reservation){
        if(reservation.getId() != 0){
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }


    public boolean update(Reservation reservation){
        if(this.findById(reservation.getId()) == null){
            Helper.showMsg("notfound");
        }
        return this.reservationDao.update(reservation);
    }


    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMsg(id+ " ID no registered hotel found.");
            return false;
        }
        return this.reservationDao.delete(id);
    }


    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> reservationRowList = new ArrayList<>();
        for(Reservation reservation : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getCustomer_name();
            rowObject[i++] = reservation.getOtel().toString();
            rowObject[i++] = reservation.getStrt_date();
            rowObject[i++] = reservation.getFnsh_date();
            rowObject[i++] = reservation.getAdult_number();
            rowObject[i++] = reservation.getChild_number();
            rowObject[i++] = reservation.getTotal_price();

            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }

}

package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public Hotel findById(int id) {
        return this.hotelDao.findById(id);
    }

    public boolean delete (int id) {
        if (this.findById(id) == null) {
            Helper.showMsg(id + "ID no registered hotel found");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    public boolean save(Hotel hotel){
        if(hotel.getId() != 0){
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }
    public boolean update(Hotel hotel){
        if(this.findById(hotel.getId()) == null){
            Helper.showMsg("notfound");
        }
        return this.hotelDao.update(hotel);
    }


    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> all){
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for(Hotel hotel : all){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getMail();
            rowObject[i++] = hotel.getPhoneno();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.getPensiontype().getName();
            rowObject[i++] = hotel.getRoomtype().getName();
            rowObject[i++] = hotel.getFeatures();
            hotelRowList.add(rowObject);


        }
        return hotelRowList;
    }


    public  ArrayList<Hotel> searchForTable(int pension_id, int room_id  ){
        String select = "SELECT * FROM public.hotel";
        ArrayList<String> wherelist = new ArrayList<>();
        if(pension_id != 0){
            wherelist.add("hotel_pensiontype_id ="+pension_id);

        }
        if (room_id != 0){
            wherelist.add("hotel_room_id ="+room_id);
        }

        String whereStr = String.join(" AND ",wherelist);
        String query = select;
        if(whereStr.length() > 0){
            query += " WHERE "+whereStr;
        }
        return  this.hotelDao.selectByQuery(query);
    }


}

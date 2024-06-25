package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
    public Room findById(int id){
        return this.roomDao.findById(id);
    }

    public boolean save(Room room){
        if(room.getId() != 0){
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room){
        if(this.findById(room.getId()) == null){
            Helper.showMsg("notfound");
        }
        return this.roomDao.update(room);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMsg(id+ "ID no registered hotel found.");
            return false;
        }
        return this.roomDao.delete(id);
    }


    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getName();
            rowObject[i++] = room.getPrice();
            rowObject[i++] = room.getStock();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

}

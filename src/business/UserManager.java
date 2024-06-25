package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PropertyResourceBundle;

public class UserManager {
    private final UserDao userDao;
    private static UserManager instance ;


    // constructor
    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username,String password) {
        // bu kısım ilk login ekranım için
        return this.userDao.findByLogin(username,password);
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public boolean save(User user) {
        if(user.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }

    public User findById(int id) {
        return this.userDao.findById(id);
    }

    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i= 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUserName();
            rowObject[i++] = user.getUserPassword();
            rowObject[i++] = user.getUserRole();
            rowObject[i++] = user.getUserFullName();
            userRowList.add(rowObject);
         }
        return userRowList;
    }

    public boolean delete(int id) {
        if(this.findById(id) == null) {
            Helper.showMsg(id+" ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }
    public boolean update(User user){
        if(this.findById(user.getId()) == null){
            Helper.showMsg("notfound");
        }
        return this.userDao.update(user);
    }



}

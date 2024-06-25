package business;

import core.Helper;
import dao.FeatureDao;
import entity.Feature;

import java.util.ArrayList;

public class FeatureManager {
    private final FeatureDao featureDao;


    public FeatureManager() {
        this.featureDao = new FeatureDao();
    }

    public ArrayList<Feature> findAll() {
        return this.featureDao.findAll();
    }


    // save
    public boolean save(Feature feature) {
        if (feature.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.featureDao.save(feature);
    }

    // edit --> update
    public boolean update(Feature feature) {
        if(this.findById(feature.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.featureDao.update(feature);
    }

    // delete
    public boolean delete(int id) {
        if (this.findById(id) == null) {
            Helper.showMsg("notFound");
        }
        return this.featureDao.delete(id);
    }

    // id
    public Feature findById(int id) {
        return this.featureDao.findById(id);
    }


    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> featureRowList = new ArrayList<>();
        for (Feature feature : this.findAll()) {
            Object[] rowObject = new Object[size];

            int i= 0;
            rowObject[i++] = feature.getId();
            rowObject[i++] = feature.getName();
            featureRowList.add(rowObject);
        }
        return featureRowList;
    }

}

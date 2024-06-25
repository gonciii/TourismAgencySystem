package view;

import business.FeatureManager;
import core.Helper;
import entity.Feature;

import javax.swing.*;

public class FeatureView extends  Layout{
    private JPanel container;
    private JLabel lbl_feature;
    private JTextField fld_feature_name;
    private JButton btn_feature_save;
    private JLabel lbl_feature_name;

    private Feature feature;
    private FeatureManager featureManager;

    // constructor
    public FeatureView(Feature feature) {
        this.featureManager = new FeatureManager();
        this.feature = feature;
        this.add(container);
        this.guiInitilaze(300,300,"Feature Panel");

        if (feature != null) {
            this.fld_feature_name.setText(feature.getName());
        }

        // save butonu :

        btn_feature_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_feature_name)){
                Helper.showMsg("fill");
            }else {
                boolean result = true;
                if(this.feature == null){
                    Feature obj = new Feature(fld_feature_name.getText());
                    result = this.featureManager.save(obj);
                }else {
                    this.feature.setName(fld_feature_name.getText());
                    result = this.featureManager.update(this.feature);
                }

                if (result){
                    Helper.showMsg("Feature saved successfully.");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });



    }
}

package view;

import business.PensionManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel container;
    private JTextField fld_pension_name;
    private JButton btn_pension_save;
    private JLabel lbl_pension;
    private JLabel lbl_pension_name;

    private Pension pension;
    private PensionManager pensionManager;

    public PensionView(Pension pension){
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(300,200,"Pension Panel");
        this.pension = pension;

        if(pension != null){
            this.fld_pension_name.setText(pension.getName());
        }

        btn_pension_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_pension_name)){
                Helper.showMsg("fill");
            }else {
                boolean result = true;
                if(this.pension == null){
                    Pension obj = new Pension(fld_pension_name.getText());
                    result = this.pensionManager.save(obj);
                }else {
                    this.pension.setName(fld_pension_name.getText());
                    result = this.pensionManager.update(this.pension);
                }

                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}

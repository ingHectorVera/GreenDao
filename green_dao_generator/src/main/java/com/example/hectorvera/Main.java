package com.example.hectorvera;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public static void main(String[] args) throws Exception {
        Schema schema1 = new Schema(2, "com.example.hectorvera.greendao.db");
        Entity personalInformation = schema1.addEntity("personalInformation");
        personalInformation.addIdProperty().autoincrement();
        personalInformation.addStringProperty("Name");
        personalInformation.addStringProperty("PhoneNumber");

        Entity personalDirection = schema1.addEntity("personalDirection");
        personalDirection.addLongProperty("Pd_id").primaryKey();
        personalDirection.addStringProperty("Street");
        personalDirection.addStringProperty("Zip_Code");
        personalDirection.addStringProperty("State");

        personalInformation.addToOneWithoutProperty("Personal_info_dir",personalDirection,"Pd_id",false,false);


        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema1, "./app/src/main/java");
    }
}

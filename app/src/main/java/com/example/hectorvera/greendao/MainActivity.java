package com.example.hectorvera.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hectorvera.greendao.db.DaoMaster;
import com.example.hectorvera.greendao.db.DaoSession;
import com.example.hectorvera.greendao.db.*;

public class MainActivity extends AppCompatActivity {

    private EditText eId, eName, eCelphone, eStreet, eZC, eState;
    private long id;
    private String name, street, state, celphone, zc;
    DaoMaster.DevOpenHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eId = ((EditText) findViewById(R.id.eId));
        eName = ((EditText) findViewById(R.id.eName));
        eCelphone = ((EditText) findViewById(R.id.eCelphone));
        eStreet = ((EditText) findViewById(R.id.eStreet));
        eZC = ((EditText) findViewById(R.id.eZC));
        eState = ((EditText) findViewById(R.id.eState));
    }

    public void onDelete(View view) {
        if(!isNoData(2)){
            fillVariable(2);
        }else{
            Toast.makeText(getBaseContext(),"There is missing data", Toast.LENGTH_LONG);
        }
    }

    public void onSearch(View view) {
        if(!isNoData(2)){
            fillVariable(2);
            Toast.makeText(getBaseContext(),"There is data", Toast.LENGTH_LONG).show();
        }else{
            if(isIdData()){
                Toast.makeText(getBaseContext(),"There is only Id", Toast.LENGTH_LONG).show();
                DaoSession session = getSession(2);
                personalInformationDao pid = session.getPersonalInformationDao();
                personalInformation pi = pid.loadDeep(Long.parseLong(eId.getText().toString()));
                personalDirectionDao pdd = session.getPersonalDirectionDao();
                personalDirection pd = pdd.load(pi.getId());
                pi.setPersonal_info_dir(pd);
                fillEText(pi);
            }else {
                Toast.makeText(getBaseContext(),"There is no Id", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onUpdate(View view) {
        if(!isNoData(2)){
            fillVariable(2);

        }else{
            if(isIdData()){

            }else {
                Toast.makeText(getBaseContext(), "There is no Id", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onInsert(View view) {
        if(!isNoData(1)){
            fillVariable(1);
            DaoSession session = getSession(1);
            personalInformation pi = new personalInformation();
            pi.setName(name);
            pi.setPhoneNumber(celphone);
            personalDirection pd = new personalDirection();
            pd.setState(state);
            pd.setStreet(street);
            pd.setZip_Code(zc);

            personalInformationDao pid = session.getPersonalInformationDao();
            personalDirectionDao pdd = session.getPersonalDirectionDao();

            //pi.setPersonal_info_dir(pd);
            //pid.insert(pi);
            pid.insertOrReplace(pi);
            pd.setPd_id(pi.getId());
            pdd.insertOrReplace(pd);

            eId.setText(pi.getId()+"");
            Toast.makeText(getBaseContext(),"You insert the information", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(),"There is missing data", Toast.LENGTH_LONG).show();
        }
    }

    private DaoSession getSession(int writable){
        helper = new DaoMaster.DevOpenHelper(getBaseContext(),"GreenDAO-DB", null);
        SQLiteDatabase db;
        if(writable == 1){
            db = helper.getWritableDatabase();
        }else {
            db = helper.getReadableDatabase();
        }
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    private boolean isNoData(int insert){
        boolean flag = false;
        if(insert == 2 && (eId.getText().toString().equals("") ||
                eName.getText().toString().equals("") ||
                eCelphone.getText().toString().equals("") ||
                eStreet.getText().toString().equals("") ||
                eZC.getText().toString().equals("") ||
                eState.getText().toString().equals(""))){
            flag = true;
        }else if(insert == 1 && (eName.getText().toString().equals("") ||
                eCelphone.getText().toString().equals("") ||
                eStreet.getText().toString().equals("") ||
                eZC.getText().toString().equals("") ||
                eState.getText().toString().equals(""))){
            flag = true;
        }
        return flag;
    }

    private boolean isIdData(){
        if(eId.getText().toString().equals("")){
            return false;
        }else {
            return true;
        }
    }

    private void fillVariable(int insert){
        if(insert == 2) {
            id = Long.parseLong(eId.getText().toString());
        }else {
            id = 0L;
        }
        celphone = eCelphone.getText().toString();
        zc = eZC.getText().toString();
        name = eName.getText().toString();
        street = eStreet.getText().toString();
        state = eState.getText().toString();
    }

    private void fillEText(personalInformation pi){
        eId.setText(pi.getId()+"");
        eName.setText(pi.getName());
        eCelphone.setText(pi.getPhoneNumber());
        personalDirection pd = pi.getPersonal_info_dir();
        if(pd.getState() != null) {
            eState.setText(pd.getState());
        }else{
            eState.setText("");
        }
        if(pd.getStreet() != null) {
            eStreet.setText(pd.getStreet());
        }else {
            eStreet.setText("");
        }
        if(pd.getZip_Code() != null) {
            eZC.setText(pd.getZip_Code());
        }else{
            eZC.setText("");
        }
    }

    public void onClear(View view) {
        eId.setText("");
        eName.setText("");
        eCelphone.setText("");
        eState.setText("");
        eStreet.setText("");
        eZC.setText("");
    }
}

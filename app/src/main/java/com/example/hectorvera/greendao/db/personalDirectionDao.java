package com.example.hectorvera.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.hectorvera.greendao.db.personalDirection;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSONAL_DIRECTION".
*/
public class personalDirectionDao extends AbstractDao<personalDirection, Long> {

    public static final String TABLENAME = "PERSONAL_DIRECTION";

    /**
     * Properties of entity personalDirection.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Pd_id = new Property(0, Long.class, "Pd_id", true, "PD_ID");
        public final static Property Street = new Property(1, String.class, "Street", false, "STREET");
        public final static Property Zip_Code = new Property(2, String.class, "Zip_Code", false, "ZIP__CODE");
        public final static Property State = new Property(3, String.class, "State", false, "STATE");
    };


    public personalDirectionDao(DaoConfig config) {
        super(config);
    }
    
    public personalDirectionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSONAL_DIRECTION\" (" + //
                "\"PD_ID\" INTEGER PRIMARY KEY ," + // 0: Pd_id
                "\"STREET\" TEXT," + // 1: Street
                "\"ZIP__CODE\" TEXT," + // 2: Zip_Code
                "\"STATE\" TEXT);"); // 3: State
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSONAL_DIRECTION\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, personalDirection entity) {
        stmt.clearBindings();
 
        Long Pd_id = entity.getPd_id();
        if (Pd_id != null) {
            stmt.bindLong(1, Pd_id);
        }
 
        String Street = entity.getStreet();
        if (Street != null) {
            stmt.bindString(2, Street);
        }
 
        String Zip_Code = entity.getZip_Code();
        if (Zip_Code != null) {
            stmt.bindString(3, Zip_Code);
        }
 
        String State = entity.getState();
        if (State != null) {
            stmt.bindString(4, State);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public personalDirection readEntity(Cursor cursor, int offset) {
        personalDirection entity = new personalDirection( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Pd_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Street
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Zip_Code
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // State
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, personalDirection entity, int offset) {
        entity.setPd_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStreet(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setZip_Code(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setState(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(personalDirection entity, long rowId) {
        entity.setPd_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(personalDirection entity) {
        if(entity != null) {
            return entity.getPd_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
package com.tapwater.phoneerp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tapwater on 17-3-30.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    private String tableName = "student_table";
    public static final int VERSION = 1;
    private Table table = new Table();

    private String infoTableName = new String();
    private String[] tableColumnsName;
    private int tableColumnNum;
    private String[] tableColumnsType;
    private boolean[] tableColumnsKey;
    private int tableColumnsKeyNum;

    public MyDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COURSES TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    /**
     * get tableName table information, column name, num, type, key
     * and return cursor for tableName information
     * @param tableName
     * @return
     */
    public void getTableInfo(String tableName)
    {
        if(infoTableName.isEmpty() || !tableName.equals(infoTableName))
        {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("PRAGMA table_info('"+ tableName +"')",null);
            infoTableName = tableName;
            tableColumnNum = cursor.getCount();
            tableColumnsName = new String[tableColumnNum];
            tableColumnsType = new String[tableColumnNum];
            tableColumnsKey = new boolean[tableColumnNum];
            for (int i = 0; i < tableColumnNum; i++)
            {
                cursor.moveToNext();
                tableColumnsName[i] = cursor.getString(1).toString();
                tableColumnsType[i] = cursor.getString(2).toString();
                if (cursor.getInt(5) == 0)
                {
                    tableColumnsKey[i] = false;
                }
                else
                {
                    tableColumnsKey[i] = true;
                    tableColumnsKeyNum = i;
                }
            }
        }
    }


    /**
     * insert insertContent into tableName
     * insert table, First Column is ID and don't need insert
     * @param tableName
     * @param insertContent
     * @return
     */
    public boolean insertDataFirstAutoID(String tableName,String[] insertContent)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        getTableInfo(tableName);
        for (int i = 0; i < tableColumnNum - 1; i++)
        {
            contentValues.put(tableColumnsName[i + 1],insertContent[i]);
        }
        long result = db.insert(tableName,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * insert insertContent into tableName
     * insert table, all columns need to insert
     * @param tableName
     * @param insertContent
     * @return
     */
    public boolean insertData(String tableName, String[] insertContent)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        getTableInfo(tableName);
        for (int i = 0; i < tableColumnNum; i++)
        {
            contentValues.put(tableColumnsName[i],insertContent[i]);
        }
        long result = db.insert(tableName,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


//    public boolean insertData(String name, String course, String mark)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
////        contentValues.put(COL_2,name);
////        contentValues.put(COL_3,course);
////        contentValues.put(COL_4,mark);
//        long result = db.insert(tableName,null,contentValues);
//        if (result == -1)
//        {
//            return false;
//        }
//        else
//        {
//            return true;
//        }
//    }

    /**
     *
     * @param tableName
     * @return
     */
    public Cursor getAllData(String tableName)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tableName,null);
        return cursor;
    }

//    public boolean updateData(String id,String name, String course, String mark)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
////        contentValues.put(COL_1,id);
////        contentValues.put(COL_2,name);
////        contentValues.put(COL_3,course);
////        contentValues.put(COL_4,mark);
//        db.update(tableName,contentValues,"ID = ?", new String[]{id});
//        return true;
//    }

    /**
     *update data input order MUST same as sql table columns
     * @param tableName
     * @param data
     * @return
     */
    public boolean updateData(String tableName, String[] data)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        getTableInfo(tableName);
        for(int i = 0; i < tableColumnNum; i++)
        {
            contentValues.put(tableColumnsName[i],data[i]);
        }
        db.update(tableName,contentValues,tableColumnsName[tableColumnsKeyNum] + " = ?", new String[]{data[tableColumnsKeyNum]});
        return true;
    }

//    public Integer deleteData(String id)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        return  db.delete(tableName,"ID = ?", new String[]{id});
//    }

    /**
     * delete data from tableName where key equal deleteKey
     * @param tableName
     * @param deleteKey
     * @return
     */
    public Integer deleteData(String tableName, String deleteKey)
    {
        SQLiteDatabase db = getWritableDatabase();
        getTableInfo(tableName);
//        return  db.delete(tableName,"ID = ?", new String[]{id});
        return  db.delete(tableName,tableColumnsName[tableColumnsKeyNum] + " = ?", new String[]{deleteKey});
    }

    public int getTableColumnNum() {
        return tableColumnNum;
    }

    public void setTableColumnNum(int tableColumnNum) {
        this.tableColumnNum = tableColumnNum;
    }

    public boolean[] getTableColumnsKey() {
        return tableColumnsKey;
    }

    public void setTableColumnsKey(boolean[] tableColumnsKey) {
        this.tableColumnsKey = tableColumnsKey;
    }

    public int getTableColumnsKeyNum() {
        return tableColumnsKeyNum;
    }

    public void setTableColumnsKeyNum(int tableColumnsKeyNum) {
        this.tableColumnsKeyNum = tableColumnsKeyNum;
    }

    public String[] getTableColumnsName() {
        return tableColumnsName;
    }

    public void setTableColumnsName(String[] tableColumnsName) {
        this.tableColumnsName = tableColumnsName;
    }

    public String[] getTableColumnsType() {
        return tableColumnsType;
    }

    public void setTableColumnsType(String[] tableColumnsType) {
        this.tableColumnsType = tableColumnsType;
    }
}
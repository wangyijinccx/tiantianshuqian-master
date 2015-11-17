package com.izqisoft.util;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SqlLiteUtil extends SQLiteOpenHelper {

    //数据库版本
    private static final int VERSION = 1;
    //新建一个表
	String sql = "create table  tb_score"+
    "(id int primary key, score varchar)";
    
    public SqlLiteUtil(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    public SqlLiteUtil(Context context,String name,int version){
        this(context,name,null,version);
    }
    
    public SqlLiteUtil(Context context,String name){
        this(context,name,VERSION);
    }

    
	
    //当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
    //重写onCreate方法，调用execSQL方法创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql); 
    }
    
	
	// 当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("sqllite由"+oldVersion+"版本,升级为"+newVersion+"版本");
    }
	
	//创建表
	public void createTable(SQLiteDatabase db,List<String> sqls){
        try {
           for(String sql:sqls){
			   db.execSQL(sql);
		      }  
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
	
	
	//select
	public Cursor selectTb(SQLiteDatabase db,String sql){
		Cursor cusor = null;
        try {
			cusor =  db.rawQuery(sql,null);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return cusor;
    }
	
	//insert
	public void insertTb(SQLiteDatabase db,List<String> sqls){
        try {
           for(String sql:sqls){
			   db.execSQL(sql);
		      } 
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
	
	
   //update
    public void updateTb(SQLiteDatabase db,List<String> sqls) {
        try {
            for(String sql:sqls){
			   db.execSQL(sql);
		      } 
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    //delete
    public void deleteTb(SQLiteDatabase db,List<String> sqls){
        try {
            for(String sql:sqls){
			    db.execSQL(sql);
		      } 
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
	
	//关闭数据库
	public void closeDb(SqlLiteUtil dbHelper){
        dbHelper.close();
    }
	
    
}
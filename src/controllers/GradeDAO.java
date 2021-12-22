/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import models.Grade;

/**
 *
 * @author phuho
 */
public class GradeDAO implements LGradeDAO{
    private ArrayList<Grade> list;
    private int current;
    private String userSQL;
    private String passSQL;
    private String urlConn;
    
    public GradeDAO() {
        list = new ArrayList<>();
        current = 0;
        userSQL = "sa";
        passSQL = "123456";
        urlConn = "jdbc:sqlserver://localhost;databaseName=FPL_DAOTAO";
    }

    @Override
    public ArrayList<Grade> get() throws Exception {
        loaddata();        
        return list;
    }

    @Override
    public Grade get(int index) throws Exception {
        return list.get(index);
    }

    @Override
    public void loaddata() throws Exception {
        try {
            Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
            Statement st = conn.createStatement();
            String sql = "Select grade.MASV, hoten, tienganh, tinhoc, gdtc, (tienganh + tinhoc + gdtc)/3 as 'DIEMTB' from STUDENTS inner join GRADE on STUDENTS.MASV = GRADE.MASV";
            ResultSet rs = st.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setMaSV(rs.getString(1));
                grade.setHoTen(rs.getString(2));
                grade.setTiengAnh(rs.getDouble(3));
                grade.setTinHoc(rs.getDouble(4));
                grade.setGDTC(rs.getDouble(5));
                grade.setTB(rs.getDouble(6));
                list.add(grade);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArrayList<Grade> getTop3() throws Exception {
        try {
            loaddata();
            Comparator<Grade> comparator = new Comparator<>() {
                @Override
                public int compare(Grade o1, Grade o2) {
                    double t = o1.getTB() - o2.getTB();
                    if(t > 0) {
                        return -1;
                    } else if(t < 0){
                        return 1;
                    }
                    return 0;
                }            
            };
            ArrayList<Grade> listTop3Stamp = list;
            Collections.sort(listTop3Stamp, comparator);
            ArrayList<Grade> listTop3 = new ArrayList<>();
            for(int t = 0; t < 3; t++) {
                listTop3.add(listTop3Stamp.get(t));
            }
            return listTop3;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public int search(String msv) throws Exception{
        try {
            for(int t = 0; t < list.size(); t++) {
                if(list.get(t).getMaSV().equals(msv)) {
                    current = t;
                    return t;
                }
            }
            return -1;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int getCurrent() {
        return current;
    }
    
    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public void insert(Grade grade) throws Exception {
        try {
            Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
            String sql = "INSERT INTO GRADE VALUES (?, ?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, grade.getMaSV());
            ppst.setDouble(2, grade.getTiengAnh());
            ppst.setDouble(3, grade.getTinHoc());
            ppst.setDouble(4, grade.getGDTC());
            ppst.executeUpdate();

            list.add(grade);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public void delete(String masv) throws Exception {
        try {
            for(var grade: list) {
                if(grade.getMaSV().equals(masv)) {
                    Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
                    String sql = "delete from grade where masv = ?";
                    PreparedStatement ppst = conn.prepareStatement(sql);
                    ppst.setString(1, masv);
                    ppst.execute();
                    
                    loaddata();
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void update(Grade grade, String masv) throws Exception {
        try {
            for(var grd: list) {
                if(grd.getMaSV().equals(grade.getMaSV())) {
                    Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
                    String sql = "Update grade set tienganh = ?, tinhoc = ?, gdtc = ? where masv = ?";
                    PreparedStatement ppst = conn.prepareStatement(sql);
                    ppst.setDouble(1, grade.getTiengAnh());
                    ppst.setDouble(2, grade.getTinHoc());
                    ppst.setDouble(3, grade.getGDTC());
                    ppst.setString(4, grade.getMaSV());
                    
                    ppst.executeUpdate();
                    
                    loaddata();
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void goTo(String direction) {
        switch(direction) {
            case "first" :
                first();
                break;
            case "previous" :
                previous();
                break;
            case "next" :
                next();
                break;
            case "last" :
                last();
                break;
        }
    }
    
    public void first() {
        current = 0;        
    }
    
    public void next() {
        current++;
        if(current > list.size()-1) {
            current = 0;
        }        
    }
    
    public void previous() {
        current--;
        if(current < 0) {
            current = list.size()-1;
        }
    }
    
    public void last() {
        current = list.size()-1;
    }
}

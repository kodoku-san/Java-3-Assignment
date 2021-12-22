/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.Students;

/**
 *
 * @author phuho
 */
public class StudentsDAO implements LStudentsDAO {

    private ArrayList<Students> list;
    private String userSQL;
    private String passSQL;
    private String urlConn;

    public StudentsDAO() {
        list = new ArrayList<>();
        userSQL = "sa";
        passSQL = "123456";
        urlConn = "jdbc:sqlserver://localhost;databaseName=FPL_DAOTAO";
    }

    @Override
    public ArrayList<Students> get() throws Exception {
        loaddata();
        return list;
    }

    @Override
    public void delete(String masv) throws Exception {
        try {
            Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
            String sql = "Delete from students where masv = ?";
            PreparedStatement ppSt = conn.prepareStatement(sql);
            ppSt.setString(1, masv.trim());
            ppSt.execute();

            loaddata();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void insert(Students student) throws Exception {
        try {
            Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
            String sqlSaveStudent = "insert into Students values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sqlSaveStudent);
            ppst.setString(1, student.getMaSV());
            ppst.setString(2, student.getHoTen());
            ppst.setString(3, student.getEmail());
            ppst.setString(4, student.getSoDT());
            ppst.setInt(5, student.getGioiTinh());
            ppst.setString(6, student.getDiaChi());
            ppst.setString(7, student.getHinhAnh());
            ppst.executeUpdate();

            list.add(student);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void loaddata() throws Exception {
        try {
            Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM students";
            ResultSet rs = st.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                Students students = new Students();
                students.setMaSV(rs.getString(1));
                students.setHoTen(rs.getString(2));
                students.setEmail(rs.getString(3));
                students.setSoDT(rs.getString(4));
                students.setGioiTinh(rs.getInt(5));
                students.setDiaChi(rs.getString(6));
                students.setHinhAnh(rs.getString(7));
                list.add(students);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void update(Students student, String masv) throws Exception {
        int t = 0;
        for (var s : list) {
            if (s.getMaSV().equals(masv)) {
                try {
                    Connection conn = DriverManager.getConnection(urlConn, userSQL, passSQL);
                    String sqlUpdate = "Update students set hoten = ?, email = ?, sodt = ?, gioitinh = ?, diachi = ?, hinh = ? where masv = ?";
                    PreparedStatement ppst = conn.prepareStatement(sqlUpdate);
                    ppst.setString(1, student.getHoTen());
                    ppst.setString(2, student.getEmail());
                    ppst.setString(3, student.getSoDT());
                    ppst.setInt(4, student.getGioiTinh());
                    ppst.setString(5, student.getDiaChi());
                    ppst.setString(6, student.getHinhAnh());
                    ppst.setString(7, student.getMaSV());

                    ppst.executeUpdate();
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
                t = 1;
                break;
            }
        }
        if(t == 0) {
            throw new Exception("Mã số sinh viên: " + masv + " không tồn tại");
        }
    }
}

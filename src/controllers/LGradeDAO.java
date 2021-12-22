/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Grade;

/**
 *
 * @author phuho
 */
public interface LGradeDAO {
    ArrayList<Grade> get() throws Exception;
    Grade get(int index) throws Exception;
    ArrayList<Grade> getTop3() throws Exception;
    void loaddata() throws Exception;
    int search(String msv) throws Exception;
    void insert(Grade grade) throws Exception;
    void delete(String masv) throws Exception;
    void update(Grade grade, String masv) throws Exception;
}

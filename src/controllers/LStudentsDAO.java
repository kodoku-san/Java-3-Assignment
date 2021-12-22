/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Students;

/**
 *
 * @author phuho
 */
public interface LStudentsDAO {
    ArrayList<Students> get() throws Exception;
    void loaddata() throws Exception;
    void delete(String masv) throws Exception;
    void insert(Students student) throws Exception;
    void update(Students student, String masv) throws Exception;
}

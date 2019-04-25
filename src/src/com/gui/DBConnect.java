/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DBConnect {
    Connection con;
    public DBConnect (){
       String stringCon = "jdbc:sqlserver://DESKTOP-H6BPC8N\\SQLEXPRESS:1433;databaseName=QuanLiThuVien";
       try{
       con = DriverManager.getConnection(stringCon,"sa","sa");
       }
       catch (SQLException ex){
           Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
       }
    }
    public ResultSet getData(String stringSQL){
        ResultSet rs = null;
        try{
            Statement state = con.createStatement();
            rs = state.executeQuery(stringSQL);
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rs;
    }
    
    public int ExecuteSQLInsert (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO SACH(MaSach,TenSach,TheLoai,TacGia,TinhTrang,GiaTien) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
            statement.setString(6,stringsSQL[5]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLUpdate (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE SACH SET TenSach=?,TheLoai=?,TacGia=?,TinhTrang=?,GiaTien=? WHERE MaSach=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
            statement.setString(6,stringsSQL[5]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLDelete (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM SACH  WHERE MaSach=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
           
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertDocGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO DOCGIA(MaDocGia,TenDocGia,DiaChi,Email,SDT) VALUES (?,?,?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
 
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLUpdateDocGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE DOCGIA SET TenDocGia=?,DiaChi=?,Email=?,SDT=? WHERE MaDocGia=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
                      
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteDocGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM DOCGIA  WHERE MaDocGia=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
               
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertMuon (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO MUON(MaPhieuMuon,MaDocGia,MaSach,NgayMuon) VALUES (?,?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLUpdateMuon (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE MUON SET MaDocGia = ? , MaSach = ? , NgayMuon = ? WHERE MaPhieuMuon = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteMuon (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM MUON WHERE MaPhieuMuon=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);

            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertNXB (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO NHAXUATBAN(MaNXB,TenNXB,DiaChi,Email,NguoiDaiDien) VALUES (?,?,?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLUpdateNXB (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE NHAXUATBAN SET TenNXB = ? , DiaChi = ? , Email = ? , NguoiDaiDien = ? WHERE MaNXB = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            statement.setString(5,stringsSQL[4]);
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteNXB (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM NHAXUATBAN WHERE MaNXB = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);

            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertNhanVien (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO NHANVIEN(MaNhanVien,TenNhanVien,ChucVu) VALUES (?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
                        
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLUpdateNhanVien (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE NHANVIEN SET TenNhanVien = ? , ChucVu = ?  WHERE MaNhanVien = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);           
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteNhanVien (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM NHANVIEN WHERE MaNhanVien=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);

            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertDichVu (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO DICHVU(MaDichVu,TenDichVu,GiaTien) VALUES (?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
           
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLUpdateDichVu (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE DICHVU SET TenDichVu = ? , GiaTien = ?  WHERE MaDichVu = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteDichVu (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM DICHVU WHERE MaDichVu=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);

            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
    public int ExecuteSQLInsertTacGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "INSERT INTO TACGIA(MaTacGia,TenTacGia,website,GhiChu) VALUES (?,?,?,?)";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }    
    public int ExecuteSQLUpdateTacGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "UPDATE TACGIA SET TenTacGia = ? , website = ? , GhiChu = ? WHERE MaTacGia = ?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);
            statement.setString(2,stringsSQL[1]);
            statement.setString(3,stringsSQL[2]);
            statement.setString(4,stringsSQL[3]);
            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }   
    public int ExecuteSQLDeleteTacGia (String[] stringsSQL){
        int rowInserted = 0;
        String sql = "DELETE FROM MUON WHERE MaPhieuMuon=?";
        PreparedStatement statement ; 
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,stringsSQL[0]);

            
            rowInserted = statement.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rowInserted;
    }
    
}

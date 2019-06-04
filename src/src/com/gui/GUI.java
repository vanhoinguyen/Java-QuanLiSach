/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JTee
 */
public class GUI extends javax.swing.JFrame {
    DBConnect con;
    private int flag = 0;
    private int flag1=0;
    private int flag2=0;
    private int flag3=0;
    private int flag4=0;
    private int flag5=0;
    private int flag6=0;
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        con = new DBConnect();
        showData();
        showDataDocGia();
        showDataMuonSach();
        showDataNXB();
        showDataNhanVien();
        showDataDichVu();
        showDataTacGia();
        showSumOfSach();
        showSumOfDocGia();
        showSumOfSachChoMuon();
        showSumOfMoney();
        showThongKeData();
    }
    public void ClearText(){
        inputMaSach.setText("");
        inputTenSach.setText("");
        inputTheLoai.setText("");
        inputTacGia.setText("");             
        inputTinhTrang.setText("");
        inputGiaTien.setText("");
        inputNXB.setText("");
    }
    public void ClearTextDocGia(){
       inputTenDocGia.setText("");
       inputDiaChi.setText("");
       inputEmail.setText("");
       inputSDT.setText("");   
    }
    public void ClearTextMuon(){
       inputTenDocGia_Muon.setText("");
       inputTenSach_Muon.setText("");
       datechooser.setDate(null);
       inputMaDocGia_Muon.setText("");
       inputMaSach_Muon.setText("");
    }  
    public void ClearTextNXB(){
        txtNXB.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtNguoiDaiDien.setText("");
    }
    public void ClearTextNhanVien(){
        txtNhanVien.setText("");
        txtChucVu.setText("");
    }
    public void ClearTextDichVu(){
        txtTenDichVu.setText("");
        txtGiaTien.setText("");
    }
    public void ClearTextTacGia(){
        txtTenTacGia.setText("");
        txtwebsite.setText("");
        txtGhiChu.setText("");
    }
    
    
    public void showThongKeData(){
        String[] columnNames = {"Tên sách","Tổng số","Đang cho mượn"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbThongKe.setModel(model);
        String TenSach = "";
        int TongSo =  0;
        int DangChoMuon=0;
        String flag1 = "";
        String flag2 = "";
        ResultSet rs = con.getData("SELECT DISTINCT TenSach FROM SACH ");
        try{
        while (rs.next()){
            TenSach = rs.getString("TenSach").trim();
            if(!flag1.equals(TenSach)){
                flag1 = TenSach;
                TongSo = 0;
                DangChoMuon = 0;
            }  
            ResultSet rs1 = con.getData("SELECT *  FROM SACH");
            while(rs1.next()){
                if(rs1.getString("TenSach").equals(TenSach))
                    TongSo++;
            }
            ResultSet rs2 = con.getData("SELECT *   FROM SACH ");
            while(rs2.next()){
                if(rs2.getString("TenSach").equals(TenSach) && rs2.getString("TinhTrang").equals("Đã cho thuê"))
                    DangChoMuon++;
            }
            
             model.addRow(new Object[]{TenSach,TongSo,DangChoMuon});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        } 
        
    }
    public void showSumOfSach(){
      
      ResultSet rs = con.getData("SELECT COUNT (*) as total FROM SACH "); 
      
       try{
        while (rs.next()){
            txtTongSoSach.setText( rs.getString("total"));
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void showSumOfDocGia(){
        ResultSet rs = con.getData("SELECT COUNT (*) as total FROM DOCGIA "); 
      
       try{
        while (rs.next()){
            txtTongDocGiaDangKy.setText( rs.getString("total"));
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
     
    public void TimKiemSach_QLSach(){
        String[] columnNames = {"Mã sách","Tên sách","Thể loại","Mã Tác giả","Mã NXB","Tình trạng","Gía tiền"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLSach.setModel(model);
        String MaSach = "";
        String TenSach = "";
        String TheLoai = "";
        String MaTacGia = "";
        String MaNXB ="";
        String TinhTrang = "";
        String GiaTien = "";
        
        ResultSet rs = con.getData("SELECT * FROM SACH WHERE TenSach like '"+inputTuKhoaSach.getText().trim()+"%'");
        try{
        while (rs.next()){
            MaSach = rs.getString("MaSach");
            TenSach = rs.getString("TenSach");
            TheLoai = rs.getString("TheLoai");
            MaTacGia = rs.getString("MaTacGia");
            MaNXB = rs.getString("MaNXB");
            TinhTrang = rs.getString("TinhTrang");
            GiaTien = rs.getString("GiaTien");
            
            model.addRow(new Object[]{MaSach,TenSach,TheLoai,MaTacGia,MaNXB,TinhTrang,GiaTien});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
    public void TimKiemTen_QLDocGia(){
       String[] columnNames = {"Mã Độc Giả","Tên Độc Giả","Địa chỉ","Email","Số Điện Thoại"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLDocGia.setModel(model);
        
        String MaDocGia = "";
        String TenDocGia = "";
        String DiaChi = "";
        String Email = "";
        String SoDienThoai = "";
        
        
        ResultSet rs = con.getData("SELECT * FROM DOCGIA WHERE TenDocGia like '"+inputTuKhoaDocGia.getText().trim()+"%'");
        try{
        while (rs.next()){
            MaDocGia = rs.getString("MaDocGia");
            TenDocGia = rs.getString("TenDocGia");
            DiaChi = rs.getString("DiaChi");
            Email = rs.getString("Email");
            SoDienThoai = rs.getString("SDT");
                       
            model.addRow(new Object[]{MaDocGia,TenDocGia,DiaChi,Email,SoDienThoai});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        } 
    }
    public void TimKiem_QLMuonSach(){
      String[] columnNames = {"Mã Phiếu Mượn","Tên Độc Giả","Tên Sách","Ngày"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLMuonSach.setModel(model);
        
        String MaPhieuMuon = "";
        String MaDocGia = "";
        String MaSach = "";
        String Ngay = "";
           
        ResultSet rs = con.getData("SELECT * FROM MUON");
        try{
        while (rs.next()){
            MaPhieuMuon = rs.getString("MaPhieuMuon");           
            MaDocGia = rs.getString("MaDocGia");
            MaSach = rs.getString("MaSach");
            Ngay = rs.getString("NgayMuon");
            
            
            model.addRow(new Object[]{MaPhieuMuon,MaDocGia,MaSach,Ngay});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }  
    }
    public void showSumOfSachChoMuon(){
       int count = 0; 
       ResultSet rs = con.getData("SELECT TinhTrang FROM SACH "); 
      
       try{
        
        while (rs.next()){           
           
           if(rs.getString("TinhTrang").equals("Đã cho thuê") == true)
           {count++;
           
           }
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        } 
       txtSoSachChoMuon.setText(String.valueOf(count));
    }
    public void showSumOfMoney(){
       ResultSet rs = con.getData("Select Sum(GiaTien) as total from ThongtinTraSach "); 
      
       try{
        while (rs.next()){          
            NumberFormat formatter = new DecimalFormat("###,###,###");        
            txtDoanhThu.setText( formatter.format(rs.getInt("total")));
            
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        } 
    }
    public void showData(){
        String[] columnNames = {"Mã sách","Tên sách","Thể loại","Tên Tác giả","Tên NXB","Tình trạng","Gía tiền"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLSach.setModel(model);
        String MaSach = "";
        String TenSach = "";
        String TheLoai = "";
        String MaTacGia = "";
        String MaNXB ="";
        String TinhTrang = "";
        String GiaTien = "";
        
        ResultSet rs = con.getData("SELECT * FROM SACH inner join NHAXUATBAN on SACH.MaNXB = NHAXUATBAN.MaNXB inner join TACGIA on SACH.MaTacGia = TACGIA.MaTacGia");
        try{
        while (rs.next()){
            MaSach = rs.getString("MaSach");
            TenSach = rs.getString("TenSach");
            TheLoai = rs.getString("TheLoai");
            MaTacGia = rs.getString("TenTacGia");
            MaNXB = rs.getString("TenNXB");
            TinhTrang = rs.getString("TinhTrang");
             
            NumberFormat formatter = new DecimalFormat("###,###,###");
            GiaTien = formatter.format(rs.getInt("GiaTien"));
            model.addRow(new Object[]{MaSach,TenSach,TheLoai,MaTacGia,MaNXB,TinhTrang,GiaTien});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }      
    }
    public void showDataDocGia(){
        String[] columnNames = {"Mã Độc Giả","Tên Độc Giả","Địa chỉ","Email","Số Điện Thoại"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLDocGia.setModel(model);
        
        String MaDocGia = "";
        String TenDocGia = "";
        String DiaChi = "";
        String Email = "";
        String SoDienThoai = "";
        
        
        ResultSet rs = con.getData("SELECT * FROM DOCGIA");
        try{
        while (rs.next()){
            MaDocGia = rs.getString("MaDocGia");
            TenDocGia = rs.getString("TenDocGia");
            DiaChi = rs.getString("DiaChi");
            Email = rs.getString("Email");
            SoDienThoai = rs.getString("SDT");
                       
            model.addRow(new Object[]{MaDocGia,TenDocGia,DiaChi,Email,SoDienThoai});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void showDataMuonSach(){
       String[] columnNames = {"Mã Phiếu Mượn","Tên Độc Giả","Tên Sách","Ngày","Mã Sách","Mã Độc Giả"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLMuonSach.setModel(model);
        
        String MaPhieuMuon = "";
        String TenDocGia = "";
        String TenSach = "";
        String Ngay = "";
        String MaDocGia = "";
        String MaSach = "";
        
        ResultSet rs = con.getData("SELECT * FROM MUON inner join DOCGIA on MUON.MaDocGia = DOCGIA.MaDocGia inner join SACH on MUON.MaSach = Sach.MaSach");
        try{
        while (rs.next()){
            MaPhieuMuon = rs.getString("MaPhieuMuon");           
            TenDocGia = rs.getString("TenDocGia");
            TenSach = rs.getString("TenSach");
            Ngay = rs.getString("NgayMuon");
            MaDocGia = rs.getString("MaDocGia");
            MaSach = rs.getString("MaSach");
            model.addRow(new Object[]{MaPhieuMuon,TenDocGia,TenSach,Ngay,MaSach,MaDocGia});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        } 
    }
    public void showDataNXB(){
        String[] columnNames = {"Mã NXB","Tên NXB","Địa chỉ","Email","Người đại diện"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLNXB.setModel(model);
        
        String MaNXB = "";
        String TenNXB = "";
        String DiaChi = "";
        String Email = "";
        String NguoiDaiDien = "";
        
        
        ResultSet rs = con.getData("SELECT * FROM NHAXUATBAN");
        try{
        while (rs.next()){
            MaNXB = rs.getString("MaNXB");           
            TenNXB = rs.getString("TenNXB");
            DiaChi = rs.getString("DiaChi");
            Email = rs.getString("Email");
            NguoiDaiDien = rs.getString("NguoiDaiDien");
            
            model.addRow(new Object[]{MaNXB,TenNXB,DiaChi,Email,NguoiDaiDien});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }    
    }
    public void showDataNhanVien(){
        String[] columnNames = {"Mã Nhân Viên","Tên Nhân Viên","Chức vụ"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLNhanVien.setModel(model);
        
        String MaNhanVien = "";
        String TenNhanVien = "";
        String ChucVu = "";
              
        ResultSet rs = con.getData("SELECT * FROM NHANVIEN");
        try{
        while (rs.next()){
            MaNhanVien = rs.getString("MaNhanVien");           
            TenNhanVien = rs.getString("TenNhanVien");
            ChucVu = rs.getString("ChucVu");
                       
            model.addRow(new Object[]{MaNhanVien,TenNhanVien,ChucVu});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }     
    }
    public void showDataDichVu(){
        String[] columnNames = {"Mã Dịch Vụ","Tên Dịch Vụ","Giá Tiền"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLDichVu.setModel(model);
        
        String MaDichVu = "";
        String TenDichVu = "";
        String GiaTien = "";
              
        ResultSet rs = con.getData("SELECT * FROM DICHVU");
        try{
        while (rs.next()){
            MaDichVu = rs.getString("MaDichVu");           
            TenDichVu = rs.getString("TenDichVu");
            GiaTien = rs.getString("GiaTien");
                       
            model.addRow(new Object[]{MaDichVu,TenDichVu,GiaTien});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }    
    }
    public void showDataTacGia(){
        String[] columnNames = {"Mã Tác Giả","Tên Tác Giả","website","Ghi Chú"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbQLTacGia.setModel(model);
        
        String MaTacGia = "";
        String TenTacGia = "";
        String website = "";
        String GhiChu = "";
              
        ResultSet rs = con.getData("SELECT * FROM TACGIA");
        try{
        while (rs.next()){
            MaTacGia = rs.getString("MaTacGia");           
            TenTacGia = rs.getString("TenTacGia");
            website = rs.getString("website");
            GhiChu = rs.getString("GhiChu") ;
            
            model.addRow(new Object[]{MaTacGia,TenTacGia,website,GhiChu});
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }      
    }
    
    public int j=1;
    public void InsertSachData(){       
        for (int i=0;i <tbQLSach.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLSach.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        System.out.println(j);
        String MaSach =  Integer.toString(j);
        String TenSach = inputTenSach.getText();
        String TheLoai = inputTheLoai.getText();
        String TacGia = inputTacGia.getText();
        String MaNXB = inputNXB.getText();
        String TinhTrang = inputTinhTrang.getText();
        String GiaTien = inputGiaTien.getText();
        String[] stringsSQL = {MaSach,TenSach,TheLoai,TacGia,MaNXB,TinhTrang,GiaTien};
        
        int isInsert = con.ExecuteSQLInsert(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        ClearText();
        showData();
        showThongKeData();
    }    
    public void UpdateSachData(){
        String MaSach = inputMaSach.getText();
        String TenSach = inputTenSach.getText();
        String TheLoai = inputTheLoai.getText();
        String TacGia = inputTacGia.getText();
        String TinhTrang = inputTinhTrang.getText();
        String GiaTien = inputGiaTien.getText();
        String[] stringsSQL = {TenSach,TheLoai,TacGia,TinhTrang,GiaTien,MaSach};
        int isInsert = con.ExecuteSQLUpdate(stringsSQL);
        
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showData();
        ClearText();
    }   
    public void DeleteSachData(){
        String MaSach = inputMaSach.getText();
        if(MaSach.length() == 0 )
        {
           JOptionPane.showMessageDialog(this,"Vui lòng chọn sách muốn xóa trong bảng ");
           return;
        }
        if(inputTinhTrang.getText().equals("Đã cho thuê"))
        {
            JOptionPane.showMessageDialog(this,"Sách cho thuê , không thể xóa!");
           return;
        }
        String[] stringsSQL = {MaSach};
        
        int isInsert = con.ExecuteSQLDelete(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showData();
        ClearText();
    }
    
    public void InsertDocGiaData(){
        for (int i=0;i <tbQLDocGia.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLDocGia.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaDocGia =  Integer.toString(j);
        String TenDocGia = inputTenDocGia.getText();
        String DiaChi = inputDiaChi.getText();
        String Email = inputEmail.getText();
        String SDT = inputSDT.getText();
        
        String[] stringsSQL = {MaDocGia,TenDocGia,DiaChi,Email,SDT};
        
        int isInsert = con.ExecuteSQLInsertDocGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j =1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showDataDocGia();
        ClearTextDocGia();
    }
    public void UpdateDocGiaData(){
        int selectedRow = tbQLDocGia.getSelectedRow();
        String MaDocGia = tbQLDocGia.getValueAt(selectedRow,0).toString();
        String TenDocGia = inputTenDocGia.getText();
        String DiaChi = inputDiaChi.getText();
        String Email = inputEmail.getText();
        String SDT = inputSDT.getText();
        
        String[] stringsSQL = {TenDocGia,DiaChi,Email,SDT,MaDocGia};
        
        int isInsert = con.ExecuteSQLUpdateDocGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showDataDocGia();
        ClearTextDocGia();
    }
    public void DeleteDocGiaData(){
        int selectedRow = tbQLDocGia.getSelectedRow();
        String MaDocGia = tbQLDocGia.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaDocGia};
        
        int isInsert = con.ExecuteSQLDeleteDocGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showDataDocGia();
        ClearTextDocGia();
    }
    
    public void InsertMuonData(){
        for (int i=0;i <tbQLMuonSach.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLMuonSach.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaPhieuMuon = Integer.toString(j);
        String MaDocGia = inputMaDocGia_Muon.getText();
        String MaSach = inputMaSach_Muon.getText();
        
        //kiểm tra cho mượn hay chưa
        for (int i=0;i <tbQLMuonSach.getRowCount();i++)
        {
            if(tbQLMuonSach.getModel().getValueAt(i, 4).toString().equals (MaSach)){
                System.out.println(tbQLMuonSach.getModel().getValueAt(i, 4).toString().equals (MaSach));
                 JOptionPane.showMessageDialog(this,"Sách này đã được cho mượn");
                 return;
            }
            
        }
        
        SimpleDateFormat Date_Format = new SimpleDateFormat("MM-dd-yyyy"); 
        String NgayMuon = Date_Format.format(datechooser.getDate()).toString();
        String MaDichVu = null;
        String[] stringsSQL = {MaPhieuMuon,MaDocGia,MaSach,NgayMuon,MaDichVu};
        String[] stringsSQL1 = {"Đã cho thuê",MaSach};
        int isInsert1 = con.ExecuteSQLUpdateTinhTrang(stringsSQL1);
        int isInsert = con.ExecuteSQLInsertMuon(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showData();
        showDataMuonSach();
        showSumOfSachChoMuon();
        ClearTextMuon();              
       
    }
    public void UpdateMuonData(){
        int selectedRow = tbQLMuonSach.getSelectedRow();
        String MaPhieuMuon = tbQLMuonSach.getValueAt(selectedRow,0).toString();
        String MaDocGia = inputMaDocGia_Muon.getText();
        String MaSach = inputMaSach_Muon.getText();
        SimpleDateFormat Date_Format = new SimpleDateFormat("MM-dd-yyyy"); 
        String NgayMuon = Date_Format.format(datechooser.getDate()).toString();
        String MaDichVu = null;
        
        
        String[] stringsSQL = {MaDocGia,MaSach,NgayMuon,MaDichVu,MaPhieuMuon};
        String[] stringsSQL1 = {"Đã cho thuê",MaSach};
        int isInsert1 = con.ExecuteSQLUpdateTinhTrang(stringsSQL1);
        int isInsert = con.ExecuteSQLUpdateMuon(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showDataMuonSach();
        ClearTextMuon();
    }
    public void DeleteMuonData(){
        //lấy dữ liệu textbox
        int selectedRow = tbQLMuonSach.getSelectedRow();
        String MaPhieuMuon = tbQLMuonSach.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaPhieuMuon};
        String MaSach = inputMaSach_Muon.getText();
        
        //Xóa
        int isInsert = con.ExecuteSQLDeleteMuon(stringsSQL);
        //Update lại chưa cho mượn
        String[] stringsSQL1 = {"Chưa cho thuê",MaSach};
        int isInsert1 = con.ExecuteSQLUpdateTinhTrang(stringsSQL1);
        //Thêm vào doanh thu
        String MaTraSach ="";
         ResultSet rs = con.getData("SELECT COUNT (*) as total FROM ThongtinTraSach "); 
      
       try{
        while (rs.next()){
            MaTraSach=Integer.toString( 1 + rs.getInt("total"));
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        SimpleDateFormat Date_Format = new SimpleDateFormat("MM-dd-yyyy"); 
        Date date = new Date();
        String NgayTraSach = Date_Format.format(date).toString();
        
        
        ResultSet rs1 = con.getData("SELECT GiaTien FROM SACH WHERE MaSach = "+MaSach+" "); 
        String GiaTien ="";
       try{
        while (rs1.next()){
            GiaTien= rs1.getString("GiaTien");
        }}
        catch(SQLException ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,ex);
        }
        String[] stringsSQL2 = {MaTraSach,NgayTraSach,GiaTien};
        int isInsert2 = con.ExecuteSQLThongtinTraSach(stringsSQL2);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showData();
        showDataMuonSach();
        showSumOfSachChoMuon();
        showSumOfMoney();
        ClearTextMuon();
    }
    
    public void InsertNXBData(){
        for (int i=0;i <tbQLNXB.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLNXB.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaNXB = Integer.toString(j);
        String TenNXB = txtNXB.getText();
        String DiaChi = txtDiaChi.getText();
        String Email =  txtEmail.getText();
        String NguoiDaiDien = txtNguoiDaiDien.getText();
        
         String[] stringsSQL = {MaNXB,TenNXB,DiaChi,Email,NguoiDaiDien};
        
        int isInsert = con.ExecuteSQLInsertNXB(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showDataNXB();
        ClearTextNXB();
    }
    public void UpdateNXBData(){
        int selectedRow = tbQLNXB.getSelectedRow();
        String MaNXB = tbQLNXB.getValueAt(selectedRow,0).toString();
        String TenNXB = txtNXB.getText();
        String DiaChi = txtDiaChi.getText();
        String Email =  txtEmail.getText();
        String NguoiDaiDien = txtNguoiDaiDien.getText();
        
        String[] stringsSQL = {TenNXB,DiaChi,Email,NguoiDaiDien,MaNXB};
        
        int isInsert = con.ExecuteSQLUpdateNXB(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
         showDataNXB();
        ClearTextNXB();  
    }
    public void DeleteNXBData(){
        int selectedRow = tbQLNXB.getSelectedRow();
        String MaNXB = tbQLNXB.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaNXB};
        
        int isInsert = con.ExecuteSQLDeleteNXB(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showDataNXB();
        ClearTextNXB();
    }
    
    public void InsertNhanVienData(){
        for (int i=0;i <tbQLNhanVien.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLNhanVien.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaNhanVien = Integer.toString(j);
        String TenNhanVien = txtNhanVien.getText();
        String ChucVu = txtChucVu.getText();
        
        
        String[] stringsSQL = {MaNhanVien,TenNhanVien,ChucVu};
        
        int isInsert = con.ExecuteSQLInsertNhanVien(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showDataNhanVien();
        ClearTextNhanVien();
    }
    public void UpdateNhanVienData(){
        int selectedRow = tbQLNhanVien.getSelectedRow();
        String MaNhanVien = tbQLNhanVien.getValueAt(selectedRow,0).toString();
        String TenNhanVien = txtNhanVien.getText();
        String ChucVu = txtChucVu.getText();
        ;
        
        String[] stringsSQL = {TenNhanVien,ChucVu,MaNhanVien};
        
        int isInsert = con.ExecuteSQLUpdateNhanVien(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showDataNhanVien();
        ClearTextNhanVien();  
    }
    public void DeleteNhanVienData(){
        int selectedRow = tbQLNhanVien.getSelectedRow();
        String MaNhanVien = tbQLNhanVien.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaNhanVien};
        
        int isInsert = con.ExecuteSQLDeleteNhanVien(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showDataNhanVien();
        ClearTextNhanVien();
    }
    
    public void InsertDichVuData(){
        for (int i=0;i <tbQLDichVu.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLDichVu.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaDichVu = Integer.toString(j);
        String TenDichVu = txtTenDichVu.getText();
        String GiaTien = txtGiaTien.getText();
        
        
        String[] stringsSQL = {MaDichVu,TenDichVu,GiaTien};
        
        int isInsert = con.ExecuteSQLInsertDichVu(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showDataDichVu();
        ClearTextDichVu();
    }
    public void UpdateDichVuData(){
       int selectedRow = tbQLDichVu.getSelectedRow();
        String MaDichVu = tbQLDichVu.getValueAt(selectedRow,0).toString();
        String TenDichVu = txtTenDichVu.getText();
        String GiaTien = txtGiaTien.getText();
        
        
        String[] stringsSQL = {TenDichVu,GiaTien,MaDichVu};
        
        int isInsert = con.ExecuteSQLUpdateDichVu(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showDataDichVu();
        ClearTextDichVu(); 
    }
    public void DeleteDichVuData(){
        int selectedRow = tbQLDichVu.getSelectedRow();
        String MaDichVu = tbQLDichVu.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaDichVu};
        
        int isInsert = con.ExecuteSQLDeleteDichVu(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showDataDichVu();
        ClearTextDichVu();
    }
    
    public void InsertTacGiaData(){
        for (int i=0;i <tbQLTacGia.getRowCount();i++)
        {
            if(Integer.valueOf(tbQLTacGia.getModel().getValueAt(i, 0).toString())==j)
                j++;
            else {
                
                break;
            }
        }
        String MaTacGia = Integer.toString(j);
        String TenTacGia = txtTenTacGia.getText();
        String website = txtwebsite.getText();
        String GhiChu = txtGhiChu.getText();
        
        String[] stringsSQL = {MaTacGia,TenTacGia,website,GhiChu};
        
        int isInsert = con.ExecuteSQLInsertTacGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã thêm dữ liệu thành công");
            j=1;
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa thêm được dữ liệu");
        }
        showDataTacGia();
        ClearTextTacGia();
    }
    public void UpdateTacGiaData(){
        int selectedRow = tbQLTacGia.getSelectedRow();
        String MaTacGia = tbQLTacGia.getValueAt(selectedRow,0).toString();
        String TenTacGia = inputTenDocGia_Muon.getText();
        String website = inputTenSach_Muon.getText();
        String GhiChu = datechooser.getDate().toString();
        
        String[] stringsSQL = {TenTacGia,website,GhiChu,MaTacGia};
        
        int isInsert = con.ExecuteSQLUpdateTacGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã sửa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa sửa được dữ liệu");
        }
        showDataTacGia();
        ClearTextTacGia();  
    }
    public void DeleteTacGiaData(){
        int selectedRow = tbQLTacGia.getSelectedRow();
        String MaTacGia = tbQLTacGia.getValueAt(selectedRow,0).toString();
        String[] stringsSQL = {MaTacGia};
        
        int isInsert = con.ExecuteSQLDeleteTacGia(stringsSQL);
        if(isInsert > 0)
        {
            JOptionPane.showMessageDialog(this,"Bạn đã xóa dữ liệu thành công");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa xóa được dữ liệu");
        }
        showDataTacGia();
        ClearTextTacGia();
    }
    
    
    public void getSelectedData (){
       int selectedRow = tbQLSach.getSelectedRow();
       inputMaSach.setText(tbQLSach.getValueAt(selectedRow,0).toString());
       inputTenSach.setText(tbQLSach.getValueAt(selectedRow,1).toString());
       inputTheLoai.setText(tbQLSach.getValueAt(selectedRow,2).toString());
       inputTacGia.setText(tbQLSach.getValueAt(selectedRow,3).toString());
       inputNXB.setText(tbQLSach.getValueAt(selectedRow,4).toString());
       inputTinhTrang.setText(tbQLSach.getValueAt(selectedRow,5).toString());
       inputGiaTien.setText(tbQLSach.getValueAt(selectedRow,6).toString());
       

    }
    public void getSelectedDataMuonSach(){
       int selectedRow = tbQLMuonSach.getSelectedRow();
       inputTenDocGia_Muon.setText(tbQLMuonSach.getValueAt(selectedRow,1).toString());
       inputTenSach_Muon.setText(tbQLMuonSach.getValueAt(selectedRow,2).toString());
       inputMaDocGia_Muon.setText(tbQLMuonSach.getValueAt(selectedRow,5).toString());
       inputMaSach_Muon.setText(tbQLMuonSach.getValueAt(selectedRow,4).toString());
       try{
           Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tbQLMuonSach.getValueAt(selectedRow, 3));
            datechooser.setDate(date);}
       catch(ParseException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getSelectedDataDocGia(){
       int selectedRow = tbQLDocGia.getSelectedRow();
       inputTenDocGia.setText(tbQLDocGia.getValueAt(selectedRow,1).toString());
       inputDiaChi.setText(tbQLDocGia.getValueAt(selectedRow,2).toString());
       inputEmail.setText(tbQLDocGia.getValueAt(selectedRow,3).toString());
       inputSDT.setText(tbQLDocGia.getValueAt(selectedRow,4).toString()); 
    }
    public void getSelectedDataNXB(){
       int selectedRow = tbQLNXB.getSelectedRow();
       txtNXB.setText(tbQLNXB.getValueAt(selectedRow,1).toString());
       txtDiaChi.setText(tbQLNXB.getValueAt(selectedRow,2).toString());
       txtEmail.setText(tbQLNXB.getValueAt(selectedRow,3).toString());
       txtNguoiDaiDien.setText(tbQLNXB.getValueAt(selectedRow,4).toString()); 
    }
    public void getSelectedDataNhanVien(){
       int selectedRow = tbQLNhanVien.getSelectedRow();
       txtNhanVien.setText(tbQLNhanVien.getValueAt(selectedRow,1).toString());
       txtChucVu.setText(tbQLNhanVien.getValueAt(selectedRow,2).toString());
       
    }
    public void getSelectedDataDichVu(){
       int selectedRow = tbQLDichVu.getSelectedRow();
       txtTenDichVu.setText(tbQLDichVu.getValueAt(selectedRow,1).toString());
       txtGiaTien.setText(tbQLDichVu.getValueAt(selectedRow,2).toString());
        
    }
    public void getSelectedDataTacGia(){
       int selectedRow = tbQLTacGia.getSelectedRow();
       txtTenTacGia.setText(tbQLTacGia.getValueAt(selectedRow,1).toString());      
       txtwebsite.setText(tbQLTacGia.getValueAt(selectedRow,2).toString());
       txtGhiChu.setText(tbQLTacGia.getValueAt(selectedRow,3).toString()); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQLSach = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbMaTacGia_Sach = new javax.swing.JLabel();
        inputTenSach = new javax.swing.JTextField();
        inputTheLoai = new javax.swing.JTextField();
        btnSuaSach = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnXoaSach = new javax.swing.JButton();
        btnThemSach = new javax.swing.JButton();
        btnLuuSach = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        inputTuKhoaSach = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnTimsach = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        inputMaSach = new javax.swing.JTextField();
        inputTacGia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        inputTinhTrang = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        inputGiaTien = new javax.swing.JTextField();
        lbNXB_Sach = new javax.swing.JLabel();
        inputNXB = new javax.swing.JTextField();
        Quaylai_QLSach = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbQLMuonSach = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        inputTenDocGia_Muon = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        inputTenSach_Muon = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        inputTuKhoaMuonSach = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btnTimMuonSach = new javax.swing.JButton();
        btnThemTTMuon = new javax.swing.JButton();
        btnSuaTTMuon = new javax.swing.JButton();
        btnLuuTTMuon = new javax.swing.JButton();
        btnXoaTTMuon = new javax.swing.JButton();
        btnThoat2 = new javax.swing.JButton();
        datechooser = new com.toedter.calendar.JDateChooser();
        Quaylai_QLMuonSach = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        inputMaSach_Muon = new javax.swing.JTextField();
        inputMaDocGia_Muon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbQLDocGia = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        inputTenDocGia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        inputDiaChi = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        inputSDT = new javax.swing.JTextField();
        btnThemDocGia = new javax.swing.JButton();
        btnSuaDocGia = new javax.swing.JButton();
        btnXoaDocGia = new javax.swing.JButton();
        btnLuuDocGia = new javax.swing.JButton();
        btnThoat1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        inputTuKhoaDocGia = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnTimDocGia = new javax.swing.JButton();
        Quaylai_QLDocGia = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbQLNXB = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNXB = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNguoiDaiDien = new javax.swing.JTextField();
        btnThemNXB = new javax.swing.JButton();
        btnXoaNXB = new javax.swing.JButton();
        btnSuaNXB = new javax.swing.JButton();
        btnLuuNXB = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbQLNhanVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNhanVien = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        btnThemNhanVien = new javax.swing.JButton();
        btnLuuNhanVien = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbQLDichVu = new javax.swing.JTable();
        btnThemDichVu = new javax.swing.JButton();
        btnLuuDichVu = new javax.swing.JButton();
        btnSuaDichVu = new javax.swing.JButton();
        btnXoaDichVu = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTenDichVu = new javax.swing.JTextField();
        txtGiaTien = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbQLTacGia = new javax.swing.JTable();
        txtTenTacGia = new javax.swing.JTextField();
        txtwebsite = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnThemTacGia = new javax.swing.JButton();
        btnLuuTacGia = new javax.swing.JButton();
        btnSuaTacGia = new javax.swing.JButton();
        btnXoaTacGia = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTongSoSach = new javax.swing.JTextField();
        txtTongDocGiaDangKy = new javax.swing.JTextField();
        txtSoSachChoMuon = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbThang = new javax.swing.JComboBox<>();
        btnXemDoanhThu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JFormattedTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbThongKe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ THƯ VIỆN");

        tbQLSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tên Sách", "Thể Loại", "Tên Tác Giả", "Nhà xuất bản", "Tình Trạng", "Gía tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbQLSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbQLSach);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tên Sách");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Thể Loại");

        lbMaTacGia_Sach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaTacGia_Sach.setText("Tác Giả");

        inputTenSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTenSach.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        inputTheLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTheLoai.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        btnSuaSach.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaSach.setText("Sửa TT Sách");
        btnSuaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSachActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThoat.setText("Thoát Chương Trình");

        btnXoaSach.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaSach.setText("Xóa TT Sách");
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });

        btnThemSach.setBackground(new java.awt.Color(255, 255, 255));
        btnThemSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemSach.setText("Thêm Sách ");
        btnThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSachActionPerformed(evt);
            }
        });

        btnLuuSach.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuSach.setText("Lưu TT Sách");
        btnLuuSach.setEnabled(false);
        btnLuuSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSachActionPerformed(evt);
            }
        });

        inputTuKhoaSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("             Tìm Kiếm Theo Tên");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Từ Khóa");

        btnTimsach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimsach.setText("Tìm");
        btnTimsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimsachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnTimsach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inputTuKhoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTuKhoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimsach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Mã Sách");

        inputMaSach.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(102, 255, 255)));
        inputMaSach.setEnabled(false);

        inputTacGia.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Tình Trạng");

        inputTinhTrang.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Giá Tiền");

        inputGiaTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputGiaTien.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        lbNXB_Sach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNXB_Sach.setText("Nhà xuất bản");

        inputNXB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputNXB.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));
        inputNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNXBActionPerformed(evt);
            }
        });

        Quaylai_QLSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Quaylai_QLSach.setText("Quay lại");
        Quaylai_QLSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quaylai_QLSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(lbMaTacGia_Sach, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addComponent(lbNXB_Sach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inputTacGia)
                        .addComponent(inputMaSach, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(inputTenSach)
                        .addComponent(inputNXB)
                        .addComponent(inputGiaTien)
                        .addComponent(inputTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(btnSuaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(355, 355, 355)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnLuuSach, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(25, 25, 25)
                                            .addComponent(btnXoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(587, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Quaylai_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(324, 324, 324))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSuaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(inputMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuuSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Quaylai_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMaTacGia_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNXB_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputTinhTrang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(inputGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Quản Lý Sách", jPanel1);

        tbQLMuonSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Mượn", "Tên Độc Giả", "Tên Sách ", "Ngày", "Mã Sách", "Mã Độc Giả", "Mã Dịch Vụ"
            }
        ));
        tbQLMuonSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLMuonSachMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbQLMuonSach);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Tên Độc Giả");

        inputTenDocGia_Muon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTenDocGia_Muon.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Ngày Mượn");

        inputTenSach_Muon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTenSach_Muon.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Tên Sách");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("         Tìm Kiếm Theo Tên Sách");

        inputTuKhoaMuonSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTuKhoaMuonSach.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Từ Khóa");

        btnTimMuonSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimMuonSach.setText("Tìm");
        btnTimMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMuonSachActionPerformed(evt);
            }
        });

        btnThemTTMuon.setBackground(new java.awt.Color(255, 255, 255));
        btnThemTTMuon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemTTMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemTTMuon.setText("Thêm TT Mượn ");
        btnThemTTMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTTMuonActionPerformed(evt);
            }
        });

        btnSuaTTMuon.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaTTMuon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaTTMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaTTMuon.setText("Sửa TT Mượn");
        btnSuaTTMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTMuonActionPerformed(evt);
            }
        });

        btnLuuTTMuon.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuTTMuon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuTTMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuTTMuon.setText("  Lưu TT Mượn");
        btnLuuTTMuon.setEnabled(false);
        btnLuuTTMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTTMuonActionPerformed(evt);
            }
        });

        btnXoaTTMuon.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTTMuon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaTTMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaTTMuon.setText("     Trả Sách");
        btnXoaTTMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTTMuonActionPerformed(evt);
            }
        });

        btnThoat2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThoat2.setText("Thoát Chương Trình");

        datechooser.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));
        datechooser.setDateFormatString("MM-dd-yyyy");

        Quaylai_QLMuonSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Quaylai_QLMuonSach.setText("Quay lại");
        Quaylai_QLMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quaylai_QLMuonSachActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Mã Sách");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Mã Độc Giả");

        inputMaSach_Muon.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        inputMaDocGia_Muon.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(inputTenSach_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inputTenDocGia_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inputMaDocGia_Muon)
                                            .addComponent(inputMaSach_Muon)
                                            .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabel41))
                        .addGap(346, 346, 346)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemTTMuon)
                                    .addComponent(btnLuuTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoaTTMuon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuaTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnThoat2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnTimMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Quaylai_QLMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inputTuKhoaMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(735, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputTenDocGia_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTenSach_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSuaTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuuTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaTTMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(inputMaSach_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputTuKhoaMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(inputMaDocGia_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Quaylai_QLMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Quản Lý Mượn Sách", jPanel3);

        tbQLDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Độc Giả", "Tên Độc Giả", "Địa Chỉ", "Email", "Số Điện Thoại"
            }
        ));
        tbQLDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLDocGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbQLDocGia);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Tên Độc Giả");

        inputTenDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTenDocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Địa Chỉ");

        inputDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Email");

        inputEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Số Điện Thoại");

        inputSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        btnThemDocGia.setBackground(new java.awt.Color(255, 255, 255));
        btnThemDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemDocGia.setText("Thêm Độc Giả");
        btnThemDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDocGiaActionPerformed(evt);
            }
        });

        btnSuaDocGia.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaDocGia.setText("Sửa TT Độc Giả");
        btnSuaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDocGiaActionPerformed(evt);
            }
        });

        btnXoaDocGia.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaDocGia.setText("Xóa TT Độc Giả");
        btnXoaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDocGiaActionPerformed(evt);
            }
        });

        btnLuuDocGia.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuDocGia.setText("Lưu TT Độc Giả");
        btnLuuDocGia.setEnabled(false);
        btnLuuDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDocGiaActionPerformed(evt);
            }
        });

        btnThoat1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThoat1.setText("Thoát Chương Trình");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("             Tìm Kiếm Theo Tên");

        inputTuKhoaDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Từ Khóa");

        btnTimDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimDocGia.setText("Tìm");
        btnTimDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDocGiaActionPerformed(evt);
            }
        });

        Quaylai_QLDocGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Quaylai_QLDocGia.setText("Quay lại");
        Quaylai_QLDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quaylai_QLDocGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(inputDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(350, 350, 350)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThoat1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnThemDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                            .addComponent(btnLuuDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnSuaDocGia)
                                            .addComponent(btnXoaDocGia))))))
                        .addGap(0, 758, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputTuKhoaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(Quaylai_QLDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLuuDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTuKhoaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Quaylai_QLDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Quản Lý Độc Giả", jPanel2);

        tbQLNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NXB", "Tên NXB", "Địa chỉ", "Email", "Người đại diện"
            }
        ));
        tbQLNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNXBMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbQLNXB);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tên nhà xuất bản");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Người đại diện");

        txtNXB.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtNguoiDaiDien.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        btnThemNXB.setBackground(new java.awt.Color(255, 255, 255));
        btnThemNXB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemNXB.setText("Thêm NXB");
        btnThemNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNXBActionPerformed(evt);
            }
        });

        btnXoaNXB.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaNXB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaNXB.setText("Xóa NXB");
        btnXoaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNXBActionPerformed(evt);
            }
        });

        btnSuaNXB.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaNXB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaNXB.setText("Sửa NXB");
        btnSuaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNXBActionPerformed(evt);
            }
        });

        btnLuuNXB.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuNXB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuNXB.setText("Lưu NXB");
        btnLuuNXB.setEnabled(false);
        btnLuuNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNXBActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Thoát chương trình");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(367, 367, 367)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(btnLuuNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 843, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(178, 178, 178))
        );

        jTabbedPane3.addTab("Nhà xuất bản", jPanel7);

        tbQLNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Chức Vụ"
            }
        ));
        tbQLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNhanVienMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbQLNhanVien);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tên nhân viên");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Chức vụ");

        txtNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtChucVu.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        btnThemNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnThemNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemNhanVien.setText("THÊM NHÂN VIÊN");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnLuuNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuNhanVien.setText("LƯU NHÂN VIÊN");
        btnLuuNhanVien.setEnabled(false);
        btnLuuNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhanVienActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaNhanVien.setText("SỬA NHÂN VIÊN");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaNhanVien.setText("XÓA NHÂN VIÊN");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Thoát chương trình");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1945, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(52, 52, 52)
                                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(361, 361, 361)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(btnLuuNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuuNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 408, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Nhân viên", jPanel8);

        tbQLDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Dịch Vụ", "Tên Dịch Vụ", "Giá Tiền"
            }
        ));
        tbQLDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLDichVuMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbQLDichVu);

        btnThemDichVu.setBackground(new java.awt.Color(255, 255, 255));
        btnThemDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemDichVu.setText("THÊM DỊCH VỤ");
        btnThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDichVuActionPerformed(evt);
            }
        });

        btnLuuDichVu.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuDichVu.setText("LƯU DỊCH VỤ");
        btnLuuDichVu.setEnabled(false);
        btnLuuDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDichVuActionPerformed(evt);
            }
        });

        btnSuaDichVu.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaDichVu.setText("SỬA DỊCH VỤ");
        btnSuaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDichVuActionPerformed(evt);
            }
        });

        btnXoaDichVu.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaDichVu.setText("XÓA DỊCH VỤ");
        btnXoaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDichVuActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Tên dịch vụ");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Giá tiền");

        txtTenDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtGiaTien.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Thoát chương trình");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 569, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(txtGiaTien))
                .addGap(351, 351, 351)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLuuDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(269, 269, 269))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(311, 311, 311))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Dịch vụ", jPanel9);

        tbQLTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Tác Giả", "Tên Tác Giả", "website", "Ghi Chú"
            }
        ));
        tbQLTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLTacGiaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbQLTacGia);

        txtTenTacGia.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtwebsite.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Tên Tác Giả");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("website");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Ghi chú");

        btnThemTacGia.setBackground(new java.awt.Color(255, 255, 255));
        btnThemTacGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/plus.png"))); // NOI18N
        btnThemTacGia.setText("THÊM TÁC GIẢ");
        btnThemTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTacGiaActionPerformed(evt);
            }
        });

        btnLuuTacGia.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuTacGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/save-as.png"))); // NOI18N
        btnLuuTacGia.setText("LƯU TÁC GIẢ");
        btnLuuTacGia.setEnabled(false);
        btnLuuTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTacGiaActionPerformed(evt);
            }
        });

        btnSuaTacGia.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaTacGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/maintenance.png"))); // NOI18N
        btnSuaTacGia.setText("SỬA TÁC GIẢ");
        btnSuaTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTacGiaActionPerformed(evt);
            }
        });

        btnXoaTacGia.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTacGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gui/cancel.png"))); // NOI18N
        btnXoaTacGia.setText("XÓA TÁC GIẢ");
        btnXoaTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTacGiaActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Thoát chương trình");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1945, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(666, 666, 666)
                        .addComponent(btnLuuTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel35))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtwebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(325, 325, 325)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnThemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemTacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaTacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtwebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 376, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuuTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane3.addTab("Thông Tin Tác Giả", jPanel6);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("THỐNG KÊ SỐ LIỆU");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tổng số sách trong kho");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tổng số độc giả đã đăng kí");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số sách đang cho mượn");

        txtTongSoSach.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));
        txtTongSoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSoSachActionPerformed(evt);
            }
        });

        txtTongDocGiaDangKy.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        txtSoSachChoMuon.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 255, 255)));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setText("THỐNG KÊ DOANH THU TRONG NĂM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Tháng");

        cbThang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "     ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        btnXemDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXemDoanhThu.setText("XEM");
        btnXemDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDoanhThuActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("TỔNG");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("VNĐ");

        txtDoanhThu.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtDoanhThu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tbThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sách", "Tổng số", "Đang cho mượn"
            }
        ));
        jScrollPane8.setViewportView(tbThongKe);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(167, 167, 167))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(txtSoSachChoMuon))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(42, 42, 42)
                                .addComponent(txtTongDocGiaDangKy))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtTongSoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(297, 297, 297))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(430, 430, 430))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnXemDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(292, 292, 292))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXemDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongSoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongDocGiaDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoSachChoMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Thống Kê", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 310, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongSoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongSoSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongSoSachActionPerformed

    //Mượn
    private void btnXoaTTMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTTMuonActionPerformed
        DeleteMuonData();
    }//GEN-LAST:event_btnXoaTTMuonActionPerformed

    private void btnLuuTTMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTTMuonActionPerformed
        if(flag1 == 1)
        {
            btnXoaTTMuon.setEnabled(true) ; 
            btnSuaTTMuon.setEnabled(true) ;
            InsertMuonData();
            btnThemTTMuon.setText("Thêm TT Mượn");
            flag1 = 0;
        }
        else if( flag1 == 2)
        {
            UpdateMuonData();
            btnThemTTMuon.setEnabled(true) ;
            btnXoaTTMuon.setEnabled(true) ;
            btnSuaTTMuon.setText("Sửa TT Mượn");
            flag1 = 0;
        }
        inputTenDocGia_Muon.setEnabled(true);
        inputTenSach_Muon.setEnabled(true);
        showDataMuonSach();
    }//GEN-LAST:event_btnLuuTTMuonActionPerformed

    private void btnSuaTTMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTMuonActionPerformed
        if(inputTenDocGia_Muon.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag1 == 0){
            btnThemTTMuon.setEnabled(false);
            btnXoaTTMuon.setEnabled(false);
            btnSuaTTMuon.setText("Hủy");
            btnLuuTTMuon.setEnabled(true) ;
            flag1 = 2;
            inputTenDocGia_Muon.setEnabled(false);
            inputTenSach_Muon.setEnabled(false);
        }
        else if( flag1 == 2)
        {
            btnThemTTMuon.setEnabled(true);
            btnXoaTTMuon.setEnabled(true);
            btnSuaTTMuon.setText("Sửa TT Mượn");
            btnLuuTTMuon.setEnabled(false) ;
            flag1 = 0;
            inputTenDocGia_Muon.setEnabled(true);
            inputTenSach_Muon.setEnabled(true);
        }
    }//GEN-LAST:event_btnSuaTTMuonActionPerformed

    private void btnThemTTMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTTMuonActionPerformed
        
        if(flag1 == 0){
            btnXoaTTMuon.setEnabled(false);
            btnSuaTTMuon.setEnabled(false);
            btnThemTTMuon.setText("Hủy");
            btnLuuTTMuon.setEnabled(true) ;
            ClearTextMuon();
            inputTenDocGia_Muon.setEnabled(false);
            inputTenSach_Muon.setEnabled(false);
            flag1 = 1;
        }
        else if( flag1 == 1)
        {
            btnXoaTTMuon.setEnabled(true);
            btnSuaTTMuon.setEnabled(true);
            btnThemTTMuon.setText("Thêm TT Mượn");
            btnLuuTTMuon.setEnabled(false) ;
            ClearTextMuon();
            inputTenDocGia_Muon.setEnabled(true);
            inputTenSach_Muon.setEnabled(true);
            flag1 = 0;
        }
    }//GEN-LAST:event_btnThemTTMuonActionPerformed

    private void tbQLMuonSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLMuonSachMouseClicked
        getSelectedDataMuonSach();// TODO add your handling code here:
    }//GEN-LAST:event_tbQLMuonSachMouseClicked

    //Độc giả
    
    private void btnLuuDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDocGiaActionPerformed

        if(flag2 == 1)
        {
            btnXoaDocGia.setEnabled(true) ; 
            btnSuaDocGia.setEnabled(true) ;
            InsertDocGiaData();
            btnThemDocGia.setText("Thêm Độc Giả");
            flag2 = 0;
        }
        else if( flag2 == 2)
        {
            UpdateDocGiaData();
            btnThemDocGia.setEnabled(true) ;
            btnXoaDocGia.setEnabled(true) ;
            btnSuaDocGia.setText("Sửa TT DocGia");
            flag2 = 0;
        }
    }//GEN-LAST:event_btnLuuDocGiaActionPerformed

    private void btnXoaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDocGiaActionPerformed
        DeleteDocGiaData();// TODO add your handling code here:
    }//GEN-LAST:event_btnXoaDocGiaActionPerformed

    private void btnSuaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDocGiaActionPerformed
        if(inputTenDocGia.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag2 == 0){
            btnThemDocGia.setEnabled(false);
            btnXoaDocGia.setEnabled(false);
            btnSuaDocGia.setText("Hủy");
            btnLuuDocGia.setEnabled(true) ;
            flag2 = 2;
        }
        else if( flag2 == 2)
        {
            btnThemDocGia.setEnabled(true);
            btnXoaDocGia.setEnabled(true);
            btnSuaDocGia.setText("Sửa TT Đôc Giả");
            btnLuuDocGia.setEnabled(false) ;
            flag2 = 0;
        }
    }//GEN-LAST:event_btnSuaDocGiaActionPerformed

    private void btnThemDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDocGiaActionPerformed
        if(flag2 == 0){
            btnXoaDocGia.setEnabled(false);
            btnSuaDocGia.setEnabled(false);
            btnThemDocGia.setText("Hủy");
            btnLuuDocGia.setEnabled(true) ;
            flag2 = 1;
        }
        else if( flag2 == 1)
        {
            btnXoaDocGia.setEnabled(true);
            btnSuaDocGia.setEnabled(true);
            btnThemDocGia.setText("Thêm Độc Giả");
            btnLuuDocGia.setEnabled(false) ;
            flag2 = 0;
        }

    }//GEN-LAST:event_btnThemDocGiaActionPerformed
  
    private void tbQLDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLDocGiaMouseClicked
        getSelectedDataDocGia();// TODO add your handling code here:
    }//GEN-LAST:event_tbQLDocGiaMouseClicked

    
    //Sách
    private void btnLuuSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSachActionPerformed
        if(flag == 1)
        {
            InsertSachData();
            btnXoaSach.setEnabled(true);
            btnSuaSach.setEnabled(true);
            btnThemSach.setText("Thêm Sách");
            lbMaTacGia_Sach.setText(" Tác Giả");
            lbNXB_Sach.setText("Nhà Xuất Bản");
            flag = 0;
            
        }
        else if( flag == 2)
        {
            UpdateSachData();
            btnThemSach.setEnabled(true) ;
            btnXoaSach.setEnabled(true) ;
            btnSuaSach.setText("Sửa TT Sách");
            lbMaTacGia_Sach.setText("Tác Giả");
            lbNXB_Sach.setText("Nhà Xuất Bản");
            flag = 0;
            
        }
    }//GEN-LAST:event_btnLuuSachActionPerformed

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        if(flag ==0){
            
            btnXoaSach.setEnabled(false);
            btnSuaSach.setEnabled(false);
            btnThemSach.setText("Hủy");
            btnLuuSach.setEnabled(true) ;
            lbMaTacGia_Sach.setText("Mã Tác Giả");
            lbNXB_Sach.setText("Mã Nhà Xuất Bản");
            flag = 1;
            ClearText();
        }
        else if( flag == 1)
        {
            
            btnXoaSach.setEnabled(true);
            btnSuaSach.setEnabled(true);
            btnThemSach.setText("Thêm Sách");
            btnLuuSach.setEnabled(false) ;
            flag = 0;
            lbMaTacGia_Sach.setText(" Tác Giả");
            lbNXB_Sach.setText("Nhà Xuất Bản");
            ClearText();
        }
        
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        DeleteSachData();
    }//GEN-LAST:event_btnXoaSachActionPerformed

    private void btnSuaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSachActionPerformed
        
        if(inputMaSach.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag == 0)
        {
        
        btnThemSach.setEnabled(false) ;
        btnXoaSach.setEnabled(false) ;
        btnSuaSach.setText("Hủy");
        btnLuuSach.setEnabled(true) ;
        flag = 2;
        lbMaTacGia_Sach.setText("Mã Tác Giả");
        lbNXB_Sach.setText("Mã Nhà Xuất Bản");
        }
        else if(flag == 2)
        {
        
        btnThemSach.setEnabled(true) ;
        btnXoaSach.setEnabled(true) ;
        btnSuaSach.setText("Sửa TT Sách");
        btnLuuSach.setEnabled(false) ;
        lbMaTacGia_Sach.setText("Tác Giả");
        lbNXB_Sach.setText("Nhà Xuất Bản");
        flag = 0;
        }
    }//GEN-LAST:event_btnSuaSachActionPerformed

    
    
    private void tbQLSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLSachMouseClicked
        getSelectedData();
    }//GEN-LAST:event_tbQLSachMouseClicked

    private void btnXemDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDoanhThuActionPerformed
        String sum = "";
        //ResultSet rs= con.getData("Select Sum(GiaTien) as total from ThongtinTraSach where NgayTraSach.getMonth "); 
    }//GEN-LAST:event_btnXemDoanhThuActionPerformed

    private void tbQLNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNXBMouseClicked
        getSelectedDataNXB();
    }//GEN-LAST:event_tbQLNXBMouseClicked

    private void tbQLNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNhanVienMouseClicked
        getSelectedDataNhanVien();
    }//GEN-LAST:event_tbQLNhanVienMouseClicked

    private void tbQLDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLDichVuMouseClicked
       getSelectedDataDichVu();
    }//GEN-LAST:event_tbQLDichVuMouseClicked

    private void tbQLTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLTacGiaMouseClicked
        getSelectedDataTacGia();
    }//GEN-LAST:event_tbQLTacGiaMouseClicked

    
    //Nhà xuất bản
    private void btnThemNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNXBActionPerformed
        if(flag3 == 0){
            btnXoaNXB.setEnabled(false);
            btnSuaNXB.setEnabled(false);
            btnThemNXB.setText("Hủy");
            btnLuuNXB.setEnabled(true) ;
            flag3 = 1;
        }
        else if( flag3 == 1)
        {
            btnXoaNXB.setEnabled(true);
            btnSuaNXB.setEnabled(true);
            btnThemNXB.setText("Thêm TT Mượn");
            btnLuuNXB.setEnabled(false) ;
            flag3 = 0;
        }
    }//GEN-LAST:event_btnThemNXBActionPerformed

    private void btnLuuNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNXBActionPerformed
        if(flag3 == 1)
        {
            btnXoaNXB.setEnabled(true) ; 
            btnSuaNXB.setEnabled(true) ;
            btnThemNXB.setText("Thêm NXB") ;
            InsertNXBData();
            flag3 = 0;
        }
        else if( flag3 == 2)
        {
            UpdateNXBData();
            btnThemNXB.setEnabled(true) ;
            btnXoaNXB.setEnabled(true) ;
            btnSuaNXB.setText("Sửa NXB");
            flag3 = 0;
        }
        
    }//GEN-LAST:event_btnLuuNXBActionPerformed

    private void btnSuaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNXBActionPerformed
        if(txtNXB.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag3 == 0){
            btnThemNXB.setEnabled(false);
            btnXoaNXB.setEnabled(false);
            btnSuaNXB.setText("Hủy");
            btnLuuNXB.setEnabled(true) ;
            flag3 = 2;
        }
        else if( flag3 == 2)
        {
            btnThemNXB.setEnabled(true);
            btnXoaNXB.setEnabled(true);
            btnSuaNXB.setText("Sửa NXB");
            btnLuuNXB.setEnabled(false) ;
            flag3 = 0;
        }
    }//GEN-LAST:event_btnSuaNXBActionPerformed

    private void btnXoaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNXBActionPerformed
        
        DeleteNXBData();
    }//GEN-LAST:event_btnXoaNXBActionPerformed

    
    
    //Nhân viên
    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        
        if(flag4 == 0){
            btnXoaNhanVien.setEnabled(false);
            btnSuaNhanVien.setEnabled(false);
            btnThemNhanVien.setText("Hủy");
            btnLuuNhanVien.setEnabled(true) ;
            flag4 = 1;
        }
        else if( flag4 == 1)
        {
            btnXoaNhanVien.setEnabled(true);
            btnSuaNhanVien.setEnabled(true);
            btnThemNhanVien.setText("Thêm Nhân Viên");
            btnLuuNhanVien.setEnabled(false) ;
            flag4 = 0;
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
       if(txtNhanVien.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag4 == 0){
            btnThemNhanVien.setEnabled(false);
            btnXoaNhanVien.setEnabled(false);
            btnSuaNhanVien.setText("Hủy");
            btnLuuNhanVien.setEnabled(true) ;
            flag4 = 2;
        }
        else if( flag4 == 2)
        {
            btnThemNhanVien.setEnabled(true);
            btnXoaNhanVien.setEnabled(true);
            btnSuaNhanVien.setText("Sửa Nhân Viên");
            btnLuuNhanVien.setEnabled(false) ;
            flag4 = 0;
        }
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnLuuNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhanVienActionPerformed
        
        if(flag4 == 1)
        {
            btnXoaNhanVien.setEnabled(true) ; 
            btnSuaNhanVien.setEnabled(true) ;
            InsertNhanVienData();
            btnThemNhanVien.setText("Thêm Nhân Viên");
            flag4 = 0;
        }
        else if( flag4 == 2)
        {
            UpdateNhanVienData();
            btnThemNhanVien.setEnabled(true) ;
            btnXoaNhanVien.setEnabled(true) ;
            btnSuaNhanVien.setText("Sửa Nhân Viên");
            flag4 = 0;
        }
    }//GEN-LAST:event_btnLuuNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        DeleteNhanVienData();
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    
    //Dịch vụ
    private void btnThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDichVuActionPerformed
        
        if(flag5 == 0){
            btnXoaDichVu.setEnabled(false);
            btnSuaDichVu.setEnabled(false);
            btnThemDichVu.setText("Hủy");
            btnLuuDichVu.setEnabled(true) ;
            flag5 = 1;
            
        }
        else if( flag5 == 1)
        {
            btnXoaDichVu.setEnabled(true);
            btnSuaDichVu.setEnabled(true);
            btnThemDichVu.setText("Thêm Dịch Vụ");
            btnLuuDichVu.setEnabled(false) ;
            flag5 = 0;
            
        }
    }//GEN-LAST:event_btnThemDichVuActionPerformed

    private void btnSuaDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDichVuActionPerformed
        if(txtTenDichVu.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag5 == 0){
            btnThemDichVu.setEnabled(false);
            btnXoaDichVu.setEnabled(false);
            btnSuaDichVu.setText("Hủy");
            btnLuuDichVu.setEnabled(true) ;
            flag5 = 2;
        }
        else if( flag5 == 2)
        {
            btnThemDichVu.setEnabled(true);
            btnXoaDichVu.setEnabled(true);
            btnSuaDichVu.setText("Sửa Dich Vụ");
            btnLuuDichVu.setEnabled(false) ;
            flag5 = 0;
        }
    }//GEN-LAST:event_btnSuaDichVuActionPerformed

    private void btnLuuDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDichVuActionPerformed
        
        if(flag5 == 1)
        {
            btnXoaDichVu.setEnabled(true) ; 
            btnSuaDichVu.setEnabled(true) ;
            InsertDichVuData();
            btnThemDichVu.setText("Thêm Dịch Vụ");
            flag5 = 0;
        }
        else if( flag5 == 2)
        {
            UpdateDichVuData();
            btnThemDichVu.setEnabled(true) ;
            btnXoaDichVu.setEnabled(true) ;
            btnSuaDichVu.setText("Sửa Dịch Vụ");
            flag5 = 0;
        }
    }//GEN-LAST:event_btnLuuDichVuActionPerformed

    private void btnXoaDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDichVuActionPerformed
        DeleteDichVuData();
    }//GEN-LAST:event_btnXoaDichVuActionPerformed

    
    //tác giả
    private void btnThemTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTacGiaActionPerformed
        
        if(flag6 == 0){
            btnXoaTacGia.setEnabled(false);
            btnSuaTacGia.setEnabled(false);
            btnThemTacGia.setText("Hủy");
            btnLuuTacGia.setEnabled(true) ;
            flag6 = 1;
        }
        else if( flag6 == 1)
        {
            btnXoaTacGia.setEnabled(true);
            btnSuaTacGia.setEnabled(true);
            btnThemTacGia.setText("Thêm Tác Giả");
            btnLuuTacGia.setEnabled(false) ;
            flag6 = 0;
        }
    }//GEN-LAST:event_btnThemTacGiaActionPerformed

    private void btnSuaTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTacGiaActionPerformed
        if(txtTenTacGia.getText().length() == 0)
        {
          JOptionPane.showMessageDialog(this,"Vui lòng chọn thông tin cần sửa trong bảng"); 
          return;
        }
        if(flag6 == 0){
            btnThemTacGia.setEnabled(false);
            btnXoaTacGia.setEnabled(false);
            btnSuaTacGia.setText("Hủy");
            btnLuuTacGia.setEnabled(true) ;
            flag6 = 2;
        }
        else if( flag6 == 2)
        {
            btnThemTacGia.setEnabled(true);
            btnXoaTacGia.setEnabled(true);
            btnSuaTacGia.setText("Sửa Tác Giả");
            btnLuuTacGia.setEnabled(false) ;
            flag6 = 0;
        }
    }//GEN-LAST:event_btnSuaTacGiaActionPerformed

    private void btnLuuTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTacGiaActionPerformed
        
        if(flag6 == 1)
        {
            btnXoaTacGia.setEnabled(true) ; 
            btnSuaTacGia.setEnabled(true) ;
            InsertTacGiaData();
            btnThemTacGia.setText("Thêm Tác Giả");
            flag6 = 0;
        }
        else if( flag6 == 2)
        {
            UpdateTacGiaData();
            btnThemTacGia.setEnabled(true) ;
            btnXoaTacGia.setEnabled(true) ;
            btnSuaTacGia.setText("Sửa Tác Giả");
            flag6 = 0;
        }
    }//GEN-LAST:event_btnLuuTacGiaActionPerformed

    private void btnXoaTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTacGiaActionPerformed
        DeleteTacGiaData();
        
    }//GEN-LAST:event_btnXoaTacGiaActionPerformed

    
    
    private void inputNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNXBActionPerformed

    private void btnTimsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimsachActionPerformed
        TimKiemSach_QLSach();
    }//GEN-LAST:event_btnTimsachActionPerformed

    private void btnTimMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMuonSachActionPerformed
        TimKiem_QLMuonSach();
    }//GEN-LAST:event_btnTimMuonSachActionPerformed

    private void btnTimDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDocGiaActionPerformed
        TimKiemTen_QLDocGia();
    }//GEN-LAST:event_btnTimDocGiaActionPerformed

    private void Quaylai_QLSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quaylai_QLSachActionPerformed
       showData();
    }//GEN-LAST:event_Quaylai_QLSachActionPerformed

    private void Quaylai_QLMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quaylai_QLMuonSachActionPerformed
        showDataMuonSach();
    }//GEN-LAST:event_Quaylai_QLMuonSachActionPerformed

    private void Quaylai_QLDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quaylai_QLDocGiaActionPerformed
        showDataDocGia();
    }//GEN-LAST:event_Quaylai_QLDocGiaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Quaylai_QLDocGia;
    private javax.swing.JButton Quaylai_QLMuonSach;
    private javax.swing.JButton Quaylai_QLSach;
    private javax.swing.JButton btnLuuDichVu;
    private javax.swing.JButton btnLuuDocGia;
    private javax.swing.JButton btnLuuNXB;
    private javax.swing.JButton btnLuuNhanVien;
    private javax.swing.JButton btnLuuSach;
    private javax.swing.JButton btnLuuTTMuon;
    private javax.swing.JButton btnLuuTacGia;
    private javax.swing.JButton btnSuaDichVu;
    private javax.swing.JButton btnSuaDocGia;
    private javax.swing.JButton btnSuaNXB;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnSuaSach;
    private javax.swing.JButton btnSuaTTMuon;
    private javax.swing.JButton btnSuaTacGia;
    private javax.swing.JButton btnThemDichVu;
    private javax.swing.JButton btnThemDocGia;
    private javax.swing.JButton btnThemNXB;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThemTTMuon;
    private javax.swing.JButton btnThemTacGia;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThoat1;
    private javax.swing.JButton btnThoat2;
    private javax.swing.JButton btnTimDocGia;
    private javax.swing.JButton btnTimMuonSach;
    private javax.swing.JButton btnTimsach;
    private javax.swing.JButton btnXemDoanhThu;
    private javax.swing.JButton btnXoaDichVu;
    private javax.swing.JButton btnXoaDocGia;
    private javax.swing.JButton btnXoaNXB;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JButton btnXoaSach;
    private javax.swing.JButton btnXoaTTMuon;
    private javax.swing.JButton btnXoaTacGia;
    private javax.swing.JComboBox<String> cbThang;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JTextField inputDiaChi;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputGiaTien;
    private javax.swing.JTextField inputMaDocGia_Muon;
    private javax.swing.JTextField inputMaSach;
    private javax.swing.JTextField inputMaSach_Muon;
    private javax.swing.JTextField inputNXB;
    private javax.swing.JTextField inputSDT;
    private javax.swing.JTextField inputTacGia;
    private javax.swing.JTextField inputTenDocGia;
    private javax.swing.JTextField inputTenDocGia_Muon;
    private javax.swing.JTextField inputTenSach;
    private javax.swing.JTextField inputTenSach_Muon;
    private javax.swing.JTextField inputTheLoai;
    private javax.swing.JTextField inputTinhTrang;
    private javax.swing.JTextField inputTuKhoaDocGia;
    private javax.swing.JTextField inputTuKhoaMuonSach;
    private javax.swing.JTextField inputTuKhoaSach;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbMaTacGia_Sach;
    private javax.swing.JLabel lbNXB_Sach;
    private javax.swing.JTable tbQLDichVu;
    private javax.swing.JTable tbQLDocGia;
    private javax.swing.JTable tbQLMuonSach;
    private javax.swing.JTable tbQLNXB;
    private javax.swing.JTable tbQLNhanVien;
    private javax.swing.JTable tbQLSach;
    private javax.swing.JTable tbQLTacGia;
    private javax.swing.JTable tbThongKe;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JFormattedTextField txtDoanhThu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtNXB;
    private javax.swing.JTextField txtNguoiDaiDien;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtSoSachChoMuon;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenTacGia;
    private javax.swing.JTextField txtTongDocGiaDangKy;
    private javax.swing.JTextField txtTongSoSach;
    private javax.swing.JTextField txtwebsite;
    // End of variables declaration//GEN-END:variables
}

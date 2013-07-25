/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemperpustakaan.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import sistemperpustakaan.koneksi.DriverRegister;
import sistemperpustakaan.model.PeminjamanModel;

/**
 *
 * @author zword
 */
public class PeminjamanImplement {
    private static String table="peminjaman";
    
    public void Input(PeminjamanModel pm)throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement statement = DriverRegister.getConnection().
                prepareStatement("insert into "+table
                                    +" (noPinjam,nim,nama,namaBuku,tglPinjam,tglKembali)"
                                    +"values"+"(?,?,?,?,?,?)");
        statement.setInt(1, pm.getNo());
        statement.setString(2, pm.getNim());
        statement.setString(3, pm.getNama());
        statement.setString(4, pm.getBuku());
        statement.setString(5, String.valueOf(format.format(pm.getTglPinjam())));
        statement.setString(6, String.valueOf(format.format(pm.getTglKembali())));
        
        statement.executeUpdate();
        
    }
    
    public void Hapus (PeminjamanModel pm) throws SQLException{
        PreparedStatement prepare = DriverRegister.getConnection().
                                    prepareStatement("delete from "+table
                                    +" where noPinjam = ? ");
            prepare.setInt(1, pm.getNo());
            prepare.executeUpdate();
    }
    
    public List<PeminjamanModel> load() throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Statement statement = DriverRegister.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select * from "+table);
        List<PeminjamanModel> list = new ArrayList<PeminjamanModel>();
        while(result.next()){
            PeminjamanModel pm = new PeminjamanModel();
            pm.setNo(result.getInt("noPinjam"));
            pm.setNim(result.getString("nim"));
            pm.setNama(result.getString("nama"));
            pm.setBuku(result.getString("namaBuku"));
            try {
                    pm.setTglPinjam(format.parse(result.getString("tglPinjam")));
                    pm.setTglKembali(format.parse(result.getString("tglKembali")));

            } catch (ParseException e) {
                System.out.println("Parsing tanggal gagal "+e.getMessage());
            }

            list.add(pm);
        }
        return list;
    }
}

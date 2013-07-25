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
import sistemperpustakaan.model.PengembalianModel;


/**
 *
 * @author zword
 */
public class PengembalianImplement {
    private static String table="pengembalian";
    
    public void Input(PengembalianModel pm)throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement statement = DriverRegister.getConnection().
                prepareStatement("insert into "+table
                                    +" (noKembali,noPinjam,tglKembali,denda)"
                                    +"values"+"(?,?,?,?)");
        statement.setInt(1, pm.getNoKembali());
        statement.setInt(2, pm.getNoPinjam());
        statement.setString(3, String.valueOf(format.format(pm.getTglKembali())));
        statement.setLong(4, pm.getDenda());
        
        statement.executeUpdate();
        
    }
    
    public void Hapus (PengembalianModel pm) throws SQLException{
        PreparedStatement prepare = DriverRegister.getConnection().
                                    prepareStatement("delete from "+table
                                    +" where noKembali = ? ");
            prepare.setInt(1, pm.getNoKembali());
            prepare.executeUpdate();
    }
    
    public List<PengembalianModel> load() throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Statement statement = DriverRegister.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select * from "+table);
        List<PengembalianModel> list = new ArrayList<PengembalianModel>();
        while(result.next()){
            PengembalianModel pm = new PengembalianModel();
            pm.setNoKembali(result.getInt("noKembali"));
            pm.setNoPinjam(result.getInt("noPinjam"));
            pm.setDenda(result.getLong("denda"));
            try {
                    pm.setTglKembali(format.parse(result.getString("tglKembali")));

            } catch (ParseException e) {
                System.out.println("Parsing tanggal gagal "+e.getMessage());
            }

            list.add(pm);
        }
        return list;
    }
}

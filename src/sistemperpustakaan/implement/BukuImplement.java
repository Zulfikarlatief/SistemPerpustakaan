/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemperpustakaan.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemperpustakaan.koneksi.DriverRegister;
import sistemperpustakaan.model.BukuModel;

/**
 *
 * @author zword
 */
public class BukuImplement {
    private static String table="buku";
    
    public void Input(BukuModel bm)throws SQLException{
        PreparedStatement statement = DriverRegister.getConnection().
                prepareStatement("insert into "+table
                                    +" (idBuku,namaBuku,pengarang,penerbit)"
                                    +"values"+"(?,?,?,?)");
        statement.setString(1, bm.getId());
        statement.setString(2, bm.getNama());
        statement.setString(3, bm.getPengarang());
        statement.setString(4, bm.getPenerbit());

        
        statement.executeUpdate();
        
    }
    
    public void Hapus (BukuModel bm) throws SQLException{
        PreparedStatement prepare = DriverRegister.getConnection().
                                    prepareStatement("delete from "+table
                                    +" where idBuku = ? ");
            prepare.setString(1, bm.getId());
            prepare.executeUpdate();
    }
    
    public List<BukuModel> load() throws SQLException {
        Statement statement = DriverRegister.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select * from "+table);
        List<BukuModel> list = new ArrayList<BukuModel>();
        while(result.next()){
            BukuModel bm = new BukuModel();
            bm.setId(result.getString("idbuku"));
            bm.setNama(result.getString("namaBuku"));
            bm.setPengarang(result.getString("pengarang"));
            bm.setPenerbit(result.getString("penerbit"));

            list.add(bm);
        }
        return list;
    }
}

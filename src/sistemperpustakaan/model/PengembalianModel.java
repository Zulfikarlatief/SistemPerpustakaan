/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemperpustakaan.model;

import java.util.Date;

/**
 *
 * @author zword
 */
public class PengembalianModel {
    
    int noKembali;
    int noPinjam;
    Date tglKembali;
    long denda;


    public long getDenda() {
        return denda;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PengembalianModel other = (PengembalianModel) obj;
        if (this.noKembali != other.noKembali) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.noKembali;
        return hash;
    }

    public int getNoKembali() {
        return noKembali;
    }

    public void setNoKembali(int noKembali) {
        this.noKembali = noKembali;
    }

    public int getNoPinjam() {
        return noPinjam;
    }

    public void setNoPinjam(int noPinjam) {
        this.noPinjam = noPinjam;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    
    
}

package model;

import dao.KendaraanDAO;
import dao.KendaraanDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class PengelolaKendaraan {

    private KendaraanDAO dao;

    public PengelolaKendaraan() {
        this.dao = new KendaraanDAOImpl();
    }

    public boolean tambahKendaraan(KendaraanTambang kendaraan) {
        try {
            if (dao.getById(kendaraan.getId()) != null) {
                return false;
            }
            dao.insert(kendaraan);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public ArrayList<KendaraanTambang> getSemuaKendaraan() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public KendaraanTambang cari(String id) {
        try {
            return dao.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public ArrayList<KendaraanTambang> cari(double hargaMin, double hargaMax) {
        try {
            return dao.getByRentangHarga(hargaMin, hargaMax);
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public boolean updateKendaraan(String id, String namaBaru, double hargaBaru) {
        KendaraanTambang k = cari(id);
        if (k == null) {
            return false;
        }
        // validasi lewat setter (lempar IllegalArgumentException jika tidak valid)
        k.setNamaKendaraan(namaBaru);
        k.setHargaSewaPerHari(hargaBaru);
        try {
            return dao.update(id, k.getNamaKendaraan(), k.getHargaSewaPerHari());
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public boolean hapusKendaraan(String id) {
        try {
            return dao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Kesalahan database: " + e.getMessage(), e);
        }
    }

    public int getJumlahData() {
        return getSemuaKendaraan().size();
    }
}

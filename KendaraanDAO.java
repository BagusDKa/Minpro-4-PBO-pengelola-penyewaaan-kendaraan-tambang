package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.KendaraanTambang;

public interface KendaraanDAO {

    void insert(KendaraanTambang kendaraan) throws SQLException;

    ArrayList<KendaraanTambang> getAll() throws SQLException;

    KendaraanTambang getById(String id) throws SQLException;

    ArrayList<KendaraanTambang> getByRentangHarga(double min, double max) throws SQLException;

    boolean update(String id, String namaBaru, double hargaBaru) throws SQLException;

    boolean delete(String id) throws SQLException;
}

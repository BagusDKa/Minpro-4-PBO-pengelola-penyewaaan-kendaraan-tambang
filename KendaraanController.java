package controller;

import java.util.ArrayList;
import model.DumpTruck;
import model.Excavator;
import model.KendaraanTambang;
import model.PengelolaKendaraan;
import view.KendaraanView;

public class KendaraanController {

    private PengelolaKendaraan model;
    private KendaraanView view;

    public KendaraanController(PengelolaKendaraan model, KendaraanView view) {
        this.model = model;
        this.view = view;
    }

    public void jalankan() {
        int pilihan;
        do {
            view.tampilkanMenu();
            pilihan = view.bacaIntRentang("Pilih menu (1-7): ", 1, 7);

            try {
                switch (pilihan) {
                    case 1:
                        tambahData();
                        break;
                    case 2:
                        view.tampilkanDaftar(model.getSemuaKendaraan());
                        break;
                    case 3:
                        updateData();
                        break;
                    case 4:
                        hapusData();
                        break;
                    case 5:
                        hitungBiayaSewa();
                        break;
                    case 6:
                        cariByHarga();
                        break;
                    case 7:
                        view.tampilkanPesan("Terima kasih, program selesai.");
                        break;
                }
            } catch (RuntimeException e) {
                view.tampilkanPesan("Terjadi kesalahan: " + e.getMessage());
            }
        } while (pilihan != 7);
    }

    private void tambahData() {
        view.tampilkanJudul("Tambah Data Kendaraan");
        view.tampilkanJenisKendaraan();
        int jenis = view.bacaIntRentang("Pilih jenis (1-2): ", 1, 2);

        String id = view.bacaStringTidakKosong("ID Kendaraan       : ");
        if (model.cari(id) != null) {
            view.tampilkanPesan("Gagal: ID sudah dipakai.");
            return;
        }
        String nama = view.bacaStringTidakKosong("Nama Kendaraan    : ");
        double harga = view.bacaDoublePositif("Harga Sewa / Hari : ");

        KendaraanTambang kendaraan; 
        if (jenis == 1) {
            double kapasitas = view.bacaDoublePositif("Kapasitas (ton)   : ");
            kendaraan = new DumpTruck(id, nama, harga, kapasitas);
        } else {
            double kapasitas = view.bacaDoublePositif("Kapasitas bucket (m3): ");
            kendaraan = new Excavator(id, nama, harga, kapasitas);
        }

        if (model.tambahKendaraan(kendaraan)) {
            view.tampilkanPesan("Data berhasil ditambahkan.");
        } else {
            view.tampilkanPesan("Gagal: ID sudah dipakai.");
        }
    }

    private void updateData() {
        view.tampilkanJudul("Update Data Kendaraan");
        if (model.getJumlahData() == 0) {
            view.tampilkanPesan("Belum ada data kendaraan.");
            return;
        }
        view.tampilkanDaftar(model.getSemuaKendaraan());
        String id = view.bacaStringTidakKosong("Masukkan ID yang akan diupdate: ");
        if (model.cari(id) == null) {
            view.tampilkanPesan("Data dengan ID tersebut tidak ditemukan.");
            return;
        }
        String namaBaru = view.bacaStringTidakKosong("Nama baru          : ");
        double hargaBaru = view.bacaDoublePositif("Harga sewa/hari baru: ");

        if (model.updateKendaraan(id, namaBaru, hargaBaru)) {
            view.tampilkanPesan("Data berhasil diupdate.");
        } else {
            view.tampilkanPesan("Gagal mengupdate data.");
        }
    }

    private void hapusData() {
        view.tampilkanJudul("Hapus Data Kendaraan");
        if (model.getJumlahData() == 0) {
            view.tampilkanPesan("Belum ada data kendaraan.");
            return;
        }
        view.tampilkanDaftar(model.getSemuaKendaraan());
        String id = view.bacaStringTidakKosong("Masukkan ID yang akan dihapus: ");
        if (model.hapusKendaraan(id)) {
            view.tampilkanPesan("Data berhasil dihapus.");
        } else {
            view.tampilkanPesan("Data dengan ID tersebut tidak ditemukan.");
        }
    }

    private void hitungBiayaSewa() {
        view.tampilkanJudul("Hitung Biaya Sewa");
        if (model.getJumlahData() == 0) {
            view.tampilkanPesan("Belum ada data kendaraan.");
            return;
        }
        view.tampilkanDaftar(model.getSemuaKendaraan());
        String id = view.bacaStringTidakKosong("Masukkan ID kendaraan: ");
        KendaraanTambang k = model.cari(id); // overloading cari(String)
        if (k == null) {
            view.tampilkanPesan("Data dengan ID tersebut tidak ditemukan.");
            return;
        }
        int hari = view.bacaIntRentang("Lama sewa (hari, 1-365): ", 1, 365);
        int diskon = view.bacaIntRentang("Diskon % (0 = tanpa diskon): ", 0, 100);

        double total;
        if (diskon == 0) {
            total = k.hitungBiayaSewa(hari);          
        } else {
            total = k.hitungBiayaSewa(hari, diskon);  
        }
        view.tampilkanPesan("Total biaya sewa : ", total); 
    }

    private void cariByHarga() {
        view.tampilkanJudul("Cari Kendaraan Berdasarkan Harga");
        double min = view.bacaDoublePositif("Harga minimum: ");
        double max = view.bacaDoublePositif("Harga maksimum: ");
        if (min > max) {
            view.tampilkanPesan("Harga minimum tidak boleh lebih besar dari maksimum.");
            return;
        }
        ArrayList<KendaraanTambang> hasil = model.cari(min, max); 
        if (hasil.isEmpty()) {
            view.tampilkanPesan("Tidak ada kendaraan pada rentang harga tersebut.");
        } else {
            view.tampilkanDaftar(hasil);
        }
    }
}

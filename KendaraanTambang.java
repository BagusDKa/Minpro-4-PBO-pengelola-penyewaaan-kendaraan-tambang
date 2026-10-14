package model;

public abstract class KendaraanTambang {

    private String id;
    private String namaKendaraan;
    private double hargaSewaPerHari;

    public KendaraanTambang(String id, String namaKendaraan, double hargaSewaPerHari) {
        setId(id);
        setNamaKendaraan(namaKendaraan);
        setHargaSewaPerHari(hargaSewaPerHari);
    }

    public String getId() {
        return id;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public double getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID tidak boleh kosong.");
        }
        this.id = id.trim();
    }

    public void setNamaKendaraan(String namaKendaraan) {
        if (namaKendaraan == null || namaKendaraan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kendaraan tidak boleh kosong.");
        }
        this.namaKendaraan = namaKendaraan.trim();
    }

    public void setHargaSewaPerHari(double hargaSewaPerHari) {
        if (hargaSewaPerHari <= 0) {
            throw new IllegalArgumentException("Harga sewa harus lebih dari 0.");
        }
        this.hargaSewaPerHari = hargaSewaPerHari;
    }

    public abstract String getJenis();

    protected abstract String getDetail();

    public abstract double hitungBiayaSewa(int hari);

    public double hitungBiayaSewa(int hari, double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus antara 0 sampai 100.");
        }
        double total = hitungBiayaSewa(hari); 
        return total - (total * diskonPersen / 100);
    }

    protected void validasiHari(int hari) {
        if (hari <= 0) {
            throw new IllegalArgumentException("Jumlah hari harus lebih dari 0.");
        }
    }

    @Override
    public String toString() {
        String harga = String.format("Rp %,.2f / hari", hargaSewaPerHari);
        return String.format("%-8s | %-12s | %-20s | %-24s | %s",
                id, getJenis(), namaKendaraan, harga, getDetail());
    }
}

package model;

public class DumpTruck extends KendaraanTambang {

    private double kapasitasTon;

    public DumpTruck(String id, String namaKendaraan, double hargaSewaPerHari, double kapasitasTon) {
        super(id, namaKendaraan, hargaSewaPerHari);
        setKapasitasTon(kapasitasTon);
    }

    public double getKapasitasTon() {
        return kapasitasTon;
    }

    public void setKapasitasTon(double kapasitasTon) {
        if (kapasitasTon <= 0) {
            throw new IllegalArgumentException("Kapasitas (ton) harus lebih dari 0.");
        }
        this.kapasitasTon = kapasitasTon;
    }

    @Override
    public String getJenis() {
        return "Dump Truck";
    }

    @Override
    protected String getDetail() {
        return String.format("Kapasitas %.1f ton", kapasitasTon);
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        validasiHari(hari);
        double total = getHargaSewaPerHari() * hari;
        if (hari >= 7) {
            total = total * 0.90;
        }
        return total;
    }
}

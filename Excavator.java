package model;

public class Excavator extends KendaraanTambang {

    private static final double BIAYA_OPERATOR_PER_HARI = 500000;

    private double kapasitasBucket;

    public Excavator(String id, String namaKendaraan, double hargaSewaPerHari, double kapasitasBucket) {
        super(id, namaKendaraan, hargaSewaPerHari);
        setKapasitasBucket(kapasitasBucket);
    }

    public double getKapasitasBucket() {
        return kapasitasBucket;
    }

    public void setKapasitasBucket(double kapasitasBucket) {
        if (kapasitasBucket <= 0) {
            throw new IllegalArgumentException("Kapasitas bucket harus lebih dari 0.");
        }
        this.kapasitasBucket = kapasitasBucket;
    }

    @Override
    public String getJenis() {
        return "Excavator";
    }

    @Override
    protected String getDetail() {
        return String.format("Bucket %.1f m3", kapasitasBucket);
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        validasiHari(hari);
        return (getHargaSewaPerHari() + BIAYA_OPERATOR_PER_HARI) * hari;
    }
}

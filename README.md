# Minpro-4-PBO-pengelola-penyewaaan-kendaraan-tambang
Alur: View → Controller → PengelolaKendaraan → DAO → MySQL. Data sekarang disimpan di database, bukan ArrayList, jadi tidak hilang saat program ditutup

Yang tidak berubah
Main, KendaraanView, KendaraanTambang, DumpTruck, dan Excavator tidak diubah. Itu bukti pemisahan MVC dan DAO berjalan baik: mengganti penyimpanan dari ArrayList ke database hampir tidak menyentuh bagian lain program.

Sebelumnya PengelolaKendaraan menyimpan data di ArrayList, jadi data hilang setiap program ditutup. Sekarang PengelolaKendaraan tidak menyimpan data sendiri. Semua operasi data diserahkan ke DAO, dan DAO dengan database lewat JDBC

File baru:
database.sql: membuat tabel kendaraan dan mengisi 3 data awal. Data awal ada di SQL, bukan di kode Java.
KoneksiDB: membuat koneksi ke MySQL lewat JDBC.
KendaraanDAO (interface): daftar operasi data (insert, getAll, getById, update, delete, cari harga).
KendaraanDAOImpl: isi SQL-nya, memakai PreparedStatement (tanda ? diisi lewat setString/setDouble) supaya aman dari SQL Injection. Method mapRow() mengubah baris tabel menjadi objek DumpTruck atau Excavator.

Perubahan di KendaraanController:
cariIndexById(id) != -1 diganti cari(id) != null.
switch dibungkus try-catch, supaya error database menampilkan pesan dan tidak membuat program crash.

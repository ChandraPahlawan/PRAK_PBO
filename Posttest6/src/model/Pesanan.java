package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Pesanan implements Pembayaran {
    protected int id;
    protected String namaPelanggan;
    protected String jenisSepatu;
    protected int jumlahSepatu;
    protected JenisPencucian jenisPencucian;
    protected final double DISKON_MAKS = 80.0; // Final attribute
    protected double hargaDasar = 25000.0; // harga dasar untuk semua jenis pesanan
    
    // Static counter untuk menghitung total pesanan
    private static int totalPesanan = 0;
    // Static formatter untuk tanggal
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Constructor yang sudah ada
    public Pesanan(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, JenisPencucian jenisPencucian) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.jenisSepatu = jenisSepatu;
        this.jumlahSepatu = jumlahSepatu;
        this.jenisPencucian = jenisPencucian;
        totalPesanan++; // Increment counter setiap kali objek dibuat
    }
    
    // Constructor overloading - versi dengan harga dasar custom
    public Pesanan(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                  JenisPencucian jenisPencucian, double hargaDasar) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.jenisSepatu = jenisSepatu;
        this.jumlahSepatu = jumlahSepatu;
        this.jenisPencucian = jenisPencucian;
        this.hargaDasar = hargaDasar;
        totalPesanan++; // Increment counter setiap kali objek dibuat
    }

    public int getId() {
        return id;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getJenisSepatu() {
        return jenisSepatu;
    }

    public void setJenisSepatu(String jenisSepatu) {
        this.jenisSepatu = jenisSepatu;
    }

    public int getJumlahSepatu() {
        return jumlahSepatu;
    }

    public void setJumlahSepatu(int jumlahSepatu) {
        if (jumlahSepatu > 0) {
            this.jumlahSepatu = jumlahSepatu;
        } else {
            throw new IllegalArgumentException("Jumlah sepatu harus lebih dari 0.");
        }
    }

    public JenisPencucian getJenisPencucian() {
        return jenisPencucian;
    }

    public void setJenisPencucian(JenisPencucian jenisPencucian) {
        this.jenisPencucian = jenisPencucian;
    }
    
    // Static method untuk mendapatkan total pesanan
    public static int getTotalPesanan() {
        return totalPesanan;
    }
    
    // Static method untuk reset counter (untuk keperluan testing)
    public static void resetTotalPesanan() {
        totalPesanan = 0;
    }
    
    // Static method untuk memformat tanggal
    public static String formatTanggal(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }
    
    // Method untuk menghitung harga
    public double hitungHarga() {
        double harga = hargaDasar * jumlahSepatu;
        
        // Harga tambahan untuk pencucian express
        if (jenisPencucian == JenisPencucian.EXPRESS) {
            harga *= 1.5; // Tambahan 50% untuk express
        }
        
        return harga;
    }
    
    // Method overloading untuk hitungHarga dengan parameter diskon
    public final double hitungHarga(double diskonPersen) { // Final method
        if (diskonPersen > DISKON_MAKS) {
            diskonPersen = DISKON_MAKS; // Batasi diskon maksimum
        }
        
        double hargaAwal = hitungHarga();
        double diskon = hargaAwal * (diskonPersen / 100.0);
        return hargaAwal - diskon;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Nama: " + namaPelanggan + ", Sepatu: " + jenisSepatu + 
                          ", Jumlah: " + jumlahSepatu + ", Pencucian: " + jenisPencucian + 
                          ", Harga: Rp" + hitungHarga());
    }
    
    // Method overloading untuk displayInfo
    public void displayInfo(boolean showPrice) {
        System.out.println("ID: " + id + ", Nama: " + namaPelanggan + ", Sepatu: " + jenisSepatu + 
                          ", Jumlah: " + jumlahSepatu + ", Pencucian: " + jenisPencucian);
        if (showPrice) {
            System.out.println("Harga: Rp" + hitungHarga());
        }
    }
    
    // Abstract method that must be implemented by subclasses
    public abstract String getServiceDetails();
    
    // Implementasi dari interface Pembayaran
    @Override
    public double hitungHargaAkhir(double pajak) {
        if (pajak < 0) {
            throw new IllegalArgumentException("Pajak tidak boleh negatif");
        }
        double harga = hitungHarga();
        return harga + (harga * pajak / 100);
    }
    
    @Override
    public void catatTransaksi(String metodePembayaran) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = formatTanggal(now);
        System.out.println("====== TRANSAKSI DICATAT ======");
        System.out.println("Tanggal: " + timestamp);
        System.out.println("ID Pesanan: " + id);
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Metode Pembayaran: " + metodePembayaran);
        System.out.println("Total Pembayaran: Rp" + hitungHarga());
        System.out.println("==============================");
    }
}
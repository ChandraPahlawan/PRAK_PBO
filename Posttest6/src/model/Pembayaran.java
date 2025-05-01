package model;

public interface Pembayaran {
    // Method untuk menghitung harga akhir setelah pajak
    double hitungHargaAkhir(double pajak);
    
    // Method untuk mencatat transaksi pembayaran
    void catatTransaksi(String metodePembayaran);
}
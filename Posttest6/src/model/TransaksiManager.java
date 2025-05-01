package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransaksiManager {
    // Static variables
    private static List<String> logTransaksi = new ArrayList<>();
    private static final String LOG_FILE = "transaksi_log.txt";
    
    // Static method untuk mencatat transaksi
    public static void catatTransaksi(Pesanan pesanan, String metodePembayaran, double pajak) {
        try {
            if (metodePembayaran == null || metodePembayaran.trim().isEmpty()) {
                throw new IllegalArgumentException("Metode pembayaran tidak boleh kosong");
            }
            
            // Hitung total pembayaran dengan pajak
            double totalPembayaran = pesanan.hitungHargaAkhir(pajak);
            
            // Format log entry
            String logEntry = String.format(
                "ID: %d, Pelanggan: %s, Total: Rp%.2f, Metode: %s, Waktu: %s",
                pesanan.getId(),
                pesanan.getNamaPelanggan(),
                totalPembayaran,
                metodePembayaran,
                Pesanan.formatTanggal(LocalDateTime.now())
            );
            
            // Tambahkan ke list log
            logTransaksi.add(logEntry);
            
            // Catat transaksi menggunakan method dari interface
            pesanan.catatTransaksi(metodePembayaran);
            
            // Simpan ke file
            simpanKeFile(logEntry);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error mencatat transaksi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mencatat transaksi: " + e.getMessage());
        }
    }
    
    // Static method untuk menyimpan transaksi ke file
    private static void simpanKeFile(String logEntry) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(logEntry);
        } catch (IOException e) {
            System.out.println("Error menyimpan log transaksi ke file: " + e.getMessage());
        }
    }
    
    // Static method untuk mendapatkan semua log transaksi
    public static List<String> getLogTransaksi() {
        return new ArrayList<>(logTransaksi); // Return copy untuk keamanan
    }
    
    // Static method untuk menghapus semua log (untuk testing)
    public static void resetLog() {
        logTransaksi.clear();
    }
}
import model.JenisPencucian;
import model.Pesanan;
import model.PesananBoots;
import model.PesananSandal;
import model.PesananSneakers;
import model.TransaksiManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class ShoesCare { // Final class
    private static ArrayList<Pesanan> pesananList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;
    private static final double PAJAK_STANDARD = 11.0; // PPN standard

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n===== Shoes Care Management =====");
                System.out.println("1. Tambah Pesanan");
                System.out.println("2. Lihat Semua Pesanan");
                System.out.println("3. Ubah Pesanan");
                System.out.println("4. Hapus Pesanan");
                System.out.println("5. Terapkan Diskon");
                System.out.println("6. Lihat Detail Layanan");
                System.out.println("7. Proses Pembayaran");
                System.out.println("8. Lihat Riwayat Transaksi");
                System.out.println("9. Lihat Statistik");
                System.out.println("10. Keluar");
                System.out.print("Pilih menu: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        tambahPesanan();
                        break;
                    case 2:
                        lihatPesanan();
                        break;
                    case 3:
                        ubahPesanan();
                        break;
                    case 4:
                        hapusPesanan();
                        break;
                    case 5:
                        terapkanDiskon();
                        break;
                    case 6:
                        lihatDetailLayanan();
                        break;
                    case 7:
                        prosesPembayaran();
                        break;
                    case 8:
                        lihatRiwayatTransaksi();
                        break;
                    case 9:
                        lihatStatistik();
                        break;
                    case 10:
                        System.out.println("Terima kasih telah menggunakan layanan Kami!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka. Silakan coba lagi.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error tidak terduga: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void tambahPesanan() {
        try {
            System.out.print("Nama Pelanggan: ");
            String nama = scanner.nextLine();
            
            if (nama.trim().isEmpty()) {
                throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong");
            }
            
            System.out.println("Pilih jenis produk:");
            System.out.println("1. Sneakers");
            System.out.println("2. Boots");
            System.out.println("3. Sandal");
            System.out.print("Pilihan: ");
            
            int jenisProduk = scanner.nextInt();
            scanner.nextLine();
            
            if (jenisProduk < 1 || jenisProduk > 3) {
                throw new IllegalArgumentException("Pilihan tidak valid. Silakan pilih antara 1-3.");
            }
            
            System.out.print("Jenis Sepatu: ");
            String jenisSepatu = scanner.nextLine();
            
            if (jenisSepatu.trim().isEmpty()) {
                throw new IllegalArgumentException("Jenis sepatu tidak boleh kosong");
            }
            
            int jumlahSepatu = 0;
            System.out.print("Jumlah Sepatu: ");
            jumlahSepatu = scanner.nextInt();
            scanner.nextLine();
            
            if (jumlahSepatu <= 0) {
                throw new IllegalArgumentException("Jumlah sepatu harus lebih dari 0.");
            }

            System.out.print("Pilih jenis pencucian (Reguler/Express): ");
            String jenisPencucianStr = scanner.nextLine().toUpperCase();
            JenisPencucian jenisPencucian = JenisPencucian.fromString(jenisPencucianStr);
            
            System.out.print("Gunakan harga dasar custom? (y/n): ");
            boolean customHarga = scanner.nextLine().equalsIgnoreCase("y");
            double hargaDasar = 0;
            
            if (customHarga) {
                System.out.print("Masukkan harga dasar: ");
                hargaDasar = scanner.nextDouble();
                scanner.nextLine();
                
                if (hargaDasar <= 0) {
                    throw new IllegalArgumentException("Harga dasar harus lebih dari 0.");
                }
            }

            Pesanan pesanan;
            
            switch (jenisProduk) {
                case 1: // Sneakers
                    System.out.print("Apakah perlu deep cleaning? (y/n): ");
                    boolean deepCleaning = scanner.nextLine().equalsIgnoreCase("y");
                    if (customHarga) {
                        pesanan = new PesananSneakers(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, deepCleaning, hargaDasar);
                    } else {
                        pesanan = new PesananSneakers(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, deepCleaning);
                    }
                    break;
                    
                case 2: // Boots
                    System.out.print("Apakah perlu perawatan kulit? (y/n): ");
                    boolean perawatanKulit = scanner.nextLine().equalsIgnoreCase("y");
                    if (customHarga) {
                        pesanan = new PesananBoots(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, perawatanKulit, hargaDasar);
                    } else {
                        pesanan = new PesananBoots(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, perawatanKulit);
                    }
                    break;
                    
                case 3: // Sandal
                    System.out.print("Apakah perlu perbaikan sol? (y/n): ");
                    boolean repairSole = scanner.nextLine().equalsIgnoreCase("y");
                    if (customHarga) {
                        pesanan = new PesananSandal(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, repairSole, hargaDasar);
                    } else {
                        pesanan = new PesananSandal(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian, repairSole);
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Jenis produk tidak valid");
            }
            
            pesananList.add(pesanan);
            System.out.println("Pesanan berhasil ditambahkan!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void lihatPesanan() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia.");
            return;
        }
        
        System.out.println("\n===== Daftar Pesanan =====");
        for (Pesanan pesanan : pesananList) {
            pesanan.displayInfo();
            System.out.println("-------------------------");
        }
    }

    private static void ubahPesanan() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia untuk diubah.");
            return;
        }
        
        try {
            System.out.println("\n===== Ubah Pesanan =====");
            for (int i = 0; i < pesananList.size(); i++) {
                System.out.print((i + 1) + ". ");
                pesananList.get(i).displayInfo(false);
            }
            
            System.out.print("Pilih nomor pesanan yang ingin diubah: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            
            if (index < 1 || index > pesananList.size()) {
                System.out.println("Nomor pesanan tidak valid.");
                return;
            }
            
            Pesanan pesanan = pesananList.get(index - 1);
            
            System.out.println("\nData Pesanan Saat Ini:");
            pesanan.displayInfo();
            
            System.out.println("\nPilih data yang ingin diubah:");
            System.out.println("1. Nama Pelanggan");
            System.out.println("2. Jenis Sepatu");
            System.out.println("3. Jumlah Sepatu");
            System.out.println("4. Jenis Pencucian");
            System.out.println("5. Opsi Tambahan");
            System.out.print("Pilihan: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Nama Pelanggan Baru: ");
                    String newName = scanner.nextLine();
                    if (newName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong");
                    }
                    pesanan.setNamaPelanggan(newName);
                    break;
                case 2:
                    System.out.print("Jenis Sepatu Baru: ");
                    String newType = scanner.nextLine();
                    if (newType.trim().isEmpty()) {
                        throw new IllegalArgumentException("Jenis sepatu tidak boleh kosong");
                    }
                    pesanan.setJenisSepatu(newType);
                    break;
                case 3:
                    System.out.print("Jumlah Sepatu Baru: ");
                    int newCount = scanner.nextInt();
                    scanner.nextLine();
                    pesanan.setJumlahSepatu(newCount);
                    break;
                case 4:
                    System.out.print("Jenis Pencucian Baru (Reguler/Express): ");
                    String newWashing = scanner.nextLine();
                    pesanan.setJenisPencucian(JenisPencucian.fromString(newWashing));
                    break;
                case 5:
                    if (pesanan instanceof PesananSneakers) {
                        System.out.print("Apakah perlu deep cleaning? (y/n): ");
                        boolean deepCleaning = scanner.nextLine().equalsIgnoreCase("y");
                        ((PesananSneakers) pesanan).setDeepCleaning(deepCleaning);
                    } else if (pesanan instanceof PesananBoots) {
                        System.out.print("Apakah perlu perawatan kulit? (y/n): ");
                        boolean perawatanKulit = scanner.nextLine().equalsIgnoreCase("y");
                        ((PesananBoots) pesanan).setPerawatanKulit(perawatanKulit);
                    } else if (pesanan instanceof PesananSandal) {
                        System.out.print("Apakah perlu perbaikan sol? (y/n): ");
                        boolean repairSole = scanner.nextLine().equalsIgnoreCase("y");
                        ((PesananSandal) pesanan).setRepairSole(repairSole);
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    return;
            }
            
            System.out.println("Pesanan berhasil diubah!");
            System.out.println("Data Pesanan Setelah Diubah:");
            pesanan.displayInfo();
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void hapusPesanan() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia untuk dihapus.");
            return;
        }
        
        try {
            System.out.println("\n===== Hapus Pesanan =====");
            for (int i = 0; i < pesananList.size(); i++) {
                System.out.print((i + 1) + ". ");
                pesananList.get(i).displayInfo(false);
            }
            
            System.out.print("Pilih nomor pesanan yang ingin dihapus: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            
            if (index < 1 || index > pesananList.size()) {
                System.out.println("Nomor pesanan tidak valid.");
                return;
            }
            
            Pesanan removedPesanan = pesananList.remove(index - 1);
            System.out.println("Pesanan berikut telah dihapus:");
            removedPesanan.displayInfo();
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void terapkanDiskon() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia untuk diterapkan diskon.");
            return;
        }
        
        try {
            System.out.println("\n===== Terapkan Diskon =====");
            for (int i = 0; i < pesananList.size(); i++) {
                System.out.print((i + 1) + ". ");
                pesananList.get(i).displayInfo();
            }
            
            System.out.print("Pilih nomor pesanan untuk diterapkan diskon: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            
            if (index < 1 || index > pesananList.size()) {
                System.out.println("Nomor pesanan tidak valid.");
                return;
            }
            
            Pesanan pesanan = pesananList.get(index - 1);
            
            System.out.print("Masukkan persentase diskon (0-80): ");
            double diskon = scanner.nextDouble();
            scanner.nextLine();
            
            if (diskon < 0) {
                System.out.println("Diskon tidak boleh negatif.");
                return;
            }
            
            if (diskon > 80) {
                System.out.println("Diskon maksimal adalah 80%. Diskon disesuaikan menjadi 80%.");
                diskon = 80;
            }
            
            double hargaSebelumDiskon = pesanan.hitungHarga();
            double hargaSetelahDiskon = pesanan.hitungHarga(diskon);
            double jumlahDiskon = hargaSebelumDiskon - hargaSetelahDiskon;
            
            System.out.println("\n===== Hasil Penerapan Diskon =====");
            System.out.println("Harga Sebelum Diskon: Rp" + hargaSebelumDiskon);
            System.out.println("Diskon: " + diskon + "% (Rp" + jumlahDiskon + ")");
            System.out.println("Harga Setelah Diskon: Rp" + hargaSetelahDiskon);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void lihatDetailLayanan() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia.");
            return;
        }
        
        System.out.println("\n===== Detail Layanan =====");
        for (Pesanan pesanan : pesananList) {
            System.out.println("ID: " + pesanan.getId());
            System.out.println("Pelanggan: " + pesanan.getNamaPelanggan());
            System.out.println("Detail: " + pesanan.getServiceDetails());
            System.out.println("Jenis Pencucian: " + pesanan.getJenisPencucian());
            System.out.println("-------------------------");
        }
    }

    private static void prosesPembayaran() {
        if (pesananList.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia untuk diproses pembayaran.");
            return;
        }
        
        try {
            System.out.println("\n===== Proses Pembayaran =====");
            for (int i = 0; i < pesananList.size(); i++) {
                System.out.print((i + 1) + ". ");
                pesananList.get(i).displayInfo();
            }
            
            System.out.print("Pilih nomor pesanan untuk diproses pembayaran: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            
            if (index < 1 || index > pesananList.size()) {
                System.out.println("Nomor pesanan tidak valid.");
                return;
            }
            
            Pesanan pesanan = pesananList.get(index - 1);
            
            System.out.println("\nDetail Pesanan:");
            pesanan.displayInfo();
            
            double hargaDasar = pesanan.hitungHarga();
            double hargaAkhir = pesanan.hitungHargaAkhir(PAJAK_STANDARD);
            double jumlahPajak = hargaAkhir - hargaDasar;
            
            System.out.println("\n===== Detail Pembayaran =====");
            System.out.println("Harga Dasar: Rp" + hargaDasar);
            System.out.println("Pajak (" + PAJAK_STANDARD + "%): Rp" + jumlahPajak);
            System.out.println("Total Pembayaran: Rp" + hargaAkhir);
            
            System.out.println("\nPilih metode pembayaran:");
            System.out.println("1. Tunai");
            System.out.println("2. Kartu Debit");
            System.out.println("3. Kartu Kredit");
            System.out.println("4. E-Wallet");
            System.out.print("Pilihan: ");
            
            int metodePembayaranChoice = scanner.nextInt();
            scanner.nextLine();
            
            String metodePembayaran;
            switch (metodePembayaranChoice) {
                case 1:
                    metodePembayaran = "Tunai";
                    break;
                case 2:
                    metodePembayaran = "Kartu Debit";
                    break;
                case 3:
                    metodePembayaran = "Kartu Kredit";
                    break;
                case 4:
                    metodePembayaran = "E-Wallet";
                    break;
                default:
                    metodePembayaran = "Tunai";
                    System.out.println("Pilihan tidak valid, menggunakan metode default: Tunai");
            }
            
            // Catat transaksi menggunakan TransaksiManager
            TransaksiManager.catatTransaksi(pesanan, metodePembayaran, PAJAK_STANDARD);
            
            // Hapus pesanan yang sudah dibayar
            System.out.print("\nApakah ingin menghapus pesanan dari daftar? (y/n): ");
            String hapusPesanan = scanner.nextLine();
            if (hapusPesanan.equalsIgnoreCase("y")) {
                pesananList.remove(index - 1);
                System.out.println("Pesanan berhasil dihapus dari daftar.");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void lihatRiwayatTransaksi() {
        List<String> logTransaksi = TransaksiManager.getLogTransaksi();
        
        if (logTransaksi.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        
        System.out.println("\n===== Riwayat Transaksi =====");
        for (String log : logTransaksi) {
            System.out.println(log);
            System.out.println("-------------------------");
        }
    }

    private static void lihatStatistik() {
        if (pesananList.isEmpty() && TransaksiManager.getLogTransaksi().isEmpty()) {
            System.out.println("Tidak ada data statistik yang tersedia.");
            return;
        }
        
        System.out.println("\n===== Statistik =====");
        System.out.println("Total Pesanan: " + Pesanan.getTotalPesanan());
        
        int countSneakers = 0;
        int countBoots = 0;
        int countSandal = 0;
        int countRegular = 0;
        int countExpress = 0;
        double totalHarga = 0;
        
        for (Pesanan pesanan : pesananList) {
            if (pesanan instanceof PesananSneakers) {
                countSneakers++;
            } else if (pesanan instanceof PesananBoots) {
                countBoots++;
            } else if (pesanan instanceof PesananSandal) {
                countSandal++;
            }
            
            if (pesanan.getJenisPencucian() == JenisPencucian.REGULER) {
                countRegular++;
            } else {
                countExpress++;
            }
            
            totalHarga += pesanan.hitungHarga();
        }
        
        System.out.println("\nDistribusi Jenis Produk:");
        System.out.println("- Sneakers: " + countSneakers);
        System.out.println("- Boots: " + countBoots);
        System.out.println("- Sandal: " + countSandal);
        
        System.out.println("\nDistribusi Jenis Pencucian:");
        System.out.println("- Reguler: " + countRegular);
        System.out.println("- Express: " + countExpress);
        
        System.out.println("\nTotal Nilai Pesanan: Rp" + totalHarga);
        System.out.println("Jumlah Transaksi: " + TransaksiManager.getLogTransaksi().size());
    }
}
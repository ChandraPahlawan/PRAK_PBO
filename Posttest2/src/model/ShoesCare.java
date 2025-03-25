package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShoesCare {
    private static ArrayList<Pesanan> pesananList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Shoes Care Management =====");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Semua Pesanan");
            System.out.println("3. Ubah Pesanan");
            System.out.println("4. Hapus Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
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
                        System.out.println("Terima kasih telah menggunakan layanan Kami!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    }

    private static void tambahPesanan() {
        System.out.print("Nama Pelanggan: ");
        String nama = scanner.nextLine();
        
        System.out.print("Jenis Sepatu: ");
        String jenisSepatu = scanner.nextLine();
        
        int jumlahSepatu = 0;
        while (true) {
            try {
                System.out.print("Jumlah Sepatu: ");
                jumlahSepatu = scanner.nextInt();
                scanner.nextLine();
                if (jumlahSepatu > 0) break;
                System.out.println("Jumlah sepatu harus lebih dari 0.");
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid, masukkan angka.");
                scanner.nextLine();
            }
        }

        JenisPencucian jenisPencucian = null;
        while (jenisPencucian == null) {
            System.out.print("Pilih jenis pencucian (Reguler/Express): ");
            String input = scanner.nextLine().toUpperCase();
            try {
                jenisPencucian = JenisPencucian.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Pilihan tidak valid! Pilih antara Reguler atau Express.");
            }
        }

        Pesanan pesanan = new Pesanan(idCounter++, nama, jenisSepatu, jumlahSepatu, jenisPencucian);
        pesananList.add(pesanan);
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    private static void lihatPesanan() {
        if (pesananList.isEmpty()) {
            System.out.println("Belum ada pesanan.");
        } else {
            for (Pesanan p : pesananList) {
                p.displayInfo();
            }
        }
    }

    private static void ubahPesanan() {
        System.out.print("Masukkan ID Pesanan yang ingin diubah: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            Pesanan pesananDitemukan = null;

            for (Pesanan p : pesananList) {
                if (p.getId() == id) {
                    pesananDitemukan = p;
                    break;
                }
            }

            if (pesananDitemukan == null) {
                System.out.println("Pesanan tidak ditemukan.");
                return;
            }

            System.out.print("Nama Pelanggan baru: ");
            pesananDitemukan.setNamaPelanggan(scanner.nextLine());

            System.out.print("Jenis Sepatu baru: ");
            pesananDitemukan.setJenisSepatu(scanner.nextLine());

            while (true) {
                try {
                    System.out.print("Jumlah Sepatu baru: ");
                    pesananDitemukan.setJumlahSepatu(scanner.nextInt());
                    scanner.nextLine();
                    if (pesananDitemukan.getJumlahSepatu() > 0) break;
                    System.out.println("Jumlah sepatu harus lebih dari 0.");
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid, masukkan angka.");
                    scanner.nextLine();
                }
            }

            JenisPencucian jenisPencucian = null;
            while (jenisPencucian == null) {
                System.out.print("Pilih jenis pencucian baru (Reguler/Express): ");
                String input = scanner.nextLine().toUpperCase();
                try {
                    jenisPencucian = JenisPencucian.valueOf(input);
                    pesananDitemukan.setJenisPencucian(jenisPencucian);
                } catch (IllegalArgumentException e) {
                    System.out.println("Pilihan tidak valid! Pilih antara Reguler atau Express.");
                }
            }

            System.out.println("Pesanan berhasil diubah!");

        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka untuk ID.");
            scanner.nextLine();
        }
    }

    private static void hapusPesanan() {
        System.out.print("Masukkan ID Pesanan yang ingin dihapus: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            pesananList.removeIf(p -> p.getId() == id);
            System.out.println("Pesanan berhasil dihapus!");

        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka untuk ID.");
            scanner.nextLine();
        }
    }
}

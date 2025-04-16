package model;

public class Pesanan {
    protected int id;
    protected String namaPelanggan;
    protected String jenisSepatu;
    protected int jumlahSepatu;
    protected JenisPencucian jenisPencucian;
    protected double hargaDasar = 25000.0; // harga dasar untuk semua jenis pesanan

    // Constructor yang sudah ada
    public Pesanan(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, JenisPencucian jenisPencucian) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.jenisSepatu = jenisSepatu;
        this.jumlahSepatu = jumlahSepatu;
        this.jenisPencucian = jenisPencucian;
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
    public double hitungHarga(double diskonPersen) {
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
}
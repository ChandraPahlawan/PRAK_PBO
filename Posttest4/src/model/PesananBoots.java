package model;

public class PesananBoots extends Pesanan {
    private boolean perawatanKulit;
    
    public PesananBoots(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                       JenisPencucian jenisPencucian, boolean perawatanKulit) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.perawatanKulit = perawatanKulit;
        this.hargaDasar = 35000.0; 
    }
    
    // overloading dengan harga dasar kustom
    public PesananBoots(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                       JenisPencucian jenisPencucian, boolean perawatanKulit, double hargaDasar) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian, hargaDasar);
        this.perawatanKulit = perawatanKulit;
    }
    
    public boolean isPerawatanKulit() {
        return perawatanKulit;
    }
    
    public void setPerawatanKulit(boolean perawatanKulit) {
        this.perawatanKulit = perawatanKulit;
    }
    
    @Override
    public double hitungHarga() {
        double harga = super.hitungHarga(); // Panggil metode parent
        
        // Tambahan biaya untuk perawatan kulit
        if (perawatanKulit) {
            harga += 20000.0 * jumlahSepatu;
        }
        
        return harga;
    }
    
    // Override method overloading dari parent
    @Override
    public double hitungHarga(double diskonPersen) {
        // Mendapatkan harga setelah penambahan perawatan kulit
        double hargaAwal = hitungHarga();
        double diskon = hargaAwal * (diskonPersen / 100.0);
        return hargaAwal - diskon;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Perawatan Kulit: " + (perawatanKulit ? "Ya" : "Tidak"));
    }
    
    @Override
    public void displayInfo(boolean showPrice) {
        super.displayInfo(showPrice);
        System.out.println("Perawatan Kulit: " + (perawatanKulit ? "Ya" : "Tidak"));
    }
}
package model;

public class PesananSneakers extends Pesanan {
    private boolean deepCleaning;
    
    public PesananSneakers(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                         JenisPencucian jenisPencucian, boolean deepCleaning) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.deepCleaning = deepCleaning;
        this.hargaDasar = 30000.0; // Sneakers lebih mahal dari sepatu biasa
    }
    
    public boolean isDeepCleaning() {
        return deepCleaning;
    }
    
    public void setDeepCleaning(boolean deepCleaning) {
        this.deepCleaning = deepCleaning;
    }
    
    @Override
    public double hitungHarga() {
        double harga = super.hitungHarga(); // Panggil metode parent
        
        // Tambahan biaya untuk deep cleaning
        if (deepCleaning) {
            harga += 15000.0 * jumlahSepatu;
        }
        
        return harga;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Deep Cleaning: " + (deepCleaning ? "Ya" : "Tidak"));
    }
}
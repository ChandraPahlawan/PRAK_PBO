package model;

public class PesananSneakers extends Pesanan {
    private boolean deepCleaning;
    
    public PesananSneakers(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                         JenisPencucian jenisPencucian, boolean deepCleaning) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.deepCleaning = deepCleaning;
        this.hargaDasar = 30000.0; 
    }
    
    // Constructor overloading dengan harga dasar kustom
    public PesananSneakers(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                         JenisPencucian jenisPencucian, boolean deepCleaning, double hargaDasar) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian, hargaDasar);
        this.deepCleaning = deepCleaning;
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
    
    // Tidak dapat mengganti hitungHarga(double diskonPersen) karena sudah final di kelas induk
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Deep Cleaning: " + (deepCleaning ? "Ya" : "Tidak"));
    }
    
    @Override
    public void displayInfo(boolean showPrice) {
        super.displayInfo(showPrice);
        System.out.println("Deep Cleaning: " + (deepCleaning ? "Ya" : "Tidak"));
    }
    
    @Override
    public String getServiceDetails() {
        return "Sneakers - " + (deepCleaning ? "With Deep Cleaning" : "Standard Cleaning");
    }
    
    // Override metode dari interface untuk kasus khusus sneakers
    @Override
    public double hitungHargaAkhir(double pajak) {
        try {
            double harga = super.hitungHargaAkhir(pajak);
            // Sneakers memiliki tambahan pajak 1% untuk deep cleaning
            if (deepCleaning) {
                harga += (harga * 0.01);
            }
            return harga;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return hitungHarga(); // Return harga tanpa pajak jika ada error
        }
    }
}
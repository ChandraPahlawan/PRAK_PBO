package model;

public class PesananBoots extends Pesanan {
    private boolean perawatanKulit;
    
    public PesananBoots(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                       JenisPencucian jenisPencucian, boolean perawatanKulit) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.perawatanKulit = perawatanKulit;
        this.hargaDasar = 35000.0; // Boots lebih mahal karena lebih sulit dibersihkan
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
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Perawatan Kulit: " + (perawatanKulit ? "Ya" : "Tidak"));
    }
}
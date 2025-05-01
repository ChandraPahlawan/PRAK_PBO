package model;

public class PesananSandal extends Pesanan {
    private boolean repairSole;
    
    public PesananSandal(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                        JenisPencucian jenisPencucian, boolean repairSole) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.repairSole = repairSole;
        this.hargaDasar = 20000.0; 
    }
    
    // overloading dengan harga dasar kustom
    public PesananSandal(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                        JenisPencucian jenisPencucian, boolean repairSole, double hargaDasar) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian, hargaDasar);
        this.repairSole = repairSole;
    }
    
    public boolean isRepairSole() {
        return repairSole;
    }
    
    public void setRepairSole(boolean repairSole) {
        this.repairSole = repairSole;
    }
    
    @Override
    public double hitungHarga() {
        double harga = super.hitungHarga(); // Panggil metode parent
        
        // Tambahan biaya perbaikan sol
        if (repairSole) {
            harga += 10000.0 * jumlahSepatu;
        }
        
        return harga;
    }
    
    // Tidak dapat mengganti hitungHarga(double diskonPersen) karena sudah final di kelas induk
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Perbaikan Sol: " + (repairSole ? "Ya" : "Tidak"));
    }
    
    @Override
    public void displayInfo(boolean showPrice) {
        super.displayInfo(showPrice);
        System.out.println("Perbaikan Sol: " + (repairSole ? "Ya" : "Tidak"));
    }
    
    @Override
    public String getServiceDetails() {
        return "Sandal - " + (repairSole ? "With Sole Repair" : "Standard Cleaning");
    }
    
    // Override metode dari interface untuk kasus khusus sandal
    @Override
    public double hitungHargaAkhir(double pajak) {
        try {
            double harga = super.hitungHargaAkhir(pajak);
            // Sandal memiliki pajak yang lebih rendah jika ada perbaikan sol
            if (repairSole) {
                harga -= (harga * 0.015);
            }
            return harga;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return hitungHarga(); // Return harga tanpa pajak jika ada error
        }
    }
}
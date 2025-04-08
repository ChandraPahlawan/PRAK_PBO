package model;

public class PesananSandal extends Pesanan {
    private boolean repairSole;
    
    public PesananSandal(int id, String namaPelanggan, String jenisSepatu, int jumlahSepatu, 
                        JenisPencucian jenisPencucian, boolean repairSole) {
        super(id, namaPelanggan, jenisSepatu, jumlahSepatu, jenisPencucian);
        this.repairSole = repairSole;
        this.hargaDasar = 20000.0; // Sandal lebih murah dibandingkan sepatu
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
        
        // Tambahan biaya untuk perbaikan sol
        if (repairSole) {
            harga += 10000.0 * jumlahSepatu;
        }
        
        return harga;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Perbaikan Sol: " + (repairSole ? "Ya" : "Tidak"));
    }
}
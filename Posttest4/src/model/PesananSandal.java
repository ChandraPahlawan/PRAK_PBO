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
    
    // Override method overloading dari parent
    @Override
    public double hitungHarga(double diskonPersen) {
        // Mendapatkan harga setelah penambahan perbaikan sol
        double hargaAwal = hitungHarga();
        double diskon = hargaAwal * (diskonPersen / 100.0);
        return hargaAwal - diskon;
    }
    
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
}
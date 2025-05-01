package model;

public enum JenisPencucian {
    REGULER(1.0), 
    EXPRESS(1.5);
    
    private final double multiplier;
    
    // Constructor enum
    JenisPencucian(double multiplier) {
        this.multiplier = multiplier;
    }
    
    // Method untuk mendapatkan multiplier
    public double getMultiplier() {
        return this.multiplier;
    }
    
    // Static method untuk mendapatkan JenisPencucian dari string
    public static JenisPencucian fromString(String text) {
        try {
            return valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Jenis pencucian tidak valid: " + text + 
                                              ". Pilih antara Reguler atau Express.");
        }
    }
}
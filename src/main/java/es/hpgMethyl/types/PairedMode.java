package es.hpgMethyl.types;

public enum PairedMode {
	
	SINGLE_END_MODE (0), PAIRED_END_MODE (1);
	
	private final Integer value;
	
	private PairedMode(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	@Override
    public String toString() { 
        return Integer.toString(value);
    } 

}

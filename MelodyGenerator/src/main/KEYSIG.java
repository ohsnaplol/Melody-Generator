package main;

public enum KEYSIG {
	//C Major
	CMAJOR("A B C D E F G"),
	//a natural minor
	ANATMIN("a b c d e f g"),
	//a harmonic minor
	AHARMIN("a b c d e f g#"),
	//a melodic minor
	AMELMIN("a b c d e f# g#"),
	//G Major
	
	
	GSMELMIN("g# a# b c# d# e f##"),
	;
	
	private String values[];
	
	private KEYSIG(String str) {
		values = str.split(" ");
	}
	
	public String getValue(int i) {
		return values[i];
	}
}

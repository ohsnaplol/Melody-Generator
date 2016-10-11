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
	//a harmonic minor
	//AHARMIN("G A B C D E F#"),
	
	
	GSMELMIN("g# a# b c# d# e f##"), //cool and interesting
	;
	
	private String values[];
	
	private KEYSIG(String str) {
		values = str.split(" ");
	}
	
	public String getValue(int i) {
		return values[i];
	}
}

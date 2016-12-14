package main;

public enum KEYSIG {
	//C Major 
	CMAJOR("C D E F G A B"),
	//a natural minor
	anatmin("a b c d e f g"),
	//a harmonic minor
	aharmin("a b c d e f g#"),
	//a melodic minor
	amelmin("a b c d e f# g#"),
	//G Major
	GMAJOR("G A B C D E F#"),
	//e natural minor
	enatmin("e f# g a b c d"),
	//e harmonic minor
	eharmin("e f# g a b c d#"),
	//e melodic minor
	emelmin("e f# g a b c# d#"),
	//D Major
	DMAJOR("D E F# G A B C#"),
	//b natural minor
	bnatmin("b c# d e f# g a"),
	//b harmonic minor
	bharmin("b c# d e f# g a#"),
	//b melodic minor
	bmelmin("b c# d e f# g# a#"),
	//A Major
	AMAJOR("A B C# D E F# G#"),
	//f# natural minor
	fsnatmin("f# g# a b c# d e"),
	//f# harmonic minor
	fsharmin("f# g# a b c# d e#"),
	//f# melodic minor
	fsmelmin("f# g# a b c# d# e#"),
	//E Major
	EMAJOR("E F# G# A B C# D#"),
	//c# natural minor
	csnatmin("c# d# e f# g# a b"),
	//c# harmonic minor
	csharmin("c# d# e f# g# a b#"),
	//c# melodic minor
	csmelmin("f c# d# e f# g# a# b#"),
	//B Major
	BMAJOR("B C# D# E F# G# A#"),
	//g# natural minor
	gsnatmin("g# a# b c# d# e f#"),
	//g# harmonic minor
	gsharmin("g# a# b c# d# e f##"),
	//g# melodic minor
	gsmelmin("g# a# b c# d# e# f##"),
	//F# Major (Same as Gb Major)
	FSMAJOR("F# G# A# B C# D# E#"),
	//d# natural minor
	dsnatmin("d# e# f# g# a# b c#"),
	//d# harmonic minor
	dsharmin("d# e# f# g# a# b c##"),
	//d# melodic minor
	dsmelmin("d# e# f# g# a# b# c##"),
	//F Major
	FMAJOR("F G A Bb C D E "),
	//d natural minor
	dnatmin("d e f g a bb c"),
	//d harmonic minor
	dharmin("d e f g a bb c#"),
	//d melodic minor
	dmelmin("d e f g a b c#"),
	//Bb Major
	BFMAJOR("Bb C D Eb F G A"),
	//g natural minor
	gnatmin("g a bb c d eb f"),
	//g harmonic minor
	gharmin("g a bb c d eb f#"),
	//g melodic minor
	gmelmin("g a bb c d e f#"),
	//Eb Major
	EFMAJOR("Eb F G Ab Bb C D "),
	//c natural minor
	cnatmin("c d eb f g ab bb"),
	//c harmonic minor
	charmin("c d eb f g ab b"),
	//c melodic minor
	cmelmin("c d eb f g a b"),
	//Ab Major
	AFMAJOR("Ab Bb C Db Eb F G"),
	//f natural minor
	fnatmin("f g ab bb c db eb"),
	//f harmonic minor
	fharmin("f g ab bb c db e"),
	//f melodic minor
	fmelmin("f g ab bb c d e"),
	//Db Major
	DFMAJOR("Db Eb F Gb Ab Bb C "),
	//bb natural minor
	bfnatmin("bb c db eb f gb ab"),
	//bb harmonic minor
	bfharmin("bb c db eb f gb a"),
	//bb melodic minor
	bfmelmin("bb c db eb f g a"),
	//Gb Major
	GFMAJOR("Gb Ab Bb Cb Db Eb F"),
	//eb natural minor
	efnatmin("eb f gb ab bb cb db"),
	//eb harmonic minor
	efharmin("eb f gb ab bb cb d"),
	//eb melodic minor
	efmelmin("eb f gb ab bb c d"),
	;
	
	private String values[];
	
	private KEYSIG(String str) {
		values = str.split(" ");
	}
	
	public String getValue(int i) {
		return values[i];
	}
}
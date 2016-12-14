package main;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class MG_MainTest {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			
			//Valid inputs
			{"10","170","20",""},		//[0]
			{"8","150","0",""},			//[1]
			{"6","110","10",""},		//[2]	
			{"15","90","32",""},		//[3]	
			{"20","200","50",""},		//[4]
			{"1","1","12",""},			//[5]
			{"1","96","30",""},			//[6]
			{"3","1","47",""},			//[7]
			{"900","250","29","false"},	//[8]
			{"10","900","2","false"},	//[9]
			{"0","0","23","true"},		//[10]
			{"0","125","14","true"},	//[11]
			{"9","0","50","false"},		//[12]
			
			//Invalid inputs
			{"3wh","110","10","true"},	//[13]
			{"6","3wh","6","true"},		//[14]
			{"11","70","3wh","true"},	//[15]
			{"","200","5","true"},		//[16]
			{"5","","12","true"},		//[17]
			{"12","160","","true"},		//[18]
			{"11","130","52","true"},	//[19]
			
			
        });	
	}

	@Parameter
	public String numOfNotes;
	@Parameter(value=1)
	public String tempo;
	@Parameter(value=2)
	public String keyIndex;
	@Parameter(value=3)
	public String playSound;
	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void test() {

		StringBuilder sb = new StringBuilder();
		sb.append(numOfNotes);
		sb.append(" ");
		sb.append(tempo);
		sb.append(" ");
		sb.append(keyIndex);
		sb.append(" ");
		sb.append(playSound);
		System.out.println(sb.toString());
		
		MG_Main mainTest = new MG_Main();
		
		String[] myArguments = new String[4];
		myArguments[0] = numOfNotes;
		myArguments[1] = tempo;
		myArguments[2] = keyIndex;
		myArguments[3] = playSound;
			
		mainTest.main(myArguments);

	}

}

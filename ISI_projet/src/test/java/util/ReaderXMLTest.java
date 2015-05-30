package util;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ReaderXMLTest {
	@Before
	public void init()
	{
		String fileSeparator=System.getProperty("file.separator");
		File f=new File("data"+fileSeparator+"grapheTest.xml");
	}
	@Test
	public void testChargement(File f)
	{
		
	}
}

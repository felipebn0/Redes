

package Servidor;

public class Trama {
	
	
	public static String  createFrame(String control,String dataCrc)
	{
	String fl = "01111110";  
        String trama = fl+control+dataCrc+fl;     
        return trama;  
	}
	
	
	

}

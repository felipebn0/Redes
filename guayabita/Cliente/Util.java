
package Cliente;




public class Util {
       
    public static int dado(int rango){
        int dado =(int) (Math.random()*rango+1);
        return dado;
    }
    
    
     public static String convertirString(byte []datos){
        
         String dd=""; 
         for(int i=0; i<datos.length;i++){
             dd+=(char) datos[i];
         }
        return dd;
    }
     
     public static String convertirBit(byte []datos){
     String mibit="";

		for (int i =0; i < datos.length;i++){
			//System.out.println(datos[i]);
			for(int j = 7; j >= 0; j--){
			    if((datos[i] & (1<<j))>0){
				//  System.out.println("1");
			      mibit+="1";
			    }    
			    else{
			    //  System.out.println("0");
			      mibit+="0";
			    }
			}
			//System.out.println("------------------------------------");
		}
         
      return mibit;
      }
     
     public static String CrcVacio(String datos){
     char ja='0';
		for (int i =0; i < 16;i++){
			datos=datos+ja;
		}
      return datos;
     }
    
    

    public static  byte getControl(String s)
    {
       int sLen = s.length();
       byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
       char c;
       for( int i = 0; i < sLen; i++ )
           if( (c = s.charAt(i)) == '1' ){
               toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
           }
           else if ( c != '0' )
               throw new IllegalArgumentException();
       
       /*for( int i = 0; i < 12; i++ ){
          System.out.println("El byte "+i+" es "+toReturn[i]);
       }*/
        return  toReturn[1]; 
    }
    
    public static  byte[] getDatos(String s)
    {
    int sLen = s.length();
    byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
    char c;
    for( int i = 0; i < sLen; i++ )
    {
        if( (c = s.charAt(i)) == '1' )
        {
         toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
        }
        else if ( c != '0' )
        {
         throw new IllegalArgumentException();
        }
    }
    return  toReturn; 
    }

}




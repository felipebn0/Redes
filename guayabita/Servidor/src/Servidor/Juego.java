
package Servidor;



import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import javax.sound.midi.Soundbank;



public class Juego 
{
	 
     
     public static void main(String[] args) throws  Exception  
     {
        // System.out.println("ICRC= "+CalculoCrc.Icalculate_crc("000000010000100000000000000000110000000100000000000000000001110011010011"));
     //documento mi    
     Parameters param = new Parameters();
     param.setPort("COM1");
     param.setBaudRate(Baud._9600);
     param.setMinDelayWrite(50);
     Com com1 = new Com(param);
     String j1=" ";
     String j2=" ";
     byte dineroj2 = 0;
     
     int counter=0;
           
     byte dado,x=0,v=0,dineroJ;
     
     
     @SuppressWarnings("resource")	
     Scanner reader=new Scanner(System.in);
        
         
     while (j2.length() <= 6)
     {
         
     j2 += com1.receiveSingleString();
     
     }
     while (dineroj2 == 0 )
     dineroj2 = (byte) com1.receiveSingleCharAsInteger();
     
     System.out.println("Nombre oponente: "+ j2 + " Dinero Oponente inicial: " + dineroj2);
     System.out.println("Nombre jugador: ");
     j1 = reader.nextLine();
     com1.sendString(j1);
     System.out.println("Dinero inicial (Menor a 100):");
     dineroJ=reader.nextByte();
     com1.sendSingleData(dineroJ);
     System.out.println("Valor minimo dejuego:");
     v=reader.nextByte();
     com1.sendSingleData(v);
     
     Jugador A = new Jugador(0,dineroj2,dineroJ-v,2*v,0,0,Util.dado(1));
     
     com1.sendSingleData(A.getTurno());
     
         System.out.println("valor turno " + A.getTurno());
     
     boolean Ack=true,waitAck = false;    
     
     		while ( (A.getDinero2()>0) && (A.getDinero1()>0) && (A.getFondo()>0)  && (A.getDinero1()>=v))   
     		{
                System.out.println("El dinero del jugador 2 :"+ A.getDinero2());
     		Thread.sleep(500);
     			if(A.getTurno()==1 && Ack==true)
     			{
				counter=0;
                                A.setDado((byte) Util.dado(6));	 
                                System.out.printf("Valor del dado: %d %n",A.getDado());			
     				if(A.getDado()==1 || A.getDado()==6)
     				{
     				System.out.printf("Perdio: %d %n",v);     				
     				A.setDinero1((byte) (A.getDinero1()-v));
     				System.out.printf("Su nuevo saldo es: %d %n",A.getDinero1());
     				A.setFondo((byte)(A.getFondo()+v));
     				A.setTurno((byte)0);
     				A.setApuesta1(v);
                                System.out.println("elturno cambio ha "+A.getTurno());
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                               outputStream.write( A.getDado() );
                               outputStream.write( A.getDinero1() );
                               outputStream.write( A.getDinero2() );
                               outputStream.write( A.getFondo() );
                               outputStream.write( A.getApuesta1() );
                               outputStream.write( A.getApuesta2());
                               outputStream.write( A.getTurno());
                               byte[] total = outputStream.toByteArray( );
                               String aBits=Util.convertirBit(total);
                               aBits=Util.CrcVacio(aBits);                     
                               aBits=CalculoCrc.calculate_crc(aBits);  
                               String tramaCompleta=Trama.createFrame("00000000", aBits);
                               com1.sendString(tramaCompleta);
                               Ack=false;
                               waitAck = true;
                               }
     				else if(A.getDado()==2 || A.getDado()==3 || A.getDado()==4)
     				{
     				byte num=A.getDado();
     					System.out.printf("El valor minimo de apuesta es: %d %n",v);
                                        System.out.println("Su saldo es: "+A.getDinero1());
                                         System.out.println("El fondo es: "+A.getFondo());
     					System.out.println("Cuanto desea apostar?");
     					x=reader.nextByte();		
     					while(x<v || x>A.getDinero1() || x%v!=0 || (A.getFondo()-x) < 0 )
	      				{
	      					System.out.printf("El valor minimo de apuesta es: %d %n",v);
                                                System.out.println("Su saldo es: "+A.getDinero1());
                                                System.out.println("El fondo es: "+A.getFondo());
                                                System.out.println("Cuanto desea apostar?");
	      					x=reader.nextByte();
	      				}
                                        A.setDado((byte) Util.dado(6));	 
                                        System.out.printf("Valor del dado: %d %n",A.getDado());	
	      				if(A.getDado()>num)
	      				{
	      				System.out.printf("Gano: %d %n",x);
	      				A.setDinero1((byte)(A.getDinero1()+x));		      	
	      				System.out.printf("Su nuevo saldo es: %d %n",A.getDinero1());
	      				A.setFondo((byte)(A.getFondo()-x));
	      				A.setTurno((byte)0);		
	      				A.setApuesta1(x);
                                           System.out.println("elturno cambio ha "+A.getTurno());
                                           ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                                           outputStream.write( A.getDado() );
                                           outputStream.write( A.getDinero1() );
                                           outputStream.write( A.getDinero2() );
                                           outputStream.write( A.getFondo() );
                                           outputStream.write( A.getApuesta1() );
                                           outputStream.write( A.getApuesta2());
                                           outputStream.write( A.getTurno());
                                           byte[] total = outputStream.toByteArray( );
                                           String aBits=Util.convertirBit(total);
                                           aBits=Util.CrcVacio(aBits); 
                                           aBits=CalculoCrc.calculate_crc(aBits);  
                                           String tramaCompleta=Trama.createFrame("00000000", aBits);
                                           com1.sendString(tramaCompleta);
                                           Ack=false;
                                           waitAck = true;
	      				}
	      				else
	      				{
	      				System.out.printf("Perdio: %d %n",x);
	      				A.setDinero1((byte)(A.getDinero1()-x));		      	
	      				System.out.printf("Su nuevo saldo es: %d %n",A.getDinero1());
	      				A.setFondo((byte)(A.getFondo()+x));
                                        A.setTurno((byte)0);	
                                         System.out.println("elturno cambio ha "+A.getTurno());
	      				A.setApuesta1(x);
							ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
							outputStream.write( A.getDado() );
							outputStream.write( A.getDinero1() );
							outputStream.write( A.getDinero2() );
							outputStream.write( A.getFondo() );
							outputStream.write( A.getApuesta1() );
							outputStream.write( A.getApuesta2());
							outputStream.write( A.getTurno());
							byte[] total = outputStream.toByteArray( );
							String aBits=Util.convertirBit(total);
							aBits=Util.CrcVacio(aBits);                     
							aBits=CalculoCrc.calculate_crc(aBits);  
							String tramaCompleta=Trama.createFrame("00000000", aBits);
							com1.sendString(tramaCompleta); 
                                                        Ack=false;
                                                        waitAck = true;
	      				}
	
     				}
     				else
     				{
     					System.out.println("Cede el turno!");
     					A.setApuesta1((byte) 0);			
                                        A.setTurno((byte)0);
                                         System.out.println("elturno cambio ha "+A.getTurno());
                                           ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                                           outputStream.write( A.getDado() );
                                           outputStream.write( A.getDinero1() );
                                           outputStream.write( A.getDinero2() );
                                           outputStream.write( A.getFondo() );
                                           outputStream.write( A.getApuesta1() );
                                           outputStream.write( A.getApuesta2());
                                           outputStream.write( A.getTurno());
                                           byte[] total = outputStream.toByteArray( );
                                           String aBits=Util.convertirBit(total);
                                           aBits=Util.CrcVacio(aBits);
                                           aBits=CalculoCrc.calculate_crc(aBits);
                                           String tramaCompleta=Trama.createFrame("00000000", aBits);
                                           com1.sendString(tramaCompleta);
                                           Ack=false;
                                           waitAck = true;
     				}
     			System.out.printf("Dinero en el fondo: %d %n",A.getFondo());  
     			}//documento mi
     			else// si es el turno de la otra persona 
     			{
                           String recive="";  
                           Thread.sleep(999);
                                if(counter==0 && Ack==true)
                                {
                                System.out.println("No es su turno, espere por favor.");     
                                counter=1;
                                }                
                                
                                
                                while(recive.length()<96 &&  waitAck==false)
                                {  
                                    
                                recive += com1.receiveSingleString();         
                                }  
                                int time=0;
                                
                                while(recive.length()<96 &&  waitAck==true)
                                {
                                    System.out.println("Temporizador: " + time);
                                recive += com1.receiveSingleString(); 
                                    if(recive.length()==0)
                                    {
                                        Thread.sleep(1000);
                                         recive += com1.receiveSingleString(); 
                                        time++;
                                    } 
                                    else if(recive.length()==24)
                                    {
                                        waitAck=false;    
                                    }
                                    if(time==5)
                                    {
                                        System.out.println("Tiempo Agotado Reenviando Trama...");
                                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                                        outputStream.write( A.getDado() );
                                        outputStream.write( A.getDinero1() );
                                        outputStream.write( A.getDinero2() );
                                        outputStream.write( A.getFondo() );
                                        outputStream.write( A.getApuesta1() );
                                        outputStream.write( A.getApuesta2());
                                        outputStream.write( A.getTurno());
                                        byte[] total = outputStream.toByteArray( );
                                        String aBits=Util.convertirBit(total);
                                        aBits=Util.CrcVacio(aBits);
                                        aBits=CalculoCrc.calculate_crc(aBits);
                                        String tramaCompleta=Trama.createFrame("00000000", aBits);
                                        com1.sendString(tramaCompleta);
                                        Ack=false;
                                        waitAck=true;
                                    }                
                                }
                                                                                      
                               byte ctrl=Util.getControl(recive);            
   
                               if(ctrl==0)//info
                               {
                                    byte [] dd =Util.getDatos(recive);
                                    String data=recive.substring(16, recive.length()-8);                                      
                                   
                                    boolean a=CalculoCrc.Icalculate_crc(data);
 
                                    System.out.println(a);
                                    
                                    if(a==true)//lego bien 
                                    {                                 
                                        A.setFondo(dd[5]); 
                                        A.setDinero1(dd[4]);
                                        A.setTurno(dd[8]); 
                                        System.out.println("elturno cambio a "+A.getTurno());
                                        String tramaCompleta=Trama.createFrame("00000001", "");               
                                        com1.sendString(tramaCompleta); // manda Ack
                                        Ack=true;
                                    }
                                   else
                                   {            
                                  
                                    String tramaCompleta=Trama.createFrame("11111111", "");
                                    com1.sendString(tramaCompleta); // manda nAck
                                   } 
                               }
                               else if(ctrl==1)//ack
                               {        
                               A.setTurno((byte)0);
                               Ack=true;
                               System.out.println("Trama buena");
                               }
                               else//nck
                               {
                                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                                        outputStream.write( A.getDado() );
                                        outputStream.write( A.getDinero1() );
                                        outputStream.write( A.getDinero2() );
                                        outputStream.write( A.getFondo() );
                                        outputStream.write( A.getApuesta1() );
                                        outputStream.write( A.getApuesta2());
                                        outputStream.write( A.getTurno());
                                        byte[] total = outputStream.toByteArray( );
                                        String aBits=Util.convertirBit(total);
                                        aBits=Util.CrcVacio(aBits);
                                        aBits=CalculoCrc.calculate_crc(aBits);
                                        String tramaCompleta=Trama.createFrame("00000000", aBits);
                                        com1.sendString(tramaCompleta);
                                        Ack=false;
                                        waitAck=true;
                            	System.out.println("Reenviando Trama...");
                               }
     			}
                       //  com1.close();
     		}//endWhile
                
                if (A.getFondo()<= 0){
                    System.out.println("Fondo vacio, se acabo el juego");
                    
                }
                if (A.getDinero1() <= 0){
                     System.out.println("Perdio partida, su dinero es 0!");
                     
                }
                System.out.println("Su dinero inicial fue: "+ dineroJ);
                System.out.println("Su dinero final es   : "+ A.getDinero1());
                System.out.println("   : "+ A.getDinero1());
                
                
               
                
               com1.close();
	}//endMain
     
}//endClass
      
     
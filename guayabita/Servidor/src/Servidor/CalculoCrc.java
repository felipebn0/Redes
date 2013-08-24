

package Servidor;



import java.util.Scanner;



public class CalculoCrc {

	/**
	 * @param args
	 */  
		public static  String calculate_crc(String mensaje) {
		
                 
		int [] polinomio = {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1};
		int [] divi   = new int [16];
		int datos[]= new int[mensaje.length()];
		String mensajeCrc="";
		int posd=0;
		int posm=0;
		boolean finale=false;
		for (int i=0;i<mensaje.length();i++){
		  if (mensaje.charAt(i)=='0'){
	    	  datos[i]=0;
	      }
		  else{
			  datos[i]=1;
		  }
		}
		
		//System.out.print("\n Dividen = ");	
		
		for (; posd<mensaje.length();posd++){
			if (datos[posd] == 1){
				for (int k = 0 ; k<16 ; k++ , posd++ ){
					divi[k] = datos[posd];
					//System.out.print(divi[k]);
					divi[k] = divi[k]^polinomio[k] ;
				}
				break;
			}
		}	
		//System.out.println("result= ");
		for (int k = 0 ; k<16 ; k++ ){
			//System.out.print(divi[k]);
		}
		while (posd<datos.length){
			//System.out.print("\n Result sin cero ");		
		
			for (int i = 0; i<divi.length;i++){
				if (divi[i] == 1){
                                    int k=0;
                                        //System.out.println(i+" "+ (datos.length-posd));
                                    if (i>datos.length-posd){
                                        //System.out.println(" Entro a finale");
                                        k=datos.length-posd;
                                        int l=k;
                                        for (int j = 0; j<16-l ;j++, k++ ){
						divi[j] = divi[k];
						//System.out.print(divi[j]);
						posm=j+1;
					}
                                        finale=true;
					break;
                                    }
                                    else{
                                        k=i;
                                        //System.out.println("\ni= "+i +" pos d= "+posd+" long datos= "+datos.length+" k "+k);
                                        //System.out.print("\nResult Xor =");
					for (int j = 0; j<16-k ;j++, i++ ){
						divi[j] = divi[i];
                                                
						//System.out.print(divi[j]);
						posm=j+1;
					}
					break ;
                                    }
				}
			}
		
			//System.out.print("\n Adicionar a Div ");	
		
			for (; posm<16;posm++,posd++){
				if(posd!=datos.length)
				divi[posm]=datos[posd];
				else
					break;
			}
				
			
			for (int i=0;i<16;i++){
				//System.out.print(divi[i]);
			}
		


			//System.out.print("\n Xor ");
			for (int j = 0 ; j<16 ; j++  ){
				if (!finale){
				divi[j] = divi[j]^polinomio[j] ; 
				//System.out.print(divi[j]);
				}
			}
			
			//System.out.println("\n Pos datos= "+posd);
			//System.out.println("___________________________________________________________________________________________________________________");
		}
		System.out.print("\nDatos    = ");
		for (int i=0  ; i<datos.length; i++  ){
			System.out.print(datos[i]);
		}
		System.out.print("\nCrc      =                                                        ");
		for (int j = 0 ; j<16 ; j++  ){
			System.out.print(divi[j]);
		}
		
		for(int j=0,i=datos.length-divi.length;j<divi.length;j++,i++){
			datos[i] = divi[j]; 
		}
		
		System.out.print("\nDatos+Crc= ");
		
		for (int i=0  ; i<datos.length; i++  ){
			System.out.print(datos[i]); 
		}
		
		//System.out.println("\nCuando llega el mensaje se verifica que este bien __________________________________________________");
		Scanner a = new Scanner(System.in);
		System.out.println("Desea Cambiar algun bit dentro del mensaje? 0=NO 1=Si");
		int camb=a.nextInt();
		while(camb==1){
			if (camb==1){
				System.out.println("Que bit desea cambiar? Rango[0,"+datos.length+"]");
				int bit=a.nextInt();
				System.out.println("el bit acutalmente se encuentra en= "+datos[bit]);
				if(datos[bit]==0)
					datos[bit]=1;
				else
					datos[bit]=0;
				System.out.println("el bit acutalmente se ha cambiado a= "+datos[bit]);
			}
			else{
				System.out.println("Mensaje enviado");
			}
			System.out.println("Desea Cambiar algun bit dentro del mensaje? 0=NO 1=Si");
			camb=a.nextInt();
		}
		for (int i=0;i<datos.length;i++){
			  if (datos[i]==1){
		    	  mensajeCrc+="1";
		      }
			  else{
				  mensajeCrc+="0";
			  }
		}
		//System.out.println("\nEl mensaje en string es ="+mensajeCrc);
		
                return mensajeCrc;
	}	
                
                
                
                
                
                
                
                
                
                
	public static boolean Icalculate_crc(String mensaje){
		
                 
		int [] polinomio = {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1};
		int [] divi   = new int [16];
		int datos[]= new int[mensaje.length()];
		String mensajeCrc="";
		int posd=0;
		int posm=0;
		boolean finale=false;
		int ceros=1;
		for (int i=0;i<mensaje.length();i++){
		  if (mensaje.charAt(i)=='0'){
	    	  datos[i]=0;
	      }
		  else{
			  datos[i]=1;
		  }
		}
		
		//System.out.print("\n Dividen = ");	
		
		for (; posd<mensaje.length();posd++){
			if (datos[posd] == 1){
				for (int k = 0 ; k<16 ; k++ , posd++ ){
					divi[k] = datos[posd];
					//System.out.print(divi[k]);
					divi[k] = divi[k]^polinomio[k] ;
				}
				break;
			}
		}	
		//System.out.println("result= ");
		for (int k = 0 ; k<16 ; k++ ){
			//System.out.print(divi[k]);
		}
		while ((ceros!=0  && !finale)|| (ceros == 0 && finale)){
			ceros=0;
			//System.out.print("\n Result sin cero ");		
		
			for (int i = 0; i<divi.length;i++){
				if (divi[i] == 1){
                                    int k=0;
                                        //System.out.println(i+" "+ (datos.length-posd));
                                    if (i>datos.length-posd){
                                        //System.out.println(" Entro a finale");
                                        k=datos.length-posd;
                                        int l=k;
                                        for (int j = 0; j<16-l ;j++, k++ ){
						divi[j] = divi[k];
						//System.out.print(divi[j]);
						posm=j+1;
					}
                                        finale=true;
					break;
                                    }
                                    else{
                                        k=i;
                                        //System.out.println("\ni= "+i +" pos d= "+posd+" long datos= "+datos.length+" k "+k);
                                        //System.out.print("\nResult Xor =");
					for (int j = 0; j<16-k ;j++, i++ ){
						divi[j] = divi[i];
                                                
						//System.out.print(divi[j]);
						posm=j+1;
					}
					break ;
                                    }
				}
			}
		
			//System.out.print("\n Adicionar a Div ");	
		
			for (; posm<16;posm++,posd++){
				if(posd!=datos.length)
				divi[posm]=datos[posd];
				else
					break;
			}
				
			
			for (int i=0;i<16;i++){
				//System.out.print(divi[i]);
			}
		


			//System.out.print("\n Xor ");
			for (int j = 0 ; j<16 ; j++  ){
				if (!finale){
				divi[j] = divi[j]^polinomio[j] ; 
				//System.out.print(divi[j]);
				}
			}
			for (int j = 0 ; j<16 ; j++  ){
				ceros=ceros|divi[j];
			}
			
			//System.out.println("\n Pos datos= "+posd);
			//System.out.println("___________________________________________________________________________________________________________________");
		}
		System.out.print("\nDatos    = ");
		for (int i=0  ; i<datos.length; i++  ){
			System.out.print(datos[i]);
		}
		System.out.print("\nCrc      =                                                 ");
		for (int j = 0 ; j<16 ; j++  ){
			System.out.print(divi[j]);
		}
		
		for(int j=0,i=datos.length-divi.length;j<divi.length;j++,i++){
			datos[i] = divi[j]; 
		}
		
		System.out.print("\nDatos+Crc= ");
		
		for (int i=0  ; i<datos.length; i++  ){
			System.out.print(datos[i]); 
		}
		
		//System.out.println("\nCuando llega el mensaje se verifica que este bien __________________________________________________");
		Scanner a = new Scanner(System.in);
		System.out.println("Desea Cambiar algun bit dentro del mensaje? 0=NO 1=Si");
		int camb=a.nextInt();
		while(camb==1){
			if (camb==1){
				System.out.println("Que bit desea cambiar? Rango[0,"+datos.length+"]");
				int bit=a.nextInt();
				System.out.println("el bit acutalmente se encuentra en= "+datos[bit]);
				if(datos[bit]==0)
					datos[bit]=1;
				else
					datos[bit]=0;
				System.out.println("el bit acutalmente se ha cambiado a= "+datos[bit]);
			}
			else{
				System.out.println("Mensaje enviado");
			}
			System.out.println("Desea Cambiar algun bit dentro del mensaje? 0=NO 1=Si");
			camb=a.nextInt();
		}
		for (int i=0;i<datos.length;i++){
			  if (datos[i]==1){
		    	  mensajeCrc+="1";
		      }
			  else{
				  mensajeCrc+="0";
			  }
		}
		//System.out.println("\nEl mensaje en string es ="+mensajeCrc);
		
       
                
                for (int j = 0 ; j<16 ; j++  ){
                    System.out.println("Entro!!!!");
                    if (divi[j]==1 ){
                        System.out.println("El mensaje llego mal!");
			return false;
                    }
                }      
                System.out.println("El mensaje llego bien!");
                return true;
                
	}	
}
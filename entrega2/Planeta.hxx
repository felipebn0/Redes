/* 
 * File:   Planeta.hxx
 * Author: julian
 *
 * Created on 12 de septiembre de 2012, 11:34 AM
 */

#ifndef PLANETA_HXX
#define	PLANETA_HXX
#include <time.h>
#include <list>
typedef std::string cadena;

Planeta::Planeta ( bool habitado , cadena nom ) {
                this->nombre = nom;
				this->productos.clear();
				this->habitado = habitado; 
}

bool Planeta::estahabitado (){
	 
	 return (this->habitado);
	 
	 }
cadena Planeta::getNombre (){
	   return (this->nombre);
	   
	   }

double Planeta::getStock (cadena nombre_pro){    //revisar
       double a = Producto.getStock(nombre_pro);
       
       return a;
       }
       
double Planeta::getFO (cadena nombre_pro){ //revisar
       double factorOferta ;
       srand (time(NULL));
       factorOferta = rand()%1000000+0;
       return factorOferta;
       }       


double Planeta::getFD (cadena nombre_pro){ //revisar
       double factorDemanda;
       srand (time(NULL));
       factorDemanda = rand()%1000000+0;
       return factorDemanda;
       
       
       
       
       }       

#endif	/* PLANETA_HXX */


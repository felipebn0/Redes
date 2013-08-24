/* 
 * File:   Planeta.h
 * Author: julian
 *
 * Created on 11 de septiembre de 2012, 09:20 PM
 */

#ifndef PLANETA_H
#define	PLANETA_H
#include<list>
#include "Producto.h"
typedef std::string cadena;
class Planeta {
private:
		cadena nombre;
    	std::list<Producto> productos;
    	bool habitado;
public :
    Planeta ( bool habitado, cadena nombre  );
    bool estahabitado ();
    cadena getNombre();
    double getStock (cadena nombre_pro); // revisar
    double getFO (cadena nombre_pro);
    double getFD (cadena nombre_pro);
    
};


#include "Planeta.hxx"
#endif	/* PLANETA_H */


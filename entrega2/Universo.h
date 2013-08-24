/* 
 * File:   Universo.h
 * Author: julian
 *
 * Created on 11 de septiembre de 2012, 08:57 PM
 */

#ifndef UNIVERSO_H
#define	UNIVERSO_H
#include <list>
#include "Estrella.h"

class Universo{
private:
            std::list<Estrella> estrella;
            
public:    
            Universo();
            Universo( Universo & );
            virtual ~Universo();
            bool instertarEstrella ( Estrella& nuevaEstrella );
            unsigned int cantidadEstrellas ( Estrella& aux  );
            
            
       };

#include "Universo.h"
//#include "Estrella.hxx"

#endif	/* UNIVERSO_H */


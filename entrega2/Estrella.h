/* 
 * File:   Estrella.h
 * Author: julian
 *
 * Created on 11 de septiembre de 2012, 09:04 PM
 */

#ifndef ESTRELLA_H
#define	ESTRELLA_H
#include <list>
#include "Planeta.h"
class Estrella {
    
private:
    std::list<Planeta> planeta;
    bool agujeroGusano;
    int m_X;
    int m_Y;
    int m_Z;
    
public: 
  
  Estrella();
  Estrella (int x, int y, int z, bool agujero);
  virtual ~Estrella();  
    
  const int getX ()  const;
  const int getY ()  const;
  const int getZ ()  const;
  const bool getAgujeroGusano () const;
  
  void setX (const int& x);
  void setY (const int& y);
  void setZ (const int& z);
  void setAgujeroGusano (const bool& agujero);
};

#include "Estrella.hxx"
//#include <Planeta.hxx>
#endif	/* ESTRELLA_H */


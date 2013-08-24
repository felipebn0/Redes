#ifndef __PRODUCTO__H__
#define __PRODUCTO__H__

typedef std::string cadena;

static int consec;
class Producto
{
public:
  Producto( cadena name );
  virtual ~Producto( );
  double aleat ();
  double getID( cadena name );
  cadena getname( int id );
  double getStock( int id );
  double getPO( int id );
  double getPD( int id );
  int getConsec( );

  private:
    int ID;
    cadena nombre;
    double stock;
    double PO;
    double PD;
    
  };
#include "Producto.hxx"
#endif

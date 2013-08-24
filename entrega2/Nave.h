

#ifndef NAVE__H__
#define	NAVE__H__
#include <list>
#include <string>

typedef std::string aux; //cuid

class Nave
{
public:
  Nave( cadena name , double time, double money, Planeta planet, std::list<Producto> invent );
  virtual ~Nave( );

  bool AgregarInventario( Producto prod);
  double getTiempoTrascurrido();
  void setTiempoTranscurrido( double time);
  double getDinero();
  void setDinero( double Money);
  aux getPlaneta();
  void setPlaneta( Planeta plan );
  double PV(aux product);
  double PC(aux product);


  private:
    aux nombre;
    double tiempo;
    double dinero;
    Planeta planeta;
    std::list<Producto> inventario;
  };


#include "Nave.hxx"
#endif

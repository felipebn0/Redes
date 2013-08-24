#ifndef __PRODUCTO__HXX__
#define __PRODUCTO__HXX__
#include <time.h>


typedef std::string cadena;


  Producto::Producto( cadena name ){
	this->nombre=name;
    this->ID=getConsec();
	this->stock=aleat();
	this->PD=aleat();
	this->PO=aleat();
  }
  double Producto::aleat(){
	srand ( time(NULL) );
	return (rand()%1000001);
  }
  double Producto::getID( cadena name ){
	return this->ID;
  }
  cadena Producto::getname( int id ){
	return (this->nombre);
  }
  double Producto::getStock( int id ){
	return this->stock;
  }		
  double Producto::getPO( int id ){
	return this->PO;
  }	
  double Producto::getPD( int id ){
	return this->PD;
  }	
  int Producto::getConsec( ){
	consec+1;
	return consec;
  }
	
#endif


#ifndef __NAVE__HXX__
#define __NAVE__HXX__
#include <list>

typedef std::string cadena;


Nave::Nave( cadena name , double time, double money, Planeta planet, std::list<Producto> invent){
  this->nombre = name;
  this->tiempo = time;
  this->dinero = money;
  bool aux = planet.estahabitado();
  this->planeta = planet;
  this->inventario = invent;

}


bool Nave::AgregarInventario( Producto prod ){
	 this->inventario.push_back(prod);
	 
/*	 
	this->inventario.push_back(1,"Manzana",500000);
	this->inventario.push_back(2,"Pera",500000);
	this->inventario.push_back(3,"Pastel",100000);
	this->inventario.push_back(4,"Botas",10000);
	this->inventario.push_back(5,"Televisor",1000);
	this->inventario.push_back(6,"Sillas",250000);
	this->inventario.push_back(7,"PC",3000);*/
	return true;
}

double Nave::getTiempoTrascurrido(){
	return this->tiempo;
}

void Nave::setTiempoTranscurrido( double time){
	this->tiempo=time;
}

double Nave::getDinero(){
	return this->dinero;
}

void Nave::setDinero( double money){
	this->dinero=money;
}

cadena Nave::getPlaneta(){
	return this->Planeta.getNombre();
}



void Nave::setPlaneta( Planeta plan){
	this->planeta=plan;
}

double Nave::PV(cadena product){
	double S=this->planeta.getStock(product);
	double FD=this->planeta.getFD(product);
	return (FD/(1+S));
}

double Nave::PC(cadena product){
	double S=this->planeta.getStock(product);
	double FO=this->planeta.getFO(product);
	return (FO/(1+S));
}

#endif

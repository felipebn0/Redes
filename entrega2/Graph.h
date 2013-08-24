#ifndef __GRAPH__H__
#define __GRAPH__H__

#include <map>
#include <vector>

template< class T, class C >
class Graph
{
public:
  typedef std::vector< T >                         TVertices;
  typedef std::map< unsigned long, C >             TRow;
  typedef std::map< unsigned long, TRow >          TMatrix;
  typedef std::map< unsigned long, unsigned long > TTree;

public:
  Graph( );
  virtual ~Graph( );

  unsigned long AddVertex( const T& v );
  void SetArc( unsigned long i, unsigned long j, const C& c );

  unsigned long GetNumberOfVertices( ) const;
  const T& GetVertex( unsigned long i ) const;

  bool HasArc( unsigned long i, unsigned long j ) const;
  const C& GetCost( unsigned long i, unsigned long j ) const;

  void PrintPlaneGraph( ) const;
  void PrintPreorderGraph( unsigned long i ) const;
  void PrintLevelsGraph( unsigned long i ) const;

protected:
  void PrintPreorderGraph( unsigned long i, std::vector< bool >& m );

protected:
  TVertices m_Vertices;
  TMatrix   m_Costs;
};

#include "Graph.hxx"

#endif // __GRAPH__H__

// eof - Graph.h


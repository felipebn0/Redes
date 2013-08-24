#ifndef __GRAPH__HXX__
#define __GRAPH__HXX__

#include <algorithm>
#include <deque>
#include <queue>
#include <limits>

// -------------------------------------------------------------------------
template< class T, class C >
Graph< T, C >::
Graph( )
{
  this->m_Vertices.clear( );
  this->m_Costs.clear( );
}

// -------------------------------------------------------------------------
template< class T, class C >
Graph< T, C >::
~Graph( )
{
}

// -------------------------------------------------------------------------
template< class T, class C >
unsigned long Graph< T, C >::
AddVertex( const T& v )
{
  this->m_Vertices.push_back( v );
  return( this->m_Vertices.size( ) - 1 );
}

// -------------------------------------------------------------------------
template< class T, class C >
void Graph< T, C >::
SetArc( unsigned long i, unsigned long j, const C& c )
{
  this->m_Costs[ i ][ j ] = c;
}

// -------------------------------------------------------------------------
template< class T, class C >
unsigned long Graph< T, C >::
GetNumberOfVertices( ) const
{
  return( this->m_Vertices.size( ) );
}

// -------------------------------------------------------------------------
template< class T, class C >
const T& Graph< T, C >::
GetVertex( unsigned long i ) const
{
  return( this->m_Vertices[ i ] );
}

// -------------------------------------------------------------------------
template< class T, class C >
bool Graph< T, C >::
HasArc( unsigned long i, unsigned long j ) const
{
  typename TMatrix::const_iterator rIt = this->m_Costs.find( i );
  if( rIt != this->m_Costs.end( ) )
    return( rIt->second.find( j ) != rIt->second.end( ) );
  return( false );
}

// -------------------------------------------------------------------------
template< class T, class C >
const C& Graph< T, C >::
GetCost( unsigned long i, unsigned long j ) const
{
  // WARNING: Invoke HasArc() before this
  return( this->m_Costs[ i ][ j ] );
}

// -------------------------------------------------------------------------
template< class T, class C >
void Graph< T, C >::
PrintPlaneGraph( ) const
{
  typename TVertices::const_iterator vIt = this->m_Vertices.begin( );
  for( ; vIt != this->m_Vertices.end( ); vIt++ )
    std::cout << *vIt << " : ";
  std::cout << std::endl;
}

// -------------------------------------------------------------------------
template< class T, class C >
void Graph< T, C >::
PrintPreorderGraph( unsigned long i ) const
{
  std::vector< bool > m( this->m_Vertices.size( ), false );
  this->PrintPreorderGraph( i, m );
  std::cout << std::endl;
}

// -------------------------------------------------------------------------
template< class T, class C >
void Graph< T, C >::
PrintPreorderGraph( unsigned long i, std::vector< bool >& m )
{
  if( m[ i ] )
    return;

  m[ i ] = true;
  std::cout << this->m_Vertices[ i ] << " : ";

  typename TMatrix::const_iterator rIt = this->m_Costs.find( i );
  if( rIt != this->m_Costs.end( ) )
  {
    typename TRow::const_iterator cIt = rIt->second.begin( );
    for( ; cIt != rIt->second.end( ); cIt++ )
      this->PrintPreorderGraph( cIt->first, m );

  } // fi
}

// -------------------------------------------------------------------------
template< class T, class C >
void Graph< T, C >::
PrintLevelsGraph( unsigned long i ) const
{
  std::queue< unsigned long > q;
  std::vector< bool > m( this->m_Vertices.size( ), false );

  q.push( i );
  while( !q.empty( ) )
  {
    unsigned long n = q.front( );
    q.pop( );

    // check if n has been visited
    if( m[ n ] )
      continue;

    // Mark it as visited
    m[ n ] = true;

    // Print it
    std::cout << this->m_Vertices[ n ] << " : ";

    // Iterate over neighbors
    typename TMatrix::const_iterator rIt = this->m_Costs.find( n );
    if( rIt != this->m_Costs.end( ) )
    {
      typename TRow::const_iterator cIt = rIt->second.begin( );
      for( ; cIt != rIt->second.end( ); cIt++ )
        q.push( cIt->first );

    } // fi

  } // elihw

  std::cout << std::endl;
}

#endif // __GRAPH__HXX__

// eof - Graph.hxx


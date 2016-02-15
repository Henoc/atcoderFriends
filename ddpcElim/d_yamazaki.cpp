
#include<cstdio>
#include<algorithm>
#include<set>
#include<limits>
using namespace std;

int const MAX_N = 60;
int const MAX_M = 60;

vector< set< int > > sums( int n, vector< int > const &values ){
  // sum of k unique values for all k <= n
  vector< set< int > > result( n + 1 );
  result[ 0 ].insert( 0 );
  for( int v : values ){
    for( int i = n; i > 0; --i ){
      for( int x : result[ i - 1 ] ){
        result[ i ].insert( x + v );
      }
    }
  }
  return result;
}

long long int score( int sum_a, int sum_b, int x ){
  return static_cast< long long int >( sum_a - x )
    * static_cast< long long int >( sum_b + x );
}

int main(){
  // input
  int n, m, k;
  scanf( " %d %d %d", &n, &m, &k );
  vector< int > a( n );
  vector< int > b( m );
  for( int i = 0; i < n; ++i ){
    scanf( " %d", &a[ i ] );
  }
  for( int i = 0; i < m; ++i ){
    scanf( " %d", &b[ i ] );
  }

  int sum_a = 0;
  for( int aa : a ){
    sum_a += aa;
  }
  int sum_b = 0;
  for( int bb : b ){
    sum_b += bb;
  }

  int kk = min( min( n, m ), k );
  vector< set< int > > aa = sums( kk, a );
  vector< set< int > > bb = sums( kk, b );

  // for( int i = 0; i <= kk; ++i ){
  //   printf( "%d:", i );
  //   for( int v : aa[ i ] ){
  //     printf( " %d", v );
  //   }
  //   printf( "\n" );
  // }
  // printf( "\n" );
  // for( int i = 0; i <= kk; ++i ){
  //   printf( "%d:", i );
  //   for( int v : bb[ i ] ){
  //     printf( " %d", v );
  //   }
  //   printf( "\n" );
  // }
  // printf( "\n" );

  long long int record = 0;

  if( k == 1 ){
    for( int v : aa[ 1 ] ){
      for( int w : bb[ 1 ] ){
        record = max( record, score( sum_a, sum_b, v - w ) );
      }
    }
  }else{
    for( int i = 0; i <= kk; ++i ){
      for( int v : aa[ i ] ){
        for( int w : bb[ i ] ){
          record = max( record, score( sum_a, sum_b, v - w ) );
        }
      }
    }
  }

  printf( "%lld\n", record );

  return 0;
}

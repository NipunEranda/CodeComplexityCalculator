#include <iostream>
using namespace std;
class PrimeNos{
  public:void findPrimeNo(){
    int i=0, j, k=10;
    do{
     i++;
     for(j = 2; j <= (i/j); j++){
        if(!(i%j)){
           break; 
        }
     }//for
     if(j > (i/j)){
        cout << i << " is prime\n";
     }
    }while(i<k);
  } //findPrimeNo
  
  public:void lol(){
	int i = 0;
	while(i > 0){
		cout << "lol lol lol";
	}
	lol();
  }
}; // PrimeNos

int main () {
  PrimeNos pn;
  pn.findPrimeNo(); 
  return 0;
}

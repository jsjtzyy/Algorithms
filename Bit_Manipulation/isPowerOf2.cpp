#include <iostream>

using namespace std;

bool isPowerOf2(int val){
    if(val == 1) return true;
    if((val & (val - 1)) == 0) return true;
    return false;
}

int main(){
    int val;
    cout << "Input an integer: " << endl;
    cin >> val;
    if(isPowerOf2(val)) cout << "Yes"<<endl;
    else cout << "No" <<endl;
    return 0;
}

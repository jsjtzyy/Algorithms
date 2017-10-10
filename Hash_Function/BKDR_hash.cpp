#include <iostream>

using namespace std;

unsigned int BKDR(string str){
    unsigned int hash;
    for(int i = 0; i < str.length(); ++i){
        hash = (hash << 7) + (hash << 1) + hash + str[i]; // hash = hash * 131 + ch;
    }
    return hash;
}

int main(){
    cout << BKDR("string to be hashed") << endl;
    return 0;
}

#include<iostream>
#include<vector>
using namespace std;
void kmp(string T, string P);
vector<int> preprocess(string P);
int main()
{
    string T = "sagertehg";
    string P = "ge";
    kmp(T, P);
    return 0;
}

void kmp(string T, string P){
    T = " " + T;
    P = " " + P;
    vector<int> prefix = preprocess(P);
    int k = 0;
    for(int i = 1; i < T.length(); ++i){
        while(k > 0 && P[k + 1] != T[i]){
            k = prefix[k];
        }
        if(P[k + 1] == T[i]){
            k += 1;
        }
        if(k == P.length() - 1){
            cout << "find match at "<<i <<endl;
            k = prefix[k];
        }
    }
}

vector<int> preprocess(string P){
    int k = 0;
    vector<int> res(P.length());
    res[1] = 0;
    for(int i = 2; i < P.length();++i){
        while(k >0 && P[k + 1] != P[i]){
            k = res[k];
        }

        if(P[k + 1] == P[i])
            k += 1;
        res[i] = k;
    }
    return res;
}

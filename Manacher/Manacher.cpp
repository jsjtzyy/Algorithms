/*  
 *  time complexity is O(N)
 *  The key point is cnt[i] = min(cnt[midIndex*2-i], bound-i);
 *
 *        i'    midIndex    i  bound
 *        |        |        |   |
 *  ---------------|------------******
 * */

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int manacher(string s);

int main(){
    cout << "Please input a string: "<<endl;
    string str;
    cin >> str;
    cout << "The length of longest palindrome is" << manacher(str) <<endl;
}

int manacher(string s){
    int len = s.length();
    for(int i = 0; i <= len; ++i){
        s.insert(i*2, "#");
    }
    cout << "the modified string is: " << s <<endl;
    len = s.length();
    vector<int> cnt(len, 1);
    int midIndex = 0, bound = 1;   // bound is out boundary of palindrome
    int res = 0;
    for(int i = 1; i < len; ++i){
        if(i < bound){
            cnt[i] = min(cnt[2*midIndex - i], bound - i);
        }

        while(s[i - cnt[i]] == s[i + cnt[i]]){
            ++cnt[i];
        }
        if(i + cnt[i] > bound)  // update the front boundary
        {
            midIndex = i;
            bound = i + cnt[i];
        }
        res = max(res, cnt[i]);
    }
    return res - 1;
}

/*
 * Given a array of numbers, find out all subset whose sum equals k 
 * */
#include<iostream>
#include<vector>

using namespace std;

vector<vector<int> > find(vector<int>& nums, int k){
    vector<vector<int> > res;
    int len = nums.size(), sum = 0;
    for(int i = 0; i < (1 << len); ++i){
        vector<int> vec;
        sum = 0;
        for(int j = 0; j < len; ++j){
            if((i >> j) & 1){
                sum += nums[j];
                vec.push_back(nums[j]);
            }
        }
        if(sum == k){
            res.push_back(vec);    
        }else{
            vec.clear();
        }
    }
    return res;
}

int main(){
    int arr[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    vector<int> nums(&arr[0], &arr[10]);
    vector<vector<int> > res = find(nums, 8);
    
    for(vector<int> vec : res){
        for(int val : vec) cout << val << " ";
        cout << endl;
    }
    return 0;
}

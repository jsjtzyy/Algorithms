#include <iostream>
#include <algorithm>

using namespace std;
bool intersect(int p1[], int p2[], int p3[], int p4[]);
int direction(int p1[], int p2[], int p3[]);
bool onSegment(int p1[], int p2[], int p3[]);

int main(){
    int p1[2], p2[2], p3[2], p4[2];
    //int p2[2];
    //int p3[2];
    cout<< "Input first segment start point:"<<endl;
    cin >> p1[0] >> p1[1];
    cout<< "Input first segment end point:"<<endl;
    cin >> p2[0] >> p2[1];
    cout << "Input second segment start point:"<<endl;
    cin >> p3[0] >> p3[1];
    cout << "Input second segment end point:" <<endl;
    cin >> p4[0] >> p4[1];
    if(intersect(p1, p2, p3, p4)) cout << "Intersect!"<<endl;
    else cout << "No intersection!" <<endl;
    return 0;
}

bool intersect(int p1[], int p2[], int p3[], int p4[]){
    int d1 = direction(p1, p2, p3);
    int d2 = direction(p1, p2, p4);
    int d3 = direction(p3, p4, p1);
    int d4 = direction(p3, p4, p2);
    if(d1 * d2 < 0 && d3 * d4 < 0) return true;
    // point on line
    if(d1 == 0) {
       if(onSegment(p1, p2, p3)) return true;
    }
    if(d2 == 0) {
       if(onSegment(p1, p2, p4)) return true;
    }
    if(d3 == 0) {
        if(onSegment(p3, p4, p1)) return true;
    }
    if(d4 == 0) {
        if(onSegment(p3, p4, p2)) return true;
    }
    return false;
}


int direction(int p1[], int p2[], int p3[]){
    int vec1[] = { p2[0] - p1[0], p2[1] - p1[1]};
    int vec2[] = { p3[0] - p1[0], p3[1] - p1[1]};
    int cross = vec1[0]*vec2[1] - vec1[1]*vec2[0];
    if(cross == 0) return 0;
    return (cross > 0) ? 1 : -1;
}

bool onSegment(int p1[], int p2[], int p3[]){
    if(min(p1[0], p2[0]) <= p3[0] && p3[0] <= max(p1[0], p2[0])
            && min(p1[1], p2[1]) <= p3[1] && p3[1] <= max(p1[1], p2[1])){
        return true;
    }
    return false;
}

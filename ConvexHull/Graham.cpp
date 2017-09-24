/*  Graham algorithm to compute convex hull
 *  Trick1: use cot instead of tan to deal with same x coordinate
 *  Trick2: use cross product to determine turn left or right
 *  Trick3: use dot product to determine vectors in same direction
 * */

#include<iostream>
#include<algorithm>
#include<vector>
#include<stack>

using namespace std;
class Node{
public:
    int x;
    int y;
    float angle;
    Node(int x, int y, float angle): x(x),y(y), angle(angle){}
};

struct cmp{
   bool operator() (Node* a, Node* b){
        if((a -> angle) - (b -> angle) < 1e-6){  // if two points have same direction
            return a -> y < b -> y;              // with lower y coordinate comes first
        }
        return (a -> angle) > (b -> angle);
   }
}cmpAngle;

struct compare{
    bool operator() (Node* a, Node* b){
        return (a->x) < (b->x);
    }
}cmpX;

stack<Node*> graham(vector<vector<int> > points);
int direction(Node* p1, Node* p2, Node* p3);

int main(){
    int input[][2] = {
        {-1, 2}, {0, 3}, {1, 0}, {1, 1}, {2, 0}, {3, 0}, {3, 1}, {3, 3}, {4, 1}, {4, 2}
    };
    int len = sizeof(input) / sizeof(*input);
    vector<vector<int> > points;
    for(int i = 0; i < len; ++i){
        vector<int> p(2);
        p[0] = input[i][0];
        p[1] = input[i][1];
        points.push_back(p);
    }

    stack<Node*> res = graham(points);
    while(!res.empty()){
        Node* n = res.top();
        cout << n->x << ", " << n->y<<endl;
        res.pop();
    }
    return 0;
}

stack<Node*> graham(vector<vector<int> > points){
    int len = points.size();
    vector<int> index;
    int minY = points[0][1], minIndex = 0;
    index.push_back(0);
    // find the point with lowest y coordinate and leftmost x coordiate
    for(int i = 1; i < len; ++i){
        if(points[i][1] == minY){
            index.push_back(i);
            if(points[i][0] < points[minIndex][0]){
                minIndex = i;
            }
        }else if(points[i][1] < minY){
            index.clear();
            index.push_back(i);
            minY = points[i][1];
            minIndex = i;
        }
    }
    vector<Node*> v;
    for(int n : index){
        v.push_back(new Node(points[n][0], points[n][1], 65536));
    }
    sort(v.begin(), v.end(), cmpX);
    stack<Node*> s;
    for(int i = 0; i < v.size(); ++i){
        s.push(v[i]);
    }
    v.clear();
    int minX = points[minIndex][0];
    // push all the nodes into vector for sorting
    for(int i = 0; i < len; ++i){
        if(points[i][1] == minY) continue;
        v.push_back(new Node(points[i][0], points[i][1], (float)(points[i][0] - minX)/(points[i][1] - minY)));
    }
    sort(v.begin(), v.end(), cmpAngle);

    // perform graham algorithm
    int idx = 0;
    while(s.size() < 3){
        s.push(v[idx++]);
    }
    Node* top;
    Node* nextTop;
    for(int i = idx; i < v.size(); ++i){
        top = s.top();
        s.pop();
        nextTop = s.top();
        while(direction(top, nextTop, v[i]) < 0){
            top = nextTop;
            s.pop();
            nextTop = s.top();
        }
        s.push(top);
        s.push(v[i]);
    }
    return s;
}

int direction(Node* p1, Node* p2, Node* p3){
    int vec1x = (p1->x) - (p2->x);
    int vec2x = (p3->x) - (p2->x);
    int vec1y = (p1->y) - (p2->y);
    int vec2y = (p3->y) - (p2->y);
    return vec1x * vec2y - vec1y * vec2x;   // negative means on right
}

This repository is created for some useful algorithms and data structures

# Algorithms
Array of Count-Min Sketches      (Estimate total number of occurrence within specific range in the data stream)

Bloom Filter

Bit Manipulation                 (Bit masking)

BKDR Hash Function               (Most popular 32-bit hash function for string)

Convex Hull                      (Find polygon with shortest perimeter)

Chinese Remainder Theory

Count-Min Sketch                 (Frequency Estimation approximate algorithm in data stream)

Dijkstra                         (Single point source, weigth >= 0)

Dynamic Programming              (also applied in probability & expectation)

Hungarian Algorithm              (matching for unweighted bipartite graph)

In-place Perfect Shuffle         (O(N) time complexity, O(1) space complexity)

Kadane Algorithm                 (find max sum subarray; 2D case see "Maximum Sum Rectangular Submatrix in Matrix" in youtube)

Karatsuba Algorithm              (Fast multiplication for large interger)

KMP                              (String match)

Lossy Count                      (Approximate algorithm for Top K frequency estimation in data stream)

Manacher                         (Find longest palindrome sub-string)

Moore's Voting / Iceberg Query   (n-1 elements appear more than 1/n)

Minimum Spanning Tree            (Kruskal, Prim)

MinMax                           (Game Strategy/ Simulation / Ad Hoc)

MaxFlow            

Morris traversal                 (O(N) time complexity and O(1) space complexity)

Two Pointers / Three Pointers    (circle in linkedlist, RGB problem)

QuickSort                        (Partition)

RadixSort                        (Counting Sort)

Reservoir Sampling               (Same probability) 

Sieve of Eratosthenes            (find all primes <= N)

Strassen Matrix Multiplication   (Divide into sub-matrix)

Sqrt Decomposition               (Bucket Method)

Sweep Line Algorithm             (Computational Geometry)

Tarjan Algorithm                 (Off-line Least Common Ancestor algorithm)

Topological Sort                 (See Leetcode topic)

Union Find                       (number of islands)



# Data Structure
Trie

Sparse Table ------ O(NlogN) preprocess  +  O(1) query      for RMQ

Segment Tree ------ O(N)   preprocess  +  O(logN) query     for RMQ

Binary Index Tree ------ O(NlogN) preprocess + O(logN) query + O(n) space for RMQ

Suffix Array / Suffix Tree

Min Stack



# Trick

(1)Rotate Right Shift = Three times reverse

(2)Tree PreOrder, InOrder, PostOrder non-recursive traverse

(3)File system is equivalent to multi-way tree (how to judge two file system have same topology?  see "Backup Integrity" problem, in Online Judge Hackrank)

(4)Expectation and Probability   (see SPOJ and UVa related problems)

(5)Median is related to minHeap and maxHeap with similar size. (maxHeap stores first half elements, minHeap stores second half)

(6)Computing components can be solved in reverse order by union-find (see ACIM Tryout #1 Problem G Artwork)

(7)use "mid = low + (high - low) / 2;" instead of "mid = (low + high) / 2" to avoid exceeding int range

(8) 


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.color;

import java.util.Arrays;
import java.util.Scanner;

/*
 *
 * @author asrire
 */
public class GraphColor {

    static int[][] sum;

    /*A utility function to check if the current color assignment
    is ok for vertex v*/
    private static boolean OK(int k, int[] color, int[][] graph) {
        for (int i = 1; i < k; i++) {
            if (graph[k][i] == 1 && color[k] == color[i]) {
                return false;
            }
        }
        return true;
    }

    /* using backtracking to solve the color problem 
    the time complexity for backtracking is O( (mV)^V)
    O(mv)if check only one */

    private static void graphColor(int[] color, int[][] graph, int v, int m, int[] Colorsum) {
        int k = 1;  //begin at the point 1 
        while (k >= 1) {
            color[k] += 1; //get the color 
            while (color[k] <= m && k <= v) {
                if (OK(k, color, graph)) {
                    break;
                } else {
                    color[k] += 1;  //if have conflict, add one color  
                }
            }

            if (color[k] <= m && k == v) {  // if search to the bottom vertices, output one color combo
                for (int i = 1; i <= k; i++) {
                    System.out.print("Vertices" + i + ":" + " Color " + color[i] + "  ");
                    for (int j = 1; j <= m; j++) {
                        if (color[i] == j) {
                            Colorsum[j] = Colorsum[j] + 1;
                            System.out.println("Color" + j + " have " + Colorsum[j] + " ");
                        }

                    }

                }
                break;

            } else if (color[k] <= m && k < v) {    //keep search in deep  
                k = k + 1;
            } else {
                color[k] = 0;   
                k = k - 1; //when color[k]>m or k>n， backtrack

            }

        }

    }

    /* v=6 m=3
    0 1 0 0 0 1
    1 0 1 1 0 1
    0 1 0 1 0 0
    0 1 1 0 1 0
    0 0 0 1 0 1 
    0 1 0 1 1 0

   v=8 m =3
   0 1 0 0 0 0 0 0 
   1 0 1 0 0 0 0 0 
   0 1 0 1 1 1 1 0 
   0 0 1 0 0 0 0 1
   0 0 1 0 0 0 0 0
   0 0 1 0 0 0 0 0
   0 0 1 0 0 0 0 1
   0 0 0 0 0 0 1 0
     */
    public static void main(String[] args) {
        int ColorSize = 10;
        int SIZE = 101;
        int[] color = new int[ColorSize];
        int[][] graph = new int[SIZE][SIZE];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the vertices v:");
        int v = sc.nextInt();
        System.out.print("Enter the color amount m：");
        int m = sc.nextInt();
        System.out.println("Enter Adjacency matrix :");
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        int[] Colorsum = new int[v];
        //initilization the color 
        for (int i = 1; i <= m; i++) {
            color[i] = 0;
        }

        graphColor(color, graph, v, m, Colorsum);
        /*      System.out.print("The color sequence:");
        for (int z = 1; z <= m; z++) {
            System.out.print(sum[z] + " ");
        }  */
        //System.out.print("\n"); 
        int len = Colorsum.length;
        quickSort(Colorsum, 0, len - 1);
        System.out.print("The max Color Sequence is:");
        for (int a = 0; a < Colorsum.length; a++) {
            if (Colorsum[a] != 0) {
                System.out.print(" " + Colorsum[a]);
            }
        }
    }

    public static void quickSort(int[] arr, int high, int low) {
        int h, l, pivot;
        h = high;
        l = low;
        pivot = arr[h];//Take the first element to the pivot 

        while (h < l) {//using recursive algr low>=high    
            while (h < l && pivot > arr[l])// if the right elemetn small than pivot, don't change, low move forward
            {
                l--;//
            }
            if (h < l) {
                arr[h] = arr[l];
                h++;
            }
            while (h < l && pivot < arr[h]) {
                h++;
            }
            if (h < l) {
                arr[l] = arr[h];
                l--;
            }
        }
        arr[h] = pivot;//put pivot to its position   

        if (high < h) {
            quickSort(arr, high, h - 1);// Do the recursive to the left side array
        }
        if (h < low) {
            quickSort(arr, h + 1, low);//Do the recursive to the right side array 
        }
    }
}

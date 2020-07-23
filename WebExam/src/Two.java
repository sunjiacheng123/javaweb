import java.util.Scanner;
/*
找出arr[i]左、右两边，离它最近的数字的下标
 */
public class Two {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                System.out.println(left(arr,i)+" "+right(arr,i));
            }
        }
    }

    private static int left(int[] arr, int i) {

        for(int j=i-1;j>0;j--){
            if(arr[j]<arr[i]){
                return j;
            }
        }
        return -1;
    }

    private static int right(int[] arr, int i) {
        for(int j=i;j<arr.length;j++){
            if(arr[j]<arr[i]){
                return j;
            }
        }
        return -1;
    }
}

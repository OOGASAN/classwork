public class Wp1 {
  public static void main(String args[]) {
    int[] a = {2,4,6,8,10,12};
    int[] b = new int[6];
    int[] c = new int[6];
    b = a;
    for (int i = 0; i < a.length; i++)
      c[i] = a[i];
    a[2] = c[5];
    c[2]++;
    System.out.println(a[2] + " " + b[2] + " " + c[2]);
  }
}
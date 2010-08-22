class PassByValueTest {
    public int[] swap (int arg1, int arg2) {
        int[] result = new int[2];
        int temp = arg1;
        arg1 = arg2;
        arg2 = temp;
        result[0] = arg1;
        result[1] = arg2;

        return result;
    }

    public static void main(String[] args) { 
        PassByValueTest pbvt = new PassByValueTest();
        int var1 = 1;
        int var2 = 2;
        System.out.println("var1: " + var1 + ", var2: " + var2);
        int[] swapped = new int[2];
        swapped = pbvt.swap(var1,var2);
        System.out.println("var1: " + swapped[0] + ", var2: " + swapped[1]);
//        pbvt.swap(var1,var2);
//        System.out.println("var1: " + var1 + ", var2: " + var2);
    }
}

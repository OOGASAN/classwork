class TestArrays {
    public static void main(String[] args) {
        String[] islands = new String[4];
        int[] index = new int[4];

        islands[0] = "Bermuda"; // 3
        islands[1] = "Figi";    // 1
        islands[2] = "Azores";  // 4
        islands[3] = "Cozumel"; // 2

        index[0] = 1;
        index[1] = 3;
        index[2] = 0;
        index[3] = 2;

        int y = 0;

        int ref;
        while (y < 4) {
            ref = index[y];
            y = y + 1;
            System.out.print("island = ");
            System.out.println(islands[ref]);
        }
    }
}

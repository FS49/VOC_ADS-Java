package miscellany;

public class SpecialSort {
    public static void specialSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tempInt = array[i];
                    array[i] = array[j];
                    array[j] = tempInt;
                }
            }   
        }
    }
    
    
    public static void main(String[] args) {
        int[] array = {7, 3, 5, 4, 8, 6, 2, 9, 1};
        
        specialSort(array);
        
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

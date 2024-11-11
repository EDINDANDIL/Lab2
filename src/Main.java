import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class Main {

    // метод, который сортирует массив
    public static void sortArr(char[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {

                if (Character.toLowerCase(array[i]) > Character.toLowerCase(array[j])) {

                    // Меняем местами слова, если они расположены в неправильном порядке
                    char z = array[i];
                    array[i] = array[j];
                    array[j] = z;

                } else if (Character.toLowerCase(array[i]) == Character.toLowerCase(array[j])) {
                    if (array[i] > array[j]) {
                        char z = array[i];
                        array[i] = array[j];
                        array[j] = z;

                    }
                }
            }
        }

    }

    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        // объявление / ввод всех переменных и структур.

        // ввод данных
        int N = in.nextInt();
        int M = in.nextInt();
        char[][] matrix = new char[N][M];

        // массив, куда будет записываться кол-во вхождений
        int[] alphabet = new int[26];
        int cnt_glas = 0;
        int cnt_sogl = 0;

        // считываем матрицу, параллельно считаю кол-во вхождений и распределяю по массивам гласные и согласные буквы
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = (char) System.in.read();

                char key = Character.toLowerCase(matrix[i][j]);

                // считаю кол-во вхождений буквы
                alphabet[key-'a'] += 1;

                // получаю длины массивов для их создания
                if (key =='a' || key == 'e' || key == 'i' || key == 'o' || key == 'u'){
                    cnt_glas += 1;
                } else{
                    cnt_sogl += 1;
                }
            }
        }

        // создаю массивы гласных и согласных букв
        char[] glas = new char[cnt_glas];
        char[] sogl = new char[cnt_sogl];

        // тут ищу самое частое вхождение
        int maxValue = 0;
        for (int i : alphabet) {
            if (i > maxValue) {
                maxValue = i;
            }
        }

        // вводим сдвиг для шифра Цезаря
        int K = in.nextInt();

        // начальный индекс каждого массива(согл и глас букв)
        int i_sogl = 0;
        int i_glas = 0;

        // распределяю по массивам буквы
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char key = Character.toLowerCase(matrix[i][j]);

                if (key=='a' || key=='e' || key=='i' || key=='o' || key=='u'){
                    glas[i_glas] = matrix[i][j];
                    i_glas++;
                } else{
                    sogl[i_sogl] = matrix[i][j];
                    i_sogl ++;
                }
            }
        }

        // сортируем матрицу, массивы согл и глас букв
        sortArr(sogl);
        sortArr(glas);
        for (int i = 0; i < N; i++){
            sortArr(matrix[i]);
        }

        // вывод исходной матрицы
        System.out.println("Исходная матрица");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print((matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Зашифрованная матрица");

        // вывод матрицы (зашифрованной)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print((char)(matrix[i][j]+K) + " ");
            }
            System.out.println();
        }
        System.out.println();

        // вывод массивов гл/согл букв
        System.out.println("Согласные буквы:"+Arrays.toString(sogl));
        System.out.println("Гласные буквы:"+Arrays.toString(glas));

        // самая частая буква
        System.out.print("Количество букв: ");
        for (int i = 0; i<alphabet.length; i++){
            if (alphabet[i]>0) System.out.print((char)(i+'a')+" = "+alphabet[i]+"; ");
        }
        System.out.println();

        //вывод самой частой буквы
        System.out.print("Самая частая(ые) буква(ы): ");
        for (int i = 0; i<alphabet.length; i++){
            if (alphabet[i]==maxValue){
                System.out.print((char)(i+'a')+" ");
            }
        }


    }
}


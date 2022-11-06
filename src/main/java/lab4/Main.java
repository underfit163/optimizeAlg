package lab4;

//Для заданного положительного числа выведите ближайшие наименьшее и наибольшее числа,
// которые имеют такое же количество единичных битов в двоичном представлении.
public class Main {

    public static void main(String[] args) {
        int n = 13948;
        System.out.println(n + ": " + Integer.toBinaryString(n));
        int max = getNext(n);
        System.out.println(max + ": " + Integer.toBinaryString(max));
        int min = getPrev(n);
        System.out.println(min + ": " + Integer.toBinaryString(min));
    }

    // наименьшее число, большее n
    public static int getNext(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        while (((c & 1) == 0) &&
                (c != 0)) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        if (c0 + c1 == 31 ||
                c0 + c1 == 0)
            return -1;

        int p = c0 + c1;
        //меняем крайний правый ноль на 1
        n |= (1 << p);

        // очищаем все биты справа от p
        n &= ~((1 << p) - 1);

        //Вставляем 1 справа от p
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }


    //Большее число, меньше, чем n
    public static int getPrev(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c == 0) return -1;

        while ((c & 1) == 0 && (c != 0)) {
            c0++;
            c >>= 1;
        }
        int p = c0 + c1;

        n &= ~(1 << p);

        n &= ~((1 << p) - 1);

        int mask = (1 << (c1 + 1)) - 1;

        n |= mask << (p - (c1 + 1));

        return n;
    }
}

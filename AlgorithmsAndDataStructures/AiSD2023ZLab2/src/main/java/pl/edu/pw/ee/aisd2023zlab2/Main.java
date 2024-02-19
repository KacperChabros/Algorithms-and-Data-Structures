package pl.edu.pw.ee.aisd2023zlab2;

public class Main {

    public static void main(String[] args) {
        HashListModular<Integer> hashList = new HashListModular<>(10);

        hashList.add(5);
        hashList.add(1);
        hashList.add(4);
        hashList.add(8);
        hashList.add(10);
        hashList.add(15);
        hashList.add(5);
        hashList.add(88);

        hashList.printTable();
        double lf = hashList.countLoadFactor();
        System.out.println(lf);
        System.out.println("-----------------------------");
        hashList.delete(4);
        hashList.delete(5);
        hashList.delete(15);
        hashList.printTable();
        System.out.println(hashList.countLoadFactor());
//        
//        System.out.println("aachen".hashCode());
//        
//        HashListMultiplicative<Integer> hashList = new HashListMultiplicative<>(10);
//        
//        hashList.add(100); //8
//        hashList.add(500); //0
//        hashList.add(4); //4
//        hashList.add(47); //0
//        hashList.add(115); //0
//        hashList.add(112); //2
//        hashList.add(120); //1
//        hashList.add(500); //zastąpi

//        
//    public void printTable() {
//        for (int i = 0; i < size; i++) {
//            System.out.print("Poziom " + i + ": ");
//            Elem<T> currentElem = hashElements[i];
//
//            while (currentElem != nil) {
//                System.out.print(currentElem.value + "->");
//                currentElem = currentElem.next;
//            }
//            System.out.println("nil\n");
//        }
//    }
//        hashList.printTable();
//        System.out.println(hashList.countLoadFactor());
//         System.out.println(hashList.get(199));
//         System.out.println(hashList.get(112));
//         System.out.println(hashList.get(115));
//         System.out.println(hashList.get(47));
//         System.out.println(hashList.get(500));
//         System.out.println("-----------------------");
//         hashList.delete(101111);
//         hashList.delete(47);
//         
//         hashList.delete(112);
//         hashList.delete(500);
//         hashList.delete(115);
//         hashList.delete(47);
//         hashList.printTable();
//         System.out.println(hashList.countLoadFactor());
//    }
    }
}

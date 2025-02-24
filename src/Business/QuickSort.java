package Business;

import java.util.List;

/**
 * QuickSort is used to sort the music tables in ascending and descending order.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class QuickSort {
    /**
     * Method to start the QuickSort.
     *
     * @param musicList Music playlist.
     * @param i         Top of the list.
     * @param j         End of the list.
     * @param option    Option, if ascending or descending.
     */
    public static void quickSort(List<Music> musicList, int i, int j, int option) {
        // Mientras los dos ejes no se crucan sigue buscando
        if (i < j) {
            int p = particion(musicList, i, j, option);
            quickSort(musicList, i, p, option);   // ordenamos subarray izquierdo
            quickSort(musicList, p + 1, j, option);   // ordenamos subarray izquierdo
        }
    }

    /**
     * List partition.
     *
     * @param musicList Music playlist.
     * @param i         Top of the list.
     * @param j         End of the list.
     * @param option    Option, if ascending or descending.
     * @return Return position in the list.
     */
    private static int particion(List<Music> musicList, int i, int j, int option) {
        // l realiza la busqueda desde la izquierda
        int l = i;
        // r realiza la busqueda desde la derecha
        int r = j;
        // Calcular en número medio, para saber en que posicion empezar
        int medio = i + (j - i) / 2;
        // Elegir el pivote, en este caso empezamos en por el medio
        String pivote = musicList.get(medio).getName();

        // Bucle infinito hasta que se cruzan
        while (true) {
            // Miramos cada elemento desde la izquierda
            // Mientras que sea mayor que el pivote continúa avanzando el índice

            // Descendete
            if (option == 0) {
                while (musicList.get(l).getName().compareTo(pivote) > 0) {
                    // Busca elemento > pivote
                    l++;
                }
                // Miramos cada elemento desde la derecha
                // Mientras que sea menor que el pivote continúa avanzando el índice
                while (musicList.get(r).getName().compareTo(pivote) < 0) {
                    // Busca elemento < pivote
                    r--;
                }
            }
            // Ascendente
            if (option == 1) {
                while (musicList.get(l).getName().compareTo(pivote) < 0) {
                    l++;
                }
                while (musicList.get(r).getName().compareTo(pivote) > 0) {
                    r--;
                }
            }

            // Si el índice izquierdo es mayor o igual al índice derecho,
            // eso quiere decir que no es necesario hacer ningun intercambio de variable
            if (l >= r) {
                // Devolvemos la posicion derecha, que es el índice donde nos hemos quedado
                return r;
            }

            // En caso, que el índice izquierdo no supera el índice derecho ni lo alcanza
            // con esto entendimos que el valor no esta ordenado y necesita un orden
            // El metodo swap, lo que hace es intercambiar los valores de la array para qui esten en orden
            swap(musicList, l, r);

            // Seguimos avanzando los índices
            l++;
            r--;
        }
    }

    /**
     * Method to swap the current element and be able to sort it.
     *
     * @param musicList Music playlist.
     * @param l         Search from the left.
     * @param r         Search from the right.
     */
    private static void swap(List<Music> musicList, int l, int r) {
        Music s = musicList.get(l);
        musicList.set(l, musicList.get(r));
        musicList.set(r, s);
    }
}

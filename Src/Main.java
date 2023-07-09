import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        ArrayList <String[]> save = new ArrayList<String[]>();
        String temp = new String();
        int width = 0;
        int large = 0;

        //Ascentos con hexadecimal
        char O = 211;
        char e = 233;

        //INDICACIONES
        System.out.println("\n\t\t---DESCOMPOSICI"+O+"N LU PARA MATRICES CUADRADAS---");
        System.out.println("\n\tIngresa el sistema de ecuaciones de la siguiente forma:");
        System.out.println("Matriz de coeficientes: \n[x1 x2 x3]\n[x1 x2 x3]\n[x1 x2 x3]");
        System.out.println("Vector de t"+e+"rminos independientes: \n[a]\n[b]\n[c]");                                 

        //MATRIZ DE COEFICIENTES [A]
        System.out.println("\nIngresa la matriz de coeficientes del sistema de ecuaciones:");

        while(save.size() < 3)
        {
            temp = stdin.nextLine();
            if (temp.equals("")) break;
            save.add(large, temp.split(" "));
            large++;
        } 
        width = save.get(0).length;
       
        float A [][] = new float [large][width];
        fillmatrix(save, A, large, width);

        System.out.println("\n");
        System.out.println("La matriz de coeficientes ingresada es:\n");
        printmatrix(A, large, width);

        //VECTOR DE TÉRMINOS INDEPENDIENTES [b]
        System.out.println("\nIngresa el vector de t"+e+"rminos independientes del sistema de ecuaciones:");

        int c = 0;
        while(c < width)
        {
            temp = stdin.next();
            save.add(temp.split(" "));
            c++;
        }
        stdin.close();
        temp = null;

        float b [] = new float [width];
        fillvector(save, b, width);
        save = null;

        System.out.println("El vector de t"+e+"rminos independientes ingresado es:\n");
        printvector(b, width);
        System.out.println("\n");

        //VECTOR RESULTANTE [X]
        float X [] = new float [width];
        LU.descomposicion(A, b, X, large, width);
        System.out.println("El resultado del sistema de ecuaciones:");
        printvectorresultante(X, width);
        System.out.println("\n");

    }
    
    public static void fillmatrix(ArrayList <String[]> helper, float X [][], int large, int width)
    {
        for(int i = 0; i < large ; i++){
            for(int j = 0; j < width ; j++){
                X[i][j] = Float.parseFloat(helper.get(i)[j]);
            }
        }
        helper.clear();
    }

    public static void fillvector(ArrayList <String[]> helper, float X [], int width)
    {
        for (int i = 0; i < width; i++){
            for(int j = 0; j < width ; j++){
                X[i] = Float.parseFloat(helper.get(i)[0]);
            }
        }
    }

    public static void printmatrix(float X[][], int large, int width)
    {
        for(int i = 0; i < large ; i++){
            for(int j = 0; j < width ; j++){
                if(j == 0)
                {
                    System.out.print("["+X[i][j] + " ");  
                } 
                if (j == 2){
                    System.out.print(""+X[i][j]+"]");
                } 
                else if (j != 0 & j!=2){
                    System.out.print(""+X[i][j] + " ");
                  }
            }
            System.out.println("");
            }
    }

    public static void printvector(float X[], int width)
    {
        for(int i = 0; i < width; i++){
            System.out.println("[" + X[i] + "]");
        }
    }

    public static void printvectorresultante(float X[], int width)
    {
        for(int i = 0; i < width; i++){
            System.out.println("X"+(i+1)+" = "+"[" + X[i] + "]");
        }
    }
}

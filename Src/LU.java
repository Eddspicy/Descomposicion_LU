public class LU {
    public static void descomposicion(float A[][], float b[], float X[], int large, int width)
    {
        //INICIALIZACION DE MATRIZ TRIANGULAR [L]
        float L[][] = new float [large][width];
        for(int i = 0; i < large ; i++){
            for(int j = 0; j < width ; j++){
                if(i == j)
                {
                    L[i][j] = 1;
                } else{
                    L[i][j] = 0;
                }
            }
        }

        //INICIALIZACION DE MATRIZ TRIANGULAR [U]
        float U[][] = new float [large][width];
        for(int i = 0; i < large ; i++){
            for(int j = 0; j < width ; j++){
                U[i][j] = 0;
            }
        }

        //DESCOMPOSICION DE LA MATRIZ [A] PARA GENERAR LOS ELEMENTOS DE [L][U]
        
        //1er renglon de U
        for (int i = 0; i < width; i++){
            U[0][i] = A[0][i];
        }

        //1ra columna de L
        for (int i = 1; i < large; i++){
            L[i][0] = A[i][0] / U[0][0];
        }

        // RENGLONES DE [2,n-1] DE [U] Y COLUMNAS DE [2,n-1] DE [L]
        int N = 3;
        int I = 1;
        int J = 1;
        
        while (I != width-1 & J != large-1)
        {
        //renglones de [U] apartir del segundo al penúltimo
        while(J < N)
        {
            U[I][J] = A[I][J];
            for(int k1 = 0; k1 <= I-1; k1++){
                U[I][J] = U[I][J]-L[I][k1]*U[k1][J]; 
            }
            J++;
        }

        J = J-1;

        //columnas de [L] apartir de la segunda
        while (I < N)
        {
            L[J][I] = A[J][I] / U[I][I];
            for(int k2 = 0; k2 <= J-1; k2++){
                L[J][I] = L[J][I]-((L[J][k2]*U[k2][I])/U[I][I]);
            }
            I++;
        }

        I = I-1;
        }
   
        //ULTIMO RENGLON DE U
        U[N-1][N-1]= A[N-1][N-1];
        for (int k3 = 0; k3 <= N-2; k3++){
            U[N-1][N-1] = U[N-1][N-1]-L[N-1][k3]*U[k3][N-1];
        }

        //OBTENCIÓN DEL VECTOR [d]
        //[L][d]=[B]
        float d [] = new float [width];
        for (int i = 0; i < width; i++){
            d[i] = 0;
        }

        int i = 0;
        while(i < N)
        {
            d[i] = b[i];
            for(int k4 = 0; k4 < i; k4++){
                d[i] = d[i] - L[i][k4]*d[k4];
            }
            i++;
        }

        //OBTENCIÓN DEL VECTOR RESULTANTE[X]
        //[U][X]=[d]
        for (int j = 0; j < width; j++){
            X[j] = 0;
        }
        
        X[width-1]= d[width-1] / U[large-1][width-1];

        int xi = width-2;
        while(0 <= xi)
        {
            X[xi] = d[xi] / U[xi][xi];
            for (int k5 = xi+1; k5 <= N-1; k5++){
                X[xi] = X[xi] - ((U[xi][k5]*X[k5]) / U[xi][xi]);
            }
            xi = xi - 1;
        }
    }
}
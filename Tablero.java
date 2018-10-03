public class Tablero
{
    char[] tabla;
    Nodo arbol;
    Tablero(){
       tabla=new char[9];
       arbol=new Nodo();
       /*
       tabla[0]='-';
       tabla[1]='O';
       tabla[2]='X';
       tabla[3]='-';
       tabla[4]='O';
       tabla[5]='X';
       tabla[6]='-';
       tabla[7]='X';
       tabla[8]='-';
       System.out.println("asi sale-->"+mejorMovimiento(tabla));*/
       
    }
    int mejorMovimiento(char tablero[]){
        tabla=tablero;
        for(int i=0;i<tablero.length;i++){
            arbol.tablero[i]=tabla[i];
        }
        IA(arbol);
        return arbol.mejorMovimiento;
    }
    
    int movDisponibles(char tablero[]){
        int res=0;
        for(int i=0;i<tablero.length;i++){
            if(tablero[i]=='-'){
                res++;
            }
        }
        return res;
    }
    int[] posicionesVacias(char tablero[]){
        int posiciones[]=new int[movDisponibles(tablero)];
        int aux=0;
        for(int i=0;i<tablero.length;i++){
            if(tablero[i]=='-'){
                posiciones[aux]=i;
                aux++;
            }
        }
        return posiciones;
    }
    void IA(Nodo raiz){
        int max,min;
        int numMov=movDisponibles(raiz.tablero);
        int[] posvacias=posicionesVacias(raiz.tablero);
        raiz.hijos=new Nodo[numMov];
        int winner=ganadorAbsoluto(raiz.tablero);
        if(winner!=0 || numMov==0){
            raiz.ganador=winner;
        }else{
            for(int i=0;i<numMov;i++){
                raiz.hijos[i]=new Nodo();
                for(int j=0;j<9;j++){
                    raiz.hijos[i].tablero[j]=raiz.tablero[j];
                }
                if(raiz.turno){
                    raiz.hijos[i].tablero[posvacias[i]]='O';
                }else{
                    raiz.hijos[i].tablero[posvacias[i]]='X';
                }
                raiz.hijos[i].turno = !raiz.turno;
                raiz.hijos[i].indiceMov = posvacias[i];
                IA(raiz.hijos[i]);
            }
            if(!raiz.turno){
                raiz.ganador=Max(raiz);
            }else{
                raiz.ganador=Min(raiz);
            }
        }
    }
    int Max(Nodo raiz){
        int max=-10;
            for(int i=0;i<raiz.hijos.length;i++){
                if(raiz.hijos[i].ganador > max){
                    max=raiz.hijos[i].ganador;
                    raiz.mejorMovimiento=raiz.hijos[i].indiceMov;
                    if(max==1){
                        break;
                    }
                }
            }
        raiz.hijos=null;
        return max;
    }
    int Min(Nodo raiz){
        int min=10;
             for(int i=0;i<raiz.hijos.length;i++){
                if(raiz.hijos[i].ganador < min){
                    min=raiz.hijos[i].ganador;
                    raiz.mejorMovimiento=raiz.hijos[i].indiceMov;
                    if(min==-1){
                        break;
                    }
                }
             }
        raiz.hijos=null;
        return min;
    }
    int ganadorAbsoluto(char tablero[]){
        int win=0;
        char aux='-';
        if ( tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != '-' )
            aux=tablero[0];
        else if ( tablero[3] == tablero[4] && tablero[3] == tablero[5]  && tablero[3] != '-'  )
             aux=tablero[3];
        else if ( tablero[6] == tablero[7] && tablero[6]== tablero[8]  && tablero[6] != '-' )
             aux=tablero[6];        
        else if( tablero[0] == tablero[3] && tablero[0] == tablero[6]  && tablero[0] != '-' )
             aux=tablero[0];
        else if ( tablero[1] == tablero[4] && tablero[1] == tablero[7]  && tablero[1] != '-'  )
             aux=tablero[1];
        else if ( tablero[2] == tablero[5] && tablero[2] == tablero[8]  && tablero[2] != '-' )
             aux=tablero[2];
        else if ( tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] !='-' )
             aux=tablero[0];
        else if ( tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != '-' )
             aux=tablero[2];
        if(aux=='X'){
            win=1;
        }else if( aux=='O'){
            win=-1;
        }
        return win;
    }
}

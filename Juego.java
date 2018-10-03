import java.util.*;
public class Juego{
 static boolean maquina=false;
 public static void main(String arg[]){
        Tablero tabla=new Tablero();
        char tablero[]=new char[9];
        Scanner sc = new Scanner(System.in);
        tablero=relleno(tablero);
        int visto=0;
        boolean emp=false;
        while(true){
            int ganador=tabla.ganadorAbsoluto(tablero);
            if(ganador==-1){
                System.out.println("Ganó la Máquina");
                System.out.println("            ");
                System.out.println("    ¡ |Lose|");
                System.out.println("  [´o´] /  ");
                System.out.println("  /[_]     ");
                System.out.println("   ] [     ");
                break;
            }else if(ganador==1){
                System.out.println("Ganaste Humano  >_<'");
                break;
            }else if(emp==true){
                System.out.println("<><><><><><>");
                System.out.println(" ¡ Empate !");
                System.out.println("<><><><><><>");
                break;
            }
            System.out.println("Introduce una posición:");
            if(visto==0){
                System.out.println("0 | 1 | 2");
                System.out.println("-- --- --");
                System.out.println("3 | 4 | 5");
                System.out.println("-- --- --");
                System.out.println("6 | 7 | 8");
                visto=1;
            }
            int humano=sc.nextInt();
            tablero=marcarH(humano,tablero);
            printer(tablero);
            if(maquina==true){
                int ia=tabla.mejorMovimiento(tablero);
                tablero=marcarM(ia,tablero);
                printer(tablero);
                maquina=false;
            }
            emp=empate(tablero);
        }
 }
 static boolean empate(char tablero[]){
    boolean res=false;
    int cont=0;
    for(int i=0;i<tablero.length;i++){
        if(tablero[i]!='-'){
            cont++;
        }     
    }
    if(cont==9){
        res=true;
    }
    return res;
 }
 static char[] marcarM(int pos,char tablero[]){
    char tab[]=tablero;
    if(tablero[pos]=='-'){
     tab[pos]='O';
    }
    return tab;
 }
 static char[] marcarH(int pos,char tablero[]){
    char tab[]=tablero;
    if(tablero[pos]=='-'){
     tab[pos]='X';
     maquina=true;
    }else{
     System.out.println("--> introduzca una posicion no marcada!");
    }
    return tab;
 }
 static char[] relleno(char tablero[]){
            char tab[]=tablero;
            for(int i=0;i<tab.length;i++){
                    tab[i]='-';
            }
            return tab;
 }   
 static void printer(char tablero[]){
     for(int i=0;i<9;i++){
         if(i==2||i==5||i==8){
             System.out.print(tablero[i]+"\n");
             if(i!=8){
                 System.out.println("-- --- --");
             }
         }else{
             System.out.print(tablero[i]+" | ");
         }
     }
     System.out.println("");
 }
}
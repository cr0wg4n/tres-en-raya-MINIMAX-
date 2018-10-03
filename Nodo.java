
public class Nodo
{
    Nodo hijos[];
    char tablero[];
    int indiceMov;
    boolean turno=true; //true para AI y false para Humano
    int ganador=0;
    int mejorMovimiento;
    Nodo(){
        tablero=new char[9];
    }
}

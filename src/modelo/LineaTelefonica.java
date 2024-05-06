
package modelo;

/**
 *
 * @author YESICA
 */
public class LineaTelefonica {
    protected int numeroLlamadas;
    protected int numeroMinutos;
    private double costoLlamadas;
    
public LineaTelefonica(){
    numeroLlamadas=0;
    numeroMinutos=0;
    costoLlamadas=0;
}
public void reiniciar(){
    numeroLlamadas=0;
    numeroMinutos=0;
    costoLlamadas=0;
    
}
public double darCostoLlamadas(){
    return costoLlamadas;
}
public int darNumeroLlamadas(){
    return numeroLlamadas;
}
public void agregarLlamadaCelular(int minutos){
    // una llamadas mas
    numeroLlamadas= numeroLlamadas +1;
    //suma los minutos consumidos
    numeroMinutos=numeroMinutos + minutos;
    // suma el costo por minuto(costo por minuto 100)
    costoLlamadas=costoLlamadas + (minutos *100);
}
public int darNumeroMinutos(){
    return numeroMinutos;
    
}
public void modificarCostoLlamada(double pCostoLlamadas){
    costoLlamadas=pCostoLlamadas;
}
public void agregarLlamadaLocal(int minutos){
    // una llamada mas
    numeroLlamadas=numeroLlamadas +1;
    // suma los minutos consumidos
    numeroMinutos=numeroMinutos + minutos;
    //suma el costo por minuto 30
    costoLlamadas= costoLlamadas+(minutos*30);
    
}
public void agregarLlamadaLargaDistancia(int minutos){
    numeroLlamadas=numeroLlamadas +1;
    numeroMinutos=numeroMinutos + minutos;
    costoLlamadas=costoLlamadas +( minutos * 150);
    
}

}

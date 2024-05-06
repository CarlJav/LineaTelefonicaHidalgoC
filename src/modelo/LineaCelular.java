
package modelo;


public class LineaCelular extends LineaTelefonica {
    private int numeroMinutosLocal;
    private int numeroMinutosCelular;
    private double saldoDisponibleLocal;
    private int numeroMinutosLargaDistancia;
     private double saldoDisponible;
     
    public LineaCelular(){
        super();
        saldoDisponibleLocal=50000;
        numeroMinutosLocal=0;
        numeroMinutosCelular=0;
    }
    public int darNumeroMinutosLocal(){
        return numeroMinutosLocal;
    }
    public int darNumeroMinutosCelular(){
        return numeroMinutosCelular;
    }
    public double darSaldoDisponibleLocal(){
        return saldoDisponibleLocal;
    }
    public double calcularCostoLlamadasLocal(){
        return numeroMinutosLocal * 20;
    }
    public double darSaldoDisponible(){
      return saldoDisponible;
  }
    public void agregarLlamadLocal(int minutos){
        //una llamada se modifica el atributo protegido heredado
        numeroLlamadas=numeroLlamadas +1;
        //suma los minutos consumidos
        numeroMinutos= numeroMinutos+minutos;
    
    //suma el costo por minuto :20
    //se obtine y modifica el atributo privado heredado atraves de los metodos
    double nuevoTotalLlamadas=darCostoLlamadas()+(minutos *20);
    modificarCostoLlamada(nuevoTotalLlamadas);
    // suma los minutos locales consumidos
    numeroMinutosLocal=numeroMinutosLocal + minutos;
    //disminuye el saldo disponible para realizar llamadas locales
    saldoDisponibleLocal=saldoDisponibleLocal - (minutos*20);
    
}
    public void agregarLlamaCelular(int minutos){
        //se invoca el metodo agregarLlamadaCelular de la clase heredada atraves de
        //esta invocacion incrementa en 1 numeroDeLlamaas
        super.agregarLlamadaCelular(minutos);
        modificarCostoLlamada(darCostoLlamadas()-minutos*989);
        //suma los minutos a celular consumido
        numeroMinutosCelular=numeroMinutosCelular+minutos;
        
    }
 public void agregarLlamadaLargaDistancia(int minutos) {
    super.agregarLlamadaLargaDistancia(minutos);
    modificarCostoLlamada(darCostoLlamadas() - minutos * 100);
    numeroMinutosLargaDistancia += minutos;
}
    public void reiniciar(){
        super.reiniciar();
        numeroMinutosLocal=0;
        numeroMinutosCelular=0;
        saldoDisponibleLocal=50000;
    }
}

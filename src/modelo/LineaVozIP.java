/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class LineaVozIP extends LineaTelefonica{
    private int numeroMinutosLargaDistancia;
    private int numeroMinutosCelular;
    private double saldoDisponible;

    public LineaVozIP(){
    super();
    numeroMinutosLargaDistancia=0;
    numeroMinutosCelular=0;
    saldoDisponible=100000;
}
  public int darNumeroMinutosLargaDistancia(){
      return numeroMinutosLargaDistancia;
  }
  public int darNumeroMinutosCelular(){
      return numeroMinutosCelular;
  }
  public double darSaldoDisponible(){
      return saldoDisponible;
  }
  public void agregarLlamadaCelular(int minutos){
      super.agregarLlamadaCelular(minutos);
      //cuesta 7 el minuto antes era 999
      double costo=darCostoLlamadas()-(minutos*992);
      modificarCostoLlamada(costo);
      //agrega los minutos
      numeroMinutosCelular=numeroMinutosCelular+minutos;
      saldoDisponible=(saldoDisponible - costo);
  }
  public void agregarLlamadaLargaDistancia(int minutos){
      super.agregarLlamadaLargaDistancia(minutos);
      // sale a 80 antes era 380
      double costo=darCostoLlamadas()-(minutos*300);
      numeroMinutosLargaDistancia=numeroMinutosLargaDistancia+minutos;
      modificarCostoLlamada(costo);
      saldoDisponible=saldoDisponible-costo;
      
  }
  public void reiniciar(){
      super.reiniciar();
      numeroMinutosLargaDistancia=0;
      numeroMinutosCelular=0;
      saldoDisponible=100000;
  }
      
}

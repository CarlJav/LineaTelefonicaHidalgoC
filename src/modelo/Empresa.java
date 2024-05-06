/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author YESICA
 */
public class Empresa {
 

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    private LineaTelefonica linea1;
    private LineaTelefonica linea2;
    private LineaTelefonica linea3;
    private LineaCelular lineaCelular1;  
    private LineaVozIP lineaVozIP1;
    public Empresa( )
    {
        linea1 = new LineaTelefonica( );
        linea2 = new LineaTelefonica( );
        linea3 = new LineaTelefonica( );
        lineaCelular1 = new LineaCelular( );
        lineaVozIP1 = new LineaVozIP();        
    }

    public LineaTelefonica darLinea1( )
    {
        return linea1;
    }

    public LineaTelefonica darLinea2( )
    {
        return linea2;
    }

    public LineaTelefonica darLinea3( )
    {
        return linea3;
    }

    public LineaCelular darLineaCelular1( )
    {
        return lineaCelular1;
    }

    public LineaVozIP darLineaVozIP1( )
    {
    	return lineaVozIP1;
    }

    public void agregarLlamadaLocalLinea1( int minutos )
    {
        linea1.agregarLlamadaLocal( minutos );
    }

    public void agregarLlamadaLocalLinea2( int minutos )
    {
        linea2.agregarLlamadaLocal( minutos );
    }

    public void agregarLlamadaLocalLinea3( int minutos )
    {
        linea3.agregarLlamadaLocal( minutos );
    }

    public boolean agregarLlamadaLocalLineaCelular1( int minutos )
    {
    	if(lineaCelular1.darSaldoDisponibleLocal() < minutos*20)
    	{
    		return false;
    	}
    	else
    	{  	
    		lineaCelular1.agregarLlamadaLocal( minutos );
    		return true;
    	}
    }    
    
    /**
     * Agrega una llamada local a la l�nea telef�nico VozIP 1 <br>
     * <b>post: </b> Se agrega la llamada a la l�nea VozIP 1
     * @param minutos N�mero de minutos de la llamada. minutos > 0.
     */
    public void agregarLlamadaLocalLineaVozIP1( int minutos )
    {
    	//TODO Completar el m�todo segun la documentaci�n.

    	if(lineaVozIP1.darSaldoDisponible() > minutos*35)
    	{
    		lineaVozIP1.agregarLlamadaLocal(minutos);
    	}
    	
    }    

    public void agregarLlamadaLargaDistanciaLinea1( int minutos )
    {
        linea1.agregarLlamadaLargaDistancia( minutos );
    }

    public void agregarLlamadaLargaDistanciaLinea2( int minutos )
    {
        linea2.agregarLlamadaLargaDistancia( minutos );
    }

    public void agregarLlamadaLargaDistanciaLinea3( int minutos )
    {
        linea3.agregarLlamadaLargaDistancia( minutos );
    }

    public boolean agregarLlamadaLargaDistanciaLineaVozIP1( int minutos )
    {
    	//TODO Completar el metodo segun la documentacion.
    	if(lineaVozIP1.darSaldoDisponible() > minutos*80)
    	{
    		lineaVozIP1.agregarLlamadaLargaDistancia(minutos);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }     

    public void agregarLlamadaCelularLinea1( int minutos )
    {
        linea1.agregarLlamadaCelular( minutos );
    }

    public void agregarLlamadaCelularLinea2( int minutos )
    {
        linea2.agregarLlamadaCelular( minutos );
    }

    public void agregarLlamadaCelularLinea3( int minutos )
    {
        linea3.agregarLlamadaCelular( minutos );
    }

    public void agregarLlamadaCelularLineaCelular1( int minutos )
    {
        lineaCelular1.agregarLlamadaCelular( minutos );
    }

    public boolean agregarLlamadaCelularLineaVozIP1( int minutos )
    {
    	//TODO Completar el metodo segun la documentacion.
    	if(lineaVozIP1.darSaldoDisponible() > minutos*7)
    	{
    		lineaVozIP1.agregarLlamadaCelular(minutos);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }      

    public int darTotalNumeroLlamadasDesdeLineasNoAlternativas( )
    {
        int total = linea1.darNumeroLlamadas( ) + linea2.darNumeroLlamadas( ) + linea3.darNumeroLlamadas( );
        return total;
    }

    public int darTotalNumeroLlamadasDesdeLineasAlternativas()
    {
    	//TODO Completar el metodo segun la documentaci�n.
    	int tot = lineaCelular1.darNumeroLlamadas() + lineaVozIP1.darNumeroLlamadas();
    	
    	return tot;
    }

    public int darTotalMinutosDesdeLineasNoAlternativas( )
    {
        int total = linea1.darNumeroMinutos( ) + linea2.darNumeroMinutos( ) + linea3.darNumeroMinutos( );
        return total;
    }

    public int darTotalMinutosDesdeLineasAlternativas()
    {
    	//TODO Completar el metodo segun la documentacion.
    	int tot = lineaCelular1.darNumeroMinutos() + lineaVozIP1.darNumeroMinutos();
    	return tot;
    	
    }

    public double darTotalCostoLlamadasDesdeLineasNoAlternativas( )
    {
        double total = linea1.darCostoLlamadas( ) + linea2.darCostoLlamadas( ) + linea3.darCostoLlamadas( );
        return total;
    }

    public double darTotalCostoLlamadasDesdeLineasAlternativas()
    {
    	//TODO Completar el metodo segun la documentacion.
    	double tot = lineaCelular1.darCostoLlamadas() + lineaVozIP1.darCostoLlamadas();
    	return tot;
    }

    public double darCostoPromedioMinutoDesdeLineasNoAlternativas( )
    {
        double promedio = darTotalCostoLlamadasDesdeLineasNoAlternativas( ) / darTotalMinutosDesdeLineasNoAlternativas( );
        return promedio;
    }

    public double darCostoPromedioMinutoDesdeLineasAlternativas( )
    {
        double promedio = darTotalCostoLlamadasDesdeLineasAlternativas( ) / darTotalMinutosDesdeLineasAlternativas( );
        return promedio;
    }

    public void reiniciarLineasNoAlternativas( )
    {
        linea1.reiniciar( );
        linea2.reiniciar( );
        linea3.reiniciar( );
    }

    public void reiniciarLineasAlternativas()
    {
    	lineaCelular1.reiniciar();
    	lineaVozIP1.reiniciar();
    }


}



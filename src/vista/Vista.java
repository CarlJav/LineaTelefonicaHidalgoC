/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;



import modelo.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;


import modelo.LineaCelular;
import modelo.Empresa;
import modelo.LineaVozIP;
import modelo.LineaTelefonica;



public class Vista extends javax.swing.JFrame {
private Empresa empresa;
  
    public Vista() {
        initComponents();
         empresa = new Empresa( );
         actualizar();
    }
     /**
     * Agrega una llamada a una lï¿½nea telefï¿½nica <br>
     * <b>pre: </b> 1 >= numeroLinea >= 3 <br>
     * @param numeroLinea Numero de la lï¿½nea telefï¿½nica
     */
    public void agregarLlamada( int numeroLinea )
    {
        //
        //Pregunta el numero de minutos
        String minutos = JOptionPane.showInputDialog( this, "Numero de Minutos hablados:" );
        try
        {
            if( minutos != null )
            {
                int min = Integer.parseInt( minutos );
                if( min <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "El numero de minutos debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE );
                    return;
                }


                Object[] possibilities = new Object[0];

                if(numeroLinea < 4 || numeroLinea == 5){
                	possibilities = new Object[3];
                	possibilities[0] = "Local"; possibilities[1] = "Larga distancia"; possibilities[2] = "Celular";
                }
                else
                {
                	possibilities = new Object[2];
                	possibilities[0] = "Local"; possibilities[1] = "Celular";

                }


                String tipo = ( String )JOptionPane.showInputDialog( this, "Tipo de llamada:", "Tipo", JOptionPane.QUESTION_MESSAGE, null, possibilities, "Local" );
                if( tipo != null )
                {
                    if( numeroLinea == 1 )
                    {
                        if( "Local".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLocalLinea1( min );
                        }
                        else if( "Larga distancia".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLargaDistanciaLinea1( min );
                        }
                        else if( "Celular".equals( tipo ) )
                        {
                            empresa.agregarLlamadaCelularLinea1( min );
                        }
                    }
                    else if( numeroLinea == 2 )
                    {
                        if( "Local".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLocalLinea2( min );
                        }
                        else if( "Larga distancia".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLargaDistanciaLinea2( min );
                        }
                        else if( "Celular".equals( tipo ) )
                        {
                            empresa.agregarLlamadaCelularLinea2( min );
                        }
                    }
                    else if( numeroLinea == 3 )
                    {
                        if( "Local".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLocalLinea3( min );
                        }
                        else if( "Larga distancia".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLargaDistanciaLinea3( min );
                        }
                        else if( "Celular".equals( tipo ) )
                        {
                            empresa.agregarLlamadaCelularLinea3( min );
                        }
                    }
                    else if( numeroLinea == 4 )
                    {

                        if( "Local".equals( tipo ) )
                        {
                        	boolean resultado;
                            resultado = empresa.agregarLlamadaLocalLineaCelular1( min );
                            if(!resultado)
                            	JOptionPane.showMessageDialog(this, "No hay saldo suficiente para esta llamada local", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if( "Celular".equals( tipo ) )
                        {
                            empresa.agregarLlamadaCelularLineaCelular1( min );
                        }
                    }else if( numeroLinea == 5 )
                    {

                        if( "Local".equals( tipo ) )
                        {
                            empresa.agregarLlamadaLocalLineaVozIP1( min );
                        }
                        else if( "Larga distancia".equals( tipo ) )
                        {

                            boolean resultado;
                            resultado = empresa.agregarLlamadaLargaDistanciaLineaVozIP1( min );
                            if(!resultado)
                            	JOptionPane.showMessageDialog(this, "No hay saldo suficiente para realizar llamadas desde esta lï¿½nea", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        else if( "Celular".equals( tipo ) )
                        {
                        	boolean resultado;
                            resultado = empresa.agregarLlamadaCelularLineaVozIP1( min );
                            if(!resultado)
                            	JOptionPane.showMessageDialog(this, "No hay saldo suficiente para realizar llamadas desde esta lï¿½nea", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    actualizar( );
                }
            }
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "El numero de minutos es invalido", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Reinicia las lï¿½neas telefï¿½nicas
     */
    private void actualizar() {
    double costoTotal = 0.0;
    int totalLlamadas = 0;
    int totalMinutos = 0;

    // Actualizar líneas básicas
    actualizarLinea1(empresa.darLinea1());
    actualizarLinea2(empresa.darLinea2());
    actualizarLinea3(empresa.darLinea3());

    // Sumar costos, llamadas y minutos de las líneas básicas
    costoTotal += empresa.darLinea1().darCostoLlamadas();
    costoTotal += empresa.darLinea2().darCostoLlamadas();
    costoTotal += empresa.darLinea3().darCostoLlamadas();
    totalLlamadas += empresa.darLinea1().darNumeroLlamadas();
    totalLlamadas += empresa.darLinea2().darNumeroLlamadas();
    totalLlamadas += empresa.darLinea3().darNumeroLlamadas();
    totalMinutos += empresa.darLinea1().darNumeroMinutos();
    totalMinutos += empresa.darLinea2().darNumeroMinutos();
    totalMinutos += empresa.darLinea3().darNumeroMinutos();

    // Actualizar líneas alternativas
    actualizarLinea4(empresa.darLineaCelular1());
    actualizarLinea5(empresa.darLineaVozIP1());

    // Sumar costos, llamadas y minutos de las líneas alternativas
    double costoTotalAlternativas = 0.0;
    int totalLlamadasAlternativas = 0;
    int totalMinutosAlternativas = 0;

    costoTotalAlternativas += empresa.darLineaCelular1().darCostoLlamadas();
    totalLlamadasAlternativas += empresa.darLineaCelular1().darNumeroLlamadas();
    totalMinutosAlternativas += empresa.darLineaCelular1().darNumeroMinutosCelular();

    costoTotalAlternativas += empresa.darLineaVozIP1().darCostoLlamadas();
    totalLlamadasAlternativas += empresa.darLineaVozIP1().darNumeroLlamadas();
    totalMinutosAlternativas += empresa.darLineaVozIP1().darNumeroMinutos();
    totalMinutosAlternativas += empresa.darLineaVozIP1().darNumeroMinutosLargaDistancia();
    totalMinutosAlternativas += empresa.darLineaVozIP1().darNumeroMinutosCelular();

    // Calcular costo promedio para las líneas básicas
    double costoPromedio = costoTotal / totalLlamadas;

    // Calcular costo promedio para las líneas alternativas
    double costoPromedioAlternativas = costoTotalAlternativas / totalLlamadasAlternativas;

    // Actualizar los componentes correspondientes en la interfaz
    lblCostoTotal.setText(formatearValor(costoTotal));
    lblLlamadasTot.setText(formatearValorEntero(totalLlamadas));
    lblMinutosTot.setText(formatearValorEntero(totalMinutos));
    lblCostoPromedio.setText(formatearValor(costoPromedio));

    // Actualizar los componentes correspondientes en la interfaz para las líneas alternativas
    lblCostoTotAlter.setText(formatearValor(costoTotalAlternativas));
    lblLlamaTotAlter.setText(formatearValorEntero(totalLlamadasAlternativas));
    lblMinTotAlter.setText(formatearValorEntero(totalMinutosAlternativas));
    lblCostoPromedioAlter.setText(formatearValor(costoPromedioAlternativas));
            
       }      
    private String formatearValor( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

    /**
     * Formatea un valor numérico para presentar en la interfaz <br>
     * @param valor Valor numérico a ser formateado
     * @return Retorna una cadena con el valor formateado con puntos y signos.
     */
    private String formatearValorEntero( int valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###" );
        df.setMinimumFractionDigits( 0 );
        return df.format( valor );
    }


  
   private void actualizarLinea1(LineaTelefonica linea) {
        lblCosto1.setText( formatearValor( linea.darCostoLlamadas( ) ) );
        lblNumLlamadas1.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumMinutos1.setText(formatearValorEntero( linea.darNumeroMinutos( ) ) );
    }

     private void actualizarLinea2(LineaTelefonica linea) {
         lblCosto2.setText( formatearValor( linea.darCostoLlamadas( ) ) );
        lblNumLlamadas2.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumMinutos2.setText(formatearValorEntero( linea.darNumeroMinutos( ) ) );
     
     }
     
     
      private void actualizarLinea3(LineaTelefonica linea) {
           lblCosto3.setText( formatearValor( linea.darCostoLlamadas( ) ) );
        lblNumLlamadas3.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumMinutos3.setText(formatearValorEntero( linea.darNumeroMinutos( ) ) );
      
      }
      
      
     private void actualizarLinea4(LineaCelular linea) {
        lblCosto4.setText( formatearValor( linea.darCostoLlamadas( ) ) );
        lblSaldo4.setText(formatearValor(linea.darSaldoDisponible()));
        lblNumLlamadas4.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumLamadasLoc4.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumMinCelular4.setText(formatearValorEntero( linea.darNumeroMinutosCelular( ) ));
     }
     
     
    private void actualizarLinea5(LineaVozIP linea) {
        lblCosto5.setText( formatearValor( linea.darCostoLlamadas( ) ) );
        lblSaldo5.setText(formatearValor(linea.darSaldoDisponible()));
        lblNumLlamadas5.setText( formatearValorEntero( linea.darNumeroLlamadas( ) ) );
        lblNumMinutos5.setText(formatearValorEntero( linea.darNumeroMinutos( ) ) );
        lblNumMinLargaDistancia5.setText(formatearValorEntero( linea.darNumeroMinutosLargaDistancia()));
        lblNumMinCelular5.setText(formatearValorEntero( linea.darNumeroMinutosCelular( ) ));
        } 
   

    // Actualizar información de las líneas
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblCosto1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNumLlamadas1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblNumMinutos1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblCosto2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblNumLlamadas2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblNumMinutos2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblCosto3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblNumLlamadas3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblNumMinutos3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblCostoTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblLlamadasTot = new javax.swing.JLabel();
        lblMinutosTot = new javax.swing.JLabel();
        lblCostoPromedio = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblCosto4 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblSaldo4 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblNumLlamadas4 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblNumLamadasLoc4 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblNumMinCelular4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lblCosto5 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblSaldo5 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblNumLlamadas5 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblNumMinLargaDistancia5 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblNumMinCelular5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNumMinutos5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        lblCostoTotAlter = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lblLlamaTotAlter = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblMinTotAlter = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lblCostoPromedioAlter = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnReiniciarBasicas = new javax.swing.JButton();
        btnReiniciarAlternativas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("lineas no Alternativas"));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Linea #1"));

        jLabel9.setText("Costo:");

        lblCosto1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCosto1.setForeground(new java.awt.Color(51, 51, 255));
        lblCosto1.setText("$ 0.00");

        jLabel11.setText("Numero Llamadas:");

        lblNumLlamadas1.setText("0");

        jLabel13.setText("Numero de minutos:");

        lblNumMinutos1.setText("0");

        jButton3.setText("Agregar Llamada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCosto1)
                            .addComponent(lblNumLlamadas1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumMinutos1)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblCosto1))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblNumLlamadas1))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblNumMinutos1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(44, 44, 44))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Linea #2"));

        jLabel15.setText("Costo:");

        lblCosto2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCosto2.setForeground(new java.awt.Color(51, 51, 255));
        lblCosto2.setText("$ 0.00");

        jLabel17.setText("Numero de llamadas:");

        lblNumLlamadas2.setText("0");

        jLabel19.setText("Numero de minutos:");

        lblNumMinutos2.setText("0");

        jButton4.setText("Agregar Llamada");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addGap(60, 60, 60)
                        .addComponent(lblCosto2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel19)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumMinutos2)
                            .addComponent(lblNumLlamadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jButton4)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblCosto2))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblNumLlamadas2))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblNumMinutos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(54, 54, 54))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Linea #3"));

        jLabel21.setText("Costo:");

        lblCosto3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCosto3.setForeground(new java.awt.Color(51, 51, 255));
        lblCosto3.setText("$ 0.00");

        jLabel23.setText("Numero llamadas:");

        lblNumLlamadas3.setText("0");

        jLabel25.setText("Numero de minutos:");

        lblNumMinutos3.setText("0");

        jButton5.setText("Agregar Llamada");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(48, 48, 48)
                                .addComponent(lblCosto3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(33, 33, 33)
                                .addComponent(lblNumLlamadas3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(33, 33, 33)
                                .addComponent(lblNumMinutos3))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton5)))
                .addGap(0, 95, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblCosto3))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblNumLlamadas3))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lblNumMinutos3))
                .addGap(43, 43, 43)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCostoTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblCostoTotal.setForeground(new java.awt.Color(51, 51, 255));
        lblCostoTotal.setText("$ 0.00 ");

        jLabel3.setText("Total Llamadas:");

        jLabel4.setText("Total Minutos:");

        jLabel5.setText("Costo promedio por minuto:");

        lblLlamadasTot.setText("0");

        lblMinutosTot.setText("0");

        lblCostoPromedio.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblLlamadasTot, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addComponent(lblMinutosTot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblCostoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(lblCostoTotal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblLlamadasTot))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblMinutosTot))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblCostoPromedio))
                .addGap(123, 123, 123)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lineas Alternativas"));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Linea #4"));

        jLabel27.setText("Costo:");

        lblCosto4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCosto4.setForeground(new java.awt.Color(51, 51, 255));
        lblCosto4.setText("$ 0.00");

        jLabel29.setText("Saldo Local:");

        lblSaldo4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSaldo4.setForeground(new java.awt.Color(102, 255, 51));
        lblSaldo4.setText("$ 50,000.00");

        jLabel31.setText("Numero llamadas:");

        lblNumLlamadas4.setText("0");

        jLabel33.setText("Numero de minutos locales:");

        lblNumLamadasLoc4.setText("0");

        jLabel35.setText("Numero de minutos celular:");

        lblNumMinCelular4.setText("0");

        jButton6.setText("Agregar Llamada");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(lblSaldo4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumLlamadas4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNumLamadasLoc4))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addGap(34, 34, 34)
                                    .addComponent(lblCosto4)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumMinCelular4))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton6)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lblCosto4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblSaldo4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lblNumLlamadas4))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblNumLamadasLoc4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblNumMinCelular4))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Linea #5"));

        jLabel37.setText("Costo:");

        lblCosto5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCosto5.setForeground(new java.awt.Color(51, 102, 255));
        lblCosto5.setText("$0.00");

        jLabel39.setText("Saldo C y LD:");

        lblSaldo5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSaldo5.setForeground(new java.awt.Color(102, 255, 51));
        lblSaldo5.setText("$ 100,000.00");

        jLabel41.setText("Numero  llamadas:");

        lblNumLlamadas5.setText("0");

        jLabel43.setText("Numero de minutos larga distancia:");

        lblNumMinLargaDistancia5.setText("0");

        jLabel45.setText("Numero de minutos celular:");

        lblNumMinCelular5.setText("0");

        jLabel2.setText("Numero de minutos:");

        lblNumMinutos5.setText("0");

        jButton7.setText("Agregar Llamada");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldo5)
                            .addComponent(lblCosto5)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel43)
                            .addComponent(jLabel41)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton7)
                                .addComponent(jLabel45)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumMinCelular5)
                            .addComponent(lblNumMinLargaDistancia5)
                            .addComponent(lblNumMinutos5)
                            .addComponent(lblNumLlamadas5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 101, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblCosto5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblSaldo5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lblNumLlamadas5))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblNumMinutos5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumMinLargaDistancia5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumMinCelular5)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        lblCostoTotAlter.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblCostoTotAlter.setForeground(new java.awt.Color(51, 51, 255));
        lblCostoTotAlter.setText("$ 0.00");

        jLabel48.setText("Total llamadas:");

        lblLlamaTotAlter.setText("0");

        jLabel50.setText("Total minutos:");

        lblMinTotAlter.setText("0");

        jLabel52.setText("Costo promedio por minuto :");

        lblCostoPromedioAlter.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel48))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMinTotAlter)
                            .addComponent(lblLlamaTotAlter)
                            .addComponent(lblCostoPromedioAlter, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(lblCostoTotAlter))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel50))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblCostoTotAlter)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(lblLlamaTotAlter))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(lblMinTotAlter))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(lblCostoPromedioAlter))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnReiniciarBasicas.setText("Reiniciar Lineas Basicas");
        btnReiniciarBasicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarBasicasActionPerformed(evt);
            }
        });

        btnReiniciarAlternativas.setText("Reiniciar Llamadas Alternativas");
        btnReiniciarAlternativas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarAlternativasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnReiniciarBasicas, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciarAlternativas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReiniciarBasicas, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
            .addComponent(btnReiniciarAlternativas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReiniciarBasicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarBasicasActionPerformed
       int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de reiniciar las líneas básicas?", "Confirmación", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
        empresa.reiniciarLineasNoAlternativas();
        actualizar();
        // Agrega aquí cualquier otro código que desees ejecutar después de reiniciar las líneas
    }
  
// TODO add your handling code here:
    }//GEN-LAST:event_btnReiniciarBasicasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
        agregarLlamada( 1 );        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       agregarLlamada( 2 );  // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     agregarLlamada( 3 );    // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       agregarLlamada( 4 );  // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 agregarLlamada( 5);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnReiniciarAlternativasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarAlternativasActionPerformed
      int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de reiniciar las líneas alternativas?", "Confirmación", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
        empresa.reiniciarLineasAlternativas();
        actualizar();  
      }   // TODO add your handling code here:
    }//GEN-LAST:event_btnReiniciarAlternativasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReiniciarAlternativas;
    private javax.swing.JButton btnReiniciarBasicas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblCosto1;
    private javax.swing.JLabel lblCosto2;
    private javax.swing.JLabel lblCosto3;
    private javax.swing.JLabel lblCosto4;
    private javax.swing.JLabel lblCosto5;
    private javax.swing.JLabel lblCostoPromedio;
    private javax.swing.JLabel lblCostoPromedioAlter;
    private javax.swing.JLabel lblCostoTotAlter;
    private javax.swing.JLabel lblCostoTotal;
    private javax.swing.JLabel lblLlamaTotAlter;
    private javax.swing.JLabel lblLlamadasTot;
    private javax.swing.JLabel lblMinTotAlter;
    private javax.swing.JLabel lblMinutosTot;
    private javax.swing.JLabel lblNumLamadasLoc4;
    private javax.swing.JLabel lblNumLlamadas1;
    private javax.swing.JLabel lblNumLlamadas2;
    private javax.swing.JLabel lblNumLlamadas3;
    private javax.swing.JLabel lblNumLlamadas4;
    private javax.swing.JLabel lblNumLlamadas5;
    private javax.swing.JLabel lblNumMinCelular4;
    private javax.swing.JLabel lblNumMinCelular5;
    private javax.swing.JLabel lblNumMinLargaDistancia5;
    private javax.swing.JLabel lblNumMinutos1;
    private javax.swing.JLabel lblNumMinutos2;
    private javax.swing.JLabel lblNumMinutos3;
    private javax.swing.JLabel lblNumMinutos5;
    private javax.swing.JLabel lblSaldo4;
    private javax.swing.JLabel lblSaldo5;
    // End of variables declaration//GEN-END:variables
}

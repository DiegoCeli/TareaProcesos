/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivoss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SmartCliente
 */
public class fichero {

    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto","txt");
    private File file = null;
    private boolean isopen =false;//bandera de control para saber si se abrio un archivo
    private ArrayList contenido = new ArrayList();//almacena los registros leidos de *.txt
    private int index = 0; //lleva control del registro actualmente visible
    //controles swing
    private JTextField cedula;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField edad;
    private JTextField fecha;
    private JTextField sexo;

    //Constructor de clase
    public fichero(){}

    // constructor de la clase fichero
    public fichero(JTextField cedula , JTextField nombre, JTextField apellido , JTextField edad, JTextField fecha , JTextField sexo)
    {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido = edad;
        this.apellido = fecha;
        this.apellido = sexo;
        System.out.print("Implementado por Diego Celi");
    }

    //Retorna el nombre del archivo abierto
    public String getFileName()
    {
        if( file != null)
            return file.getName();
        else
            return "Sin Titulo";
    }

    /* Abre la cja de dialogo Guardar como
        Input: String de la forma "campo1,campo2,campo2, campo3,campo4,campo5,campo6"
     */
    public void GuardarComo(String texto)
    {       
       fileChooser = new JFileChooser();
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){
                this.isopen = false;
                this.contenido.clear();
                this.index=1;
                if ( escribir( fileChooser.getSelectedFile(),  texto) )
                {
                    JOptionPane.showMessageDialog(null, "Archivo ' " + fileChooser.getSelectedFile().getName() + "' guardado ");
                    this.isopen=true;
                }
        }
    }
    
    //metodo para escribir el fichero .txt
    private boolean escribir(File fichero, String texto)
    {
        boolean res=false;        
        PrintWriter writer = null;
        try {
            String f = fichero.toString();
            //verifica que extension exista sino lo agrega
            if(!f.substring( f.length()-4, f.length()).equals(".txt") )
            {
                f = f + ".txt";
                fichero = new File(f);
            }            
            writer = new PrintWriter( fichero );
            //si hay un archivo abierto
            if( this.isopen )
            {   //añade primero linea por linea conenido anterior
                Iterator It = contenido.iterator();
                while (It.hasNext())
                {
                    writer.println( It.next() );
                }
                //se añade fila de texto al archivo
                writer.println( texto );
                this.contenido.add(texto);
            }
            else //esta guardando por primera vez
            {
                this.contenido.add(texto);
                writer.println( texto );    
            }            
            this.file = fichero;
            writer.close();            
            res = true;
        } catch (FileNotFoundException ex) {
            System.out.println("Error:" + ex);
        } finally {
            writer.close();
        }
        return res;
    }
    
    //metodo de actualizar: sobreescribe el fichero
    public void Actualizar(String texto)
    {
        //Si existe archivo abierto
        if( this.file != null)
        {
            if ( escribir( this.file ,  texto) )
            {
                JOptionPane.showMessageDialog(null, "ARCHIVO '" + this.file.getName() + "' ACTUALIZADO CORRECTAMENTE  ");
            }                        
        }
        else //sino crear nuevo archivo
        {            
            GuardarComo( texto );
        }
    }


    
}

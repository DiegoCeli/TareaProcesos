/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivoss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SmartCliente
 */
public class Buscar {
    
    //METODO PARA LEER EL ARCHIVO
    public void LeerArchivo(JTextArea textArea)
    {
       
        FileReader leerArchivo=null;
      
        try
        {
            //Ruta del Archivo ya creado
            
            File archivo = new File ("D:\\SmartCliente\\Documents\\persona.txt");
                    
         
            leerArchivo = new FileReader (archivo);
            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
          
            String linea=null;
          
            textArea.setText("");
            while((linea = memoriaParaLectura.readLine())!=null)
            {
                textArea.append(linea);
                textArea.append("\n");
            }
        }
        catch (Exception ex)
        {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally
        {
            try
            {
                if (null != leerArchivo)
                {
                    leerArchivo.close();
                }
            }
            catch (Exception ex1)
            {
                JOptionPane.showMessageDialog(null, ex1.getMessage());
            }
        }
    }
}
  

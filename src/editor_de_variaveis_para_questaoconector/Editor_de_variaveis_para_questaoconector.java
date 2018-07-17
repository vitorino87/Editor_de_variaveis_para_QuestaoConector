/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor_de_variaveis_para_questaoconector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago.lucas
 */
public class Editor_de_variaveis_para_questaoconector {

    private JFileChooser fc = new JFileChooser();
    private File f;
    private int contador1=0, contador2=0;
    private String texto1="", texto2="";
    
    public int criarSeletorDeArquivos(){
        fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);  
        return returnVal;
    }
    
    public File abrirArquivo(int a){
        //arquivo selecionado
        if(a==JFileChooser.APPROVE_OPTION){
            f = fc.getSelectedFile();
        }
        return f;
    }
    
    @SuppressWarnings("empty-statement")
    public void realizarLeituraDaLinhaDoArquivo(File file) throws FileNotFoundException, IOException{
        try(FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            int ch;
            String text="";
            while((ch=fis.read())!=-1){
                if(ch!=10){
                    text += String.valueOf(ch);
                }else{
                    processarLinha(text);
                }
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
    }
    
    public void processarLinha(String text){
        String resposta = "";
        if(text.indexOf("xa)")>0){
            resposta = "0";
            text=text.replace("xa)", "a)");
            //text=text.substring(0, text.indexOf("xa)")) + 
            //        text.substring(text.indexOf("xa)")+1, text.length());
        }else{
            if(text.indexOf("xb)")>0){
                resposta = "1";
                text=text.replace("xb)", "b)");
                //text=text.substring(0, text.indexOf("xb)")) + 
                //    text.substring(text.indexOf("xb)")+1, text.length());
            }else{
                if(text.indexOf("xc)")>0){
                    resposta = "2";
                    text=text.replace("xc)", "c)");
                    //text=text.substring(0, text.indexOf("xc)")) + 
                    //    text.substring(text.indexOf("xc)")+1, text.length());
                }else{
                    if(text.indexOf("xd)")>0){
                        resposta = "3";
                        text=text.replace("xd)", "d)");
                        //text=text.replace("xe)", "e)");
                        //text=text.substring(0, text.indexOf("xd)")) + 
                           // text.substring(text.indexOf("xd)")+1, text.length());
                    }else{
                        if(text.indexOf("xe)")>0){
                            resposta = "4";
                            text=text.replace("xe)", "e)");                            
                        }
                    }
                }
            }           
        }
        if(text.indexOf("xe)")>0){
            String text2 = "";
            String[] text3 = {"a","b","c","d","e"};
            text2+="a["+contador1+"]=R.string.q"+contador1+"\n";
            for(contador2=0;contador2<5;contador2++){
                text2+="b["+contador1+"]["+contador2+"]=R.string.q"+contador1+text3[contador2];                           
            }
            text2+="b["+contador1+"]["+contador2+"]="+resposta;       
            contador1++;      
            texto2+=text2;
        }
    }
    
    public void salvarLinhasProcessadasEmDoisArquivos(String text, String text2){
        File file = selecionarLocalParaSalvar();
        if(file != null){
            
        }
    }
    
    public File selecionarLocalParaSalvar(){
        int returnval = fc.showSaveDialog(fc);
        File file = null;
        if(returnval==JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }else{
            JOptionPane.showMessageDialog(null, "O programa será encerrado!");
        }
        return file;
    }
    
    public static void main(String[] args) throws IOException {
        Editor_de_variaveis_para_questaoconector editor = new Editor_de_variaveis_para_questaoconector();
        int a = editor.criarSeletorDeArquivos();
        File file = editor.abrirArquivo(a);
        editor.realizarLeituraDaLinhaDoArquivo(file);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserverisnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bronius
 */
public class WebServer {

    private static final String folderPath = "C:\\temp\\webziba-server";
    private String fileRequest = "";

    public WebServer() throws IOException {
        createFiles();
        ServerSocket ss = new ServerSocket(8000);
        while (true) {
            Socket s = ss.accept();
            Reader r = new InputStreamReader(s.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(r);
            String st;

            do {
                st = br.readLine();
                System.out.println(st);
                if (st.startsWith("GET")) {
                    String[] params = st.split(" ");
                    
                    if (params[1].equals("/")) {
                        fileRequest = "";
                    } else {
                        fileRequest = params[1].substring(1);
                    }
                    
                    System.out.println("====================");
                    System.out.println(Arrays.toString(params));
                    System.out.println("====================");
                    
                }
            } while (st != null && !st.equals(""));

            Writer w = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
            BufferedWriter bw = new BufferedWriter(w);

            bw.write("HTTP/1.1 200 OK");
            bw.newLine();

            bw.newLine();
            if (fileRequest.equals("")) {
                bw.write("<html><body>Hellow from server ["+fileRequest+"]</body></html>");
            } else {
                String fullPath = folderPath + "\\" + fileRequest;
                try(FileInputStream fis = new FileInputStream(fullPath)) {
                    byte[] fisArray = new byte[fis.available()];
                    fis.read(fisArray);
                    String textInWeb = new String(fisArray);
                    bw.write(textInWeb);
                } catch(FileNotFoundException e) {
                    String errorMessage = "Klaida, failas nerastas: " + fileRequest + " klaidos pranesimas: " + e.getMessage(); 
                    System.out.println(errorMessage);
                    bw.write(errorMessage);
                }
            }

            bw.flush();
            s.close();
        }
    }

    public void createFiles() throws FileNotFoundException {
        String tekstas = "Rytas sventas - skirtas miegui";
        try (FileOutputStream fos = new FileOutputStream(folderPath + "\\" + "FileName1.html")) {
            byte[] FileName1 = tekstas.getBytes();
            fos.write(FileName1);
        } catch (IOException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

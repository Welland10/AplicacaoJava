/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicação;

/**
 * configurar os arquivos nescessarios para implantação caso eles não existam na maquina do cliente
 * @author weliton.andrade
 */
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class ConfiguradorSistema {

    private static final String SQLITE_JAR_URL = "https://search.maven.org/remotecontent?filepath=org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar";
    private static final String SLF4J_JAR_URL = "https://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar";
    private static final String SQLITE_JAR_PATH = System.getProperty("user.home") + "/sqlite-jdbc.jar";
    private static final String SLF4J_JAR_PATH = System.getProperty("user.home") + "/slf4j-api.jar";
    private static final String SQLITE_DOWNLOAD_URL = "https://www.sqlite.org/2023/sqlite-tools-win32-x86-3410200.zip";
    private static final String SQLITE_ZIP_PATH = System.getProperty("user.home") + "/sqlite-tools.zip";
    private static final String SQLITE_EXTRACTED_PATH = System.getProperty("user.home") + "/sqlite-tools";

    public static void main(String[] args) {
        try {
            verificarEBaixarArquivos();
            System.out.println("Configuração concluída com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarEBaixarArquivos() throws IOException {
        if (!Files.exists(Paths.get(SQLITE_JAR_PATH))) {
            baixarArquivo(SQLITE_JAR_URL, SQLITE_JAR_PATH);
        }

        if (!Files.exists(Paths.get(SLF4J_JAR_PATH))) {
            baixarArquivo(SLF4J_JAR_URL, SLF4J_JAR_PATH);
        }

        if (!Files.exists(Paths.get(SQLITE_ZIP_PATH))) {
            baixarArquivo(SQLITE_DOWNLOAD_URL, SQLITE_ZIP_PATH);
            extrairZip(SQLITE_ZIP_PATH, SQLITE_EXTRACTED_PATH);
        }
    }

    private static void baixarArquivo(String urlString, String destino) throws IOException {
        URL url = new URL(urlString);
        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(destino)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    private static void extrairZip(String zipPath, String destino) throws IOException {
        File destDir = new File(destino);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (FileInputStream fis = new FileInputStream(zipPath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDir, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}


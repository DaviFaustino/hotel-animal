package com.animal.hotel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/* Esta classe fornece métodos que exibem interfaces discretas para o usuário
* para que este possa interagir inserindo através do teclado quaisquer tipos
* de dados, quer sejam de hospedes quer serjam de clientes. Tais métodos
* retornam os valores das entradas feitas pelo usuário.
*  */
public class InputDados {
    /*
     * Este método gera um número de identificação(ID) para o cliente de forma
     * a nunca repetir o número dos clientes e sempre gerar um número em ordem
     * crescente. O número gerado é atribuído ao respectivo atributo.
     * */
    public static String numeroIdentificacao(boolean isHospede) {
        /* Este HashMap armazena como chaves o nome do tipo de identificação(cliente/hospede)
         * e como valores o último ID gerado de cada tipo */
        HashMap<String, Integer> ultimosIDs = new HashMap<String, Integer>();

        String local = "src/arquivos/ultimosIDs.txt";

        /*
         * Neste trecho é lido o arquivo com o ultimo ID gerado de cliente e hospede
         * e então seus dados são armazenados no HashMap ultimosIDs.
         * */
        File arquivo = new File(local);
        Scanner lerArquivo = null;
        try {
            lerArquivo = new Scanner(arquivo);
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
            try {
                // Cria um objeto FileWriter para escrever no arquivo
                FileWriter escritor = new FileWriter(arquivo);
                // Cria um objeto BufferedWriter para escrever no FileWriter
                BufferedWriter bufferEscrita = new BufferedWriter(escritor);
    
                String conteudo = "cliente;0000\nhospede;00000\n";

                // Escreve o conteúdo no arquivo
                bufferEscrita.write(conteudo);
                // Fecha o BufferedWriter e o FileWriter
                bufferEscrita.close();
                escritor.close();
    
                lerArquivo = new Scanner(arquivo);
            } catch (IOException ie) {
                System.out.println("Ocorreu um erro ao criar o arquivo: " + ie.getMessage());
            }
        }

        String[] chaveValor;

        while (lerArquivo.hasNextLine()) {
            chaveValor = lerArquivo.nextLine().replace("\n", "").split(";");
            ultimosIDs.put(chaveValor[0], Integer.parseInt(chaveValor[1]));
        }
        
        lerArquivo.close();

        /* Estas são as variáveis que armazenam os IDs atualizados em forma de String */
        String idClienteAtualizadoString;
        String idHospedeAtualizadoString;

        /* Se isHospede for true, então o ID que o método está gerando e retornando
         * é de hospede, se for false, o ID é de cliente. */
        if (isHospede) {
            // O ID de hospede é gerado
            int idHospedeAtualizado = ultimosIDs.get("hospede") + 1;

            // Os IDs são transformados em Strings
            idHospedeAtualizadoString = String.valueOf(idHospedeAtualizado);
            idClienteAtualizadoString = String.valueOf(ultimosIDs.get("cliente"));
        } else {
            // O ID de cliente é gerado
            int idClienteAtualizado = ultimosIDs.get("cliente") + 1;

            // Os IDs são transformados em strings
            idClienteAtualizadoString = String.valueOf(idClienteAtualizado);
            idHospedeAtualizadoString = String.valueOf(ultimosIDs.get("hospede"));
        }

        /* A String do ID de cliente é preenchida com zeros a esquerda até completar
         * 4 casas decimais. */
        int lengthID = idClienteAtualizadoString.length();
        for (int i = 0; i < (4 - lengthID); i++) {
            idClienteAtualizadoString = "0" + idClienteAtualizadoString;
        }
        /* A String do ID de hospede é preenchida com zeros a esquerda até completar
         * 5 casas decimais. */
        lengthID = idHospedeAtualizadoString.length();
        for (int i = 0; i < (5 - lengthID); i++) {
            idHospedeAtualizadoString = "0" + idHospedeAtualizadoString;
        }

        /*
         * Este trecho salva os IDs de volta no arquivo txt já com o último ID gerado
         * de hospede atualizado
         * */
        try {
            OutputStream outputStream = new FileOutputStream(local);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write("cliente;" + idClienteAtualizadoString + "\n");
            bufferedWriter.write("hospede;" + idHospedeAtualizadoString + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Se o ID gerado é de hospede, então retorna o ID de hospede. Se não
         * retorna o ID de cliente */
        if (isHospede) {
            return idHospedeAtualizadoString;
        } else {
            return idClienteAtualizadoString;
        }
    }
}

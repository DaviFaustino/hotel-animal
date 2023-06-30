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

        /* Este método coleta o nome do hospede pela leitura do teclado
    *  */
    public static String nomeHospede() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Nome do hospede: ");
            String nome = lerTeclado.nextLine();
            if (nome.length() != 0) {
                lerTeclado.close();
                return nome;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
    }

    /* Este metodo coleta a fase da vida do hospede pela leitura do teclado
    * */
    public static String faseVida() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| O hospede é filhote(f), adulto(a) ou idoso(i): [f/a/i]: ");
            String fase = lerTeclado.nextLine();
            if (fase.equals("f")) {
                lerTeclado.close();
                return "Filhote";
            } else {
                if (fase.equals("a")) {
                    lerTeclado.close();
                    return "Adulto";
                } else {
                    if (fase.equals("i")) {
                        lerTeclado.close();
                        return "Idoso";
                    } else {
                        System.out.println("| !! Insira um valor válido !!");
                    }
                }
            }
        }
    }

    /* Este método coleta a quantidade de ração que hospede receberá pela leitura do teclado
    * */
    public static int quantRacaoHospede() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Quantidade de ração do hospede(g): ");
            String quantRacao = lerTeclado.nextLine();
            try {
                lerTeclado.close();
                return Integer.parseInt(quantRacao);
            } catch (Exception e) {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
    }

    /* Este método coleta a data de check-in do hospede pela leitura do teclado.
    * Primeiro o ano, depois o mês e por último o dia.
    * */
    public static String[] dataCheckIn() {
        Scanner lerTeclado = new Scanner(System.in);
        String[] data = new String[3];

        System.out.println("------------------------------------------------------");
        System.out.println("|         Data do Check-In          |");

        while (true) {
            System.out.print("| Ano: ");
            String ano = lerTeclado.nextLine();
            if (ano.length() == 4) {
                data[2] = ano;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
        while (true) {
            System.out.print("| Mês: ");
            String mes = lerTeclado.nextLine();
            if (mes.length() == 2) {
                data[1] = mes;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
        while (true) {
            System.out.print("| Dia: ");
            String dia = lerTeclado.nextLine();
            if (dia.length() == 2) {
                data[0] = dia;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }

        System.out.println("Data de Check-In: " + data[0] + "/" + data[1] + "/" + data[2]);

        lerTeclado.close();
        return data;
    }

    /* Este método coleta a data de check-out do hospede pela leitura do teclado.
     * Primeiro o ano, depois o mês e por último o dia.
     * */
    public static String[] dataCheckOut() {
        Scanner lerTeclado = new Scanner(System.in);
        String[] data = new String[3];

        System.out.println("------------------------------------------------------");
        System.out.println("|         Data do Check-Out         |");
        while (true) {
            System.out.print("| Ano: ");
            String ano = lerTeclado.nextLine();
            if (ano.length() == 4) {
                data[2] = ano;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
        while (true) {
            System.out.print("| Mês: ");
            String mes = lerTeclado.nextLine();
            if (mes.length() == 2) {
                data[1] = mes;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }
        while (true) {
            System.out.print("| Dia: ");
            String dia = lerTeclado.nextLine();
            if (dia.length() == 2) {
                data[0] = dia;
                break;
            } else {
                System.out.println("| !! Insira um valor válido !!");
            }
        }

        System.out.println("Data de Check-Out: " + data[0] + "/" + data[1] + "/" + data[2]);

        lerTeclado.close();
        return data;
    }

    /* Este método coleta a informação de o hospede poder socializar com outros animais ou não
    *  */
    public static boolean podeSocializar() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Pode socializar com outros, sim(s) ou não(n)? [s/n]:  ");
            String resposta = lerTeclado.nextLine();
            if (resposta.equals("s")) {
                lerTeclado.close();
                return true;
            } else {
                if (resposta.equals("n")) {
                    lerTeclado.close();
                    return false;
                } else {
                    System.out.println("!! Insira um valor válido !!");
                }
            }
        }
    }

    /* Este método coleta a informação de o cachorro poder passear ou não
    *  */
    public static boolean podePassear() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| O cachorro pode passear, sim(s) ou não(n)? [s/n]:  ");
            String resposta = lerTeclado.nextLine();
            if (resposta.equals("s")) {
                lerTeclado.close();
                return true;
            } else {
                if (resposta.equals("n")) {
                    lerTeclado.close();
                    return false;
                } else {
                    System.out.println("!! Insira um valor válido !!");
                }
            }
        }
    }

    /* Este método coleta a quantidade de companheiros de gatil que o gato pode ter de 0 a 2
    *  */
    public static int quantCompanheirosGatil() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Quantidade de companheiros de gatil do gato(de 0 a 2): ");

            try {
                int resposta = Integer.parseInt(lerTeclado.nextLine());
                if (resposta >= 0 && resposta <= 2) {
                    lerTeclado.close();
                    return resposta;
                } else {
                    System.out.println("!! Insira um valor de 0 a 2 !!");
                }
            } catch (Exception e) {
                System.out.println("!! Insira um número inteiro !!");
            }
        }
    }

    /* Este método coleta o nome do cliente pela leitura do teclado
    *  */
    public static String nomeCliente() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Nome do cliente: ");

            String nome = lerTeclado.nextLine();
            if (nome.length() != 0) {
                lerTeclado.close();
                return nome;
            } else {
                System.out.println("!! Insira um valor válido !!");
            }
        }
    }

    /* Este método coleta o número de telefone do cliente pela leitura do teclado
    *  */
    public static String numeroTelefone() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| Numero de telefone: ");

            String numero = lerTeclado.nextLine();
            if (numero.length() != 0) {
                lerTeclado.close();
                return numero;
            } else {
                System.out.println("!! Insira um valor válido !!");
            }
        }
    }

    /* Este método coleta o email do cliente pela leitura do teclado
    *  */
    public static String emailCliente() {
        Scanner lerTeclado = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.print("| E-mail: ");

            String numero = lerTeclado.nextLine();
            if (numero.length() != 0) {
                lerTeclado.close();
                return numero;
            } else {
                System.out.println("!! Insira um valor válido !!");
            }
        }
    }
}

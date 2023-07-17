package com.animal.hotel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/*
* A classe abstrata Hospede define a maioria das informações que serão utilizadas por
* suas subclasses Cachorro e Gato. Ela disponibiliza todos os atributos que armazenarão
* os dados fornecidos na reserva de um Hospede.
* */
public abstract class Hospede implements Identificacao {
    /*
    * Este atributo armazena o número de identificação do cliente responsável
    * pelo hospede.
    * */
    private String numeroIdentificacaoResponsavel;

    /*
    * Este atributo armazena o número de identificação do hospede gerado pelo
    * sistema.
    * */
    private String numeroIdentificacao;

    /*
    * Este atributo armazena o nome do hospede.
    * */
    private String nome;

    /*
    * Este atributo armazena a fase da vida do animal. Filhote, adulto ou idoso.
    * */
    private String faseDaVida;

    /*
    * Este atributo armazena a quantidade de ração em gramas que o hospede vai
    * consumir fornecido pelo estabelecimento.
    * */
    private int quantRacaoGramas;

    /*
    * Este atributo armazena o nome da ração escolhida.
    * */
    private String nomeRacao;

    /*
    * Este atributo armazena o preço do kilograma da ração escolhida.
    * */
    private float precoRacaoKilo;

    /*
    * Este atributo armazena o calculo do gasto total com a ração do hospede.
    * */
    private float gastoComRacao;

    /*
    * Este atributo armazena true quando o hospede pode socializar com outros
    * animais do seu tipo.
    * */
    private boolean podeSocializarComOutros;

    /*
    * Este atributo armazena um array que expressa as informações do dia, mês
    * e do ano que será feito o check-in do hospede.
    * */
    private String[] dataCheckIn = new String[3];

    /*
     * Este atributo armazena um array que expressa as informações do dia,
     * mês e do ano que será feito o check-out do hospede.
     * */
    private String[] dataCheckOut = new String[3];

    /*
    * Este atributo armazena o custo da diária do hospede calculado.
    * */
    private float custoDiaria = 0.0f;

    /*
    * Este atributo armazena o custo total do serviço prestado.
    * */
    private float custoHospede;


    /*
    * Este método interage com o usuário exibindo as opções de ração disponíveis
    * para que o cliente possa informar a ração escolhida para o pet. Em seguida,
    * a partir da ração informada, ele guarda a informação do nome da ração e do
    * preço do kilograma da ração.
    * */
    public void escolhaRacao() {
        /* Este HashMap armazena como chaves os nomes das rações e como valores
        * os preços das rações. */
        HashMap<String, Float> opcoesRacoes = new HashMap<String, Float>();

        Scanner lerTeclado = new Scanner(System.in);

        /*
        * Neste trecho é lido o arquivo com as rações e seus preços e então armazenados
        * no HaspMap opcoesRacoes.
        * */
        try {
            File arquivo = new File("src/arquivos/racoes.txt");
            Scanner lerArquivo = new Scanner(arquivo);

            String[] chaveValor;

            while (lerArquivo.hasNextLine()) {
                chaveValor = lerArquivo.nextLine().replace("\n", "").split(";");
                opcoesRacoes.put(chaveValor[0], Float.valueOf(chaveValor[1]));
            }

            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        * Neste trecho são exibidas as opcões de rações e então armazenados, de acordo com
        * a escolha do cliente, os seus nomes e preços. Em seguida é coletada a escolha do
        * cliente e a partir dela são setados o preço do kilo da ração e o nome da ração
        * no objeto filho de Hospede.
        * */
        while (true) {
            System.out.println("------------------------------------------------------");
            Object[] racoes =  opcoesRacoes.keySet().toArray();
            for (int i = 0; i < racoes.length; i++) {
                System.out.println("| [" + i + "] " + racoes[i]);
            }
            System.out.print("| Ração do hospede: ");

            try {
                int racaoEscolhida = Integer.parseInt(lerTeclado.nextLine());
                setPrecoRacaoKilo(opcoesRacoes.get(racoes[racaoEscolhida]));
                setNomeRacao((String) racoes[racaoEscolhida]);
                break;
            } catch (Exception e) {
                System.out.println("!! Escolha uma racao dentre as opçãoe !!");
            }
        }

        lerTeclado.close();
    }

    public void calculaGastoRacao() {
        setGastoComRacao(getPrecoRacaoKilo() * (getQuantRacaoGramas() / 1000f));
    }

    public String getNumeroIdentificacaoResponsavel() {
        return numeroIdentificacaoResponsavel;
    }

    public void setNumeroIdentificacaoResponsavel(String numeroIdentificacaoResponsavel) {
        this.numeroIdentificacaoResponsavel = numeroIdentificacaoResponsavel;
    }

    public String getNomeRacao() {
        return nomeRacao;
    }

    public void setNomeRacao(String nomeRacao) {
        this.nomeRacao = nomeRacao;
    }

    @Override
    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPodeSocializarComOutros() {
        return podeSocializarComOutros;
    }

    public void setPodeSocializarComOutros(boolean podeSocializarComOutros) {
        this.podeSocializarComOutros = podeSocializarComOutros;
    }

    public String getFaseDaVida() {
        return faseDaVida;
    }

    public void setFaseDaVida(String faseDaVida) {
        this.faseDaVida = faseDaVida;
    }

    public int getQuantRacaoGramas() {
        return quantRacaoGramas;
    }

    public void setQuantRacaoGramas(int quantRacaoGramas) {
        this.quantRacaoGramas = quantRacaoGramas;
    }

    public float getPrecoRacaoKilo() {
        return precoRacaoKilo;
    }

    public void setPrecoRacaoKilo(float precoRacaoKilo) {
        this.precoRacaoKilo = precoRacaoKilo;
    }

    public float getGastoComRacao() {
        return gastoComRacao;
    }

    public void setGastoComRacao(float gastoComRacao) {
        this.gastoComRacao = gastoComRacao;
    }

    public float getCustoHospede() {
        return custoHospede;
    }

    public void setCustoHospede(float custoHospede) {
        this.custoHospede = custoHospede;
    }

    public float getCustoDiaria() {
        return custoDiaria;
    }

    public void setCustoDiaria(float custoDiaria) {
        this.custoDiaria = custoDiaria;
    }

    public String[] getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(String[] dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public String[] getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(String[] dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }
}

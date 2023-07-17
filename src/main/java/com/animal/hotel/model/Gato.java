package com.animal.hotel.model;

import java.util.Calendar;

/*
* Esta subclasse de Hospede instacia um objeto do tipo Gato que adiciona atributo próprio
* da subclasse, impletenta o método que calcula o custo do hospede e subscreve o método
* toString para exibir os dados do Gato em forma de menu.
* */
public class Gato extends Hospede implements Custo {
    /*
     * Este atributo armazena a quantidade de companheiros que vão dividir o gatil com o gato
     * em questão.
     * */
    private int quantCompanheirosGatil;

    /*
    * Este método calcula o custo total do serviço a partir dos dados do animal
    * */
    @Override
    public float calculaCusto() {
        /* Este trecho calcula o custo da diária a partir de atributos do hospede */
        setCustoDiaria(0f);
        if (getFaseDaVida().equals("Adulto")) {
            setCustoDiaria(getCustoDiaria() + 7f);
        } else {
            setCustoDiaria(getCustoDiaria() + 3f);
        }

        if (isPodeSocializarComOutros()) {
            setCustoDiaria(getCustoDiaria() + 3.5f);
        }
        setCustoDiaria(getCustoDiaria() + 15); // Custo de serviço
        setCustoDiaria(getCustoDiaria() + (24 / (getQuantCompanheirosGatil() + 1))); // Custo de estadia

        /* Objetos Calendar para armazenar as datas de check-in e check-out */
        Calendar diaCheckIn = Calendar.getInstance();
        Calendar diaCheckOut = Calendar.getInstance();

        /* Seta-se as datas de check-in e check-out a partir dos atriburos da classe*/
        diaCheckIn.set(Calendar.YEAR, Integer.parseInt(getDataCheckIn()[2]));
        diaCheckIn.set(Calendar.MONTH, Integer.parseInt(getDataCheckIn()[1]) - 1);
        diaCheckIn.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getDataCheckIn()[0]));
        diaCheckOut.set(Calendar.YEAR, Integer.parseInt(getDataCheckOut()[2]));
        diaCheckOut.set(Calendar.MONTH, Integer.parseInt(getDataCheckOut()[1]) - 1);
        diaCheckOut.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getDataCheckOut()[0]));

        long diaEmMillis = 86400000; //Total de milissegundos no dia

        /* Calcula-se a quantidade de dias do serviço, o gasto com a ração e então o custo
        * total do serviço do hospede */
        int quantDias = (int) ((diaCheckOut.getTimeInMillis() / diaEmMillis) - (diaCheckIn.getTimeInMillis() / diaEmMillis));
        calculaGastoRacao();
        float custoHospede = (getCustoDiaria() * quantDias) + getGastoComRacao();
        setCustoHospede(custoHospede);

        return custoHospede;
    }

    /* Reescrita do toString para exibir os dados em forma de menu.
    *  */
    @Override
    public String toString() {
        return "|    Id do responsável: " + getNumeroIdentificacaoResponsavel() + "\n" +
                "|    Id do cachorro: " + getNumeroIdentificacao() + "\n" +
                "|[1] Nome: " + getNome() + "\n" +
                "|[2] Fase da vida: " + getFaseDaVida() + "\n" +
                "|[3] Nome da ração: " + getNomeRacao() + "\n" +
                "|    Preço do kilo da ração: " + getPrecoRacaoKilo() + "\n" +
                "|[4] Quantidade de ração: " + getQuantRacaoGramas() + "g" + "\n" +
                "|    Gasto com ração: " + getGastoComRacao() + "\n" +
                "|[5] Pode socializar com outros cachorros: " + isPodeSocializarComOutros() + "\n" +
                "|[6] Quantidade de companheiros de gatil: " + getQuantCompanheirosGatil() + "\n" +
                "|[7] Data de check-in: " + getDataCheckIn()[0] + "/" + getDataCheckIn()[1] + "/" + getDataCheckIn()[2] + "\n" +
                "|[8] Data de check-out: " + getDataCheckOut()[0] + "/" + getDataCheckOut()[1] + "/" + getDataCheckOut()[2] + "\n" +
                "|    Custo da hospedagem: " + getCustoHospede() + "\n" +
                "--------------------------------------------------------"
                ;
    }

    public int getQuantCompanheirosGatil() {
        return quantCompanheirosGatil;
    }

    public void setQuantCompanheirosGatil(int quantCompanheirosGatil) {
        this.quantCompanheirosGatil = quantCompanheirosGatil;
    }
}
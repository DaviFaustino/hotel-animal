package com.animal.hotel.model;

/*
 * A classe Cliente instacia um objeto do tipo Cliente que armazena os dados do cliente
 * responsável pelos animais com reservas feitas no sistema, impletenta a interface
 * Identificação e subscreve o método toString para exibir os dados do Cliente em forma
 * de menu.
 * */
public class Cliente implements Identificacao {
    /*
     * Este atributo armazena o número de identificação do cliente
     * */
    private String numeroIdentificacao;

    /*
     * Este atributo armazena o nome do cliente
     * */
    private String nome;

    /*
     * Este atributo armazena o número de telefone do cliente
     * */
    private String numeroTelefone;

    /*
     * Este atributo armazena o e-mail do cliente
     * */
    private String email;

    /*
     * Este atributo armazena na posição 0 do array o custo com os cachorros do cliente
     * e na posição 1 o custo com os gatos do cliente.
     * */
    private float[] custoCachorrosGatos = {0.0f, 0.0f};

    /* Reescrita do toString para exibir os dados em forma de menu.
     *  */
    @Override
    public String toString() {
        return "|[0] Id do cliente: " + getNumeroIdentificacao() + "\n" +
                "|[1] Nome do cliente: " + getNome() + "\n" +
                "|[2] Número de telefone: " + getNumeroTelefone() + "\n" +
                "|[3] E-mail: " + getEmail() + "\n" +
                "|    Débito: " + (getCustoCachorrosGatos()[0] + getCustoCachorrosGatos()[1]) + "\n" +
                "--------------------------------------------------------"
                ;
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

    public float[] getCustoCachorrosGatos() {
        return custoCachorrosGatos;
    }

    public void setCustoCachorrosGatos(float[] custoCachorrosGatos) {
        this.custoCachorrosGatos = custoCachorrosGatos;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

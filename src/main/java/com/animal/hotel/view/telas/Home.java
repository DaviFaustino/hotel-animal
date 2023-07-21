package com.animal.hotel.view.telas;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.animal.hotel.view.funcoes.EventosHome.*;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

/*
* Este programa administra os arquivos do Hotel Animal, um negócio de hospedagem de cachorros e gatos.
* Quanto uma pessoa precisa ficar fora de casa por algum tempo e não tem quem cuide do seu pet, ela
* pode procurar os serviços do Hotel Animal.
*
* Neste sistema, podem ser feitos os cadastros de clientes onde são coletados os dados do mesmo e salvos
* nos arquivos dos clientes. Também é possível visualizar todos os clientes cadastrados no sistema bem
* como seus dados. Esses dados também podem ser editados pelo usuário depois que o cadastro foi feito.
*
* Aqui também são registradas reservas de animais quando o cliente quer contratar o serviço. Também é
* possível visualizar todos os hospedes com reserva feita no sistema bem os dados do mesmo. Os dados
* dos animais/reservas podem ser editados caso haja solicitação do cliente e removidos depois do serviço
* prestado for finalizado.
* */
public class Home {
    public static int larguraPainelPrincipal = 170;
    public static int alturaPainelPrincipal = 148;
    public static int tamanhoLaterais;
    public static int tamanhoCimaBaixo;
    static JButton butClientes, butCadastrarCliente, butHospedes, butExit;
    public static JFrame telaPrincipal;
    public static CadastrarCliente telaCadastrarCliente;
    public static EditarReserva  telaEditarReserva;
    public static FazerReserva telaFazerReserva;
    public static Clientes telaClientes;
    public static EditarCliente telaEditarCliente;
    public static Reservas telaReservas;
    public static ArrayList<JPanel> paineisPrincipal;
    public static int telaWidth = 700;
    public static int telaHeight = 500;
    public static Point posicaoTela;
    public static Font fontePadrao = new Font("padrao", Font.BOLD, 15);
    public static boolean alterar;
    

    /*
    * O método principal inicía exibindo um menu com as ações que podem ser realizadas
    * pelo usuário e depois, a partir da escolha feita pela leitura do teclado, ele chama
    * o método estático responsável por executar a ação ou função escolhida.
    *  */
    public static void main (String[] args) {
        telaPrincipal = new JFrame("Menu Principal");
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setSize(telaWidth, telaHeight);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setVisible(true);
        telaPrincipal.setLayout(new BorderLayout());

        posicaoTela = telaPrincipal.getLocation();

        paineisPrincipal = new ArrayList<>();
        paineisPrincipal.add(new JPanel());
        paineisPrincipal.get(0).setLayout(new BoxLayout(paineisPrincipal.get(0), BoxLayout.Y_AXIS));
        paineisPrincipal.add(new JPanel());
        paineisPrincipal.add(new JPanel());
        paineisPrincipal.add(new JPanel());
        paineisPrincipal.add(new JPanel());

        tamanhoLaterais = (telaPrincipal.getWidth() - larguraPainelPrincipal) / 2;
        tamanhoCimaBaixo = (telaPrincipal.getHeight() - alturaPainelPrincipal) / 2;

        paineisPrincipal.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisPrincipal.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisPrincipal.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
        paineisPrincipal.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));

        telaPrincipal.addComponentListener(new EventosTela());

        butClientes = new JButton("Clientes");
        butCadastrarCliente = new JButton("Cadastrar Cliente");
        butHospedes = new JButton("Hospedes");
        butExit = new JButton("Exit");

        butClientes.setSize(larguraPainelPrincipal, 30);
        butCadastrarCliente.setSize(larguraPainelPrincipal, 30);
        butHospedes.setSize(larguraPainelPrincipal, 30);
        butExit.setSize(larguraPainelPrincipal, 30);

        butClientes.addActionListener(new EventoButaoClientes());
        butCadastrarCliente.addActionListener(new EventoButaoCadastrarClientes());
        butHospedes.addActionListener(new EventoButaoHospedes());
        butExit.addActionListener(new EventoButaoExit());

        telaPrincipal.add(paineisPrincipal.get(0), BorderLayout.CENTER);
        telaPrincipal.add(paineisPrincipal.get(1), BorderLayout.WEST);
        telaPrincipal.add(paineisPrincipal.get(2), BorderLayout.EAST);
        telaPrincipal.add(paineisPrincipal.get(3), BorderLayout.NORTH);
        telaPrincipal.add(paineisPrincipal.get(4), BorderLayout.SOUTH);
        paineisPrincipal.get(0).add(butClientes);
        paineisPrincipal.get(0).add(butCadastrarCliente);
        paineisPrincipal.get(0).add(butHospedes);
        paineisPrincipal.get(4).add(butExit);
    }
}

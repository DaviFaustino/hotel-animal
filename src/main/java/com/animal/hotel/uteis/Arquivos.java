package com.animal.hotel.uteis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
* Esta classe disponibiliza métodos para salvar instancias das Classes Cachorro, Gato e
* Cliente na base de dados em formato json.
* */
public class Arquivos {

    /*
    * O método addAosArquivos lê os dados salvos da classe recebida como parêmetro, transforma
    * eles em um List<Classe> em questão onde é adicionado o objeto que se pretende salvar
    * e então o List<Classe> é salvo novamente atualizado na base de dados.
    * */

    public static void addAosArquivos(Cachorro cachorro) throws IOException {
        Path path = Paths.get("src/arquivos/cachorros.json");
        String jsonsString;
        try {
            jsonsString = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            jsonsString = "[]";
        }

        Gson gson = new Gson();
        List<Cachorro> cachorros = gson.fromJson(jsonsString, new TypeToken<List<Cachorro>>(){}.getType());

        cachorros.add(cachorro);
        salvar(cachorros, 0);
    }

    public static void addAosArquivos(Gato gato) throws IOException {
        Path path = Paths.get("src/arquivos/gatos.json");
        String jsonsString;
        try {
            jsonsString = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            jsonsString = "[]";
        }

        Gson gson = new Gson();
        List<Gato> gatos = gson.fromJson(jsonsString, new TypeToken<List<Gato>>(){}.getType());

        gatos.add(gato);
        salvar(gatos, 1);
    }

    public static void addAosArquivos(Cliente cliente) throws IOException {
        Path path = Paths.get("src/arquivos/clientes.json");
        String jsonsString;
        try {
            jsonsString = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            jsonsString = "[]";
        }

        Gson gson = new Gson();
        List<Cliente> clientes = gson.fromJson(jsonsString, new TypeToken<List<Cliente>>(){}.getType());

        clientes.add(cliente);
        salvar(clientes, 2);
    }

    /*
    * Este método recebe como parâmetro o List<Classe>, transforma ele em json e salva na
    * no arquivo da Classe respectiva.
    * */
    public static void salvar(List list, int classe) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        FileWriter fw;
        if (classe == 0) {
            fw = new FileWriter("src/arquivos/cachorros.json");
        } else {
            if (classe == 1) {
                fw = new FileWriter("src/arquivos/gatos.json");
            } else {
                fw = new FileWriter("src/arquivos/clientes.json");
            }
        }
        fw.write(json);
        fw.flush();
        fw.close();
    }
}

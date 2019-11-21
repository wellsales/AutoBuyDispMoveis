package com.example.autobuydm;

public class Carros {

    private String nome;
    private byte[] imagem1;
    private String valor;


    public Carros(String nome, String valor, byte[] imagem1){
        this.nome = nome;
        this.valor = valor;
        this.imagem1 = imagem1;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem1() {
        return imagem1;
    }

    public void setImagem1(byte[] imagem1) {
        this.imagem1 = imagem1;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}

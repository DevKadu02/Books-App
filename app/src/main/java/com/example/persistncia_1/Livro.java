package com.example.persistncia_1;
@SuppressWarnings("SpellCheckingInspection")
public class Livro {
    // Atributos
    private long id;
    private String titulo;
    private String autor;
    private String editora;
    private String ano;
    private String genero;

    // Construtor
    public Livro(long id, String titulo, String autor, String editora, String ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
        this.genero = genero;
    }

    public String textoLista() {
        return "Título: " + getTitulo() +
                "\nAutor: " + getAutor() +
                "\nEditora: " + getEditora() +
                "\nAno: " + getAno() +
                "\nGênero: " + getGenero();
    }


    public Livro() {

    }

    // Métodos de acesso
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (!titulo.isEmpty()) {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (!autor.isEmpty()) {
            this.autor = autor;
        }
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        if (!editora.isEmpty()) {
            this.editora = editora;
        }
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        if(!ano.isEmpty()){

        this.ano = ano;
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (!genero.isEmpty()) {
            this.genero = genero;
        }
    }

}



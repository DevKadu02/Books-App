package com.example.persistncia_1;
import  java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
@SuppressWarnings("SpellCheckingInspection")
public class Livro_DAO {
    private SQLiteDatabase database;
    private String[] columns = {

            LivroSQLiteOpenHelper.COLUNA_ID,
            LivroSQLiteOpenHelper.COLUNA_TITULO,
            LivroSQLiteOpenHelper.COLUNA_AUTOR,
            LivroSQLiteOpenHelper.COLUNA_EDITORA,
            LivroSQLiteOpenHelper.COLUNA_ANO,
            LivroSQLiteOpenHelper.COLUNA_GENERO};
    private LivroSQLiteOpenHelper sqLiteOpenHelper;

    Livro_DAO(Context context) {

        sqLiteOpenHelper = new LivroSQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteOpenHelper.close();
    }

    public void inserir(String titulo, String autor, String editora, String ano, String genero) {
        ContentValues values = new ContentValues();
        values.put(LivroSQLiteOpenHelper.COLUNA_ANO, String.valueOf(ano));
        values.put(LivroSQLiteOpenHelper.COLUNA_AUTOR, autor);
        values.put(LivroSQLiteOpenHelper.COLUNA_EDITORA, editora);
        values.put(LivroSQLiteOpenHelper.COLUNA_GENERO, genero);
        values.put(LivroSQLiteOpenHelper.COLUNA_TITULO, titulo);
        long insertID = database.insert(LivroSQLiteOpenHelper.TABELA, null, values);

    }

    public void alterar(long id, String titulo, String autor, String editora, String ano, String genero) {
        ContentValues values = new ContentValues();
        values.put(LivroSQLiteOpenHelper.COLUNA_ANO, String.valueOf(ano));
        values.put(LivroSQLiteOpenHelper.COLUNA_AUTOR, autor);
        values.put(LivroSQLiteOpenHelper.COLUNA_EDITORA, editora);
        values.put(LivroSQLiteOpenHelper.COLUNA_GENERO, genero);
        values.put(LivroSQLiteOpenHelper.COLUNA_TITULO, titulo);
        database.update(LivroSQLiteOpenHelper.TABELA, values, LivroSQLiteOpenHelper.COLUNA_ID + " = " + id, null);
    }

    public void apagar(long id) {
        database.delete(LivroSQLiteOpenHelper.TABELA, LivroSQLiteOpenHelper.COLUNA_ID + " = " + id, null);
    }

    public Livro buscar(long id) {
        Cursor cursor = database.query(LivroSQLiteOpenHelper.TABELA, columns, LivroSQLiteOpenHelper.COLUNA_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Livro livro = new Livro(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        cursor.close();
        return livro;
    }

    public List<Livro> getAll() {
        List<Livro> livros = new ArrayList<Livro>();
        Cursor cursor = database.query(LivroSQLiteOpenHelper.TABELA, columns, null, null, null, null, null);

        // Certifique-se de mover o cursor para o primeiro registro
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Livro livro = new Livro();
            livro.setId(cursor.getLong(0));
            livro.setAno(cursor.getString(4));
            livro.setAutor(cursor.getString(2));
            livro.setEditora(cursor.getString(3));
            livro.setGenero(cursor.getString(5));
            livro.setTitulo(cursor.getString(1));
            livros.add(livro);
            cursor.moveToNext();
        }
        cursor.close();
        return livros;
    }
}

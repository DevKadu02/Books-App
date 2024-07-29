package com.example.persistncia_1;

import android.content.Context;
import  android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
@SuppressWarnings("SpellCheckingInspection")
public class LivroSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TABELA = "Livro";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_AUTOR = "autor";
    public static final String COLUNA_EDITORA = "editora";
    public static final String COLUNA_ANO = "ano";
    public static final String COLUNA_GENERO = "genero";

    private static final String DATABASE_NAME = "disciplinas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CRIAR_BANCO = "create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_TITULO + " text not null, "
            + COLUNA_AUTOR + " text not null , "
            + COLUNA_ANO + " text not null , "
            + COLUNA_GENERO + " text not null, "
            + COLUNA_EDITORA + " text not null );";

    public LivroSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRIAR_BANCO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion < 2) {

            sqLiteDatabase.execSQL("ALTER TABLE " + TABELA + " ADD COLUMN nova_coluna TEXT;");
        }

    }
}

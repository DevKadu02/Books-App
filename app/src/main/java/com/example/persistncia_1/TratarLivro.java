package com.example.persistncia_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

@SuppressWarnings("SpellCheckingInspection")
public class TratarLivro extends AppCompatActivity {

    EditText ed1 , ed2 , ed3, ed4 , ed5;
    Button bt1 , bt2, bt3;
    TextView tv1;

    private int acao;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_livro);
        bt1 = (Button) findViewById(R.id.button1);
        bt3 = (Button) findViewById(R.id.button3);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
        tv1 = (TextView) findViewById(R.id.textView2);
        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");

        if (acao == -1){
            tv1.setText("Inserir Livro");
            bt1.setText("incluir");
            bt3.setEnabled(false);
            ed1.setText("Titulo");
            ed2.setText("Autor");
            ed3.setText("Editora");
            ed4.setText("Ano");
            ed5.setText("Genero");
        } else {
            tv1.setText("Alterar ou Excluir Livro");
            bt1.setText("Alterar");
            Livro aux = new Livro();
            Livro_DAO dao = new Livro_DAO(this);

            dao.open();

            aux = dao.buscar(id);

            ed1.setText(aux.getTitulo());
            ed2.setText(aux.getAutor());
            ed3.setText(aux.getEditora());
            ed4.setText(aux.getAno());
            ed5.setText(aux.getGenero());

            dao.close();
        }

    }
    public void alterarInserir (View v){
         String titulo;
         String autor;
         String editora;
         String ano;
         String genero;

         titulo = ed1.getText().toString();
         autor = ed2.getText().toString();
         editora = ed3.getText().toString();
         ano = ed4.getText().toString();
         genero = ed5.getText().toString();

         Livro_DAO dao = new Livro_DAO(this);
         dao.open();

        if (acao == -1) {
            dao.inserir(titulo, autor, editora, ano, genero);
        } else {
            setTitle("Alterar ou excluir");
            dao.alterar(id, titulo, autor, editora, ano, genero);
        }
        dao.close();
        finish();
    }
    public void excluir (View v){
        if (acao == 0){
            Livro_DAO dao = new Livro_DAO(this);
            dao.open();
            dao.apagar(id);
            dao.close();
        }
        finish();
    }
    public void voltar (View v){
        finish();
    }
}
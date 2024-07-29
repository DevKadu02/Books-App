package com.example.persistncia_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import android.os.Bundle;
@SuppressWarnings("SpellCheckingInspection")
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lista;
    Intent intent;
    public static final int ACTIVITY_REQUEST_LIVRO = 1;
    private Livro_DAO dao;

    private String[] livros;
    private long[] idLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        dao = new Livro_DAO (this);
        dao.open();
        lista.setOnItemClickListener(this);
    }
    @Override
    protected void onResume(){
        dao.open();
        super.onResume();
        List<Livro> listaLivros = dao.getAll();
        livros = new String[listaLivros.size()];
        idLivros = new long[listaLivros.size()];

        int i = 0;
        Iterator<Livro> iterator = listaLivros.iterator();

        while (iterator.hasNext()){
            Livro aux = new Livro();
            aux = (Livro) iterator.next();
            livros[i] = aux.textoLista();
            idLivros[i] = aux.getId();
            i++;
        }
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(
        this,
                android.R.layout.simple_list_item_1, livros);
        lista.setAdapter(adapter);
    }
    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long ident) {
        long id = idLivros[position];
        intent = new Intent(getApplicationContext(), TratarLivro.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public  void incluirLivro (View v){
        intent = new Intent(getApplicationContext(), TratarLivro.class);
        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);
        startActivity(intent);
    }
    public void sair (View v){
        finish();
    }
}
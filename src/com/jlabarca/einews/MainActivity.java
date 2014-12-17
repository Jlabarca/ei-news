package com.jlabarca.einews;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.jlabarca.einews.noticia.Noticia;
import com.jlabarca.einews.noticia.NoticiaAdapter;
import com.jlabarca.einews.pojo.GoogleNews;
import com.jlabarca.einews.pojo.Result;
import com.jlabarca.einews.rest.GoogleNewsService;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
/**
 * 
 * @author Julio Labarca
 *
 */
public class MainActivity extends Activity implements OnItemClickListener{

	
	PullToRefreshListView listView;
	NoticiaAdapter adapter;
	// Tema del cual se harán las busquedas de noticias
	String tema = "Nintendo";
	// Etiqueta para el log
	String logLayer = "EINEWS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Log.i(logLayer, "PROBANDO");

		// Siempre inicializo
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Agrego el Layout
		this.setContentView(R.layout.main);

		// ListView de noticias
		this.listView = (PullToRefreshListView) this.findViewById(R.id.pull_to_refresh_listview);

		// Construyo el adaptador de noticias
		this.adapter = new NoticiaAdapter(this);

		// Asigna el adaptador al ListActivity
		this.listView.setAdapter(this.adapter);

		// Listener de click
		this.listView.setOnItemClickListener(this);
		
		// Listener de refresh
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				MainActivity.this.refresh();
				Log.i(logLayer, "REFRESHING");
				MainActivity.this.listView.onRefreshComplete();
			}

		});
	}


	/**
	 * 
	 * Carga las noticias guardadas en el archivo en memoria
	 * y luego hace un refresh inicial
	 *
	 */
	@Override
	protected void onStart() {
		super.onStart();
		try {
			adapter.addAll(App.getInstance(this).readNoticias(new File(getCacheDir(), "noticias.json")));
			refresh();
		} catch (final IOException e) {
			Log.i(logLayer, e.toString());
		}
	}
	
	/**
	 * 
	 * Desplega los titulos que originalmente estan ocultos
	 *
	 */
	public void showTitles(){
		ViewGroup ll = ((ViewGroup) findViewById(R.id.pull_to_refresh_listview));
		int childcount = ll.getChildCount();
		for (int i=0; i < childcount; i++){
			try {
				View v = ll.getChildAt(i);
				v.findViewById(R.id.rn_tv_titulo).setVisibility(View.VISIBLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Obtiene noticias desde Internet
	 */
	private void refresh() {
		Log.i(logLayer,"Loading News ..");

		final App app = App.getInstance(this);
		if (app == null) {
			return;
		}

		Log.i(logLayer,"Loading News ..");
		final GoogleNewsService ns = app.getNewsService();

		/*
		 * CallBack!
		 */
		final Callback<GoogleNews> callback = new Callback<GoogleNews>() {

			/**
			 *
			 * @see retrofit.Callback#failure(retrofit.RetrofitError)
			 */
			@Override
			public void failure(final RetrofitError error) {
				Log.i(logLayer, error.toString());
			}
			/**
			 *
			 * @see retrofit.Callback#success(java.lang.Object, retrofit.client.Response)
			 */
			@Override
			public void success(final GoogleNews gnews, final Response response) {
				Log.i(logLayer, gnews.toString());

				final List<Noticia> news = Lists.newArrayList();

				for (final Result r : gnews.getResponseData().getResults()) {

					final Noticia noticia = new Noticia();
					noticia.setTitulo(r.getTitleNoFormatting());
					noticia.setAutor(r.getPublisher());
					if (r.getImage() != null) {
						noticia.setImagen(r.getImage().getUrl());
					}
					noticia.setFecha(new Date(r.getPublishedDate()).getTime());
					noticia.setId(r.getTitleNoFormatting().hashCode());
					noticia.setResumen(r.getContent());
					noticia.setContenido(r.getContent());
					noticia.setUrl(r.getUrl());
					news.add(noticia);

				}
				MainActivity.this.adapter.addAll(news);

				// Write to sd
				try {
					app.saveNoticias(news, new File(MainActivity.this.
							getCacheDir(), "noticias.json"));
				} catch (final IOException e) {
					Log.i(logLayer, e.toString());
				}

			} 
		};

		/** Ejecucion del metodo de busqueda de Noticias de GoogleNews.
		 *  
		 *  Se generan 3 consultas continuas 
		 *  de 4 resultados cada una
		 */ 
		ns.searchGoogleNews(0,tema, callback);
		ns.searchGoogleNews(4,tema, callback);
		ns.searchGoogleNews(8,tema, callback);
		
		// handler para ejecutar el metodo showTitles despues de 5 segundos
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				showTitles();
			}
		}, 5000);
	}
	
	
	/**
	 * Click en una noticia para abrir el detalle de esta
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		try {
			if(!listView.isRefreshing()){
				//titulo dentro de la row_noticia
				View a =  arg1.findViewById(R.id.rn_tv_titulo);
				
				//detalles dentro de la row_noticia 
				View b =  arg1.findViewById(R.id.rn_ll_detalles);
				
				//si el titulo estaba escondido se muestra
				if(a.getVisibility() == View.GONE)
					a.setVisibility(View.VISIBLE);
				else{
					//intent con la nueva activity
					Intent intent = new Intent(this, Detalle.class);
					
					//se añaden parametros que utilizará la nueva activity
					TextView resumen = (TextView)b.findViewById(R.id.rn_tv_resumen);
					intent.putExtra("resumen",resumen.getText().toString());
					TextView title = (TextView)a.findViewById(R.id.rn_tv_titulo);
					intent.putExtra("title",title.getText().toString());
					TextView publisher = (TextView)b.findViewById(R.id.rn_tv_publisher);
					intent.putExtra("publisher",publisher.getText().toString());
					TextView tiempo = (TextView)b.findViewById(R.id.rn_tv_time);
					intent.putExtra("tiempo",tiempo.getText().toString());
					TextView urlimagen = (TextView)b.findViewById(R.id.rn_tv_imageurl);
					intent.putExtra("urlimagen",urlimagen.getText().toString());
					TextView url = (TextView)b.findViewById(R.id.rn_tv_url);
					intent.putExtra("url",url.getText().toString());
					
					//inicia la nueva activity
					startActivity(intent);

				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}

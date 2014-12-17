package com.jlabarca.einews.noticia;

import java.util.Collection;
import java.util.SortedMap;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.jlabarca.einews.R;
import com.squareup.picasso.Picasso;
/**
 * 
 * @author Julio Labarca
 *
 */
public class NoticiaAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private final SortedMap<Long,Noticia> noticias = Maps.newTreeMap();

	public NoticiaAdapter(Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/* Metodo para agregar una coleccion de noticias al adaptador.
	 *
	 * @param noticias
	 */
	public void addAll(final Collection<Noticia> noticias) {

		// Verificacion de nulidad
		Preconditions.checkNotNull(noticias, "Noticias a agregar es null");
		int counter = 0;
		for (final Noticia n : noticias) 
			if (this.noticias.put(n.getId(), n) == null) 
				counter++;
		Log.i("ADAPTER","Se han agregado "+counter +" noticias.");
		if (counter != 0) 
			this.notifyDataSetChanged();

	}

	/**
	 * The number of items in the list is determined by the number of speeches
	 * in our array.
	 * 
	 * @see android.widget.ListAdapter#getCount()
	 */
	public int getCount() {
		return this.noticias.size();
	}


	public Noticia getItem(final int position){
		// Obtengo el identificador de la noticia que se encuentra en la position.
		final Long id = (Long) this.noticias.keySet().toArray()[position];

		// Retorno la noticia en esa posicion.
		return this.noticias.get(id);
	}

	/**
	 * Use the array index as a unique id.
	 * 
	 * @see android.widget.ListAdapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return (Long) this.noticias.keySet().toArray()[position];
	}

	/**
	 * Make a view to hold each row.
	 * 
	 * @see android.widget.ListAdapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		// Si es una fila no existente ..
		if (convertView == null) {
			Log.i("ADAPTER","Inflating");
			// La inflo!
			convertView = this.mInflater.inflate(R.layout.row_noticia, parent, false);
		}

		// Obtengo la noticia en esa posicion
		final Noticia noticia = this.getItem(position);

		// TODO: Agregar la URL de la imagen
		// final ImageView image = ViewHolder.get(convertView, R.id.rn_iv_image);

		final TextView titulo = ViewHolder.get(convertView, R.id.rn_tv_titulo);
		titulo.setText(Html.fromHtml(noticia.getTitulo()));

		final TextView resumen = ViewHolder.get(convertView, R.id.rn_tv_resumen);
		resumen.setText(Html.fromHtml(noticia.getResumen()));

		final TextView autor = ViewHolder.get(convertView, R.id.rn_tv_publisher);
		autor.setText(noticia.getAutor());

		final TextView fecha=ViewHolder.get(convertView, R.id.rn_tv_time);
		fecha.setText(noticia.getTime());

		final TextView imageurl = ViewHolder.get(convertView, R.id.rn_tv_imageurl);
		imageurl.setText(noticia.getImagen());

		final TextView url = ViewHolder.get(convertView, R.id.rn_tv_url);
		url.setText(noticia.getUrl());

		ImageView image=(ImageView)convertView.findViewById(R.id.rn_iv_image);
		Picasso.with(convertView.getContext()).load(noticia.getImagen()).fit().centerCrop().into(image);

		return convertView;


	}
}

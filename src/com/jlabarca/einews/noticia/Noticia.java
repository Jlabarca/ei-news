package com.jlabarca.einews.noticia;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.annotation.SuppressLint;

import com.google.common.collect.Lists;

/**
 * 
 * @author Julio Labarca
 *
 */

public class Noticia {
	
	/**
	 * Identificador unico de la noticia
	 */
	private long id;
	private String titulo;
	private String resumen;
	private String contenido;
	private String url;
	
	/**
	 * Listado de tags dados por el sistema
	 */
	private final List<String> tag=Lists.newArrayList();
	
	/**
	 * Fecha de la noticia, ha sido representada como ticks o milisegundos
	 * han transcurrido desde el 1 de enero de 1970 
	 */
	private long fecha;
	
	/**
	 * URL de la imagen de 64x64 que representa la noticia
	 */
	private String imagen;
	
	/**
	 * Nombre del autor, deberia incluir el correo electronico
	 */
	private String autor;
	
	/**
	 * Numero de likes que ha recibido la noticia
	 */
	private int likes;
	
	/**
	 * Constructor vaio para utilizar las bondandes de GSON
	 */
	public Noticia(){
	}
	
	/**
	 * @return id
	 */
	public final long getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @param id
	 * @return id to set
	 */
	public final Noticia setId(final long id){
		this.id=id;
		return this;
	}
	
	/**
	 * @return titulo
	 */
	public final String getTitulo(){
		return this.titulo;
	}
	
	/**
	 * @param titulo
	 * @return titulo to set
	 */
	public final Noticia setTitulo(final String titulo){
		this.titulo=titulo;
		return this;
	}
	
	/**
	 * @return resumen
	 */
	public final String getResumen(){
		return this.resumen;
	}
	
	
	/**
	 * @param resumen
	 * @return resumen to set
	 */
	public final Noticia setResumen(final String resumen){
		this.resumen=resumen;
		return this;
	}
	
	/**
	 * @return contenido 
	 */
	public final String getContenido(){
		return this.contenido;
	}
	
	/**
	 * @param contenido
	 * @return contenido to set
	 */
	public final Noticia setContenido(final String contenido){
		this.contenido=contenido;
		return this;
	}
	
	/**
	 * @return fecha
	 */
	public final long getFecha(){
		return this.fecha;
	}
	
	/**
	 * 
	 * @param fecha
	 * @return fecha to set
	 */
	public final Noticia setFecha(final long fecha){
		this.fecha=fecha;
		return this;
	}
	
	/**
	 * @return imagen
	 */
	public final String getImagen(){
		return this.imagen;
	}
	
	/**
	 * @param imagen
	 * @return imagen to set
	 */
	public final Noticia setImagen(final String imagen){
		this.imagen=imagen;
		return this;
	}
	
	/**
	 * @return autor
	 */
	public final String getAutor(){
		return this.autor;
	}
	
	/**
	 * @param autor
	 * @return autor to set
	 */
	public final Noticia setAutor(final String autor){
		this.autor=autor;
		return this;
	}

	/**
	 * @return likes
	 */
	public final int getLikes(){
		return this.likes;
	}
	
	/**
	 * @param likes
	 * @return likes to set
	 */
	public final Noticia setLikes(final int likes){
		this.likes=likes;
		return this;
	}
	
	/**
	 * @return tag
	 */
	public final List<String> getTag(){
		return this.tag;
	}
	
	@SuppressLint("DefaultLocale")
	public String getTime(){
		long duration=System.currentTimeMillis()-this.fecha;
	    String res = "";
	    long days  = TimeUnit.MILLISECONDS.toDays(duration);
	    long hours = TimeUnit.MILLISECONDS.toHours(duration)
	                   - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
	                     - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
	                   - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
	   
	    if (days == 0 && hours == 0 && minutes < 2) {
	    	  res = "hace un momento";
	    }
	    else if (days == 0 && hours == 0) {
	    	  res = String.format("hace %02d minutos %02d segundos", minutes, seconds);
	    }
	    else if (days == 0) {
		      res = String.format("hace %02d horas %02d minutos %02d segundos", hours, minutes, seconds);
		    }
	    else {
		      res = String.format("%dd dias %02d horas %02d minutos %02d segundos", days, hours, minutes, seconds);
		  }
	    return res;
		
	}
	
	  
	/*
	 * @see java.lang.Object#toString()
	 * @see org.apache.commons.lang.builder.ToStringBuilder#
		reflectionToString(Object)
	 */
	 @Override
	 public final String toString() {
		 // Implementacion por medio de la libreria commons-lang.
		 return ToStringBuilder.reflectionToString(this);
	 }
	
	 /**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @see org.apache.commons.lang.builder.EqualsBuilder#
		lectionEquals(Object, Object)
	 */
	 @Override
	 public boolean equals(final Object obj) {
		 return EqualsBuilder.reflectionEquals(this, obj);
	 }

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
	

	
	
}


	


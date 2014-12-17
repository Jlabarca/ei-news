package com.jlabarca.einews;

import java.util.List;

import com.google.common.collect.Lists;
import com.jlabarca.einews.noticia.Noticia;
/**
 * 
 * @author Julio Labarca
 *
 */
public class Mockup {

	public static final Mockup INSTANCE=new Mockup();

	public List<Noticia> buildNoticias(){
		final List<Noticia> noticias=Lists.newArrayList();

		noticias.add(new Noticia()
		.setId(2)
		.setLikes(13)
		.setAutor("Diego P. Urrutia <durrutia@ucn.cl>")
		.setTitulo("Solo hasta el miércoles hay plazo para postular a rendir la PSU gratis")
		.setResumen("A las 13:00 del 8 de octubre...")
		.setImagen("https://33.media.tumblr.com/aa4e5718981cc81cf89a6fbd9b79b659/tumblr_nfj3qbOZm91rb8pu7o1_1280.jpg")
		.setFecha(1417753666839L));

		noticias.add(new Noticia()
		.setId(1)
		.setLikes(14)
		.setAutor("Prensa UCN <prensa@ucn.cl>")
		.setTitulo("Esterilizarán a cuatro mil perros y gatos en Antofagasta gracias a clinica móvil")
		.setResumen("El vehículo recorrerá la ciudad...")
		.setImagen("https://38.media.tumblr.com/5d28f715de7340c261cc2ca3a202f6b5/tumblr_nbyd420pgH1qimrito3_1280.png")
		.setFecha(1417752570034L));

		noticias.add(new Noticia()
		.setId(0)
		.setLikes(12)
		.setAutor("Prensa UCN <prensa@ucn.cl>")
		.setTitulo("Emprendedores antofagastinos podrán potenciar sus negocios en el extranjero")
		.setResumen("Los beneficiarios corresponden al programa de entrenamiento...")
		.setImagen("https://33.media.tumblr.com/d4cb477d75c1e1586f69df11132305e9/tumblr_ncdoa6DMb31t2c7h9o5_500.jpg")
		.setFecha(1416742970034L));
		return noticias;

	}


}
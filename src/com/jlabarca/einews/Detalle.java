package com.jlabarca.einews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
/**
 * Activity con el detalle de la noticia, la imagen mas grande y un boton para ir a la pagina web
 * @author Julio Labarca
 *
 */
public class Detalle extends Activity{
	//url de la noticia
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// layout detalles
		this.setContentView(R.layout.more_noticia);
		// se toman los parametros recibidos y se insertan a las distintas Views
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			((TextView)findViewById(R.id.rn_tv_titulo)).setText(extras.getString("title"));
			((TextView)findViewById(R.id.rn_tv_resumen)).setText(extras.getString("resumen"));
			((TextView)findViewById(R.id.rn_tv_time)).setText(extras.getString("tiempo"));
			((TextView)findViewById(R.id.rn_tv_publisher)).setText(extras.getString("publisher"));


			try {
				ImageView image=(ImageView)findViewById(R.id.rn_iv_image);
				Picasso.with(this).load(extras.getString("urlimagen")).fit().centerCrop().into(image);
			} catch (Exception e) {
				e.printStackTrace();
			}

			url = extras.getString("url");

		}
	}

	public void linkToWeb(View view) {
		//la url debe ser pasada a unicode
		Uri uriUrl = Uri.parse(Uri.decode(url));
		Log.i("URIQL", uriUrl.toString());
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);	    
	}


}

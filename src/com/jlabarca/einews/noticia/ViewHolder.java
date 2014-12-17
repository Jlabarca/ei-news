package com.jlabarca.einews.noticia;


import android.util.SparseArray;
import android.view.View;

/**
 * @author Diego P. Urrutia <durrutia@ucn.cl>
 * http://plus.google.com/107264729678111825621/posts/aykMojDjYxU
 */
public class ViewHolder {

	// I added a generic return type to reduce the casting noise in client code
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(final View view, final int id) {

		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}

		View childView = viewHolder.get(id);

		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}

		return (T) childView;
	}
}
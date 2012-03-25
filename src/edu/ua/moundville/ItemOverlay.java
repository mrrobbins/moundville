package edu.ua.moundville;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ItemOverlay extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
	
	ItemOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public void addItem(OverlayItem item) {
		items.add(item);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return items.get(i);
	}

	@Override
	public int size() {
		return items.size();
	}
	
	public boolean onTap (final GeoPoint p, final MapView mapView){
	    boolean tapped = super.onTap(p, mapView);
	    if (tapped){            
	        //do what you want to do when you hit an item - i.e. drawInfoBox(...)    
	    }           
	    else{
	        //do what you want to do when you DONT hit an item
	        }                   
	    return true;
	}

	//You must have this method, even if it doesn't visibly do anything

	@Override
	protected boolean onTap(int index) {
	    return true;
	}
	
}

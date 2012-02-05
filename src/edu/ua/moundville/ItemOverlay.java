package edu.ua.moundville;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
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
	
}

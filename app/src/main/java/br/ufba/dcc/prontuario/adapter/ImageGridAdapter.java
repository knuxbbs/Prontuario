package br.ufba.dcc.prontuario.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import br.ufba.dcc.prontuario.R;

public class ImageGridAdapter extends BaseAdapter {

    private Context context;
    private String[] items;
    LayoutInflater inflater;

    public ImageGridAdapter(Context context, String[] items){
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.image_grid_cell, null);
        }

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.grid_image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}

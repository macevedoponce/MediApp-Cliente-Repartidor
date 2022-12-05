package acevedo.EvalFin.org.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import acevedo.EvalFin.org.Clases.Categoria;
import acevedo.EvalFin.org.Mediapp.ui.InicioFragment;
import acevedo.EvalFin.org.R;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder> implements View.OnClickListener{

    Context context;
    List<Categoria> categoriaList;
    View.OnClickListener listener;

    public CategoriaAdapter(Context contex, List<Categoria> categoriaList) {
        this.context = contex;
        this.categoriaList = categoriaList;
    }


    @NonNull
    @NotNull
    @Override
    public CategoriaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.cat_item, parent,false);
        mView.setOnClickListener(this);
        return new CategoriaHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriaHolder holder, int position) {
        Categoria categoria = categoriaList.get(position);
        holder.setRuta_imagen(categoria.getRuta_imagen());
        holder.setNombre(categoria.getNombre());
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class CategoriaHolder extends RecyclerView.ViewHolder {

        ImageView imgCat;
        TextView txtNomCat;
        View view;

        public CategoriaHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setRuta_imagen(String ruta_imagen){
            imgCat = view.findViewById(R.id.imgCat);
            Glide.with(context).load(ruta_imagen).into(imgCat);
        }
        public void setNombre(String nombre){
            txtNomCat = view.findViewById(R.id.txtNomCat);
            txtNomCat.setText(nombre);
        }

    }
}

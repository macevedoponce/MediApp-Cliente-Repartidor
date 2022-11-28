package acevedo.EvalFin.org.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import acevedo.EvalFin.org.Clases.Producto;
import acevedo.EvalFin.org.Mediapp.DetalleProductoActivity;
import acevedo.EvalFin.org.Mediapp.ListProductosActivity;
import acevedo.EvalFin.org.R;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> implements View.OnClickListener{

    Context context;
    List<Producto> productoList;
    View.OnClickListener listener;

    public ProductoAdapter(Context context, List<Producto> mList) {
        this.context = context;
        this.productoList = mList;
    }


    @NonNull
    @NotNull
    @Override
    public ProductoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.prod_item, parent,false);
        mView.setOnClickListener(this);
        return new ProductoHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductoHolder holder, int position) {
        Producto producto = productoList.get(position);
        String precio = producto.getPrecio_venta()+"";
        holder.setRuta_imagen(producto.getRuta_imagen());
        holder.setNombre(producto.getNombre());
        holder.setPresentacion(producto.getPresentacion());
        holder.setPrecio(precio);

    }

    @Override
    public int getItemCount() {
        return productoList.size();
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

    public class ProductoHolder extends RecyclerView.ViewHolder {
        ImageView imgProducto;
        TextView txtPresentacion, txtNombre, txtPrecio, txtStock;
        View view;

        public ProductoHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setRuta_imagen(String ruta_imagen){
            imgProducto = view.findViewById(R.id.imgProducto);
            Glide.with(context).load(ruta_imagen).into(imgProducto);
        }
        public void setNombre(String nombre){
            txtNombre = view.findViewById(R.id.txtNombre);
            txtNombre.setText(nombre);
        }
        public void setPresentacion(String presentacion){
            txtPresentacion = view.findViewById(R.id.txtPresentacion);
            txtPresentacion.setText(presentacion);
        }
        public void setPrecio(String precio){
            txtPrecio = view.findViewById(R.id.txtPrecio);
            txtPrecio.setText(precio);
        }


    }
}

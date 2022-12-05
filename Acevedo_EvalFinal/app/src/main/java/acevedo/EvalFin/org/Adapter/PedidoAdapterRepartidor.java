package acevedo.EvalFin.org.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import acevedo.EvalFin.org.Clases.Pedido;
import acevedo.EvalFin.org.R;

public class PedidoAdapterRepartidor extends RecyclerView.Adapter<PedidoAdapterRepartidor.PedidoHolder> implements View.OnClickListener{

    Context context;
    List<Pedido> pedidoList;
    View.OnClickListener listener;


    public PedidoAdapterRepartidor(Context context, List<Pedido> mList) {
        this.context = context;
        this.pedidoList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public PedidoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.pedido_repartidor_item, parent,false);
        mView.setOnClickListener(this);
        return new PedidoHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidoHolder holder, int position) {
        Pedido pedido = pedidoList.get(position);

        int estado = pedido.getEstado();
        int id_pedido = pedido.getId();
        String txtPersona = pedido.getPersona_recepcion();
        String txtProducto = pedido.getNombreProducto();
        String txtPrecio = pedido.getTotal();

        holder.setEstado(estado);
        holder.setCodigo(id_pedido);
        holder.setPersona(txtPersona);
        holder.setProducto(txtProducto);
        holder.setPrecio(txtPrecio);
    }

    @Override
    public int getItemCount() {
        return pedidoList.size();
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

    public class PedidoHolder extends RecyclerView.ViewHolder {

        TextView txtEstadoPedido,txtPersona,txtCodigo,txtProducto,txtPrecio;
        View view;


        public PedidoHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setEstado(int estado){
            txtEstadoPedido = view.findViewById(R.id.txtEstadoPedido);
            if(estado == 0){
                txtEstadoPedido.setText("PEDIDO LIBRE");
            }else {
                txtEstadoPedido.setText("PEDIDO OCUPADO");
                txtEstadoPedido.setTextColor(Color.rgb(74,133,222));
            }
        }


        public void setCodigo(int id_pedido) {
            txtCodigo = view.findViewById(R.id.txtCodigo);
            txtCodigo.setText(id_pedido+"");
        }

        public void setPersona(String Persona) {
            txtPersona = view.findViewById(R.id.txtPersona);
            txtPersona.setText(Persona);
        }

        public void setProducto(String Producto) {
            txtProducto = view.findViewById(R.id.txtProducto);
            txtProducto.setText(Producto);
        }

        public void setPrecio(String Precio) {
            txtPrecio = view.findViewById(R.id.txtPrecio);
            txtPrecio.setText(Precio);
        }
    }
}

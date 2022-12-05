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

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoHolder> implements View.OnClickListener{

    Context context;
    List<Pedido> pedidoList;
    View.OnClickListener listener;


    public PedidoAdapter(Context context, List<Pedido> mList) {
        this.context = context;
        this.pedidoList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public PedidoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.pedido_item, parent,false);
        mView.setOnClickListener(this);
        return new PedidoHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidoHolder holder, int position) {
        Pedido pedido = pedidoList.get(position);

        int estado = pedido.getEstado();
        int id_pedido = pedido.getId();
        String total = pedido.getTotal();
        String fecha = pedido.getFecha();

        holder.setEstado(estado);
        holder.setIdPedido(id_pedido);
        holder.setTotal(total);
        holder.setFecha(fecha);
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

        TextView txtEstadoPedido,txtIdPedido,txtTotal,txtFecha;
        View view;


        public PedidoHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setEstado(int estado){
            txtEstadoPedido = view.findViewById(R.id.txtEstadoPedido);
            if(estado == 0){
                txtEstadoPedido.setText("PEDIDO RECIBIDO");
            }else {
                txtEstadoPedido.setText("PEDIDO ENTREGADO");
                txtEstadoPedido.setTextColor(Color.rgb(74,133,222));
            }
        }

        public void setIdPedido(int id_pedido) {
            txtIdPedido = view.findViewById(R.id.txtIdPedido);
            txtIdPedido.setText(id_pedido+"");
        }

        public void setTotal(String total) {
            txtTotal = view.findViewById(R.id.txtTotal);
            txtTotal.setText(total);
        }

        public void setFecha(String fecha) {
            txtFecha = view.findViewById(R.id.txtFecha);
            txtFecha.setText(fecha);
        }

    }
}

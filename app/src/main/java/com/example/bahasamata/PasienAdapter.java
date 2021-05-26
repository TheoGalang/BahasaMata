package com.example.bahasamata;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;
import java.util.Random;

public class PasienAdapter extends RecyclerView.Adapter <PasienAdapter.PasienlistViewHolder> {

    private List<Pasienlist> listPasien;
    private Context mContext;
    Dialog dialogdetailpasien;
    Button btn_simpan;
    Random nomoracak;
    EditText namapasiendialog,idpasiendialog;



    public PasienAdapter(List<Pasienlist> myDataset, Context context) {
        this.listPasien= myDataset;
        this.mContext = context;
        dialogdetailpasien = new Dialog(mContext);
        dialogdetailpasien.setContentView(R.layout.dialog_detailpasien);
        dialogdetailpasien.setCancelable(true);
        btn_simpan = dialogdetailpasien.findViewById(R.id.btn_simpandetail_pasien);
        namapasiendialog = dialogdetailpasien.findViewById(R.id.et_namapasien);
        idpasiendialog = dialogdetailpasien.findViewById(R.id.et_Idpasien);


    }

    @NonNull
    @Override
    public PasienlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.viewlistpasien, parent, false);
        final PasienlistViewHolder vHolder = new PasienlistViewHolder(v);



        nomoracak = new Random();


        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PasienlistViewHolder holder, int position) {
        holder.namapasien.setText(listPasien.get(position).namapasien);
        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
        holder.viewlistpasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = position;
                dialogdetailpasien.show();
                namapasiendialog.setText(listPasien.get(temp).namapasien);
                idpasiendialog.setText(String.valueOf(listPasien.get(temp).idpasien));
                btn_simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listPasien.set(temp,new Pasienlist(namapasiendialog.getText().toString(), nomoracak.nextInt()));
                        dialogdetailpasien.hide();
                        notifyDataSetChanged();
                    }


                });
            }
        });
    }
    void remove(int position) {
        int temp = position;
       listPasien.remove(position);
        notifyItemChanged(position);
        notifyItemRangeRemoved(position, 1);
    }


    @Override
    public int getItemCount() {
        return listPasien.size();
    }


    public class PasienlistViewHolder extends RecyclerView.ViewHolder {
        TextView namapasien;




        private CardView viewlistpasien;
        public ImageButton ib_settings,ib_delete;

        public PasienlistViewHolder(@NonNull View itemView) {
            super(itemView);

            ib_delete = itemView.findViewById(R.id.btn_deletepasien);
            namapasiendialog = dialogdetailpasien.findViewById(R.id.et_namapasien);
            idpasiendialog = dialogdetailpasien.findViewById(R.id.et_Idpasien);
            ib_settings = itemView.findViewById(R.id.btn_settings);
            viewlistpasien = itemView.findViewById(R.id.cv_listpasien);
            namapasien= itemView.findViewById(R.id.tv_namapasien);




        }
    }
}

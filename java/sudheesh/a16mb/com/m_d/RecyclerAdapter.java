package sudheesh.a16mb.com.m_d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by KUTTAN on 23-11-2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private  String[] name={"Php Retrive single file","Php retrive from DB","BAR/QR Code Scanner","tec..","Samples","cardview","toolbars","tec.."};

    class  ViewHolder extends RecyclerView.ViewHolder{

        public  int names;
        public TextView t;
        public ViewHolder(final View itemView) {
            super(itemView);
            t= (TextView) itemView.findViewById(R.id.textid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context=v.getContext();

                    int pos=getAdapterPosition();
                   // Snackbar.make(v,"Clicked pos:"+pos,Snackbar.LENGTH_LONG)
                     //       .setAction("Action",null).show();

                    switch (pos){
                        case 0:

                            Intent intent=new Intent(context,Basic.class);
                            context.startActivity(intent);

                            break;
                        case 1:
                            Intent intent1=new Intent(context,dbview_java.class);
                            context.startActivity(intent1);
                            Snackbar.make(v,"Clicked pos 2",Snackbar.LENGTH_LONG)
                                    .show();
                            break;

                        case 2:
                            Intent intent2=new Intent(context,barcode.class);
                            context.startActivity(intent2);
                            Snackbar.make(v,"Barcode",Snackbar.LENGTH_LONG)
                                    .show();

                        default:
                            Snackbar.make(v,"Try Again :(",Snackbar.LENGTH_LONG)
                                    .show();

                    }



                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlaout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.t.setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}

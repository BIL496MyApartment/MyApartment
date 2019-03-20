
package com.bil495calendear.bitirmeprojesi;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.mobilhanem.androidcrudexample.R;

import java.util.List;

/**
 * Created by alper on 23/04/2017.
 */

public class MyApartmentsAdapter extends RecyclerView.Adapter<MyApartmentsAdapter.ViewHolder> {

    List<Apartment> apartmentList;
    Context context;
    private ItemClickListener clickListener;

    public MyApartmentsAdapter(Context context, List<Apartment> apartmentList) {

        this.apartmentList = apartmentList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView apartmentName;
        public TextView apartmentAddress;
        public ImageView apartmentPhoto;
        public ImageView optionMenu;


        public ViewHolder(View view) {
            super(view);

            apartmentName = itemView.findViewById(R.id.apartmentName);
            apartmentAddress = itemView.findViewById(R.id.apartmentAddress);
            apartmentPhoto = itemView.findViewById(R.id.apartment_photo);
            optionMenu = itemView.findViewById(R.id.optionMenu);
            view.setTag(view);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    @Override
    public MyApartmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apartmentadapter_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.apartmentName.setText(apartmentList.get(position).getApartmentName());
        holder.apartmentAddress.setText(apartmentList.get(position).getAdress());
        holder.apartmentPhoto.setImageResource(R.drawable.user);
        holder.optionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(context, holder.optionMenu);
                popup.inflate(R.menu.menu_items);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                //update customer
                                //updateCustomer(apartmentList.get(position));
                                break;
                            case R.id.delete:
                                //delete customer
                                //deleteCustomer(apartmentList.get(position).getApartmentID());
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    private Apartment updateApartment;
    /*
    private void updateCustomer(Apartment apartment) {

        updateApartment = apartment;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final Spinner spinnerUpdate = (Spinner) dialogView.findViewById(R.id.spinnerUpdate);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateCustomer);

        editTextName.setText(updateCustomer.getCustomerName());

        dialogBuilder.setTitle("Müşteri Düzenleme");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextName.getText().length()>0){

                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("customers").child(updateApartment.getCustomerId());
                    Apartment apartment = new Apartment(updateApartment.getApartmentID(),editTextName.getText().toString(),spinnerUpdate.getSelectedItem().toString());
                    dR.setValue(apartment);
                    b.dismiss();
                    Toast.makeText(context, "Müşteri Güncellendi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteCustomer(String id) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("customers").child(id);
        dR.removeValue();
        DatabaseReference drOrders = FirebaseDatabase.getInstance().getReference("orders").child(id);
        drOrders.removeValue();
        Toast.makeText(context ,"Müşteri Silindi", Toast.LENGTH_SHORT).show();
    }
    */
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return apartmentList.size();
    }
}


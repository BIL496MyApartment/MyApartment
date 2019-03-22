
package com.bil495calendear.bitirmeprojesi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

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
                                leaveFromApartment(apartmentList.get(position).getApartmentID());
                                break;
                            case R.id.show_apartmentID:
                                String s = apartmentList.get(position).getApartmentID();
                                Intent intentRegister = new Intent(context, ShowApartmentID.class);
                                intentRegister.putExtra("send_string",s);
                                context.startActivity(intentRegister);


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

    private DatabaseReference databaseReferenceApartments;
    private FirebaseUser firebaseUser;
    private String userid;

    private List<String>userList;

    private Apartment updateApartment;

    private void leaveFromApartment(String id) {


        final String paramId =id;

        databaseReferenceApartments = FirebaseDatabase.getInstance().getReference("Apartments");

        databaseReferenceApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                userid = firebaseUser.getUid();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Apartment apartment = postSnapshot.getValue(Apartment.class);

                    if (apartment.getApartmentID().equals(paramId)) {
                        boolean isAdmin = false;
                        userList = apartment.getUserIDList();
                        updateApartment = apartment;
                        if (!userList.isEmpty()) {
                            for (int i = 0; i < userList.size(); i++) {
                                if (userid.equals(userList.get(i))) {
                                    userList.remove(i);
                                    if(i==0){// Admin quit
                                        isAdmin=true;
                                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Apartments").child(updateApartment.getApartmentID());
                                        Apartment newapartment = new Apartment(updateApartment.getApartmentName(),
                                                updateApartment.getCity(),updateApartment.getAdress(),updateApartment.getUserIDList(), updateApartment.getApartmentID());
                                        dR.setValue(newapartment);
                                        Toast.makeText(context, "Apartmandan yönetici olarak ayrıldıgınız ve yeni yönetici sizden sonra apartmana kayıt olan bir kişi hala varsa ona atanmıştır.", Toast.LENGTH_SHORT).show();
                                    }

                                }


                            }
                            if (isAdmin==false) {

                                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Apartments").child(updateApartment.getApartmentID());
                                Apartment newapartment = new Apartment(updateApartment.getApartmentName(),
                                        updateApartment.getCity(),updateApartment.getAdress(),updateApartment.getUserIDList(),userList.get(0), updateApartment.getApartmentID());
                                dR.setValue(newapartment);
                                Toast.makeText(context, "Apartmandan kullanıcı olarak ayrıldınız.", Toast.LENGTH_SHORT).show();


                            }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return apartmentList.size();
    }
}


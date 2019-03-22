package com.bil495calendear.bitirmeprojesi;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {


    public FriendsFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;

    private FriendAdapter friendAdapter;
    private List<User> mUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends,container,false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mUsers = new ArrayList<>();
        readUsers();

        return view;
    }

    private void readUsers() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference aptRef = FirebaseDatabase.getInstance().getReference("Apartments");

        /*aptRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                User aptAdmin = new User();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Apartment apartment = snapshot.getValue(Apartment.class);
                    List ap = new ArrayList<>();
                    ap = apartment.getUserIDList();
                    aptAdmin.setId(apartment.getAdminID());
                    aptAdmin.setImageURL("default");
                    aptAdmin.setUsername(getUsername(apartment.getAdminID()));

                    mUsers.add(aptAdmin);

                    for(int i = 1 ; i<ap.size();i++){
                        if (!ap.get(i).equals(firebaseUser.getUid())){
                            if(!mUsers.contains(getUser((String)ap.get(i))))
                                mUsers.add(getUser((String)ap.get(i)));

                        }
                    }

                }
                friendAdapter = new FriendAdapter(getContext(),mUsers);
                recyclerView.setAdapter(friendAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);


                    assert user != null;
                    assert firebaseUser != null;
                    if(!user.getId().equals(firebaseUser.getUid())){
                        mUsers.add(user);
                    }
                }

                friendAdapter = new FriendAdapter(getContext(),mUsers);
                recyclerView.setAdapter(friendAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
   /* private static String getUsername(final String userid){
        final User[] toRet = new User[1];
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    if (userid.equals(user.getId())){
                        toRet[0] = new User(userid,user.getUsername(),user.getImageURL());
                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return toRet[0].getUsername();
    }
    private static User getUser(final String userid){
        final User[] toRet = new User[1];
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    if (userid.equals(user.getId())){
                        toRet[0] = new User(userid,user.getUsername(),user.getImageURL());
                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return toRet[0];
    }
*/
}

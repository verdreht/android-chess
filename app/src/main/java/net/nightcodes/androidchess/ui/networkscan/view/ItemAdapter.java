package net.nightcodes.androidchess.ui.networkscan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import net.nightcodes.androidchess.R;

import net.nightcodes.androidchess.client.Client;
import net.nightcodes.androidchess.client.ClientHandler;
import net.nightcodes.androidchess.client.packet.PacketType;
import net.nightcodes.androidchess.client.packet.ServerJoinPacket;
import net.nightcodes.androidchess.server.broadcast.objects.RemoteServer;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<RemoteServer> remoteServers;

    // Pass in the contact array into the constructor
    public ItemAdapter(List<RemoteServer> remoteServers) {
        this.remoteServers = remoteServers;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.join_host, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        RemoteServer server = remoteServers.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(server.getName());

        TextView addressTextView = holder.addressTextView;
        addressTextView.setText(server.getAddress().getHostAddress());

        Button joinButton = holder.joinButton;

        joinButton.setOnClickListener(view -> new Client(server.getAddress(), server.getPort()).sendPackets(
                    Collections.singletonList(new ServerJoinPacket().build(PacketType.SERVER_JOIN, new JsonObject()))));


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return remoteServers.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView addressTextView;
        public Button joinButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.host_name);
            addressTextView = itemView.findViewById(R.id.host_ip);
            joinButton = itemView.findViewById(R.id.message_button);
        }
    }
}
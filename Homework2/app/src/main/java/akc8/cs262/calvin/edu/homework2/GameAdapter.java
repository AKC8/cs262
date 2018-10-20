package akc8.cs262.calvin.edu.homework2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<GameItem>  {
    private Context gmContext;

    public GameAdapter(Context context, ArrayList<GameItem> data) {
        super(context, R.layout.game_item, data);
        this.gmContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GameItem playerItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(gmContext);
            convertView = inflater.inflate(R.layout.game_item, parent, false);
        }

        if (playerItem != null) {
            TextView playerText = (convertView.findViewById(R.id.game_item_string));
            playerText.setText(playerItem.getString());
        }
        return convertView;
    }
}

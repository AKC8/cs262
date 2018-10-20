package akc8.cs262.calvin.edu.lab05;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.support.v4.content.AsyncTaskLoader;
import org.json.JSONObject;
import org.json.JSONArray;

public class FetchBook extends AsyncTask<String,Void,String>{
    private TextView mTitleText;
    private TextView mAuthorText;

    private String mQueryString

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }

    @Override
    protected String doInBackground(String... params) {
        return NetworkUtils.getBookInfo(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for(int i = 0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i);
                String title=null;
                String authors=null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");


                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }


                if (title != null && authors != null){
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    return;
                }
            }


            mTitleText.setText("No Results Found");
            mAuthorText.setText("");

        } catch (Exception e){
            mTitleText.setText("No Results Found");
            mAuthorText.setText("");
            e.printStackTrace();
        }
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
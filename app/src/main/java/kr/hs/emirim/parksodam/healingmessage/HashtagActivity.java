package kr.hs.emirim.parksodam.healingmessage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 민경 on 2018-03-16.
 */

public class HashtagActivity extends ClickableSpan {


    public interface ClickEventListener{
        void onClickEvent(String data);
    }

    private ClickEventListener mClickEventListener = null;
    private Context context;
    private TextPaint textPaint;
    private List<String> mTagLists;
    TextView tags_view;

    public HashtagActivity(Context ctx){
        super();
        context = ctx;
    }

    public void setOnClickEventListener(ClickEventListener listener){
        mClickEventListener = listener;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        textPaint = ds;
        ds.setColor(ds.linkColor);
        ds.setARGB(255, 30, 144, 255);
    }

    @Override
    public void onClick(View widget) {
        TextView tv = (TextView) widget;
        Spanned s = (Spanned) tv.getText();
        int start = s.getSpanStart(this);
        int end = s.getSpanEnd(this);
        String theWord = s.subSequence(start + 1, end).toString();
        mClickEventListener.onClickEvent(theWord);
    }

    private void setContent(){
        String tag = "";
        int i;
        for(i = 0 ; i < mTagLists.size(); i++){
            tag += "#" + mTagLists.get(i) + " ";
        }

        ArrayList<int[]> hashtagSpans = getSpans(tag, '#');

        SpannableString tagsContent = new SpannableString(tag);

        for(i = 0; i < hashtagSpans.size(); i++){
            int[] span = hashtagSpans.get(i);
            int hashTagStart = span[0];
            int hashTagEnd = span[1];

            HashtagActivity hashTag = new HashtagActivity(context);
            hashTag.setOnClickEventListener(new HashtagActivity.ClickEventListener() {
                @Override
                public void onClickEvent(String data) {
                }
            });

            tagsContent.setSpan(hashTag,
                    hashTagStart,
                    hashTagEnd,
                    0);
        }

        tags_view = (TextView) tags_view.findViewById(R.id.textview_tag);
        if(tags_view != null) {
            tags_view.setMovementMethod(LinkMovementMethod.getInstance());
            tags_view.setText(tagsContent);
        }
    }

    public ArrayList<int[]> getSpans(String body, char prefix) {
        ArrayList<int[]> spans = new ArrayList<int[]>();

        Pattern pattern = Pattern.compile(prefix + "\\w+");
        Matcher matcher = pattern.matcher(body);

        // Check all occurrences
        while (matcher.find()) {
            int[] currentSpan = new int[2];
            currentSpan[0] = matcher.start();
            currentSpan[1] = matcher.end();
            spans.add(currentSpan);
        }

        return  spans;
    }
}
package com.digitcreativestudio.dzulfikar68.myrecyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";
    private Context context;
    TextView tvObject;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvObject = (TextView)findViewById(R.id.tv_object_received);
        imgPhoto = (ImageView)findViewById(R.id.img_detail_photo);

        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        Glide.with(this)
                .load(person.getPhoto())
                .override(350,350)
                .into(imgPhoto);
        String text = "<b>" + "Nama: " + "</b> " + person.getName() + ",<br><br><b>" + "Deskripsi: " + "</b> " + person.getDescription();
        tvObject.setText(Html.fromHtml(text));
    }
}

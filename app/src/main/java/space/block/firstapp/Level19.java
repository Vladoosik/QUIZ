package space.block.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class Level19 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная для правой картинки + текст
    Array array = new Array (); //создали новый обьект из класса Array
    Random random = new Random (); //для генерации случайных чисел
    public int count = 0; //Счетчик правильных ответов
    public InterstitialAd interstitialAd; //реклама
    public int transition = 0;


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Реклама - начало
        MobileAds.initialize(this, "ca-app-pub-3943717185593488~2375802540");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3943717185593488/5095951767");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        //Реклама- конец

        //Закрытие рекламного блока с помощью крестика - начало
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                try {
                    switch (transition){
                        case 0: break;
                        case 1: Intent intent = new Intent(Level19.this,Level20.class);
                            startActivity(intent);finish();break;
                        case 2:Intent intent1 = new Intent(Level19.this,GameLevels.class);
                            startActivity(intent1);finish();break;
                        default:break;
                    }

                }catch (Exception e){
                    //пусто
                }
            }
        });
        //Закрытие рекламного блока с помощью крестика - конец

        //СОздаем переменную Text Levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText (R.string.level19); // Установили текст
        text_levels.setTextColor(R.color.black85);


        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код который скругляет углы левой картинки
        img_left.setClipToOutline (true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код который скругляет углы правой картинки
        img_right.setClipToOutline (true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black85);
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black85);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //развернуть игру на весь экран - конец

        //Утснавливаем фон  - начало
        ImageView background = (ImageView)findViewById(R.id.imageView2);
        background.setImageResource(R.drawable.level19);
        //Устанавливаем фон  - конец

        //Вызов диалогового окна в начале уровня
        dialog = new Dialog(this); //создаем диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //команда которая скрывает заголовок
        dialog.setContentView(R.layout.prewiewdialog); //путь к картинке диалогового окна
        dialog.getWindow(). setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад

        //Устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg19);
        //устанавливаем картинку в диалоговое окно - конец

        //Утснавливаем фон диалогового окна - начало
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground19);
        //Утснавливаем фон диалогового окна - конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescriptiom);
        textdescription.setText(R.string.lvlnineteen);
        textdescription.setTextColor(R.color.black85);
        //Устанавливаем описание задания - конец

        //кнопка которая закрывает диалоговое окно- начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setTextColor(R.color.black85);
        btnclose.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатывает нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level19.this, GameLevels.class); //создали намерения для переххода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //тут кода.нет (она нужна для уловки ошибок)
                }
                dialog.dismiss(); //конструкция которая закрывает диалоговое окно
                //Обрабатывает нажатие кнопки - начало
            }
        });
        //кнопка которая закрывает диалоговое окно- конец

        //Кнопка "Продолжить" - начало
        Button btncountiniue = (Button)dialog.findViewById(R.id.btncontiniue);
        btncountiniue.setTextColor(R.color.black85);
        btncountiniue.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btncountiniue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //Кнопка ""Продолжить - конец

        dialog.show(); //показать диалоговое окно

        //________________________________________
        //Вызов диалогового окна в конце уровня
        dialogEnd = new Dialog(this); //создаем диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //команда которая скрывает заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к картинке диалогового окна
        dialogEnd.getWindow(). setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой назад

        //Устанавливаем фон диалогового окна- начало
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground19);
        //Устанавливаем фон диалогового окна- конец

        //Интересный факт - начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptiomEnd);
        textdescriptionEnd.setText(R.string.lvlnineteenEnd);
        textdescriptionEnd.setTextColor(R.color.black85);
        //Интересныый факт - конец

        //кнопка которая закрывает диалоговое окно- начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setTextColor(R.color.black85);
        btnclose2.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатывает нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level19.this, GameLevels.class); //создали намерения для переххода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //тут кода.нет (она нужна для уловки ошибок)
                }
                dialogEnd.dismiss(); //конструкция которая закрывает диалоговое окно
                //Обрабатывает нажатие кнопки - начало
            }
        });
        //кнопка которая закрывает диалоговое окно- конец

        //Кнопка "Продолжить" - начало
        Button btncountiniue2 = (Button)dialogEnd.findViewById(R.id.btncontiniue);
        btncountiniue2.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btncountiniue2.setTextColor(R.color.black85);
        btncountiniue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    transition=1;
                    interstitialAd.show();
                } else {
                    try {
                        Intent intent = new Intent(Level19.this, Level20.class);
                        startActivity(intent);
                        finish();

                    } catch (Exception e) {
                    }
                    dialogEnd.dismiss();
                }
            }
        });
        //Кнопка ""Продолжить - конец
        //________________________________________

        //кнопка "назад" - начало
        Button btn_back = (Button) findViewById(R.id.button_back_lvl1);
        btn_back.setTextColor(R.color.black85);
        btn_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатываем нажатие кнопки начало
                if (interstitialAd.isLoaded()){
                    transition=2;
                    interstitialAd.show(); //Показать рекламу
                }else {
                    try {
                        //Вернуться назад к выбору уровня - начало
                        Intent intent = new Intent(Level19.this, GameLevels.class); //создали намерения для перехода
                        startActivity(intent);
                        finish();
                        //Вернуться назад к выбору уровня - конец
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //Обрабатываем нажатие кнопки конец
            }

        });
        //кнопка "назад" - конец

        //Массив для прогресса игры - начало
        final int [] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        //Макссив для прогресса игры - конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level19.this, R.anim.alpha );
        //подключаем анимацию конец

        numLeft = random.nextInt(20); //Генерация случайного числа
        img_left.setImageResource(array.images19[numLeft]); //Достаем из масива картинку
        text_left.setText(array.texts19[numLeft]); //Достаем из массива текст

        numRight = random.nextInt(20); //Генерация случайного числа

        //Цикл с предусловием, проверяющий равенство чисел - начало
        while (array.strong8[numLeft]==array.strong8[numRight]){
            numRight = random.nextInt(20);
        }
        //Цикл с предусловием, проверяющий равенство чисел - конец

        img_right.setImageResource(array.images19 [numRight]); //Достаем из массива картинку
        text_right.setText(array.texts19 [numRight]); //Достаем из массива текст

        //Обрабатываем нажатие левой картинки - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условия начала картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);  //Блокируем правую картинку
                    if (array.strong8[numLeft]>array.strong8[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец 
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (array.strong8[numLeft]>array.strong8[numRight]){
                        //Если левая картинка больше
                        if (count<20){
                            count = count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                            for (int i=0; i<20; i++){
                                TextView tv = findViewById(progress [i]);
                                tv.setBackgroundResource(R.drawable.tyle_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                            for (int i=0; i<count; i++){
                                TextView tv = findViewById(progress [i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }else {
                        //если левая картинка меньше
                        if (count>0){
                            if (count ==1){
                                count = 0;
                            }else {
                                count = count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.tyle_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    }
                    //Если отпустил палец - конец
                        if (count ==20){
                            //Выход из уровня
                            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                            final int level = save.getInt("Level",1);
                            if (level>19){
                                //пусто
                            }else{
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("Level",20);
                                editor.commit();
                            }
                            dialogEnd.show();
                        }else {
                            numLeft = random.nextInt(20); //Генерация случайного числа
                            img_left.setImageResource(array.images19[numLeft]); //Достаем из масива картинку
                            img_left.startAnimation(a);
                            text_left.setText(array.texts19[numLeft]); //Достаем из массива текст

                            numRight = random.nextInt(20); //Генерация случайного числа

                            //Цикл с предусловием, проверяющий равенство чисел - начало
                            while (array.strong8[numLeft]==array.strong8[numRight]){
                                numRight = random.nextInt(20);
                            }
                            //Цикл с предусловием, проверяющий равенство чисел - конец

                            img_right.setImageResource(array.images19 [numRight]); //Достаем из массива картинку
                            img_right.startAnimation(a);
                            text_right.setText(array.texts19 [numRight]); //Достаем из массива текст
                            img_right.setEnabled(true); //Включаем обратно правую картинку
                        }
                }
                //Условия начала картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие левой картинки - коенц

        //Обрабатываем нажатие правой картинки - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условия начала картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);  //Блокируем левую картинку
                    if (array.strong8[numLeft]<array.strong8[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (array.strong8[numLeft]<array.strong8[numRight]){
                        //Если правая картинка больше
                        if (count<20){
                            count = count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.tyle_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }else {
                        //если правая картинка меньше
                        if (count>0){
                            if (count ==1){
                                count = 0;
                            }else {
                                count = count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.tyle_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress [i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    }
                    //Если отпустил палец - конец
                    if (count ==20){
                        //Выход из уровня
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level>19){
                            //пусто
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",20);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else {
                        numLeft = random.nextInt(20); //Генерация случайного числа
                        img_left.setImageResource(array.images19[numLeft]); //Достаем из масива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts19[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(20); //Генерация случайного числа

                        //Цикл с предусловием, проверяющий равенство чисел - начало
                        while (array.strong8[numLeft]==array.strong8[numRight]){
                            numRight = random.nextInt(20);
                        }
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images19 [numRight]); //Достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts19 [numRight]); //Достаем из массива текст
                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                //Условия начала картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие правой картинки - коенц


    }

    //Системная кнопка "назад" - начало
    @Override
    public void  onBackPressed (){
        //Обрабатываем нажатие кнопки начало
        if (interstitialAd.isLoaded()){
            transition=2;
            interstitialAd.show(); //показать рекламу
        }else {
            try {
                //Вернуться назад к выбору уровня - начало
                Intent intent = new Intent(Level19.this, GameLevels.class); //создали намерения для перехода
                startActivity(intent);
                finish();
                //Вернуться назад к выбору уровня - конец
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Обрабатываем нажатие кнопки конец
    }
    //Системная кнопка "назад" - конец

}
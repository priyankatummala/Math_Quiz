package com.example.priyankatummala.mathquiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
//import java.util.logging.Handler;


public class quizScreen extends AppCompatActivity   {

    Handler handler = new Handler();
    Runnable mRunnable;

    int count =1, right=0;
    int i;
    private Button mOne, mTwo, mThree,mFour,mFive,mSix,mSeven,mEight,mNine, mZero,mNext;
    String answer="";
   // int res=0;
   // public static Question q;
    Fragment question;
    Fragment timer_frag;
    FragmentTransaction trans;
    Fragment progress_indicator;
    FragmentManager fm;
    String incorrect="Incorrect";
    String correct="Correct";
    boolean res=false;
    int duration = Toast.LENGTH_SHORT;
    long time_remaining=5000,cur_time;
    Bundle operator_sign;

    Question callanswer;
    CountDownTimer countdownTimer;

    public void startTimer(long remaining){

        countdownTimer = new CountDownTimer( remaining, 1000) {

            public void onTick(long millisUntilFinished) {
                //Log.d("Time before",String.valueOf(time_remaining));
                cur_time=millisUntilFinished;
                if(callanswer == null) {

                    callanswer = (Question) fm.findFragmentById(R.id.question);
                    Log.d("Time after",String.valueOf(millisUntilFinished));
                    callanswer.tick(millisUntilFinished);
                }
                else
                {
                    callanswer.tick(millisUntilFinished);
                    //Log.d("Time after2",String.valueOf(millisUntilFinished));
                }


            }

            public void onFinish() {
                callanswer = (Question) fm.findFragmentById(R.id.question);
                //Toast toast = Toast.makeText(getApplicationContext(), incorrect, duration);
                //toast.show();
                AlertDialog.Builder alert = new AlertDialog.Builder(quizScreen.this).setTitle("")
                        .setMessage("Incorrect ").setCancelable(false);

                final AlertDialog closedialog= alert.create();

                closedialog.show();

                TextView messageText = (TextView)closedialog.findViewById(android.R.id.message);
                messageText.setGravity(Gravity.CENTER);
                closedialog.show();


                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        closedialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 1000);

               // showResult();

                count++;
                if (count <= 10) {
                    //callanswer.setValues();
                    //countdownTimer.cancel();
                    showResult();
                    //startTimer(5000);
                    //callanswer.setProgress();
                    //count++;
                } else {
                    String msg = "You scored " + right + " out of " + (count-1) + " !";
                    //callanswer.onDestroy();
                    countdownTimer.cancel();
                    //callanswer.onDestroy();
                    new AlertDialog.Builder(quizScreen.this).setTitle("Summary")
                            .setMessage(msg).setPositiveButton(android.R.string
                            .yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            quizScreen.this.finish();
                        }
                    }).show();
                    count = 1;
                    //finish();
                }

                answer = "";


            }
        }.start();



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        //count =1;


        Log.d("In","onCreate");
        if(savedInstanceState !=null){
            Log.d("In","onCreate not null");
            count = savedInstanceState.getInt("count");
            right=savedInstanceState.getInt("right");
            answer=savedInstanceState.getString("answer");
            time_remaining=savedInstanceState.getLong("timeLeft");
            Log.d("Time remaining",String.valueOf(time_remaining));
            //savedInstanceState=null;


            // trans = fm.beginTransaction().replace(R.id.question,question);
            // trans.commit();



        }

        Log.d("In","onCreate not null 1");
        //callanswer.setRetainInstance(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("In","onCreate not null 2");
        fm = getSupportFragmentManager();
        Log.d("In","onCreate not null 3");

        question = fm.findFragmentById(R.id.question);
        callanswer=(Question) fm.findFragmentById(R.id.question);



       // else {

            //question = fm.findFragmentById(R.id.question);
            //callanswer=(Question) fm.findFragmentById(R.id.question);
            trans = fm.beginTransaction();

            Intent intent = getIntent();
            String operator_value = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            operator_sign = new Bundle();
            operator_sign.putString("op", operator_value);
            Bundle timer_signal = new Bundle();
            //countdownTimer.cancel();
            startTimer(time_remaining);

    //    }


//        res = q.randomInt1 + q.randomInt2;
        //Log.d("res",String.valueOf(q.randomInt1));


        if (count <= 10) {
            mOne = (Button) findViewById(R.id.one_btn);
            mOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "1";


                    if (sendanswertoquestion(answer)) {

                        // Log.d("str","success");
                    }

                }
            });

            mTwo = (Button) findViewById(R.id.two_btn);
            mTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    answer = answer + "2";
                    if (sendanswertoquestion(answer)) {

                        Log.d("str", "success");
                    }


                }
            });
            mThree = (Button) findViewById(R.id.three_btn);
            mThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "3";
                    if (sendanswertoquestion(answer)) {

                        //Log.d("str","success");
                    }
                }
            });
            mFour = (Button) findViewById(R.id.four_btn);
            mFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    answer = answer + "4";
                    if (sendanswertoquestion(answer)) {

                        // Log.d("str","success");
                    }
                }
            });
            mFive = (Button) findViewById(R.id.five_btn);
            mFive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "5";
                    if (sendanswertoquestion(answer)) {

                        // Log.d("str","success");
                    }

                }
            });
            mSix = (Button) findViewById(R.id.six_btn);
            mSix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "6";
                    if (sendanswertoquestion(answer)) {

                        //Log.d("str","success");
                    }

                }
            });

            mSeven = (Button) findViewById(R.id.seven_btn);
            mSeven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "7";
                    if (sendanswertoquestion(answer)) {

                        //Log.d("str","success");
                    }

                }
            });

            mEight = (Button) findViewById(R.id.eight_btn);
            mEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "8";
                    if (sendanswertoquestion(answer)) {

                        //Log.d("str","success");
                    }

                }
            });

            mNine = (Button) findViewById(R.id.nine_btn);
            mNine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    answer = answer + "9";
                    if (sendanswertoquestion(answer)) {
                        //Log.d("str","success");
                    }


                }
            });

            mZero = (Button) findViewById(R.id.zero_btn);
            mZero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = answer + "0";
                    if (sendanswertoquestion(answer)) {
                        //Log.d("str","success");
                    }
                }
            });

            mNext = (Button) findViewById(R.id.next_btn);
            mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer = "";
                    if (callanswer == null) {
                        callanswer = (Question) fm.findFragmentById(R.id.question);
                    }
                    if (res == false) {
                        //Toast toast = Toast.makeText(getApplicationContext(), incorrect, duration);
                        //toast.show();

                        countdownTimer.cancel();
                        AlertDialog.Builder alert = new AlertDialog.Builder(quizScreen.this).setTitle("")
                                .setMessage("Incorrect ").setCancelable(true);

                        final AlertDialog closedialog= alert.create();

                        closedialog.show();

                        TextView messageText = (TextView)closedialog.findViewById(android.R.id.message);
                        messageText.setGravity(Gravity.CENTER);
                        closedialog.show();
                        countdownTimer.cancel();

                        final Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            public void run() {
                                closedialog.dismiss();
                                timer2.cancel(); //this will cancel the timer of the system
                            }
                        }, 1000);


                    }

                    //showResult();

                   count++;
                    Log.d("Count", String.valueOf(count));
                    if (count <= 10) {
                        //callanswer.setValues();
                        //countdownTimer.cancel();
                        showResult();
                        //startTimer(5000);
                        callanswer.setProgress();
                        //count++;
                    } else {
                        String msg = "You scored " + right + " out of " + (count-1) + " !";
                        countdownTimer.cancel();
                        new AlertDialog.Builder(quizScreen.this).setTitle("Summary")
                                .setMessage(msg).setPositiveButton(android.R.string
                                .yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                quizScreen.this.finish();
                            }
                        }).show();
                        count = 1;
                        //finish();
                    }


                }
            });
        }
        else {
            String msg = "You scored " + right + " out of " + (count-1) + " !";
            new AlertDialog.Builder(quizScreen.this).setTitle("Summary")
                    .setMessage(msg).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            count = 1;


        }





            if (question == null) {

                question = new Question();
                timer_frag = new timer();
                question.setArguments(operator_sign);
                trans.add(R.id.question, question);
//                Log.d("res2",String.valueOf(q.randomInt1));
                trans.commit();
                //q=new Question();
                //res = q.randomInt1 + q.randomInt2;
                //Log.d("res2",String.valueOf(q.getRandomInt1()));

            } else {
                //question.setArguments(operator_sign);
                trans.replace(R.id.question, question);
                trans.commit();
            }


    }



    /*@Override
    public void onResume() {
        super.onResume();
        count=1;
    }*/
    public boolean checkAnswer() {


        return false;
    }

   /* public void startTimer(){
       // countdownTimer.cancel();
        countdownTimer.start();


//        if(countdownTimer != null){
//            countdownTimer.cancel();
//        }
//        else {
//            countdownTimer.cancel();
//            countdownTimer.start();
//        }

    }*/

    public boolean sendanswertoquestion(String text){
        //question.getFragmentManager()
        if (callanswer == null)
        {
            callanswer=(Question) fm.findFragmentById(R.id.question);
        }
        res=callanswer.checkresult(text);

       if(res)
       {

          //Toast toast = Toast.makeText(this, Correct+" : " + text, duration);
           //toast.show();
           countdownTimer.cancel();
          AlertDialog.Builder alert = new AlertDialog.Builder(quizScreen.this).setTitle("")
                   .setMessage("Correct ").setCancelable(true);

           final AlertDialog closedialog= alert.create();
           res=false;

           closedialog.show();




           //AlertDialog dialog = closedialog.show(); //builder is your just created builder
           TextView messageText = (TextView)closedialog.findViewById(android.R.id.message);
           messageText.setGravity(Gravity.CENTER);
           closedialog.show();
           countdownTimer.cancel();

           final Timer timer2 = new Timer();
           timer2.schedule(new TimerTask() {
               public void run() {
                   closedialog.dismiss();
                   timer2.cancel(); //this will cancel the timer of the system
               }
           }, 1000);

           answer="";

          // handler.postDelayed(new Runnable() {
            //   public void run() {

           count++;
           Log.d("CCount",String.valueOf(count));
           right++;
           if(count<=10) {
               //callanswer.setValues();
               //countdownTimer.cancel();
               //startTimer(5000);
               showResult();
               answer="";
               int count_up=callanswer.setProgress();
              // if(count_up !=count) {

                   Log.d("timeout","complete");
                  // count = count_up;
                    answer="";
             //  }

           }
           else
           {
               Log.d("in","else");
               String msg = "You scored " + right + " out of "+ (count-1) +" !";
               new AlertDialog.Builder(quizScreen.this).setTitle("Summary")
                       .setMessage(msg).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);

                   }
               }).show();
               count =1;
               //finish();
           }


              // }
           //}, 1000);
       }

        return res;

    }

    @Override
    public void onDestroy() {
        countdownTimer.cancel();
        super.onDestroy();

        //Store data


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String msg = "Are you sure you want to quit?";

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                quizScreen.this.finish();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle
        savedInstanceState.putInt("count",count);
        savedInstanceState.putInt("right",right);
        savedInstanceState.putString("answer",answer);
        savedInstanceState.putLong("timeLeft", cur_time);
        savedInstanceState.putBundle("bundle",operator_sign);
        countdownTimer.cancel();



        //savedInstanceState.putString(right, someStringValue);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    /* public void showResult(){
        handler.postDelayed(new Runnable() {
            public void run() {

                count++;
                Log.d("CCount",String.valueOf(count));
                right++;
                if(count<=10) {
                    callanswer.setValues();
                    countdownTimer.cancel();
                    startTimer(5000);
                    answer="";
                    int count_up=callanswer.setProgress();
                    // if(count_up !=count) {

                    Log.d("timeout","complete");
                    // count = count_up;
                    answer="";
                    //  }

                }
                else
                {
                    String msg = "You scored " + right + " out of "+ (count-1) +" !";
                    new AlertDialog.Builder(getApplicationContext()).setTitle("Summary")
                            .setMessage(msg).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        }
                    }).show();
                    count =1;
                    //finish();
                }


            }
        }, 1000);
    } */

    public void showResult(){
        handler.postDelayed(new Runnable() {
            public void run() {


                    callanswer.setValues();
                    countdownTimer.cancel();
                startTimer(5000);
                callanswer.setProgress();

            }
        }, 1000);
    }





}

package com.example.priyankatummala.mathquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.Random;


public class Question extends Fragment {

    public Question data;

    Random randomGenerator = new Random();
    public  int randomInt1, randomInt2;


    int r1max=9, r1min=5;
    int r2max=5, r2min=0;
    public static int result=0;
    View cur_view;
    public String userRes;
    TextView res;
    String operator;
    TextView timerValue, progress_state;
    int cur_question=0;




    public void tick(long millisUntilFinished)
    {
       timerValue = (TextView) cur_view.findViewById(R.id.timerValue);
        timerValue.setText("Time Left: " + millisUntilFinished / 1000);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Question newInstance(String param1, String param2) {
        Question fragment = new Question();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if(savedInstanceState !=null){
            Log.d("Retreiving saved","values1");
            operator = savedInstanceState.getString("operator");
            randomInt1 = savedInstanceState.getInt("operand1");
            randomInt2 = savedInstanceState.getInt("operand2");
            cur_question = savedInstanceState.getInt("currentQ");

            //savedInstanceState = null;


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cur_view=inflater.inflate(R.layout.fragment_question, container, false);


       if(savedInstanceState !=null){

           Log.d("setting saved","values1");
            TextView sign = (TextView) cur_view.findViewById(R.id.operator);
            sign.setText(operator);

           Log.d("rans1",String.valueOf(randomInt1));
            TextView value1 = (TextView) cur_view.findViewById(R.id.operand1);
            value1.setText(String.valueOf(randomInt1));

            TextView value2 = (TextView) cur_view.findViewById(R.id.operand2);
            value2.setText(String.valueOf(randomInt2));

           progress_state = (TextView) cur_view.findViewById(R.id.progress);
           setProgress();
        }



        else {


            operator = getArguments().getString("op");
            TextView sign = (TextView) cur_view.findViewById(R.id.operator);
            sign.setText(operator);

            setValues();

            timerValue = (TextView) cur_view.findViewById(R.id.timerValue);
            //startTimer();

            progress_state = (TextView) cur_view.findViewById(R.id.progress);
            setProgress();
        }

        // Inflate the layout for this fragment
        return cur_view;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    public boolean checkresult(String text){
        boolean ansmatch=false;
        int sentresult=Integer.valueOf(text);
        res=(TextView) cur_view.findViewById(R.id.result);
        res.setText(text);
        //SystemClock.sleep(1000);
        //Log.d("Priyanka",String.valueOf(result));
        if(result==sentresult){

            ansmatch= true;

        }
        return ansmatch;
    }
    public void setValues(){

        Log.d("Setting From", "Question");
        //Set first operand
        randomInt1 = getRandomNumberInRange(r1min,r1max);
        String op1 = Integer.toString(randomInt1);
        TextView value1 = (TextView) cur_view.findViewById(R.id.operand1);
        value1.setText(op1);

        //Set second operand
        randomInt2 = getRandomNumberInRange(0,randomInt1);
        String op2 = Integer.toString(randomInt2);
        TextView value2 = (TextView) cur_view.findViewById(R.id.operand2);
        value2.setText(op2);
        res=(TextView) cur_view.findViewById(R.id.result);
        res.setText("");

        if(operator.equals("+")) {
            result = randomInt1 + randomInt2;
            //Log.d("rand1", String.valueOf(randomInt1));
            //Log.d("rand2", String.valueOf(randomInt2));
        }

        if(operator.equals("-")) {
            result = randomInt1 - randomInt2;
        }

        if(operator.equals("*")) {
            result = randomInt1 * randomInt2;
        }

       // startTimer();
        cur_question++;
        //Log.d("curQ", String.valueOf(cur_question));
    }

    public int setProgress(){

        //cur_question++;
        if(cur_question <=10) {
            String progress_msg = "Question " + cur_question + " of 10";
            TextView progress = (TextView) cur_view.findViewById(R.id.progress);
            progress.setText(progress_msg);
            return cur_question;
        }
        else
        {
            int ret = cur_question;
            cur_question=0;
            return ret;
        }

    }


    public void setData(int rand1, int rand2, String op) {

        TextView value1 = (TextView) cur_view.findViewById(R.id.operand1);
        value1.setText(rand1);

        TextView value2 = (TextView) cur_view.findViewById(R.id.operand2);
        value1.setText(rand2);

        TextView sign = (TextView) cur_view.findViewById(R.id.operator);
        sign.setText(op);

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        savedInstanceState.putInt("operand1",randomInt1);
        savedInstanceState.putInt("operand2",randomInt2);
        savedInstanceState.putString("operator",operator);
        savedInstanceState.putInt("currentQ",cur_question);

    }

}

package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MessageFragment extends BaseFragment {

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }


<<<<<<< HEAD
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            try {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }catch(Exception e){

            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
=======
>>>>>>> master

    @Override
    public String getTitle() {
        return "메세지";
    }

}

package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class SetFragment extends BaseFragment {

    public SetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

<<<<<<< HEAD
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        return "도움말";
    }

}

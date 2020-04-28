package net.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import net.test.R


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewProfile.setImageResource(R.drawable.godfather)
        nameValue.text = "Marc Marquez"
        birthdayValue.text = "02/3/1993"
        cityValue.text = "Cervera (Spain)"
        descValue.text =
            "Marc Márquez Alentà (born 17 February 1993) is a Spanish Grand Prix motorcycle road" +
                    " racer and one of the most successful motorcycle racers of all time with eight" +
                    " Grand Prix world championships to his name - six of which are in the premier" +
                    " MotoGP class. Márquez races for Honda's factory team since his MotoGP debut" +
                    " in 2013, his most recent contract running until 2024."
    }
}

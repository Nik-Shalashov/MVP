package ru.android.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.android.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener {
            presenter.counter1Click()
        }
        vb?.btnCounter2?.setOnClickListener {
            presenter.counter2Click()
        }
        vb?.btnCounter3?.setOnClickListener {
            presenter.counter3Click()
        }

    }

    override fun showCounter1(counter: String) {
        vb?.btnCounter1?.text = counter
    }

    override fun showCounter2(counter: String) {
        vb?.btnCounter2?.text = counter
    }

    override fun showCounter3(counter: String) {
        vb?.btnCounter3?.text = counter
    }

}

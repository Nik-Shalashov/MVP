package ru.android.mvp

class MainPresenter(val view: MainView) {
    private val model = CountersModel()

    fun counter1Click() {
        val nextValue  = model.next(0)
        view.showCounter1(nextValue.toString())
    }fun counter2Click() {
        val nextValue  = model.next(1)
        view.showCounter2(nextValue.toString())
    }fun counter3Click() {
        val nextValue  = model.next(2)
        view.showCounter3(nextValue.toString())
    }
}
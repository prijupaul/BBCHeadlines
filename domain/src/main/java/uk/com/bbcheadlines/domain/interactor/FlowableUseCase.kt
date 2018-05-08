package uk.com.bbcheadlines.domain.executor.interactor.browse

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.executor.ThreadExecutor

abstract class FlowableUseCase<T, in Params> constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun constructUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(observer: DisposableSubscriber<T>, params: Params? = null) {
        val observable = constructUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Flowable<T>
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}
package uk.com.bbcheadlines.bbcheadlines

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
package uk.com.bbcheadlines.domain.executor.interactor.browse

import io.reactivex.Flowable
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.executor.ThreadExecutor
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.domain.repository.NewsRepository
import javax.inject.Inject

open class GetNews @Inject constructor(val newsRepository: NewsRepository,
                                       threadExecutor: ThreadExecutor,
                                       postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<News>,Void>(threadExecutor,postExecutionThread) {

    public override fun constructUseCaseObservable(params: Void?): Flowable<List<News>> {
        return newsRepository.getNews()
    }
}

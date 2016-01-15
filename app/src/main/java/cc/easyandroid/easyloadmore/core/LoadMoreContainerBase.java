package cc.easyandroid.easyloadmore.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 */
public abstract class LoadMoreContainerBase extends FrameLayout {
    LoadMoreHandler loadMoreHandler;
    public LoadMoreContainerBase(Context context) {
        super(context);
    }

    public LoadMoreContainerBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreContainerBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadMoreHandler = retrieveLoadMoreHandler();
//        init();
    }

    abstract  void addOnScrollListener(AbsListView.OnScrollListener l);

    abstract  void addFooterView(View view);

    abstract   void removeFooterView(View view);

    abstract  void removeOnScrollListener(AbsListView.OnScrollListener l);

    protected abstract LoadMoreHandler retrieveLoadMoreHandler();
}

package cc.easyandroid.easyloadmore.core;

import android.view.View;
import android.widget.AbsListView;

public interface LoadMoreHandler {
    void addOnScrollListener(AbsListView.OnScrollListener l);

    void addFooterView(View view);

    void removeFooterView(View view);

    void removeOnScrollListener(AbsListView.OnScrollListener l);
}

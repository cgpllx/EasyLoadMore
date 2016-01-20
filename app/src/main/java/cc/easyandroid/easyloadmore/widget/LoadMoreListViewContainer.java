package cc.easyandroid.easyloadmore.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * @author huqiu.lhq
 */
public class LoadMoreListViewContainer extends LoadMoreContainerBase {

    private ListView mListView;
    private Scroller mScroller; // used for scroll back
    public LoadMoreListViewContainer(Context context) {
        super(context);
    }
    KListViewFooter mFooterView;
    public LoadMoreListViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context, new DecelerateInterpolator());
        // init footer view
        mFooterView = new KListViewFooter(context);
        /* 2014 04 22 cgp */
        mFooterView.hide();
    }

    @Override
    protected void addFooterView(View view) {
        mListView.addFooterView(view);
    }

    @Override
    protected void removeFooterView(View view) {
        mListView.removeFooterView(view);
    }

    @Override
    protected AbsListView retrieveAbsListView() {
        mListView = (ListView) getChildAt(0);
        return mListView;
    }
    private float mLastY = -1; // save event y

    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if(mListView!=null){
                    if (mListView.getLastVisiblePosition() == mListView.getCount() && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                        // last item, already pulled up or want to pull up.
                        updateFooterHeight(-deltaY / OFFSET_RADIO);
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (mListView.getLastVisiblePosition() == mListView.getCount()) {
                    // invoke load more.
                    if (mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }

                break;
        }
        return super.onTouchEvent(ev);
    }
    /**
     * 加载一次后必须停下才能加载第二次
     */
    public void startLoadMore() {
//        if (mListViewListener != null) {
            if (!mPullLoading) {
                // flipPage();
//                mListViewListener.onLoadMore();
            }
//        }
        mPullLoading = true;
        mFooterView.setState(KListViewFooter.STATE_LOADING);
    }
    private final static int SCROLL_DURATION = 400; // scroll back duration
    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
//            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }
    private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px
    private boolean mEnablePullLoad = true;// +++++++F++++++++++++++++++++加载
    private boolean mPullLoading = false;// +++++++F++++++++++++++++++++加载
    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                // more.
                mFooterView.setState(KListViewFooter.STATE_READY);
            } else {
                mFooterView.setState(KListViewFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);// 解决list item中间一条线问题（在item没有充满屏幕时候出现）

        // setSelection(mTotalItemCount - 1); // scroll to bottom
    }
}

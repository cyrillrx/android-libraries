package com.cyrillrx.android.widget.slidingtab;

import androidx.fragment.app.Fragment;

/**
 * A pager item for fragments.
 *
 * @author Cyril Leroux
 *         Created on 29/10/14.
 */
public class PagerItem<PageFragment extends Fragment> {

    private final CharSequence title;
    private final PageFragment fragment;

    public PagerItem(CharSequence title, PageFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public CharSequence getTitle() { return title; }

    public PageFragment getFragment() { return fragment; }
}
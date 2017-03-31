package com.cyrillrx.android.toolbox;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Schedule a countdown until a time in the future, with
 * regular notifications on intervals along the way.
 * <p/>
 * Example of showing a 30 second countdown in a text field:
 * <p/>
 * <pre class="prettyprint">
 * new CountdownTimer(30000, 1000) {
 * <p/>
 * public void onTick(long millisUntilFinished) {
 * mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
 * }
 * <p/>
 * public void onFinish() {
 * mTextField.setText("done!");
 * }
 * }.start();
 * </pre>
 * <p/>
 * The calls to {@link #onTick(long)} are synchronized to this object so that
 * one call to {@link #onTick(long)} won't ever occur before the previous
 * callback is complete.  This is only relevant when the implementation of
 * {@link #onTick(long)} takes an amount of time to execute that is significant
 * compared to the countdown interval.
 */
public abstract class CountDownTimer {

    /** Millis since epoch when alarm should stop. */
    protected long millisInFuture;

    /** The interval in millis that the user receives callbacks */
    protected final long countdownInterval;

    private long stopTimeInFuture;

    private boolean cancelled = false;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimer(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        countdownInterval = countDownInterval;
    }

    /**
     * Cancel the countdown.
     * <p/>
     * Do not call it from inside CountDownTimer threads
     */
    public final void cancel() {
        handler.removeMessages(MSG);
        cancelled = true;
    }

    /**
     * Start the countdown.
     */
    public synchronized final CountDownTimer start() {
        if (millisInFuture <= 0) {
            onFinish();
            return this;
        }
        stopTimeInFuture = SystemClock.elapsedRealtime() + millisInFuture;
        handler.sendMessage(handler.obtainMessage(MSG));
        cancelled = false;
        return this;
    }

    public long getMillisLeft() { return stopTimeInFuture - SystemClock.elapsedRealtime(); }

    /**
     * Callback fired on regular interval.
     *
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();

    private static final int MSG = 1;

    // handles counting down
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimer.this) {
                final long millisLeft = stopTimeInFuture - SystemClock.elapsedRealtime();

                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < countdownInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);

                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + countdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) { delay += countdownInterval; }

                    if (!cancelled) {
                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        }
    };
}

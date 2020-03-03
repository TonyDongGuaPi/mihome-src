package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

public final class SerialSubscription implements Subscription {
    final AtomicReference<State> state = new AtomicReference<>(new State(false, Subscriptions.empty()));

    private static final class State {
        final boolean isUnsubscribed;
        final Subscription subscription;

        State(boolean z, Subscription subscription2) {
            this.isUnsubscribed = z;
            this.subscription = subscription2;
        }

        /* access modifiers changed from: package-private */
        public State unsubscribe() {
            return new State(true, this.subscription);
        }

        /* access modifiers changed from: package-private */
        public State set(Subscription subscription2) {
            return new State(this.isUnsubscribed, subscription2);
        }
    }

    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }

    public void unsubscribe() {
        State state2;
        AtomicReference<State> atomicReference = this.state;
        do {
            state2 = atomicReference.get();
            if (state2.isUnsubscribed) {
                return;
            }
        } while (!atomicReference.compareAndSet(state2, state2.unsubscribe()));
        state2.subscription.unsubscribe();
    }

    public void set(Subscription subscription) {
        State state2;
        if (subscription != null) {
            AtomicReference<State> atomicReference = this.state;
            do {
                state2 = atomicReference.get();
                if (state2.isUnsubscribed) {
                    subscription.unsubscribe();
                    return;
                }
            } while (!atomicReference.compareAndSet(state2, state2.set(subscription)));
            state2.subscription.unsubscribe();
            return;
        }
        throw new IllegalArgumentException("Subscription can not be null");
    }

    public Subscription get() {
        return this.state.get().subscription;
    }
}

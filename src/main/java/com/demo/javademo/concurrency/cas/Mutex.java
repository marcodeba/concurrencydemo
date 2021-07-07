package com.demo.javademo.concurrency.cas;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {
    private Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public void unlock () {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire (int arg) {
            return super.compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease (int arg) {
            super.setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively () {
            return super.getState() == 1;
        }
    }
}

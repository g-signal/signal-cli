package org.asamk.signal.manager.internal;

import org.signal.core.util.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignalLogger extends Log.Logger {

    private static final Logger logger = LoggerFactory.getLogger("LibSignalService");

    public static void initLogger() {
        Log.initialize(() -> true, new SignalLogger());
    }

    private SignalLogger() {
    }

    @Override
    public void v(final String tag, final String message, final Throwable throwable, final boolean b) {
        logger.trace("[{}]: {}", tag, message, throwable);
    }

    @Override
    public void d(final String tag, final String message, final Throwable throwable, final boolean b) {
        logger.debug("[{}]: {}", tag, message, throwable);
    }

    @Override
    public void i(final String tag, final String message, final Throwable throwable, final boolean b) {
        logger.info("[{}]: {}", tag, message, throwable);
    }

    @Override
    public void w(final String tag, final String message, final Throwable throwable, final boolean b) {
        logger.warn("[{}]: {}", tag, message, throwable);
    }

    @Override
    public void e(final String tag, final String message, final Throwable throwable, final boolean b) {
        logger.error("[{}]: {}", tag, message, throwable);
    }

    @Override
    public void flush() {
    }
}

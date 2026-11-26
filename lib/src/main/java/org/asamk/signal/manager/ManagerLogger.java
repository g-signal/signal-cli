package org.asamk.signal.manager;

import org.asamk.signal.manager.internal.LibSignalLogger;
import org.asamk.signal.manager.internal.SignalLogger;

public class ManagerLogger {

    public static void initLogger() {
        LibSignalLogger.initLogger();
        SignalLogger.initLogger();
    }
}

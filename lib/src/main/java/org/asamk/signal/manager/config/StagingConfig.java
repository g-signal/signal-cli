package org.asamk.signal.manager.config;

import org.signal.libsignal.net.Network;
import org.signal.libsignal.protocol.InvalidKeyException;
import org.signal.libsignal.protocol.ecc.ECPublicKey;
import org.whispersystems.signalservice.api.push.TrustStore;
import org.whispersystems.signalservice.internal.configuration.HttpProxy;
import org.whispersystems.signalservice.internal.configuration.SignalCdnUrl;
import org.whispersystems.signalservice.internal.configuration.SignalCdsiUrl;
import org.whispersystems.signalservice.internal.configuration.SignalProxy;
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration;
import org.whispersystems.signalservice.internal.configuration.SignalServiceUrl;
import org.whispersystems.signalservice.internal.configuration.SignalStorageUrl;
import org.whispersystems.signalservice.internal.configuration.SignalSvr2Url;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import okhttp3.Dns;
import okhttp3.Interceptor;

import static org.asamk.signal.manager.api.ServiceEnvironment.STAGING;

class StagingConfig {

    private static final byte[] UNIDENTIFIED_SENDER_TRUST_ROOT = Base64.getDecoder()
            .decode("BX4nQt7OxWnkqgcYeYyIA1XX43ZfPTEfusNoYTV5NJlj");
    private static final String CDSI_MRENCLAVE = "a1bc651354e220714c84d6016310334c4bfe5605b1c7608bd030258d5094806d";
    private static final String SVR2_MRENCLAVE_LEGACY = "b49a2d7aa6a92623713541be3342cc2432cbb4052a9ab83b50aef3375651e68f";
    private static final String SVR2_MRENCLAVE = "b49a2d7aa6a92623713541be3342cc2432cbb4052a9ab83b50aef3375651e68f";

    private static final String URL = "https://chat.imba-test.com";
    private static final String CDN_URL = "https://cdn.imba-test.com";
    private static final String CDN2_URL = "https://cdn2.imba-test.com";
    private static final String CDN3_URL = "https://cdn3.imba-test.com";
    private static final String STORAGE_URL = "https://storage.imba-test.com";
    private static final String SIGNAL_CDSI_URL = "https://cdsi.imba-test.com";
    private static final String SIGNAL_SVR2_URL = "https://svr2.imba-test.com";
    private static final TrustStore TRUST_STORE = new WhisperTrustStore();

    private static final Optional<Dns> dns = Optional.empty();
    private static final Optional<SignalProxy> proxy = Optional.empty();
    private static final Optional<HttpProxy> systemProxy = Optional.empty();

    private static final byte[] zkGroupServerPublicParams = Base64.getDecoder()
            .decode("AHbJ9KmFfwzDoqJhN6Vouyqdv5B9jqpZZBC1Nj4CRPdRur1cvdvE38qtK+a7fMy/m3SR0oK3PJ5UozxVvuUE6zQcQ50e8e/1dVceVfh80g1WPRpQu5c6MJnrKDkTPifMQ7wd87L7PmgijxKaDD+zz3k9IRLtdrTjCoimFtvt7uoZpNB2ufr6vr2b7VgOEvD9BqPtPErEw9LejE6sHFDhfy/anH9IU7s/Sc4veQBbYgJlaGY7wewt1xSC5k3uxnyQVSYjSh0aYbaSas9LquAFb0fLezOkLZLoFTvj/CbQ6to0dikNvCwVwCQOBQ5sfc8sPwT0Sik59lej6g8NU54DI3XeTjFXOSPpH0XGIVG5jHrIEKCjkc74RqsLaG846m3/cqQm3nHhVffEMAVx6yXAQU9sYiDZpYBJS2R1XiqGWtl2HNfBJwaKvvJ96SOIYOhMNMYm0SU023g2M4/RVhL8WQqyPlzyoZfTk2OvFCRcweQ14GlTzzBJLdYXUEh7Gi/KFdbjaA9Lg8bxlA8OzeyarzAenNU/CrIHCqLNU5re8l08+t7CXF8KftkwdcWjkKIfKDKHrZTNNBRxz1cRcPKhQzgC5I9YW2WsTaeCkMExzOxMA8HvzQv9mZDuNDS7Re8lZ3rGkzyQpKC8QBh7vlVd/qy6JZVCeAJxWO/HRTtj9GsbGt90ioDy4n3byEBg9QXksAyCYTdQvIv3nzzVcpcrZwLsz+z83QBpsdmqPfgwb9BYdZqPt4bSheUZZRU87r/IL+xG2N6QZPFS8vAknjJ+XUk8NvhXU53oT02Omq9EOrtc6LVNgG9BfT/c/lo+WTJfE5WUdGE7Xp13Qnss9Ej8PTiqzabyS+fu98QPcqoNxIyKv6bvcw3Uggofe0tNaumuFg==");
    private static final byte[] genericServerPublicParams = Base64.getDecoder()
            .decode("ADKN7E6cEU87eJMvv69wLvPBwFKJq3JZkAKxWahgwd4jAiHjn31mbvn3eUhAKN2W5ub2C+wj6L6EIr2XZNvoIwO47X5IEjEnCpRrMofYfriKQIDIbFFSyft7PaUT50GbHHJ+Fw+NMVCz1VxNb8HBjIgtqqqj2+OUbUBERqL52mok7qajiPtrwMeTA0iCXDAxUPyGYFfWoa+yrfWJdUJ+byd4lIwfIQnFuF07Fo5VIkK17+QTPMUeyMNB+BZRlCs9asAsM+eOv3H4YOXomc2/Dm0YpwcoThQ5MOo2gJI+tcU/");

    private static final byte[] backupServerPublicParams = Base64.getDecoder()
            .decode("AIIe881NQpY7o8wTPlTiIlMjUX8gvzqp4MVQb63dvX4JTLDLty9gz3EirUPkgSecfSjwY/eNZwDIcTc5gYzMBSj46Roe1r5wRp6Qemvkade9mXY/VMi4bNZgBX/+k11OU+JbPl5ADt2FCLI0L6MRyFr3ZLesYdEEaztCkxkISChj8g8BhFaRTjb+g4CHrm/d3DWuyyOMJ3VNtt2/qeWsqn/SpVpiHC0QM8sS83b5U4IAe7MiwZ+2nCPdAMqwrZwZcO6l+IRes7Za3SyYEABV+0GhmlmpQtHdcCEiWFb4FvtK");

    private static final Network.Environment LIBSIGNAL_NET_ENV = Network.Environment.STAGING;

    static SignalServiceConfiguration createDefaultServiceConfiguration(
            final List<Interceptor> interceptors
    ) {
        return new SignalServiceConfiguration(new SignalServiceUrl[]{new SignalServiceUrl(URL, TRUST_STORE)},
                Map.of(0,
                        new SignalCdnUrl[]{new SignalCdnUrl(CDN_URL, TRUST_STORE)},
                        2,
                        new SignalCdnUrl[]{new SignalCdnUrl(CDN2_URL, TRUST_STORE)},
                        3,
                        new SignalCdnUrl[]{new SignalCdnUrl(CDN3_URL, TRUST_STORE)}),
                new SignalStorageUrl[]{new SignalStorageUrl(STORAGE_URL, TRUST_STORE)},
                new SignalCdsiUrl[]{new SignalCdsiUrl(SIGNAL_CDSI_URL, TRUST_STORE)},
                new SignalSvr2Url[]{new SignalSvr2Url(SIGNAL_SVR2_URL, TRUST_STORE, null, null)},
                interceptors,
                dns,
                proxy,
                systemProxy,
                zkGroupServerPublicParams,
                genericServerPublicParams,
                backupServerPublicParams,
                false);
    }

    static ECPublicKey getUnidentifiedSenderTrustRoot() {
        try {
            return new ECPublicKey(UNIDENTIFIED_SENDER_TRUST_ROOT);
        } catch (InvalidKeyException e) {
            throw new AssertionError(e);
        }
    }

    static ServiceEnvironmentConfig getServiceEnvironmentConfig(List<Interceptor> interceptors) {
        return new ServiceEnvironmentConfig(STAGING,
                LIBSIGNAL_NET_ENV,
                createDefaultServiceConfiguration(interceptors),
                getUnidentifiedSenderTrustRoot(),
                CDSI_MRENCLAVE,
                List.of(SVR2_MRENCLAVE, SVR2_MRENCLAVE_LEGACY));
    }

    private StagingConfig() {
    }
}

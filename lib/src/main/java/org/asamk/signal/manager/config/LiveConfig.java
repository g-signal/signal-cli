package org.asamk.signal.manager.config;

import org.signal.libsignal.net.Network.Environment;
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

import static org.asamk.signal.manager.api.ServiceEnvironment.LIVE;

class LiveConfig {

    private static final byte[] UNIDENTIFIED_SENDER_TRUST_ROOT = Base64.getDecoder()
            .decode("Bd8hujwt+PY1jMqO5xC/8pmIuxwzwuX7ZjHKoJ2BVL4g");
    private static final String CDSI_MRENCLAVE = "3ded708ca5a42fd84b4639dc661a7ec4b9c9f1b92809c0fc91da2349a5a89d05";
    private static final String SVR2_MRENCLAVE_LEGACY = "b49a2d7aa6a92623713541be3342cc2432cbb4052a9ab83b50aef3375651e68f";
    private static final String SVR2_MRENCLAVE = "b49a2d7aa6a92623713541be3342cc2432cbb4052a9ab83b50aef3375651e68f";

    private static final String URL = "https://chat.ba-chat.com";
    private static final String CDN_URL = "https://cdn.ba-chat.com";
    private static final String CDN2_URL = "https://cdn2.ba-chat.com";
    private static final String CDN3_URL = "https://cdn3.ba-chat.com";
    private static final String STORAGE_URL = "https://storage.ba-chat.com";
    private static final String SIGNAL_CDSI_URL = "https://cdsi.ba-chat.com";
    private static final String SIGNAL_SVR2_URL = "https://svr2.ba-chat.com";
    private static final TrustStore TRUST_STORE = new WhisperTrustStore();

    private static final Optional<Dns> dns = Optional.empty();
    private static final Optional<SignalProxy> proxy = Optional.empty();
    private static final Optional<HttpProxy> systemProxy = Optional.empty();

    private static final byte[] zkGroupServerPublicParams = Base64.getDecoder()
            .decode("AOSwc07bu3ImyxbdBax3eJsIIsjzyXELmZpQj3IUp1wbcGL/eeUU2b3LuYJnA+jbXA5z/VYyAm3shM1Fd6NIkhVMzsF4vkKTvBAEjDpXuYR8cFz5YzNdYum1sOwMVVKedIzQAT9YRr+qHwVZ3bFJM69AifuC8MrbhzvWwHWEZ1dU0LRos1YtWCtXtW3w/KZuEqJWJvf9rA6y708DMt0swBE27QeWRdOJKhRnxhMj7R+6bEh92/jXjtZfY9awQo1mX+wSu4qvXDopKGubupTLBa+DDgs9VG7xCewJPzh/cM5jhN7AS8BN0nwXNwjh9UOR1twZDp0RZ+B6yDBxJbTVN1Wo8szJewewkZ6riE7dGEp3ypTnZ+8/JZoB8xKAvzMEYzaEc6CGGqqtx7O5Wty7GpnumYtBxotao2WHgqbYiB44ENPEFWwPg8XSqM6ZJW5cyi5XAD31ubQ49hQs1asJUUsoWHcru8VIBX9UvN128yoDQcNouTXzeVrK+c9VMtzkLtRNVVGtEzCcmGsSpCg0oMqvXAgZTXCT6KdEnO0CMGIJ5kINHo6yC/1AMu8E9pvDBlfLyt1PQh9up5UxDXgRZUTQDkrnv8eoRWb/wx8perlTQGZV7QmWNN7f3W2B9bKOL26Le/0MfTFue3VDqUN4uGqlzzm4bVwV2nKf4Z5gwlFK9gOn9k6UiZ9aN5/Fm7zXL9btLT79Ly2K3s52ZfiZKnoe5bPAULcJ1MRawuS8XFSPo9soklHqaAhDwqjsHs0RfaxOKAkAIV/4ZQP1Ty5BGRg7XP1ZuaHMNEzQFjBdHdcW5qGR6AJtUOKVJn7+SVErGnw1Tu5KbZ6V+sLc0QTrLzqecKgrb0q77eXCv6r7THP79imh/Turlk1rgRHyqgRiPQ==");
    private static final byte[] genericServerPublicParams = Base64.getDecoder()
            .decode("ACoSrbFfXNCwT2TOgSXKl+mqxApYYxFvA+fqP9TnhDZlMpxZfzVHKNaYn44P6lJWTT6YIzNHB1S1XeoxG8vT7iVU+AhVsESiC/wAxel3mz8QLsYYUu1WwhGdS9SiCrZbA1hdvWdPR9aevqDckr0reXY2b80Yvx2MXwanJZxty6MyTJhyzeQE62+clBZwPY4sTd/hwn+ye7V0h6yrZ2JXPSSGXKDJsozGVnOmi/JFtAyh0AK6ItRG94omkTid8zhxWSYN5bct4svdQOWhWOZoQT5/1NJOfTGUM5WfX4i+TvtL");

    private static final byte[] backupServerPublicParams = Base64.getDecoder()
            .decode("ALy0PQXREe4drGk4xClPewbfE3pnFLALZ4mJ7TUFj0lvLAVpI4yQChBt20gxD7PfYZvVK8sGzYQ4G8UhM7sJY3Gw2aNwg3IjheOzY5web/1nVmnxVdt2gIco3+fVBG8jfRotXjJ2IGP/x9ayTqCBeQEc0xGtQZioKZo2PtFIy3oUQgn02z4gpnIXcT5VJu2G9YYC3cfiycEMEd+AZyB22w3qb3YWr2LfDA/aXDcQE/pio4jju5JQQIA2W1QBKNNqDrrlG765zfVBQDFEr8ruAn+gE3QvVKi2k+pH6jl6vdYv");

    private static final Environment LIBSIGNAL_NET_ENV = Environment.PRODUCTION;

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
        return new ServiceEnvironmentConfig(LIVE,
                LIBSIGNAL_NET_ENV,
                createDefaultServiceConfiguration(interceptors),
                getUnidentifiedSenderTrustRoot(),
                CDSI_MRENCLAVE,
                List.of(SVR2_MRENCLAVE, SVR2_MRENCLAVE_LEGACY));
    }

    private LiveConfig() {
    }
}
